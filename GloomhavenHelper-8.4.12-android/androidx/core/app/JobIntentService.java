package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.PowerManager.WakeLock;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    final class CommandProcessor extends AsyncTask {
        @Override  // android.os.AsyncTask
        protected Object doInBackground(Object[] arr_object) {
            return this.doInBackground(((Void[])arr_object));
        }

        protected Void doInBackground(Void[] arr_void) {
            GenericWorkItem jobIntentService$GenericWorkItem0;
            while((jobIntentService$GenericWorkItem0 = JobIntentService.this.dequeueWork()) != null) {
                Intent intent0 = jobIntentService$GenericWorkItem0.getIntent();
                JobIntentService.this.onHandleWork(intent0);
                jobIntentService$GenericWorkItem0.complete();
            }
            return null;
        }

        @Override  // android.os.AsyncTask
        protected void onCancelled(Object object0) {
            this.onCancelled(((Void)object0));
        }

        protected void onCancelled(Void void0) {
            JobIntentService.this.processorFinished();
        }

        @Override  // android.os.AsyncTask
        protected void onPostExecute(Object object0) {
            this.onPostExecute(((Void)object0));
        }

        protected void onPostExecute(Void void0) {
            JobIntentService.this.processorFinished();
        }
    }

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer {
        private final Context mContext;
        private final PowerManager.WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final PowerManager.WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        CompatWorkEnqueuer(Context context0, ComponentName componentName0) {
            super(context0, componentName0);
            this.mContext = context0.getApplicationContext();
            PowerManager powerManager0 = (PowerManager)context0.getSystemService("power");
            this.mLaunchWakeLock = powerManager0.newWakeLock(1, componentName0.getClassName() + ":launch");
            this.mLaunchWakeLock.setReferenceCounted(false);
            this.mRunWakeLock = powerManager0.newWakeLock(1, componentName0.getClassName() + ":run");
            this.mRunWakeLock.setReferenceCounted(false);
        }

        @Override  // androidx.core.app.JobIntentService$WorkEnqueuer
        void enqueueWork(Intent intent0) {
            Intent intent1 = new Intent(intent0);
            intent1.setComponent(this.mComponentName);
            if(this.mContext.startService(intent1) != null) {
                synchronized(this) {
                    if(!this.mLaunchingService) {
                        this.mLaunchingService = true;
                        if(!this.mServiceProcessing) {
                            this.mLaunchWakeLock.acquire(60000L);
                        }
                    }
                }
            }
        }

        @Override  // androidx.core.app.JobIntentService$WorkEnqueuer
        public void serviceProcessingFinished() {
            synchronized(this) {
                if(this.mServiceProcessing) {
                    if(this.mLaunchingService) {
                        this.mLaunchWakeLock.acquire(60000L);
                    }
                    this.mServiceProcessing = false;
                    this.mRunWakeLock.release();
                }
            }
        }

        @Override  // androidx.core.app.JobIntentService$WorkEnqueuer
        public void serviceProcessingStarted() {
            synchronized(this) {
                if(!this.mServiceProcessing) {
                    this.mServiceProcessing = true;
                    this.mRunWakeLock.acquire(600000L);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        @Override  // androidx.core.app.JobIntentService$WorkEnqueuer
        public void serviceStartReceived() {
            synchronized(this) {
                this.mLaunchingService = false;
            }
        }
    }

    final class CompatWorkItem implements GenericWorkItem {
        final Intent mIntent;
        final int mStartId;

        CompatWorkItem(Intent intent0, int v) {
            this.mIntent = intent0;
            this.mStartId = v;
        }

        @Override  // androidx.core.app.JobIntentService$GenericWorkItem
        public void complete() {
            JobIntentService.this.stopSelf(this.mStartId);
        }

        @Override  // androidx.core.app.JobIntentService$GenericWorkItem
        public Intent getIntent() {
            return this.mIntent;
        }
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    @RequiresApi(26)
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem mJobWork;

            WrapperWorkItem(JobWorkItem jobWorkItem0) {
                this.mJobWork = jobWorkItem0;
            }

            @Override  // androidx.core.app.JobIntentService$GenericWorkItem
            public void complete() {
                synchronized(JobServiceEngineImpl.this.mLock) {
                    if(JobServiceEngineImpl.this.mParams != null) {
                        JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
                    }
                }
            }

            @Override  // androidx.core.app.JobIntentService$GenericWorkItem
            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }
        }

        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock;
        JobParameters mParams;
        final JobIntentService mService;

        JobServiceEngineImpl(JobIntentService jobIntentService0) {
            super(jobIntentService0);
            this.mLock = new Object();
            this.mService = jobIntentService0;
        }

        @Override  // androidx.core.app.JobIntentService$CompatJobEngine
        public IBinder compatGetBinder() {
            return this.getBinder();
        }

        @Override  // androidx.core.app.JobIntentService$CompatJobEngine
        public GenericWorkItem dequeueWork() {
            JobWorkItem jobWorkItem0;
            synchronized(this.mLock) {
                if(this.mParams == null) {
                    return null;
                }
                jobWorkItem0 = this.mParams.dequeueWork();
            }
            if(jobWorkItem0 != null) {
                jobWorkItem0.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
                return new WrapperWorkItem(this, jobWorkItem0);
            }
            return null;
        }

        @Override  // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters0) {
            this.mParams = jobParameters0;
            this.mService.ensureProcessorRunningLocked(false);
            return true;
        }

        @Override  // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters0) {
            boolean z = this.mService.doStopCurrentWork();
            synchronized(this.mLock) {
                this.mParams = null;
            }
            return z;
        }
    }

    @RequiresApi(26)
    static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context0, ComponentName componentName0, int v) {
            super(context0, componentName0);
            this.ensureJobId(v);
            this.mJobInfo = new JobInfo.Builder(v, this.mComponentName).setOverrideDeadline(0L).build();
            this.mJobScheduler = (JobScheduler)context0.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override  // androidx.core.app.JobIntentService$WorkEnqueuer
        void enqueueWork(Intent intent0) {
            JobWorkItem jobWorkItem0 = new JobWorkItem(intent0);
            this.mJobScheduler.enqueue(this.mJobInfo, jobWorkItem0);
        }
    }

    static abstract class WorkEnqueuer {
        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        WorkEnqueuer(Context context0, ComponentName componentName0) {
            this.mComponentName = componentName0;
        }

        abstract void enqueueWork(Intent arg1);

        void ensureJobId(int v) {
            if(!this.mHasJobId) {
                this.mHasJobId = true;
                this.mJobId = v;
                return;
            }
            if(this.mJobId != v) {
                throw new IllegalArgumentException("Given job ID " + v + " is different than previous " + this.mJobId);
            }
        }

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    static final boolean DEBUG = false;
    static final String TAG = "JobIntentService";
    final ArrayList mCompatQueue;
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    boolean mDestroyed;
    boolean mInterruptIfStopped;
    CompatJobEngine mJobImpl;
    boolean mStopped;
    static final HashMap sClassWorkEnqueuer;
    static final Object sLock;

    static {
        JobIntentService.sLock = new Object();
        JobIntentService.sClassWorkEnqueuer = new HashMap();
    }

    public JobIntentService() {
        this.mInterruptIfStopped = false;
        this.mStopped = false;
        this.mDestroyed = false;
        if(Build.VERSION.SDK_INT >= 26) {
            this.mCompatQueue = null;
            return;
        }
        this.mCompatQueue = new ArrayList();
    }

    GenericWorkItem dequeueWork() {
        CompatJobEngine jobIntentService$CompatJobEngine0 = this.mJobImpl;
        if(jobIntentService$CompatJobEngine0 != null) {
            return jobIntentService$CompatJobEngine0.dequeueWork();
        }
        synchronized(this.mCompatQueue) {
            return this.mCompatQueue.size() > 0 ? ((GenericWorkItem)this.mCompatQueue.remove(0)) : null;
        }
    }

    boolean doStopCurrentWork() {
        CommandProcessor jobIntentService$CommandProcessor0 = this.mCurProcessor;
        if(jobIntentService$CommandProcessor0 != null) {
            jobIntentService$CommandProcessor0.cancel(this.mInterruptIfStopped);
        }
        this.mStopped = true;
        return true;
    }

    public static void enqueueWork(@NonNull Context context0, @NonNull ComponentName componentName0, int v, @NonNull Intent intent0) {
        if(intent0 == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized(JobIntentService.sLock) {
            WorkEnqueuer jobIntentService$WorkEnqueuer0 = JobIntentService.getWorkEnqueuer(context0, componentName0, true, v);
            jobIntentService$WorkEnqueuer0.ensureJobId(v);
            jobIntentService$WorkEnqueuer0.enqueueWork(intent0);
        }
    }

    public static void enqueueWork(@NonNull Context context0, @NonNull Class class0, int v, @NonNull Intent intent0) {
        JobIntentService.enqueueWork(context0, new ComponentName(context0, class0), v, intent0);
    }

    void ensureProcessorRunningLocked(boolean z) {
        if(this.mCurProcessor == null) {
            this.mCurProcessor = new CommandProcessor(this);
            WorkEnqueuer jobIntentService$WorkEnqueuer0 = this.mCompatWorkEnqueuer;
            if(jobIntentService$WorkEnqueuer0 != null && z) {
                jobIntentService$WorkEnqueuer0.serviceProcessingStarted();
            }
            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    static WorkEnqueuer getWorkEnqueuer(Context context0, ComponentName componentName0, boolean z, int v) {
        WorkEnqueuer jobIntentService$WorkEnqueuer0 = (WorkEnqueuer)JobIntentService.sClassWorkEnqueuer.get(componentName0);
        if(jobIntentService$WorkEnqueuer0 == null) {
            if(Build.VERSION.SDK_INT >= 26) {
                if(!z) {
                    throw new IllegalArgumentException("Can\'t be here without a job id");
                }
                jobIntentService$WorkEnqueuer0 = new JobWorkEnqueuer(context0, componentName0, v);
            }
            else {
                jobIntentService$WorkEnqueuer0 = new CompatWorkEnqueuer(context0, componentName0);
            }
            JobIntentService.sClassWorkEnqueuer.put(componentName0, jobIntentService$WorkEnqueuer0);
        }
        return jobIntentService$WorkEnqueuer0;
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    @Override  // android.app.Service
    public IBinder onBind(@NonNull Intent intent0) {
        return this.mJobImpl == null ? null : this.mJobImpl.compatGetBinder();
    }

    @Override  // android.app.Service
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT >= 26) {
            this.mJobImpl = new JobServiceEngineImpl(this);
            this.mCompatWorkEnqueuer = null;
            return;
        }
        this.mJobImpl = null;
        this.mCompatWorkEnqueuer = JobIntentService.getWorkEnqueuer(this, new ComponentName(this, this.getClass()), false, 0);
    }

    @Override  // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList arrayList0 = this.mCompatQueue;
        if(arrayList0 != null) {
            synchronized(arrayList0) {
                this.mDestroyed = true;
                this.mCompatWorkEnqueuer.serviceProcessingFinished();
            }
        }
    }

    protected abstract void onHandleWork(@NonNull Intent arg1);

    @Override  // android.app.Service
    public int onStartCommand(@Nullable Intent intent0, int v, int v1) {
        if(this.mCompatQueue != null) {
            this.mCompatWorkEnqueuer.serviceStartReceived();
            ArrayList arrayList0 = this.mCompatQueue;
            synchronized(arrayList0) {
                ArrayList arrayList1 = this.mCompatQueue;
                if(intent0 == null) {
                    intent0 = new Intent();
                }
                arrayList1.add(new CompatWorkItem(this, intent0, v1));
                this.ensureProcessorRunningLocked(true);
                return 3;
            }
        }
        return 2;
    }

    public boolean onStopCurrentWork() [...] // Inlined contents

    void processorFinished() {
        ArrayList arrayList0 = this.mCompatQueue;
        if(arrayList0 != null) {
            synchronized(arrayList0) {
                this.mCurProcessor = null;
                if(this.mCompatQueue != null && this.mCompatQueue.size() > 0) {
                    this.ensureProcessorRunningLocked(false);
                }
                else if(!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.serviceProcessingFinished();
                }
            }
        }
    }

    public void setInterruptIfStopped(boolean z) {
        this.mInterruptIfStopped = z;
    }
}

