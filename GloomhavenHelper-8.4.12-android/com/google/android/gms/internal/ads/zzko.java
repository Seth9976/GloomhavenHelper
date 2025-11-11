package com.google.android.gms.internal.ads;

import android.util.Log;

final class zzko {
    private static final int zzawf;
    private static final int zzawg;
    private static final int zzawh;
    private static final int zzawi;
    private static final int zzawj;
    private static final int zzawk;
    private static final int zzawl;
    private static final int zzawm;
    private static final int zzawn;
    private static final int zzawo;
    private static final int zzawp;
    private static final int zzawq;
    private static final int zzawr;
    private static final int zzaws;
    private static final int zzawt;
    private static final int zzawu;
    private static final int zzawv;
    private static final int zzaww;
    private static final int zzawx;
    private static final int zzawy;
    private static final int zzawz;
    private static final int zzaxa;
    private static final int zzaxb;
    private static final int zzaxc;
    private static final int zzaxd;
    private static final int zzaxe;
    private static final int zzaxf;
    private static final int zzaxg;
    private static final int zzaxh;
    private static final String[] zzaxi;

    static {
        zzko.zzawf = zzop.zzbo("nam");
        zzko.zzawg = zzop.zzbo("trk");
        zzko.zzawh = zzop.zzbo("cmt");
        zzko.zzawi = zzop.zzbo("day");
        zzko.zzawj = zzop.zzbo("ART");
        zzko.zzawk = zzop.zzbo("too");
        zzko.zzawl = zzop.zzbo("alb");
        zzko.zzawm = zzop.zzbo("com");
        zzko.zzawn = zzop.zzbo("wrt");
        zzko.zzawo = zzop.zzbo("lyr");
        zzko.zzawp = zzop.zzbo("gen");
        zzko.zzawq = zzop.zzbo("covr");
        zzko.zzawr = zzop.zzbo("gnre");
        zzko.zzaws = zzop.zzbo("grp");
        zzko.zzawt = zzop.zzbo("disk");
        zzko.zzawu = zzop.zzbo("trkn");
        zzko.zzawv = zzop.zzbo("tmpo");
        zzko.zzaww = zzop.zzbo("cpil");
        zzko.zzawx = zzop.zzbo("aART");
        zzko.zzawy = zzop.zzbo("sonm");
        zzko.zzawz = zzop.zzbo("soal");
        zzko.zzaxa = zzop.zzbo("soar");
        zzko.zzaxb = zzop.zzbo("soaa");
        zzko.zzaxc = zzop.zzbo("soco");
        zzko.zzaxd = zzop.zzbo("rtng");
        zzko.zzaxe = zzop.zzbo("pgap");
        zzko.zzaxf = zzop.zzbo("sosn");
        zzko.zzaxg = zzop.zzbo("tvsh");
        zzko.zzaxh = zzop.zzbo("----");
        zzko.zzaxi = new String[]{"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    }

    private static zzln zza(int v, String s, zzom zzom0) {
        int v1 = zzom0.readInt();
        if(zzom0.readInt() == zzkc.zzaum) {
            zzom0.zzbi(8);
            return new zzln(s, null, zzom0.zzbj(v1 - 16));
        }
        String s1 = zzkc.zzap(v);
        Log.w("MetadataUtil", (s1.length() == 0 ? new String("Failed to parse text attribute: ") : "Failed to parse text attribute: " + s1));
        return null;
    }

    private static zzlo zza(int v, String s, zzom zzom0, boolean z, boolean z1) {
        int v1 = zzko.zze(zzom0);
        if(z1) {
            v1 = Math.min(1, v1);
        }
        if(v1 >= 0) {
            return z ? new zzln(s, null, Integer.toString(v1)) : new zzlj("und", s, Integer.toString(v1));
        }
        String s1 = zzkc.zzap(v);
        Log.w("MetadataUtil", (s1.length() == 0 ? new String("Failed to parse uint8 attribute: ") : "Failed to parse uint8 attribute: " + s1));
        return null;
    }

    private static zzln zzb(int v, String s, zzom zzom0) {
        int v1 = zzom0.readInt();
        if(zzom0.readInt() == zzkc.zzaum && v1 >= 22) {
            zzom0.zzbi(10);
            int v2 = zzom0.readUnsignedShort();
            if(v2 > 0) {
                String s1 = v2;
                int v3 = zzom0.readUnsignedShort();
                if(v3 > 0) {
                    s1 = s1 + "/" + v3;
                }
                return new zzln(s, null, s1);
            }
        }
        String s2 = zzkc.zzap(v);
        Log.w("MetadataUtil", (s2.length() == 0 ? new String("Failed to parse index/count attribute: ") : "Failed to parse index/count attribute: " + s2));
        return null;
    }

    public static zza zzd(zzom zzom0) {
        String s1;
        zza zzlh$zza1;
        int v = zzom0.getPosition() + zzom0.readInt();
        int v1 = zzom0.readInt();
        zza zzlh$zza0 = null;
        try {
            if(v1 >>> 24 != 0xA9 && v1 >>> 24 != 0xFFFD) {
                if(v1 == zzko.zzawr) {
                    int v3 = zzko.zze(zzom0);
                    String s = v3 <= 0 || v3 > zzko.zzaxi.length ? null : zzko.zzaxi[v3 - 1];
                    if(s == null) {
                        Log.w("MetadataUtil", "Failed to parse standard genre code");
                        zzlh$zza1 = null;
                    }
                    else {
                        zzlh$zza1 = new zzln("TCON", null, s);
                    }
                    return zzlh$zza1;
                }
                if(v1 == zzko.zzawt) {
                    return zzko.zzb(v1, "TPOS", zzom0);
                }
                if(v1 == zzko.zzawu) {
                    return zzko.zzb(v1, "TRCK", zzom0);
                }
                if(v1 == zzko.zzawv) {
                    return zzko.zza(v1, "TBPM", zzom0, true, false);
                }
                if(v1 == zzko.zzaww) {
                    return zzko.zza(v1, "TCMP", zzom0, true, true);
                }
                if(v1 == zzko.zzawq) {
                    int v4 = zzom0.readInt();
                    if(zzom0.readInt() == zzkc.zzaum) {
                        int v5 = zzkc.zzao(zzom0.readInt());
                        if(v5 == 13) {
                            s1 = "image/jpeg";
                        }
                        else {
                            s1 = v5 == 14 ? "image/png" : null;
                        }
                        if(s1 == null) {
                            Log.w("MetadataUtil", "Unrecognized cover art flags: " + v5);
                        }
                        else {
                            zzom0.zzbi(4);
                            byte[] arr_b = new byte[v4 - 16];
                            zzom0.zze(arr_b, 0, arr_b.length);
                            zzlh$zza0 = new zzli(s1, null, 3, arr_b);
                        }
                    }
                    else {
                        Log.w("MetadataUtil", "Failed to parse cover art attribute");
                    }
                    return zzlh$zza0;
                }
                if(v1 == zzko.zzawx) {
                    return zzko.zza(v1, "TPE2", zzom0);
                }
                if(v1 == zzko.zzawy) {
                    return zzko.zza(v1, "TSOT", zzom0);
                }
                if(v1 == zzko.zzawz) {
                    return zzko.zza(v1, "TSO2", zzom0);
                }
                if(v1 == zzko.zzaxa) {
                    return zzko.zza(v1, "TSOA", zzom0);
                }
                if(v1 == zzko.zzaxb) {
                    return zzko.zza(v1, "TSOP", zzom0);
                }
                if(v1 == zzko.zzaxc) {
                    return zzko.zza(v1, "TSOC", zzom0);
                }
                if(v1 == zzko.zzaxd) {
                    return zzko.zza(v1, "ITUNESADVISORY", zzom0, false, false);
                }
                if(v1 == zzko.zzaxe) {
                    return zzko.zza(v1, "ITUNESGAPLESS", zzom0, false, true);
                }
                if(v1 == zzko.zzaxf) {
                    return zzko.zza(v1, "TVSHOWSORT", zzom0);
                }
                if(v1 == zzko.zzaxg) {
                    return zzko.zza(v1, "TVSHOW", zzom0);
                }
                if(v1 != zzko.zzaxh) {
                    goto label_160;
                }
                String s2 = null;
                String s3 = null;
                int v6 = -1;
                int v7 = -1;
                while(zzom0.getPosition() < v) {
                    int v8 = zzom0.getPosition();
                    int v9 = zzom0.readInt();
                    int v10 = zzom0.readInt();
                    zzom0.zzbi(4);
                    if(v10 == zzkc.zzauk) {
                        s2 = zzom0.zzbj(v9 - 12);
                    }
                    else if(v10 == zzkc.zzaul) {
                        s3 = zzom0.zzbj(v9 - 12);
                    }
                    else {
                        if(v10 == zzkc.zzaum) {
                            v6 = v8;
                            v7 = v9;
                        }
                        zzom0.zzbi(v9 - 12);
                    }
                }
                if("com.apple.iTunes".equals(s2) && "iTunSMPB".equals(s3) && v6 != -1) {
                    zzom0.zzbh(v6);
                    zzom0.zzbi(16);
                    zzlh$zza0 = new zzlj("und", s3, zzom0.zzbj(v7 - 16));
                }
                return zzlh$zza0;
            }
            if((0xFFFFFF & v1) == zzko.zzawh) {
                int v11 = zzom0.readInt();
                if(zzom0.readInt() == zzkc.zzaum) {
                    zzom0.zzbi(8);
                    String s4 = zzom0.zzbj(v11 - 16);
                    zzlh$zza0 = new zzlj("und", s4, s4);
                }
                else {
                    String s5 = zzkc.zzap(v1);
                    Log.w("MetadataUtil", (s5.length() == 0 ? new String("Failed to parse comment attribute: ") : "Failed to parse comment attribute: " + s5));
                }
                return zzlh$zza0;
            }
            if((0xFFFFFF & v1) != zzko.zzawf && (0xFFFFFF & v1) != zzko.zzawg) {
                if((0xFFFFFF & v1) != zzko.zzawm && (0xFFFFFF & v1) != zzko.zzawn) {
                    if((0xFFFFFF & v1) == zzko.zzawi) {
                        return zzko.zza(v1, "TDRC", zzom0);
                    }
                    if((0xFFFFFF & v1) == zzko.zzawj) {
                        return zzko.zza(v1, "TPE1", zzom0);
                    }
                    if((0xFFFFFF & v1) == zzko.zzawk) {
                        return zzko.zza(v1, "TSSE", zzom0);
                    }
                    if((0xFFFFFF & v1) == zzko.zzawl) {
                        return zzko.zza(v1, "TALB", zzom0);
                    }
                    if((0xFFFFFF & v1) == zzko.zzawo) {
                        return zzko.zza(v1, "USLT", zzom0);
                    }
                    if((0xFFFFFF & v1) == zzko.zzawp) {
                        return zzko.zza(v1, "TCON", zzom0);
                    }
                    if((0xFFFFFF & v1) == zzko.zzaws) {
                        return zzko.zza(v1, "TIT1", zzom0);
                    }
                label_160:
                    String s6 = zzkc.zzap(v1);
                    Log.d("MetadataUtil", (s6.length() == 0 ? new String("Skipped unknown metadata entry: ") : "Skipped unknown metadata entry: " + s6));
                    return null;
                }
                return zzko.zza(v1, "TCOM", zzom0);
            }
            return zzko.zza(v1, "TIT2", zzom0);
        }
        finally {
            zzom0.zzbh(v);
        }
    }

    private static int zze(zzom zzom0) {
        zzom0.zzbi(4);
        if(zzom0.readInt() == zzkc.zzaum) {
            zzom0.zzbi(8);
            return zzom0.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}

