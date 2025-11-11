package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.ObjectMap;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;

public class OpenALLwjgl3Audio implements Lwjgl3Audio {
   private final int deviceBufferSize;
   private final int deviceBufferCount;
   private IntArray idleSources;
   private IntArray allSources;
   private LongMap soundIdToSource;
   private IntMap sourceToSoundId;
   private long nextSoundId = 0L;
   private ObjectMap extensionToSoundClass = new ObjectMap();
   private ObjectMap extensionToMusicClass = new ObjectMap();
   private OpenALSound[] recentSounds;
   private int mostRecetSound = -1;
   Array music = new Array(false, 1, OpenALMusic.class);
   long device;
   long context;
   boolean noDevice = false;

   public OpenALLwjgl3Audio() {
      this(16, 9, 512);
   }

   public OpenALLwjgl3Audio(int simultaneousSources, int deviceBufferCount, int deviceBufferSize) {
      this.deviceBufferSize = deviceBufferSize;
      this.deviceBufferCount = deviceBufferCount;
      this.registerSound("ogg", Ogg.Sound.class);
      this.registerMusic("ogg", Ogg.Music.class);
      this.registerSound("wav", Wav.Sound.class);
      this.registerMusic("wav", Wav.Music.class);
      this.registerSound("mp3", Mp3.Sound.class);
      this.registerMusic("mp3", Mp3.Music.class);
      this.device = ALC10.alcOpenDevice((ByteBuffer)null);
      if (this.device == 0L) {
         this.noDevice = true;
      } else {
         ALCCapabilities deviceCapabilities = ALC.createCapabilities(this.device);
         this.context = ALC10.alcCreateContext(this.device, (IntBuffer)null);
         if (this.context == 0L) {
            ALC10.alcCloseDevice(this.device);
            this.noDevice = true;
         } else if (!ALC10.alcMakeContextCurrent(this.context)) {
            this.noDevice = true;
         } else {
            AL.createCapabilities(deviceCapabilities);
            this.allSources = new IntArray(false, simultaneousSources);

            for (int i = 0; i < simultaneousSources; i++) {
               int sourceID = AL10.alGenSources();
               if (AL10.alGetError() != 0) {
                  break;
               }

               this.allSources.add(sourceID);
            }

            this.idleSources = new IntArray(this.allSources);
            this.soundIdToSource = new LongMap();
            this.sourceToSoundId = new IntMap();
            FloatBuffer orientation = BufferUtils.createFloatBuffer(6).put(new float[]{0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F});
            ((Buffer)orientation).flip();
            AL10.alListenerfv(4111, orientation);
            FloatBuffer velocity = BufferUtils.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F});
            ((Buffer)velocity).flip();
            AL10.alListenerfv(4102, velocity);
            FloatBuffer position = BufferUtils.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F});
            ((Buffer)position).flip();
            AL10.alListenerfv(4100, position);
            this.recentSounds = new OpenALSound[simultaneousSources];
         }
      }
   }

   public void registerSound(String extension, Class soundClass) {
      if (extension == null) {
         throw new IllegalArgumentException("extension cannot be null.");
      } else if (soundClass == null) {
         throw new IllegalArgumentException("soundClass cannot be null.");
      } else {
         this.extensionToSoundClass.put(extension, soundClass);
      }
   }

   public void registerMusic(String extension, Class musicClass) {
      if (extension == null) {
         throw new IllegalArgumentException("extension cannot be null.");
      } else if (musicClass == null) {
         throw new IllegalArgumentException("musicClass cannot be null.");
      } else {
         this.extensionToMusicClass.put(extension, musicClass);
      }
   }

   public OpenALSound newSound(FileHandle file) {
      if (file == null) {
         throw new IllegalArgumentException("file cannot be null.");
      } else {
         Class soundClass = (Class<? extends OpenALSound>)this.extensionToSoundClass.get(file.extension().toLowerCase());
         if (soundClass == null) {
            throw new GdxRuntimeException("Unknown file extension for sound: " + file);
         } else {
            try {
               return (OpenALSound)soundClass.getConstructor(OpenALLwjgl3Audio.class, FileHandle.class).newInstance(this, file);
            } catch (Exception var4) {
               throw new GdxRuntimeException("Error creating sound " + soundClass.getName() + " for file: " + file, var4);
            }
         }
      }
   }

   public OpenALMusic newMusic(FileHandle file) {
      if (file == null) {
         throw new IllegalArgumentException("file cannot be null.");
      } else {
         Class musicClass = (Class<? extends OpenALMusic>)this.extensionToMusicClass.get(file.extension().toLowerCase());
         if (musicClass == null) {
            throw new GdxRuntimeException("Unknown file extension for music: " + file);
         } else {
            try {
               return (OpenALMusic)musicClass.getConstructor(OpenALLwjgl3Audio.class, FileHandle.class).newInstance(this, file);
            } catch (Exception var4) {
               throw new GdxRuntimeException("Error creating music " + musicClass.getName() + " for file: " + file, var4);
            }
         }
      }
   }

   int obtainSource(boolean isMusic) {
      if (this.noDevice) {
         return 0;
      } else {
         int i = 0;

         for (int n = this.idleSources.size; i < n; i++) {
            int sourceId = this.idleSources.get(i);
            int state = AL10.alGetSourcei(sourceId, 4112);
            if (state != 4114 && state != 4115) {
               if (isMusic) {
                  this.idleSources.removeIndex(i);
               } else {
                  Long oldSoundId = (Long)this.sourceToSoundId.remove(sourceId);
                  if (oldSoundId != null) {
                     this.soundIdToSource.remove(oldSoundId);
                  }

                  long soundId = this.nextSoundId++;
                  this.sourceToSoundId.put(sourceId, soundId);
                  this.soundIdToSource.put(soundId, sourceId);
               }

               AL10.alSourceStop(sourceId);
               AL10.alSourcei(sourceId, 4105, 0);
               AL10.alSourcef(sourceId, 4106, 1.0F);
               AL10.alSourcef(sourceId, 4099, 1.0F);
               AL10.alSource3f(sourceId, 4100, 0.0F, 0.0F, 1.0F);
               return sourceId;
            }
         }

         return -1;
      }
   }

   void freeSource(int sourceID) {
      if (!this.noDevice) {
         AL10.alSourceStop(sourceID);
         AL10.alSourcei(sourceID, 4105, 0);
         Long soundId = (Long)this.sourceToSoundId.remove(sourceID);
         if (soundId != null) {
            this.soundIdToSource.remove(soundId);
         }

         this.idleSources.add(sourceID);
      }
   }

   void freeBuffer(int bufferID) {
      if (!this.noDevice) {
         int i = 0;

         for (int n = this.idleSources.size; i < n; i++) {
            int sourceID = this.idleSources.get(i);
            if (AL10.alGetSourcei(sourceID, 4105) == bufferID) {
               Long soundId = (Long)this.sourceToSoundId.remove(sourceID);
               if (soundId != null) {
                  this.soundIdToSource.remove(soundId);
               }

               AL10.alSourceStop(sourceID);
               AL10.alSourcei(sourceID, 4105, 0);
            }
         }
      }
   }

   void stopSourcesWithBuffer(int bufferID) {
      if (!this.noDevice) {
         int i = 0;

         for (int n = this.idleSources.size; i < n; i++) {
            int sourceID = this.idleSources.get(i);
            if (AL10.alGetSourcei(sourceID, 4105) == bufferID) {
               Long soundId = (Long)this.sourceToSoundId.remove(sourceID);
               if (soundId != null) {
                  this.soundIdToSource.remove(soundId);
               }

               AL10.alSourceStop(sourceID);
            }
         }
      }
   }

   void pauseSourcesWithBuffer(int bufferID) {
      if (!this.noDevice) {
         int i = 0;

         for (int n = this.idleSources.size; i < n; i++) {
            int sourceID = this.idleSources.get(i);
            if (AL10.alGetSourcei(sourceID, 4105) == bufferID) {
               AL10.alSourcePause(sourceID);
            }
         }
      }
   }

   void resumeSourcesWithBuffer(int bufferID) {
      if (!this.noDevice) {
         int i = 0;

         for (int n = this.idleSources.size; i < n; i++) {
            int sourceID = this.idleSources.get(i);
            if (AL10.alGetSourcei(sourceID, 4105) == bufferID && AL10.alGetSourcei(sourceID, 4112) == 4115) {
               AL10.alSourcePlay(sourceID);
            }
         }
      }
   }

   @Override
   public void update() {
      if (!this.noDevice) {
         for (int i = 0; i < this.music.size; i++) {
            ((OpenALMusic[])this.music.items)[i].update();
         }
      }
   }

   public long getSoundId(int sourceId) {
      Long soundId = (Long)this.sourceToSoundId.get(sourceId);
      return soundId != null ? soundId : -1L;
   }

   public int getSourceId(long soundId) {
      Integer sourceId = (Integer)this.soundIdToSource.get(soundId);
      return sourceId != null ? sourceId : -1;
   }

   public void stopSound(long soundId) {
      Integer sourceId = (Integer)this.soundIdToSource.get(soundId);
      if (sourceId != null) {
         AL10.alSourceStop(sourceId);
      }
   }

   public void pauseSound(long soundId) {
      Integer sourceId = (Integer)this.soundIdToSource.get(soundId);
      if (sourceId != null) {
         AL10.alSourcePause(sourceId);
      }
   }

   public void resumeSound(long soundId) {
      int sourceId = (Integer)this.soundIdToSource.get(soundId, -1);
      if (sourceId != -1 && AL10.alGetSourcei(sourceId, 4112) == 4115) {
         AL10.alSourcePlay(sourceId);
      }
   }

   public void setSoundGain(long soundId, float volume) {
      Integer sourceId = (Integer)this.soundIdToSource.get(soundId);
      if (sourceId != null) {
         AL10.alSourcef(sourceId, 4106, volume);
      }
   }

   public void setSoundLooping(long soundId, boolean looping) {
      Integer sourceId = (Integer)this.soundIdToSource.get(soundId);
      if (sourceId != null) {
         AL10.alSourcei(sourceId, 4103, looping ? 1 : 0);
      }
   }

   public void setSoundPitch(long soundId, float pitch) {
      Integer sourceId = (Integer)this.soundIdToSource.get(soundId);
      if (sourceId != null) {
         AL10.alSourcef(sourceId, 4099, pitch);
      }
   }

   public void setSoundPan(long soundId, float pan, float volume) {
      int sourceId = (Integer)this.soundIdToSource.get(soundId, -1);
      if (sourceId != -1) {
         AL10.alSource3f(sourceId, 4100, MathUtils.cos((pan - 1.0F) * (float) (Math.PI / 2)), 0.0F, MathUtils.sin((pan + 1.0F) * (float) (Math.PI / 2)));
         AL10.alSourcef(sourceId, 4106, volume);
      }
   }

   @Override
   public void dispose() {
      if (!this.noDevice) {
         int i = 0;

         for (int n = this.allSources.size; i < n; i++) {
            int sourceID = this.allSources.get(i);
            int state = AL10.alGetSourcei(sourceID, 4112);
            if (state != 4116) {
               AL10.alSourceStop(sourceID);
            }

            AL10.alDeleteSources(sourceID);
         }

         this.sourceToSoundId.clear();
         this.soundIdToSource.clear();
         ALC10.alcDestroyContext(this.context);
         ALC10.alcCloseDevice(this.device);
      }
   }

   @Override
   public AudioDevice newAudioDevice(int sampleRate, final boolean isMono) {
      return (AudioDevice)(this.noDevice ? new AudioDevice() {
         @Override
         public void writeSamples(float[] samples, int offset, int numSamples) {
         }

         @Override
         public void writeSamples(short[] samples, int offset, int numSamples) {
         }

         @Override
         public void setVolume(float volume) {
         }

         @Override
         public boolean isMono() {
            return isMono;
         }

         @Override
         public int getLatency() {
            return 0;
         }

         @Override
         public void dispose() {
         }
      } : new OpenALAudioDevice(this, sampleRate, isMono, this.deviceBufferSize, this.deviceBufferCount));
   }

   @Override
   public AudioRecorder newAudioRecorder(int samplingRate, boolean isMono) {
      return (AudioRecorder)(this.noDevice ? new AudioRecorder() {
         @Override
         public void read(short[] samples, int offset, int numSamples) {
         }

         @Override
         public void dispose() {
         }
      } : new JavaSoundAudioRecorder(samplingRate, isMono));
   }

   protected void retain(OpenALSound sound, boolean stop) {
      this.mostRecetSound++;
      this.mostRecetSound = this.mostRecetSound % this.recentSounds.length;
      if (stop && this.recentSounds[this.mostRecetSound] != null) {
         this.recentSounds[this.mostRecetSound].stop();
      }

      this.recentSounds[this.mostRecetSound] = sound;
   }

   public void forget(OpenALSound sound) {
      for (int i = 0; i < this.recentSounds.length; i++) {
         if (this.recentSounds[i] == sound) {
            this.recentSounds[i] = null;
         }
      }
   }
}
