package androidx.core.app;

import android.app.Notification.Action.Builder;
import android.app.Notification.Action;
import android.app.Notification.Builder;
import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private final List mActionExtrasList;
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Bundle mExtras;
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(Builder notificationCompat$Builder0) {
        this.mActionExtrasList = new ArrayList();
        this.mExtras = new Bundle();
        this.mBuilderCompat = notificationCompat$Builder0;
        this.mBuilder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(notificationCompat$Builder0.mContext, notificationCompat$Builder0.mChannelId) : new Notification.Builder(notificationCompat$Builder0.mContext);
        Notification notification0 = notificationCompat$Builder0.mNotification;
        this.mBuilder.setWhen(notification0.when).setSmallIcon(notification0.icon, notification0.iconLevel).setContent(notification0.contentView).setTicker(notification0.tickerText, notificationCompat$Builder0.mTickerView).setVibrate(notification0.vibrate).setLights(notification0.ledARGB, notification0.ledOnMS, notification0.ledOffMS).setOngoing((notification0.flags & 2) != 0).setOnlyAlertOnce((notification0.flags & 8) != 0).setAutoCancel((notification0.flags & 16) != 0).setDefaults(notification0.defaults).setContentTitle(notificationCompat$Builder0.mContentTitle).setContentText(notificationCompat$Builder0.mContentText).setContentInfo(notificationCompat$Builder0.mContentInfo).setContentIntent(notificationCompat$Builder0.mContentIntent).setDeleteIntent(notification0.deleteIntent).setFullScreenIntent(notificationCompat$Builder0.mFullScreenIntent, (notification0.flags & 0x80) != 0).setLargeIcon(notificationCompat$Builder0.mLargeIcon).setNumber(notificationCompat$Builder0.mNumber).setProgress(notificationCompat$Builder0.mProgressMax, notificationCompat$Builder0.mProgress, notificationCompat$Builder0.mProgressIndeterminate);
        if(Build.VERSION.SDK_INT < 21) {
            this.mBuilder.setSound(notification0.sound, notification0.audioStreamType);
        }
        if(Build.VERSION.SDK_INT >= 16) {
            this.mBuilder.setSubText(notificationCompat$Builder0.mSubText).setUsesChronometer(notificationCompat$Builder0.mUseChronometer).setPriority(notificationCompat$Builder0.mPriority);
            for(Object object0: notificationCompat$Builder0.mActions) {
                this.addAction(((Action)object0));
            }
            if(notificationCompat$Builder0.mExtras != null) {
                this.mExtras.putAll(notificationCompat$Builder0.mExtras);
            }
            if(Build.VERSION.SDK_INT < 20) {
                if(notificationCompat$Builder0.mLocalOnly) {
                    this.mExtras.putBoolean("android.support.localOnly", true);
                }
                if(notificationCompat$Builder0.mGroupKey != null) {
                    this.mExtras.putString("android.support.groupKey", notificationCompat$Builder0.mGroupKey);
                    if(notificationCompat$Builder0.mGroupSummary) {
                        this.mExtras.putBoolean("android.support.isGroupSummary", true);
                    }
                    else {
                        this.mExtras.putBoolean("android.support.useSideChannel", true);
                    }
                }
                if(notificationCompat$Builder0.mSortKey != null) {
                    this.mExtras.putString("android.support.sortKey", notificationCompat$Builder0.mSortKey);
                }
            }
            this.mContentView = notificationCompat$Builder0.mContentView;
            this.mBigContentView = notificationCompat$Builder0.mBigContentView;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            this.mBuilder.setShowWhen(notificationCompat$Builder0.mShowWhen);
            if(Build.VERSION.SDK_INT < 21 && notificationCompat$Builder0.mPeople != null && !notificationCompat$Builder0.mPeople.isEmpty()) {
                String[] arr_s = (String[])notificationCompat$Builder0.mPeople.toArray(new String[notificationCompat$Builder0.mPeople.size()]);
                this.mExtras.putStringArray("android.people", arr_s);
            }
        }
        if(Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setLocalOnly(notificationCompat$Builder0.mLocalOnly).setGroup(notificationCompat$Builder0.mGroupKey).setGroupSummary(notificationCompat$Builder0.mGroupSummary).setSortKey(notificationCompat$Builder0.mSortKey);
            this.mGroupAlertBehavior = notificationCompat$Builder0.mGroupAlertBehavior;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setCategory(notificationCompat$Builder0.mCategory).setColor(notificationCompat$Builder0.mColor).setVisibility(notificationCompat$Builder0.mVisibility).setPublicVersion(notificationCompat$Builder0.mPublicVersion).setSound(notification0.sound, notification0.audioAttributes);
            for(Object object1: notificationCompat$Builder0.mPeople) {
                this.mBuilder.addPerson(((String)object1));
            }
            this.mHeadsUpContentView = notificationCompat$Builder0.mHeadsUpContentView;
            if(notificationCompat$Builder0.mInvisibleActions.size() > 0) {
                Bundle bundle0 = notificationCompat$Builder0.getExtras().getBundle("android.car.EXTENSIONS");
                if(bundle0 == null) {
                    bundle0 = new Bundle();
                }
                Bundle bundle1 = new Bundle();
                for(int v = 0; v < notificationCompat$Builder0.mInvisibleActions.size(); ++v) {
                    bundle1.putBundle(Integer.toString(v), NotificationCompatJellybean.getBundleForAction(((Action)notificationCompat$Builder0.mInvisibleActions.get(v))));
                }
                bundle0.putBundle("invisible_actions", bundle1);
                notificationCompat$Builder0.getExtras().putBundle("android.car.EXTENSIONS", bundle0);
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle0);
            }
        }
        if(Build.VERSION.SDK_INT >= 24) {
            this.mBuilder.setExtras(notificationCompat$Builder0.mExtras).setRemoteInputHistory(notificationCompat$Builder0.mRemoteInputHistory);
            if(notificationCompat$Builder0.mContentView != null) {
                this.mBuilder.setCustomContentView(notificationCompat$Builder0.mContentView);
            }
            if(notificationCompat$Builder0.mBigContentView != null) {
                this.mBuilder.setCustomBigContentView(notificationCompat$Builder0.mBigContentView);
            }
            if(notificationCompat$Builder0.mHeadsUpContentView != null) {
                this.mBuilder.setCustomHeadsUpContentView(notificationCompat$Builder0.mHeadsUpContentView);
            }
        }
        if(Build.VERSION.SDK_INT >= 26) {
            this.mBuilder.setBadgeIconType(notificationCompat$Builder0.mBadgeIcon).setShortcutId(notificationCompat$Builder0.mShortcutId).setTimeoutAfter(notificationCompat$Builder0.mTimeout).setGroupAlertBehavior(notificationCompat$Builder0.mGroupAlertBehavior);
            if(notificationCompat$Builder0.mColorizedSet) {
                this.mBuilder.setColorized(notificationCompat$Builder0.mColorized);
            }
            if(!TextUtils.isEmpty(notificationCompat$Builder0.mChannelId)) {
                this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
    }

    private void addAction(Action notificationCompat$Action0) {
        if(Build.VERSION.SDK_INT >= 20) {
            Notification.Action.Builder notification$Action$Builder0 = new Notification.Action.Builder(notificationCompat$Action0.getIcon(), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent());
            if(notificationCompat$Action0.getRemoteInputs() != null) {
                RemoteInput[] arr_remoteInput = androidx.core.app.RemoteInput.fromCompat(notificationCompat$Action0.getRemoteInputs());
                for(int v = 0; v < arr_remoteInput.length; ++v) {
                    notification$Action$Builder0.addRemoteInput(arr_remoteInput[v]);
                }
            }
            Bundle bundle0 = notificationCompat$Action0.getExtras() == null ? new Bundle() : new Bundle(notificationCompat$Action0.getExtras());
            bundle0.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action0.getAllowGeneratedReplies());
            if(Build.VERSION.SDK_INT >= 24) {
                notification$Action$Builder0.setAllowGeneratedReplies(notificationCompat$Action0.getAllowGeneratedReplies());
            }
            bundle0.putInt("android.support.action.semanticAction", notificationCompat$Action0.getSemanticAction());
            if(Build.VERSION.SDK_INT >= 28) {
                notification$Action$Builder0.setSemanticAction(notificationCompat$Action0.getSemanticAction());
            }
            bundle0.putBoolean("android.support.action.showsUserInterface", notificationCompat$Action0.getShowsUserInterface());
            notification$Action$Builder0.addExtras(bundle0);
            Notification.Action notification$Action0 = notification$Action$Builder0.build();
            this.mBuilder.addAction(notification$Action0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            Bundle bundle1 = NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, notificationCompat$Action0);
            this.mActionExtrasList.add(bundle1);
        }
    }

    public Notification build() {
        Style notificationCompat$Style0 = this.mBuilderCompat.mStyle;
        if(notificationCompat$Style0 != null) {
            notificationCompat$Style0.apply(this);
        }
        RemoteViews remoteViews0 = notificationCompat$Style0 == null ? null : notificationCompat$Style0.makeContentView(this);
        Notification notification0 = this.buildInternal();
        if(remoteViews0 != null) {
            notification0.contentView = remoteViews0;
        }
        else if(this.mBuilderCompat.mContentView != null) {
            notification0.contentView = this.mBuilderCompat.mContentView;
        }
        if(Build.VERSION.SDK_INT >= 16 && notificationCompat$Style0 != null) {
            RemoteViews remoteViews1 = notificationCompat$Style0.makeBigContentView(this);
            if(remoteViews1 != null) {
                notification0.bigContentView = remoteViews1;
            }
        }
        if(Build.VERSION.SDK_INT >= 21 && notificationCompat$Style0 != null) {
            RemoteViews remoteViews2 = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
            if(remoteViews2 != null) {
                notification0.headsUpContentView = remoteViews2;
            }
        }
        if(Build.VERSION.SDK_INT >= 16 && notificationCompat$Style0 != null) {
            Bundle bundle0 = NotificationCompat.getExtras(notification0);
            if(bundle0 != null) {
                notificationCompat$Style0.addCompatExtras(bundle0);
            }
        }
        return notification0;
    }

    protected Notification buildInternal() {
        if(Build.VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        if(Build.VERSION.SDK_INT >= 24) {
            Notification notification0 = this.mBuilder.build();
            if(this.mGroupAlertBehavior != 0) {
                if(notification0.getGroup() != null && (notification0.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2) {
                    this.removeSoundAndVibration(notification0);
                }
                if(notification0.getGroup() != null && (notification0.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1) {
                    this.removeSoundAndVibration(notification0);
                }
            }
            return notification0;
        }
        if(Build.VERSION.SDK_INT >= 21) {
            this.mBuilder.setExtras(this.mExtras);
            Notification notification1 = this.mBuilder.build();
            RemoteViews remoteViews0 = this.mContentView;
            if(remoteViews0 != null) {
                notification1.contentView = remoteViews0;
            }
            RemoteViews remoteViews1 = this.mBigContentView;
            if(remoteViews1 != null) {
                notification1.bigContentView = remoteViews1;
            }
            RemoteViews remoteViews2 = this.mHeadsUpContentView;
            if(remoteViews2 != null) {
                notification1.headsUpContentView = remoteViews2;
            }
            if(this.mGroupAlertBehavior != 0) {
                if(notification1.getGroup() != null && (notification1.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2) {
                    this.removeSoundAndVibration(notification1);
                }
                if(notification1.getGroup() != null && (notification1.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1) {
                    this.removeSoundAndVibration(notification1);
                }
            }
            return notification1;
        }
        if(Build.VERSION.SDK_INT >= 20) {
            this.mBuilder.setExtras(this.mExtras);
            Notification notification2 = this.mBuilder.build();
            RemoteViews remoteViews3 = this.mContentView;
            if(remoteViews3 != null) {
                notification2.contentView = remoteViews3;
            }
            RemoteViews remoteViews4 = this.mBigContentView;
            if(remoteViews4 != null) {
                notification2.bigContentView = remoteViews4;
            }
            if(this.mGroupAlertBehavior != 0) {
                if(notification2.getGroup() != null && (notification2.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2) {
                    this.removeSoundAndVibration(notification2);
                }
                if(notification2.getGroup() != null && (notification2.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1) {
                    this.removeSoundAndVibration(notification2);
                }
            }
            return notification2;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            SparseArray sparseArray0 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if(sparseArray0 != null) {
                this.mExtras.putSparseParcelableArray("android.support.actionExtras", sparseArray0);
            }
            this.mBuilder.setExtras(this.mExtras);
            Notification notification3 = this.mBuilder.build();
            RemoteViews remoteViews5 = this.mContentView;
            if(remoteViews5 != null) {
                notification3.contentView = remoteViews5;
            }
            RemoteViews remoteViews6 = this.mBigContentView;
            if(remoteViews6 != null) {
                notification3.bigContentView = remoteViews6;
            }
            return notification3;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            Notification notification4 = this.mBuilder.build();
            Bundle bundle0 = NotificationCompat.getExtras(notification4);
            Bundle bundle1 = new Bundle(this.mExtras);
            for(Object object0: this.mExtras.keySet()) {
                String s = (String)object0;
                if(bundle0.containsKey(s)) {
                    bundle1.remove(s);
                }
            }
            bundle0.putAll(bundle1);
            SparseArray sparseArray1 = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if(sparseArray1 != null) {
                NotificationCompat.getExtras(notification4).putSparseParcelableArray("android.support.actionExtras", sparseArray1);
            }
            RemoteViews remoteViews7 = this.mContentView;
            if(remoteViews7 != null) {
                notification4.contentView = remoteViews7;
            }
            RemoteViews remoteViews8 = this.mBigContentView;
            if(remoteViews8 != null) {
                notification4.bigContentView = remoteViews8;
            }
            return notification4;
        }
        return this.mBuilder.getNotification();
    }

    @Override  // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    private void removeSoundAndVibration(Notification notification0) {
        notification0.sound = null;
        notification0.vibrate = null;
        notification0.defaults &= -3;
    }
}

