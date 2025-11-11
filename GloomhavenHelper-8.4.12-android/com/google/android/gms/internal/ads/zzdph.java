package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public class zzdph implements zzdpe {
    private final zzdpj zzhdw;
    private final Class zzhdx;

    public zzdph(zzdpj zzdpj0, Class class0) {
        if(!zzdpj0.zzavd().contains(class0) && !Void.class.equals(class0)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", zzdpj0.toString(), class0.getName()));
        }
        this.zzhdw = zzdpj0;
        this.zzhdx = class0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpe
    public final String getKeyType() {
        return this.zzhdw.getKeyType();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpe
    public final Object zza(zzeah zzeah0) throws GeneralSecurityException {
        String s = this.zzhdw.zzavb().getName();
        String s1 = s.length() == 0 ? new String("Expected proto of type ") : "Expected proto of type " + s;
        if(!this.zzhdw.zzavb().isInstance(zzeah0)) {
            throw new GeneralSecurityException(s1);
        }
        return this.zzb(zzeah0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpe
    public final Class zzauy() {
        return this.zzhdx;
    }

    private final zzdpg zzauz() {
        return new zzdpg(this.zzhdw.zzavf());
    }

    private final Object zzb(zzeah zzeah0) throws GeneralSecurityException {
        if(Void.class.equals(this.zzhdx)) {
            throw new GeneralSecurityException("Cannot create a primitive for Void");
        }
        this.zzhdw.zze(zzeah0);
        return this.zzhdw.zza(zzeah0, this.zzhdx);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpe
    public final Object zzm(zzdxn zzdxn0) throws GeneralSecurityException {
        try {
            return this.zzb(this.zzhdw.zzr(zzdxn0));
        }
        catch(zzdzh zzdzh0) {
            String s = this.zzhdw.zzavb().getName();
            throw new GeneralSecurityException((s.length() == 0 ? new String("Failures parsing proto of type ") : "Failures parsing proto of type " + s), zzdzh0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpe
    public final zzeah zzn(zzdxn zzdxn0) throws GeneralSecurityException {
        try {
            return this.zzauz().zzp(zzdxn0);
        }
        catch(zzdzh zzdzh0) {
            String s = this.zzhdw.zzavf().zzava().getName();
            throw new GeneralSecurityException((s.length() == 0 ? new String("Failures parsing proto of type ") : "Failures parsing proto of type " + s), zzdzh0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdpe
    public final zzduc zzo(zzdxn zzdxn0) throws GeneralSecurityException {
        try {
            zzeah zzeah0 = this.zzauz().zzp(zzdxn0);
            return (zzduc)(((zzdyz)zzduc.zzayk().zzhe(this.zzhdw.getKeyType()).zzaf(zzeah0.zzbai()).zzb(this.zzhdw.zzavc()).zzbcx()));
        }
        catch(zzdzh zzdzh0) {
            throw new GeneralSecurityException("Unexpected proto", zzdzh0);
        }
    }
}

