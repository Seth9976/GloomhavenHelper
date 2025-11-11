package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.text.Normalizer.Form;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public final class zzqz {
    private final int zzbqq;
    private final zzqp zzbqs;
    private String zzbqx;
    private String zzbqy;
    private final boolean zzbqz;
    private final int zzbra;
    private final int zzbrb;

    public zzqz(int v, int v1, int v2) {
        this.zzbqq = v;
        this.zzbqz = false;
        this.zzbra = v1 > 0x40 || v1 < 0 ? 0x40 : v1;
        this.zzbrb = v2 <= 0 ? 1 : v2;
        this.zzbqs = new zzra(this.zzbra);
    }

    // This method was un-flattened
    public final String zza(ArrayList arrayList0, ArrayList arrayList1) {
        String s1;
        Collections.sort(arrayList1, new zzrc(this));
        HashSet hashSet0 = new HashSet();
        int v = 0;
    alab1:
        while(v < arrayList1.size()) {
            String[] arr_s = Normalizer.normalize(((CharSequence)arrayList0.get(((zzqq)arrayList1.get(v)).zzmq())), Normalizer.Form.NFKC).toLowerCase(Locale.US).split("\n");
            if(arr_s.length != 0) {
                int v1 = 0;
                while(v1 < arr_s.length) {
                    String s = arr_s[v1];
                    if(s.indexOf("\'") == -1) {
                        s1 = s;
                    }
                    else {
                        StringBuilder stringBuilder0 = new StringBuilder(s);
                        boolean z = false;
                        for(int v2 = 1; v2 + 2 <= stringBuilder0.length(); ++v2) {
                            if(stringBuilder0.charAt(v2) == 39) {
                                if(stringBuilder0.charAt(v2 - 1) == 0x20) {
                                    stringBuilder0.setCharAt(v2, ' ');
                                }
                                else {
                                    switch(stringBuilder0.charAt(v2 + 1)) {
                                        case 83: 
                                        case 0x73: {
                                            if(v2 + 2 == stringBuilder0.length() || stringBuilder0.charAt(v2 + 2) == 0x20) {
                                                stringBuilder0.insert(v2, ' ');
                                                v2 += 2;
                                            }
                                        }
                                    }
                                }
                                z = true;
                            }
                        }
                        s1 = z ? stringBuilder0.toString() : null;
                        if(s1 != null) {
                            this.zzbqy = s1;
                        }
                    }
                    String[] arr_s1 = zzqt.zzd(s1, true);
                    if(arr_s1.length >= this.zzbrb) {
                        int v3 = 0;
                        while(v3 < arr_s1.length) {
                            String s2 = "";
                            int v4 = 0;
                            while(true) {
                                boolean z1 = true;
                                if(v4 < this.zzbrb) {
                                    int v5 = v3 + v4;
                                    if(v5 >= arr_s1.length) {
                                        z1 = false;
                                    }
                                    else {
                                        if(v4 > 0) {
                                            s2 = s2 + " ";
                                        }
                                        String s3 = String.valueOf(s2);
                                        String s4 = String.valueOf(arr_s1[v5]);
                                        s2 = s4.length() == 0 ? new String(s3) : s3 + s4;
                                        ++v4;
                                        continue;
                                    }
                                }
                                break;
                            }
                            if(z1) {
                                hashSet0.add(s2);
                                if(hashSet0.size() < this.zzbqq) {
                                    ++v3;
                                    continue;
                                }
                                else {
                                    break alab1;
                                }
                            }
                            else {
                                break;
                            }
                            goto label_66;
                        }
                        if(hashSet0.size() < this.zzbqq) {
                            ++v1;
                            continue;
                        }
                        else {
                            break alab1;
                        }
                    }
                    else {
                        ++v1;
                        continue;
                    }
                    goto label_66;
                }
            }
            ++v;
        }
        zzqu zzqu0 = new zzqu();
        this.zzbqx = "";
        Iterator iterator0 = hashSet0.iterator();
    label_66:
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            String s5 = (String)object0;
            try {
                zzqu0.write(this.zzbqs.zzbw(s5));
            }
            catch(IOException iOException0) {
                zzawf.zzc("Error while writing hash to byteStream", iOException0);
                if(true) {
                    break;
                }
            }
        }
        return zzqu0.toString();
    }
}

