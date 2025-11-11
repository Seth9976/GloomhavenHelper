package com.google.android.gms.ads.rewarded;

public class ServerSideVerificationOptions {
    public static final class Builder {
        private String zzdpa;
        private String zzdpb;

        public Builder() {
            this.zzdpa = "";
            this.zzdpb = "";
        }

        public final ServerSideVerificationOptions build() {
            return new ServerSideVerificationOptions(this, null);
        }

        public final Builder setCustomData(String s) {
            this.zzdpb = s;
            return this;
        }

        public final Builder setUserId(String s) {
            this.zzdpa = s;
            return this;
        }
    }

    private final String zzdpa;
    private final String zzdpb;

    private ServerSideVerificationOptions(Builder serverSideVerificationOptions$Builder0) {
        this.zzdpa = serverSideVerificationOptions$Builder0.zzdpa;
        this.zzdpb = serverSideVerificationOptions$Builder0.zzdpb;
    }

    ServerSideVerificationOptions(Builder serverSideVerificationOptions$Builder0, zzb zzb0) {
        this(serverSideVerificationOptions$Builder0);
    }

    public String getCustomData() {
        return this.zzdpb;
    }

    public String getUserId() {
        return this.zzdpa;
    }
}

