package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest {
    private final Date zzmg;
    private final Gender zzmh;
    private final Set zzmi;
    private final boolean zzmj;
    private final Location zzmk;

    public MediationAdRequest(Date date0, Gender adRequest$Gender0, Set set0, boolean z, Location location0) {
        this.zzmg = date0;
        this.zzmh = adRequest$Gender0;
        this.zzmi = set0;
        this.zzmj = z;
        this.zzmk = location0;
    }

    public Integer getAgeInYears() {
        if(this.zzmg != null) {
            Calendar calendar0 = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            calendar0.setTime(this.zzmg);
            Integer integer0 = (int)(calendar1.get(1) - calendar0.get(1));
            return calendar1.get(2) >= calendar0.get(2) && (calendar1.get(2) != calendar0.get(2) || calendar1.get(5) >= calendar0.get(5)) ? integer0 : ((int)(((int)integer0) - 1));
        }
        return null;
    }

    public Date getBirthday() {
        return this.zzmg;
    }

    public Gender getGender() {
        return this.zzmh;
    }

    public Set getKeywords() {
        return this.zzmi;
    }

    public Location getLocation() {
        return this.zzmk;
    }

    public boolean isTesting() {
        return this.zzmj;
    }
}

