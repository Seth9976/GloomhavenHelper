package androidx.core.app;

import android.app.Notification.Action.Builder;
import android.app.Notification.Action;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.InboxStyle;
import android.app.Notification.MessagingStyle.Message;
import android.app.Notification.MessagingStyle;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput.Builder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.R.color;
import androidx.core.R.dimen;
import androidx.core.R.drawable;
import androidx.core.R.id;
import androidx.core.R.integer;
import androidx.core.R.layout;
import androidx.core.R.string;
import androidx.core.text.BidiFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotificationCompat {
    public static class Action {
        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
                this(v, charSequence0, pendingIntent0, new Bundle(), null, true, 0, true);
            }

            private Builder(int v, CharSequence charSequence0, PendingIntent pendingIntent0, Bundle bundle0, RemoteInput[] arr_remoteInput, boolean z, int v1, boolean z1) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = v;
                this.mTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
                this.mIntent = pendingIntent0;
                this.mExtras = bundle0;
                this.mRemoteInputs = arr_remoteInput == null ? null : new ArrayList(Arrays.asList(arr_remoteInput));
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = v1;
                this.mShowsUserInterface = z1;
            }

            public Builder(Action notificationCompat$Action0) {
                this(notificationCompat$Action0.icon, notificationCompat$Action0.title, notificationCompat$Action0.actionIntent, new Bundle(notificationCompat$Action0.mExtras), notificationCompat$Action0.getRemoteInputs(), notificationCompat$Action0.getAllowGeneratedReplies(), notificationCompat$Action0.getSemanticAction(), notificationCompat$Action0.mShowsUserInterface);
            }

            public Builder addExtras(Bundle bundle0) {
                if(bundle0 != null) {
                    this.mExtras.putAll(bundle0);
                }
                return this;
            }

            public Builder addRemoteInput(RemoteInput remoteInput0) {
                if(this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList();
                }
                this.mRemoteInputs.add(remoteInput0);
                return this;
            }

            public Action build() {
                ArrayList arrayList0 = new ArrayList();
                ArrayList arrayList1 = new ArrayList();
                ArrayList arrayList2 = this.mRemoteInputs;
                if(arrayList2 != null) {
                    for(Object object0: arrayList2) {
                        RemoteInput remoteInput0 = (RemoteInput)object0;
                        if(remoteInput0.isDataOnly()) {
                            arrayList0.add(remoteInput0);
                        }
                        else {
                            arrayList1.add(remoteInput0);
                        }
                    }
                }
                RemoteInput[] arr_remoteInput = arrayList0.isEmpty() ? null : ((RemoteInput[])arrayList0.toArray(new RemoteInput[arrayList0.size()]));
                if(arrayList1.isEmpty()) {
                    return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, null, arr_remoteInput, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
                }
                RemoteInput[] arr_remoteInput1 = (RemoteInput[])arrayList1.toArray(new RemoteInput[arrayList1.size()]);
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arr_remoteInput1, arr_remoteInput, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
            }

            public Builder extend(Extender notificationCompat$Action$Extender0) {
                notificationCompat$Action$Extender0.extend(this);
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setSemanticAction(int v) {
                this.mSemanticAction = v;
                return this;
            }

            public Builder setShowsUserInterface(boolean z) {
                this.mShowsUserInterface = z;
                return this;
            }
        }

        public interface Extender {
            Builder extend(Builder arg1);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
                this.mFlags = 1;
            }

            public WearableExtender(Action notificationCompat$Action0) {
                this.mFlags = 1;
                Bundle bundle0 = notificationCompat$Action0.getExtras().getBundle("android.wearable.EXTENSIONS");
                if(bundle0 != null) {
                    this.mFlags = bundle0.getInt("flags", 1);
                    this.mInProgressLabel = bundle0.getCharSequence("inProgressLabel");
                    this.mConfirmLabel = bundle0.getCharSequence("confirmLabel");
                    this.mCancelLabel = bundle0.getCharSequence("cancelLabel");
                }
            }

            public WearableExtender clone() {
                WearableExtender notificationCompat$Action$WearableExtender0 = new WearableExtender();
                notificationCompat$Action$WearableExtender0.mFlags = this.mFlags;
                notificationCompat$Action$WearableExtender0.mInProgressLabel = this.mInProgressLabel;
                notificationCompat$Action$WearableExtender0.mConfirmLabel = this.mConfirmLabel;
                notificationCompat$Action$WearableExtender0.mCancelLabel = this.mCancelLabel;
                return notificationCompat$Action$WearableExtender0;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            @Override  // androidx.core.app.NotificationCompat$Action$Extender
            public Builder extend(Builder notificationCompat$Action$Builder0) {
                Bundle bundle0 = new Bundle();
                int v = this.mFlags;
                if(v != 1) {
                    bundle0.putInt("flags", v);
                }
                CharSequence charSequence0 = this.mInProgressLabel;
                if(charSequence0 != null) {
                    bundle0.putCharSequence("inProgressLabel", charSequence0);
                }
                CharSequence charSequence1 = this.mConfirmLabel;
                if(charSequence1 != null) {
                    bundle0.putCharSequence("confirmLabel", charSequence1);
                }
                CharSequence charSequence2 = this.mCancelLabel;
                if(charSequence2 != null) {
                    bundle0.putCharSequence("cancelLabel", charSequence2);
                }
                notificationCompat$Action$Builder0.getExtras().putBundle("android.wearable.EXTENSIONS", bundle0);
                return notificationCompat$Action$Builder0;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                this.setFlag(1, z);
                return this;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence0) {
                this.mCancelLabel = charSequence0;
                return this;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence0) {
                this.mConfirmLabel = charSequence0;
                return this;
            }

            private void setFlag(int v, boolean z) {
                if(z) {
                    this.mFlags |= v;
                    return;
                }
                this.mFlags &= ~v;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                this.setFlag(4, z);
                return this;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                this.setFlag(2, z);
                return this;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence0) {
                this.mInProgressLabel = charSequence0;
                return this;
            }
        }

        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        public Action(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            this(v, charSequence0, pendingIntent0, new Bundle(), null, null, true, 0, true);
        }

        Action(int v, CharSequence charSequence0, PendingIntent pendingIntent0, Bundle bundle0, RemoteInput[] arr_remoteInput, RemoteInput[] arr_remoteInput1, boolean z, int v1, boolean z1) {
            this.mShowsUserInterface = true;
            this.icon = v;
            this.title = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.actionIntent = pendingIntent0;
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            this.mExtras = bundle0;
            this.mRemoteInputs = arr_remoteInput;
            this.mDataOnlyRemoteInputs = arr_remoteInput1;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = v1;
            this.mShowsUserInterface = z1;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public int getIcon() {
            return this.icon;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public CharSequence getTitle() {
            return this.title;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public static class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle() {
        }

        public BigPictureStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.setBuilder(notificationCompat$Builder0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 16) {
                Notification.BigPictureStyle notification$BigPictureStyle0 = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
                if(this.mBigLargeIconSet) {
                    notification$BigPictureStyle0.bigLargeIcon(this.mBigLargeIcon);
                }
                if(this.mSummaryTextSet) {
                    notification$BigPictureStyle0.setSummaryText(this.mSummaryText);
                }
            }
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap0) {
            this.mBigLargeIcon = bitmap0;
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap0) {
            this.mPicture = bitmap0;
            return this;
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence0) {
            this.mBigContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence0) {
            this.mSummaryText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        public BigTextStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.setBuilder(notificationCompat$Builder0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle notification$BigTextStyle0 = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
                if(this.mSummaryTextSet) {
                    notification$BigTextStyle0.setSummaryText(this.mSummaryText);
                }
            }
        }

        public BigTextStyle bigText(CharSequence charSequence0) {
            this.mBigText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence0) {
            this.mBigContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence0) {
            this.mSummaryText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public static class androidx.core.app.NotificationCompat.Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 0x1400;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public ArrayList mActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList mInvisibleActions;
        Bitmap mLargeIcon;
        boolean mLocalOnly;
        Notification mNotification;
        int mNumber;
        @Deprecated
        public ArrayList mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        String mShortcutId;
        boolean mShowWhen;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        @Deprecated
        public androidx.core.app.NotificationCompat.Builder(Context context0) {
            this(context0, null);
        }

        public androidx.core.app.NotificationCompat.Builder(@NonNull Context context0, @NonNull String s) {
            this.mActions = new ArrayList();
            this.mInvisibleActions = new ArrayList();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mNotification = new Notification();
            this.mContext = context0;
            this.mChannelId = s;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
        }

        public androidx.core.app.NotificationCompat.Builder addAction(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            this.mActions.add(new Action(v, charSequence0, pendingIntent0));
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addAction(Action notificationCompat$Action0) {
            this.mActions.add(notificationCompat$Action0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addExtras(Bundle bundle0) {
            if(bundle0 != null) {
                Bundle bundle1 = this.mExtras;
                if(bundle1 == null) {
                    this.mExtras = new Bundle(bundle0);
                    return this;
                }
                bundle1.putAll(bundle0);
            }
            return this;
        }

        @RequiresApi(21)
        public androidx.core.app.NotificationCompat.Builder addInvisibleAction(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            return this.addInvisibleAction(new Action(v, charSequence0, pendingIntent0));
        }

        @RequiresApi(21)
        public androidx.core.app.NotificationCompat.Builder addInvisibleAction(Action notificationCompat$Action0) {
            this.mInvisibleActions.add(notificationCompat$Action0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addPerson(String s) {
            this.mPeople.add(s);
            return this;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Extender notificationCompat$Extender0) {
            notificationCompat$Extender0.extend(this);
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.mColor;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.mContentView;
        }

        public Bundle getExtras() {
            if(this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @Deprecated
        public Notification getNotification() {
            return this.build();
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.mPriority;
        }

        // 去混淆评级： 低(20)
        @RestrictTo({Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            return this.mShowWhen ? this.mNotification.when : 0L;
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence0) {
            if(charSequence0 == null) {
                return null;
            }
            return charSequence0.length() <= 0x1400 ? charSequence0 : charSequence0.subSequence(0, 0x1400);
        }

        private Bitmap reduceLargeIconSize(Bitmap bitmap0) {
            if(bitmap0 != null && Build.VERSION.SDK_INT < 27) {
                Resources resources0 = this.mContext.getResources();
                int v = resources0.getDimensionPixelSize(dimen.compat_notification_large_icon_max_width);
                int v1 = resources0.getDimensionPixelSize(dimen.compat_notification_large_icon_max_height);
                if(bitmap0.getWidth() <= v && bitmap0.getHeight() <= v1) {
                    return bitmap0;
                }
                double f = Math.min(((double)v) / ((double)Math.max(1, bitmap0.getWidth())), ((double)v1) / ((double)Math.max(1, bitmap0.getHeight())));
                return Bitmap.createScaledBitmap(bitmap0, ((int)Math.ceil(((double)bitmap0.getWidth()) * f)), ((int)Math.ceil(((double)bitmap0.getHeight()) * f)), true);
            }
            return bitmap0;
        }

        public androidx.core.app.NotificationCompat.Builder setAutoCancel(boolean z) {
            this.setFlag(16, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setBadgeIconType(int v) {
            this.mBadgeIcon = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCategory(String s) {
            this.mCategory = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setChannelId(@NonNull String s) {
            this.mChannelId = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setColor(@ColorInt int v) {
            this.mColor = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setColorized(boolean z) {
            this.mColorized = z;
            this.mColorizedSet = true;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContent(RemoteViews remoteViews0) {
            this.mNotification.contentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentInfo(CharSequence charSequence0) {
            this.mContentInfo = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentIntent(PendingIntent pendingIntent0) {
            this.mContentIntent = pendingIntent0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentText(CharSequence charSequence0) {
            this.mContentText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentTitle(CharSequence charSequence0) {
            this.mContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCustomBigContentView(RemoteViews remoteViews0) {
            this.mBigContentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCustomContentView(RemoteViews remoteViews0) {
            this.mContentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCustomHeadsUpContentView(RemoteViews remoteViews0) {
            this.mHeadsUpContentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setDefaults(int v) {
            Notification notification0 = this.mNotification;
            notification0.defaults = v;
            if((v & 4) != 0) {
                notification0.flags |= 1;
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setDeleteIntent(PendingIntent pendingIntent0) {
            this.mNotification.deleteIntent = pendingIntent0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setExtras(Bundle bundle0) {
            this.mExtras = bundle0;
            return this;
        }

        private void setFlag(int v, boolean z) {
            if(z) {
                this.mNotification.flags |= v;
                return;
            }
            this.mNotification.flags &= ~v;
        }

        public androidx.core.app.NotificationCompat.Builder setFullScreenIntent(PendingIntent pendingIntent0, boolean z) {
            this.mFullScreenIntent = pendingIntent0;
            this.setFlag(0x80, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setGroup(String s) {
            this.mGroupKey = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setGroupAlertBehavior(int v) {
            this.mGroupAlertBehavior = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setGroupSummary(boolean z) {
            this.mGroupSummary = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLargeIcon(Bitmap bitmap0) {
            this.mLargeIcon = this.reduceLargeIconSize(bitmap0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLights(@ColorInt int v, int v1, int v2) {
            this.mNotification.ledARGB = v;
            this.mNotification.ledOnMS = v1;
            this.mNotification.ledOffMS = v2;
            this.mNotification.flags = (this.mNotification.ledOnMS == 0 || this.mNotification.ledOffMS == 0 ? 0 : 1) | this.mNotification.flags & -2;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLocalOnly(boolean z) {
            this.mLocalOnly = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setNumber(int v) {
            this.mNumber = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setOngoing(boolean z) {
            this.setFlag(2, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setOnlyAlertOnce(boolean z) {
            this.setFlag(8, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setPriority(int v) {
            this.mPriority = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setProgress(int v, int v1, boolean z) {
            this.mProgressMax = v;
            this.mProgress = v1;
            this.mProgressIndeterminate = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setPublicVersion(Notification notification0) {
            this.mPublicVersion = notification0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setRemoteInputHistory(CharSequence[] arr_charSequence) {
            this.mRemoteInputHistory = arr_charSequence;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setShortcutId(String s) {
            this.mShortcutId = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setShowWhen(boolean z) {
            this.mShowWhen = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSmallIcon(int v) {
            this.mNotification.icon = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSmallIcon(int v, int v1) {
            this.mNotification.icon = v;
            this.mNotification.iconLevel = v1;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSortKey(String s) {
            this.mSortKey = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSound(Uri uri0) {
            this.mNotification.sound = uri0;
            this.mNotification.audioStreamType = -1;
            if(Build.VERSION.SDK_INT >= 21) {
                Notification notification0 = this.mNotification;
                notification0.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSound(Uri uri0, int v) {
            this.mNotification.sound = uri0;
            this.mNotification.audioStreamType = v;
            if(Build.VERSION.SDK_INT >= 21) {
                Notification notification0 = this.mNotification;
                notification0.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(v).build();
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setStyle(Style notificationCompat$Style0) {
            if(this.mStyle != notificationCompat$Style0) {
                this.mStyle = notificationCompat$Style0;
                Style notificationCompat$Style1 = this.mStyle;
                if(notificationCompat$Style1 != null) {
                    notificationCompat$Style1.setBuilder(this);
                }
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSubText(CharSequence charSequence0) {
            this.mSubText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setTicker(CharSequence charSequence0) {
            Notification notification0 = this.mNotification;
            notification0.tickerText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setTicker(CharSequence charSequence0, RemoteViews remoteViews0) {
            Notification notification0 = this.mNotification;
            notification0.tickerText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mTickerView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setTimeoutAfter(long v) {
            this.mTimeout = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setUsesChronometer(boolean z) {
            this.mUseChronometer = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setVibrate(long[] arr_v) {
            this.mNotification.vibrate = arr_v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setVisibility(int v) {
            this.mVisibility = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setWhen(long v) {
            this.mNotification.when = v;
            return this;
        }
    }

    public static final class CarExtender implements androidx.core.app.NotificationCompat.Extender {
        public static class UnreadConversation {
            public static class androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder {
                private long mLatestTimestamp;
                private final List mMessages;
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder(String s) {
                    this.mMessages = new ArrayList();
                    this.mParticipant = s;
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder addMessage(String s) {
                    this.mMessages.add(s);
                    return this;
                }

                public UnreadConversation build() {
                    return new UnreadConversation(((String[])this.mMessages.toArray(new String[this.mMessages.size()])), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder setLatestTimestamp(long v) {
                    this.mLatestTimestamp = v;
                    return this;
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder setReadPendingIntent(PendingIntent pendingIntent0) {
                    this.mReadPendingIntent = pendingIntent0;
                    return this;
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder setReplyAction(PendingIntent pendingIntent0, RemoteInput remoteInput0) {
                    this.mRemoteInput = remoteInput0;
                    this.mReplyPendingIntent = pendingIntent0;
                    return this;
                }
            }

            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            UnreadConversation(String[] arr_s, RemoteInput remoteInput0, PendingIntent pendingIntent0, PendingIntent pendingIntent1, String[] arr_s1, long v) {
                this.mMessages = arr_s;
                this.mRemoteInput = remoteInput0;
                this.mReadPendingIntent = pendingIntent1;
                this.mReplyPendingIntent = pendingIntent0;
                this.mParticipants = arr_s1;
                this.mLatestTimestamp = v;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public String getParticipant() {
                return this.mParticipants.length <= 0 ? null : this.mParticipants[0];
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        @RestrictTo({Scope.LIBRARY_GROUP})
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
            this.mColor = 0;
        }

        public CarExtender(Notification notification0) {
            this.mColor = 0;
            if(Build.VERSION.SDK_INT < 21) {
                return;
            }
            Bundle bundle0 = NotificationCompat.getExtras(notification0) == null ? null : NotificationCompat.getExtras(notification0).getBundle("android.car.EXTENSIONS");
            if(bundle0 != null) {
                this.mLargeIcon = (Bitmap)bundle0.getParcelable("large_icon");
                this.mColor = bundle0.getInt("app_color", 0);
                this.mUnreadConversation = CarExtender.getUnreadConversationFromBundle(bundle0.getBundle("car_conversation"));
            }
        }

        @Override  // androidx.core.app.NotificationCompat$Extender
        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            if(Build.VERSION.SDK_INT < 21) {
                return notificationCompat$Builder0;
            }
            Bundle bundle0 = new Bundle();
            Bitmap bitmap0 = this.mLargeIcon;
            if(bitmap0 != null) {
                bundle0.putParcelable("large_icon", bitmap0);
            }
            int v = this.mColor;
            if(v != 0) {
                bundle0.putInt("app_color", v);
            }
            UnreadConversation notificationCompat$CarExtender$UnreadConversation0 = this.mUnreadConversation;
            if(notificationCompat$CarExtender$UnreadConversation0 != null) {
                bundle0.putBundle("car_conversation", CarExtender.getBundleForUnreadConversation(notificationCompat$CarExtender$UnreadConversation0));
            }
            notificationCompat$Builder0.getExtras().putBundle("android.car.EXTENSIONS", bundle0);
            return notificationCompat$Builder0;
        }

        @RequiresApi(21)
        private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation notificationCompat$CarExtender$UnreadConversation0) {
            Bundle bundle0 = new Bundle();
            String s = notificationCompat$CarExtender$UnreadConversation0.getParticipants() == null || notificationCompat$CarExtender$UnreadConversation0.getParticipants().length <= 1 ? null : notificationCompat$CarExtender$UnreadConversation0.getParticipants()[0];
            Parcelable[] arr_parcelable = new Parcelable[notificationCompat$CarExtender$UnreadConversation0.getMessages().length];
            for(int v = 0; v < arr_parcelable.length; ++v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("text", notificationCompat$CarExtender$UnreadConversation0.getMessages()[v]);
                bundle1.putString("author", s);
                arr_parcelable[v] = bundle1;
            }
            bundle0.putParcelableArray("messages", arr_parcelable);
            RemoteInput remoteInput0 = notificationCompat$CarExtender$UnreadConversation0.getRemoteInput();
            if(remoteInput0 != null) {
                bundle0.putParcelable("remote_input", new RemoteInput.Builder(remoteInput0.getResultKey()).setLabel(remoteInput0.getLabel()).setChoices(remoteInput0.getChoices()).setAllowFreeFormInput(remoteInput0.getAllowFreeFormInput()).addExtras(remoteInput0.getExtras()).build());
            }
            bundle0.putParcelable("on_reply", notificationCompat$CarExtender$UnreadConversation0.getReplyPendingIntent());
            bundle0.putParcelable("on_read", notificationCompat$CarExtender$UnreadConversation0.getReadPendingIntent());
            bundle0.putStringArray("participants", notificationCompat$CarExtender$UnreadConversation0.getParticipants());
            bundle0.putLong("timestamp", notificationCompat$CarExtender$UnreadConversation0.getLatestTimestamp());
            return bundle0;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        @RequiresApi(21)
        private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle bundle0) {
            String[] arr_s1;
            RemoteInput remoteInput0 = null;
            if(bundle0 == null) {
                return null;
            }
            Parcelable[] arr_parcelable = bundle0.getParcelableArray("messages");
            if(arr_parcelable == null) {
                arr_s1 = null;
            }
            else {
                String[] arr_s = new String[arr_parcelable.length];
                boolean z = false;
                for(int v = 0; true; ++v) {
                    if(v >= arr_s.length) {
                        z = true;
                        break;
                    }
                    if(!(arr_parcelable[v] instanceof Bundle)) {
                        break;
                    }
                    arr_s[v] = ((Bundle)arr_parcelable[v]).getString("text");
                    if(arr_s[v] == null) {
                        break;
                    }
                }
                if(z) {
                    arr_s1 = arr_s;
                    goto label_21;
                }
                return null;
            }
        label_21:
            Parcelable parcelable0 = bundle0.getParcelable("on_read");
            Parcelable parcelable1 = bundle0.getParcelable("on_reply");
            android.app.RemoteInput remoteInput1 = (android.app.RemoteInput)bundle0.getParcelable("remote_input");
            String[] arr_s2 = bundle0.getStringArray("participants");
            if(arr_s2 != null && arr_s2.length == 1) {
                if(remoteInput1 != null) {
                    remoteInput0 = new RemoteInput(remoteInput1.getResultKey(), remoteInput1.getLabel(), remoteInput1.getChoices(), remoteInput1.getAllowFreeFormInput(), remoteInput1.getExtras(), null);
                }
                return new UnreadConversation(arr_s1, remoteInput0, ((PendingIntent)parcelable1), ((PendingIntent)parcelable0), arr_s2, bundle0.getLong("timestamp"));
            }
            return null;
        }

        public CarExtender setColor(@ColorInt int v) {
            this.mColor = v;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap0) {
            this.mLargeIcon = bitmap0;
            return this;
        }

        public CarExtender setUnreadConversation(UnreadConversation notificationCompat$CarExtender$UnreadConversation0) {
            this.mUnreadConversation = notificationCompat$CarExtender$UnreadConversation0;
            return this;
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
            }
        }

        private RemoteViews createRemoteViews(RemoteViews remoteViews0, boolean z) {
            boolean z1 = true;
            int v = 0;
            RemoteViews remoteViews1 = this.applyStandardTemplate(true, layout.notification_template_custom_big, false);
            remoteViews1.removeAllViews(id.actions);
            if(!z || this.mBuilder.mActions == null) {
                z1 = false;
            }
            else {
                int v1 = Math.min(this.mBuilder.mActions.size(), 3);
                if(v1 > 0) {
                    for(int v2 = 0; v2 < v1; ++v2) {
                        RemoteViews remoteViews2 = this.generateActionButton(((Action)this.mBuilder.mActions.get(v2)));
                        remoteViews1.addView(id.actions, remoteViews2);
                    }
                }
                else {
                    z1 = false;
                }
            }
            if(!z1) {
                v = 8;
            }
            remoteViews1.setViewVisibility(id.actions, v);
            remoteViews1.setViewVisibility(id.action_divider, v);
            this.buildIntoRemoteViews(remoteViews1, remoteViews0);
            return remoteViews1;
        }

        private RemoteViews generateActionButton(Action notificationCompat$Action0) {
            boolean z = notificationCompat$Action0.actionIntent == null;
            RemoteViews remoteViews0 = new RemoteViews("com.esotericsoftware.gloomhavenhelper", (z ? layout.notification_action_tombstone : layout.notification_action));
            Bitmap bitmap0 = this.createColoredBitmap(notificationCompat$Action0.getIcon(), this.mBuilder.mContext.getResources().getColor(color.notification_action_color_filter));
            remoteViews0.setImageViewBitmap(id.action_image, bitmap0);
            remoteViews0.setTextViewText(id.action_text, notificationCompat$Action0.title);
            if(!z) {
                remoteViews0.setOnClickPendingIntent(id.action_container, notificationCompat$Action0.actionIntent);
            }
            if(Build.VERSION.SDK_INT >= 15) {
                remoteViews0.setContentDescription(id.action_container, notificationCompat$Action0.title);
            }
            return remoteViews0;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews remoteViews0 = this.mBuilder.getBigContentView();
            if(remoteViews0 == null) {
                remoteViews0 = this.mBuilder.getContentView();
            }
            return remoteViews0 == null ? null : this.createRemoteViews(remoteViews0, true);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            return this.mBuilder.getContentView() == null ? null : this.createRemoteViews(this.mBuilder.getContentView(), false);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews remoteViews0 = this.mBuilder.getHeadsUpContentView();
            return remoteViews0 == null ? null : this.createRemoteViews((remoteViews0 == null ? this.mBuilder.getContentView() : remoteViews0), true);
        }
    }

    public interface androidx.core.app.NotificationCompat.Extender {
        androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder arg1);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    public static class InboxStyle extends Style {
        private ArrayList mTexts;

        public InboxStyle() {
            this.mTexts = new ArrayList();
        }

        public InboxStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.mTexts = new ArrayList();
            this.setBuilder(notificationCompat$Builder0);
        }

        public InboxStyle addLine(CharSequence charSequence0) {
            this.mTexts.add(androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0));
            return this;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 16) {
                Notification.InboxStyle notification$InboxStyle0 = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(this.mBigContentTitle);
                if(this.mSummaryTextSet) {
                    notification$InboxStyle0.setSummaryText(this.mSummaryText);
                }
                for(Object object0: this.mTexts) {
                    notification$InboxStyle0.addLine(((CharSequence)object0));
                }
            }
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence0) {
            this.mBigContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence0) {
            this.mSummaryText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public static class MessagingStyle extends Style {
        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            @Nullable
            private String mDataMimeType;
            @Nullable
            private Uri mDataUri;
            private Bundle mExtras;
            @Nullable
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence0, long v, @Nullable Person person0) {
                this.mExtras = new Bundle();
                this.mText = charSequence0;
                this.mTimestamp = v;
                this.mPerson = person0;
            }

            @Deprecated
            public Message(CharSequence charSequence0, long v, CharSequence charSequence1) {
                this(charSequence0, v, new androidx.core.app.Person.Builder().setName(charSequence1).build());
            }

            @NonNull
            static Bundle[] getBundleArrayForMessages(List list0) {
                Bundle[] arr_bundle = new Bundle[list0.size()];
                int v = list0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    arr_bundle[v1] = ((Message)list0.get(v1)).toBundle();
                }
                return arr_bundle;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            static Message getMessageFromBundle(Bundle bundle0) {
                Person person0;
                try {
                    if(bundle0.containsKey("text") && bundle0.containsKey("time")) {
                        if(bundle0.containsKey("person")) {
                            person0 = Person.fromBundle(bundle0.getBundle("person"));
                        }
                        else if(bundle0.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
                            person0 = Person.fromAndroidPerson(((android.app.Person)bundle0.getParcelable("sender_person")));
                        }
                        else {
                            person0 = bundle0.containsKey("sender") ? new androidx.core.app.Person.Builder().setName(bundle0.getCharSequence("sender")).build() : null;
                        }
                        Message notificationCompat$MessagingStyle$Message0 = new Message(bundle0.getCharSequence("text"), bundle0.getLong("time"), person0);
                        if(bundle0.containsKey("type") && bundle0.containsKey("uri")) {
                            notificationCompat$MessagingStyle$Message0.setData(bundle0.getString("type"), ((Uri)bundle0.getParcelable("uri")));
                        }
                        if(bundle0.containsKey("extras")) {
                            notificationCompat$MessagingStyle$Message0.getExtras().putAll(bundle0.getBundle("extras"));
                        }
                        return notificationCompat$MessagingStyle$Message0;
                    }
                }
                catch(ClassCastException unused_ex) {
                }
                return null;
            }

            @NonNull
            static List getMessagesFromBundleArray(Parcelable[] arr_parcelable) {
                List list0 = new ArrayList(arr_parcelable.length);
                for(int v = 0; v < arr_parcelable.length; ++v) {
                    if(arr_parcelable[v] instanceof Bundle) {
                        Message notificationCompat$MessagingStyle$Message0 = Message.getMessageFromBundle(((Bundle)arr_parcelable[v]));
                        if(notificationCompat$MessagingStyle$Message0 != null) {
                            list0.add(notificationCompat$MessagingStyle$Message0);
                        }
                    }
                }
                return list0;
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                return this.mPerson == null ? null : this.mPerson.getName();
            }

            @NonNull
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String s, Uri uri0) {
                this.mDataMimeType = s;
                this.mDataUri = uri0;
                return this;
            }

            private Bundle toBundle() {
                Bundle bundle0 = new Bundle();
                CharSequence charSequence0 = this.mText;
                if(charSequence0 != null) {
                    bundle0.putCharSequence("text", charSequence0);
                }
                bundle0.putLong("time", this.mTimestamp);
                Person person0 = this.mPerson;
                if(person0 != null) {
                    bundle0.putCharSequence("sender", person0.getName());
                    if(Build.VERSION.SDK_INT >= 28) {
                        bundle0.putParcelable("sender_person", this.mPerson.toAndroidPerson());
                    }
                    else {
                        bundle0.putBundle("person", this.mPerson.toBundle());
                    }
                }
                String s = this.mDataMimeType;
                if(s != null) {
                    bundle0.putString("type", s);
                }
                Uri uri0 = this.mDataUri;
                if(uri0 != null) {
                    bundle0.putParcelable("uri", uri0);
                }
                Bundle bundle1 = this.mExtras;
                if(bundle1 != null) {
                    bundle0.putBundle("extras", bundle1);
                }
                return bundle0;
            }
        }

        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        @Nullable
        private CharSequence mConversationTitle;
        @Nullable
        private Boolean mIsGroupConversation;
        private final List mMessages;
        private Person mUser;

        private MessagingStyle() {
            this.mMessages = new ArrayList();
        }

        public MessagingStyle(@NonNull Person person0) {
            this.mMessages = new ArrayList();
            if(TextUtils.isEmpty(person0.getName())) {
                throw new IllegalArgumentException("User\'s name must not be empty.");
            }
            this.mUser = person0;
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence charSequence0) {
            this.mMessages = new ArrayList();
            this.mUser = new androidx.core.app.Person.Builder().setName(charSequence0).build();
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void addCompatExtras(Bundle bundle0) {
            super.addCompatExtras(bundle0);
            bundle0.putCharSequence("android.selfDisplayName", this.mUser.getName());
            bundle0.putBundle("android.messagingStyleUser", this.mUser.toBundle());
            bundle0.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
            if(this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                bundle0.putCharSequence("android.conversationTitle", this.mConversationTitle);
            }
            if(!this.mMessages.isEmpty()) {
                bundle0.putParcelableArray("android.messages", Message.getBundleArrayForMessages(this.mMessages));
            }
            Boolean boolean0 = this.mIsGroupConversation;
            if(boolean0 != null) {
                bundle0.putBoolean("android.isGroupConversation", boolean0.booleanValue());
            }
        }

        public MessagingStyle addMessage(Message notificationCompat$MessagingStyle$Message0) {
            this.mMessages.add(notificationCompat$MessagingStyle$Message0);
            if(this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence charSequence0, long v, Person person0) {
            this.addMessage(new Message(charSequence0, v, person0));
            return this;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence0, long v, CharSequence charSequence1) {
            Message notificationCompat$MessagingStyle$Message0 = new Message(charSequence0, v, new androidx.core.app.Person.Builder().setName(charSequence1).build());
            this.mMessages.add(notificationCompat$MessagingStyle$Message0);
            if(this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            Notification.MessagingStyle.Message notification$MessagingStyle$Message0;
            this.setGroupConversation(this.isGroupConversation());
            if(Build.VERSION.SDK_INT >= 24) {
                Notification.MessagingStyle notification$MessagingStyle0 = Build.VERSION.SDK_INT < 28 ? new Notification.MessagingStyle(this.mUser.getName()) : new Notification.MessagingStyle(this.mUser.toAndroidPerson());
                if(this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28) {
                    notification$MessagingStyle0.setConversationTitle(this.mConversationTitle);
                }
                if(Build.VERSION.SDK_INT >= 28) {
                    notification$MessagingStyle0.setGroupConversation(this.mIsGroupConversation.booleanValue());
                }
                for(Object object0: this.mMessages) {
                    Message notificationCompat$MessagingStyle$Message0 = (Message)object0;
                    if(Build.VERSION.SDK_INT >= 28) {
                        Person person0 = notificationCompat$MessagingStyle$Message0.getPerson();
                        notification$MessagingStyle$Message0 = new Notification.MessagingStyle.Message(notificationCompat$MessagingStyle$Message0.getText(), notificationCompat$MessagingStyle$Message0.getTimestamp(), (person0 == null ? null : person0.toAndroidPerson()));
                    }
                    else {
                        notification$MessagingStyle$Message0 = new Notification.MessagingStyle.Message(notificationCompat$MessagingStyle$Message0.getText(), notificationCompat$MessagingStyle$Message0.getTimestamp(), (notificationCompat$MessagingStyle$Message0.getPerson() == null ? null : notificationCompat$MessagingStyle$Message0.getPerson().getName()));
                    }
                    if(notificationCompat$MessagingStyle$Message0.getDataMimeType() != null) {
                        notification$MessagingStyle$Message0.setData(notificationCompat$MessagingStyle$Message0.getDataMimeType(), notificationCompat$MessagingStyle$Message0.getDataUri());
                    }
                    notification$MessagingStyle0.addMessage(notification$MessagingStyle$Message0);
                }
                notification$MessagingStyle0.setBuilder(notificationBuilderWithBuilderAccessor0.getBuilder());
                return;
            }
            Message notificationCompat$MessagingStyle$Message1 = this.findLatestIncomingMessage();
            if(this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setContentTitle(this.mConversationTitle);
            }
            else if(notificationCompat$MessagingStyle$Message1 != null) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setContentTitle("");
                if(notificationCompat$MessagingStyle$Message1.getPerson() != null) {
                    notificationBuilderWithBuilderAccessor0.getBuilder().setContentTitle(notificationCompat$MessagingStyle$Message1.getPerson().getName());
                }
            }
            if(notificationCompat$MessagingStyle$Message1 != null) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setContentText((this.mConversationTitle == null ? notificationCompat$MessagingStyle$Message1.getText() : this.makeMessageLine(notificationCompat$MessagingStyle$Message1)));
            }
            if(Build.VERSION.SDK_INT >= 16) {
                SpannableStringBuilder spannableStringBuilder0 = new SpannableStringBuilder();
                boolean z = this.mConversationTitle != null || this.hasMessagesWithoutSender();
                for(int v = this.mMessages.size() - 1; v >= 0; --v) {
                    Message notificationCompat$MessagingStyle$Message2 = (Message)this.mMessages.get(v);
                    CharSequence charSequence0 = z ? this.makeMessageLine(notificationCompat$MessagingStyle$Message2) : notificationCompat$MessagingStyle$Message2.getText();
                    if(v != this.mMessages.size() - 1) {
                        spannableStringBuilder0.insert(0, "\n");
                    }
                    spannableStringBuilder0.insert(0, charSequence0);
                }
                new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(null).bigText(spannableStringBuilder0);
            }
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification0) {
            Bundle bundle0 = NotificationCompat.getExtras(notification0);
            if(bundle0 != null && !bundle0.containsKey("android.selfDisplayName") && !bundle0.containsKey("android.messagingStyleUser")) {
                return null;
            }
            try {
                MessagingStyle notificationCompat$MessagingStyle0 = new MessagingStyle();
                notificationCompat$MessagingStyle0.restoreFromCompatExtras(bundle0);
                return notificationCompat$MessagingStyle0;
            }
            catch(ClassCastException unused_ex) {
                return null;
            }
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            for(int v = this.mMessages.size() - 1; v >= 0; --v) {
                Message notificationCompat$MessagingStyle$Message0 = (Message)this.mMessages.get(v);
                if(notificationCompat$MessagingStyle$Message0.getPerson() != null && !TextUtils.isEmpty(notificationCompat$MessagingStyle$Message0.getPerson().getName())) {
                    return notificationCompat$MessagingStyle$Message0;
                }
            }
            return this.mMessages.isEmpty() ? null : ((Message)this.mMessages.get(this.mMessages.size() - 1));
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public List getMessages() {
            return this.mMessages;
        }

        public Person getUser() {
            return this.mUser;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        private boolean hasMessagesWithoutSender() {
            for(int v = this.mMessages.size() - 1; v >= 0; --v) {
                Message notificationCompat$MessagingStyle$Message0 = (Message)this.mMessages.get(v);
                if(notificationCompat$MessagingStyle$Message0.getPerson() != null && notificationCompat$MessagingStyle$Message0.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        public boolean isGroupConversation() {
            return this.mIsGroupConversation == null ? false : this.mIsGroupConversation.booleanValue();
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int v) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(v), null);
        }

        private CharSequence makeMessageLine(Message notificationCompat$MessagingStyle$Message0) {
            BidiFormatter bidiFormatter0 = BidiFormatter.getInstance();
            CharSequence charSequence0 = new SpannableStringBuilder();
            boolean z = Build.VERSION.SDK_INT >= 21;
            int v = z ? 0xFF000000 : -1;
            CharSequence charSequence1 = notificationCompat$MessagingStyle$Message0.getPerson() == null ? "" : notificationCompat$MessagingStyle$Message0.getPerson().getName();
            if(TextUtils.isEmpty(charSequence1)) {
                charSequence1 = this.mUser.getName();
                if(z && this.mBuilder.getColor() != 0) {
                    v = this.mBuilder.getColor();
                }
            }
            CharSequence charSequence2 = bidiFormatter0.unicodeWrap(charSequence1);
            ((SpannableStringBuilder)charSequence0).append(charSequence2);
            ((SpannableStringBuilder)charSequence0).setSpan(this.makeFontColorSpan(v), ((SpannableStringBuilder)charSequence0).length() - charSequence2.length(), ((SpannableStringBuilder)charSequence0).length(), 33);
            CharSequence charSequence3 = notificationCompat$MessagingStyle$Message0.getText() == null ? "" : notificationCompat$MessagingStyle$Message0.getText();
            ((SpannableStringBuilder)charSequence0).append("  ").append(bidiFormatter0.unicodeWrap(charSequence3));
            return charSequence0;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        @RestrictTo({Scope.LIBRARY_GROUP})
        protected void restoreFromCompatExtras(Bundle bundle0) {
            this.mMessages.clear();
            this.mUser = bundle0.containsKey("android.messagingStyleUser") ? Person.fromBundle(bundle0.getBundle("android.messagingStyleUser")) : new androidx.core.app.Person.Builder().setName(bundle0.getString("android.selfDisplayName")).build();
            this.mConversationTitle = bundle0.getCharSequence("android.conversationTitle");
            if(this.mConversationTitle == null) {
                this.mConversationTitle = bundle0.getCharSequence("android.hiddenConversationTitle");
            }
            Parcelable[] arr_parcelable = bundle0.getParcelableArray("android.messages");
            if(arr_parcelable != null) {
                List list0 = Message.getMessagesFromBundleArray(arr_parcelable);
                this.mMessages.addAll(list0);
            }
            if(bundle0.containsKey("android.isGroupConversation")) {
                this.mIsGroupConversation = Boolean.valueOf(bundle0.getBoolean("android.isGroupConversation"));
            }
        }

        public MessagingStyle setConversationTitle(@Nullable CharSequence charSequence0) {
            this.mConversationTitle = charSequence0;
            return this;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = Boolean.valueOf(z);
            return this;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    public static abstract class Style {
        CharSequence mBigContentTitle;
        @RestrictTo({Scope.LIBRARY_GROUP})
        protected androidx.core.app.NotificationCompat.Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;

        public Style() {
            this.mSummaryTextSet = false;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle bundle0) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews applyStandardTemplate(boolean z, int v, boolean z1) {
            boolean z5;
            boolean z3;
            Resources resources0 = this.mBuilder.mContext.getResources();
            RemoteViews remoteViews0 = new RemoteViews("com.esotericsoftware.gloomhavenhelper", v);
            int v1 = 0;
            boolean z2 = this.mBuilder.getPriority() < -1;
            if(Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
                if(z2) {
                    remoteViews0.setInt(id.notification_background, "setBackgroundResource", drawable.notification_bg_low);
                    remoteViews0.setInt(id.icon, "setBackgroundResource", drawable.notification_template_icon_low_bg);
                }
                else {
                    remoteViews0.setInt(id.notification_background, "setBackgroundResource", drawable.notification_bg);
                    remoteViews0.setInt(id.icon, "setBackgroundResource", drawable.notification_template_icon_bg);
                }
            }
            if(this.mBuilder.mLargeIcon != null) {
                if(Build.VERSION.SDK_INT >= 16) {
                    remoteViews0.setViewVisibility(id.icon, 0);
                    remoteViews0.setImageViewBitmap(id.icon, this.mBuilder.mLargeIcon);
                }
                else {
                    remoteViews0.setViewVisibility(id.icon, 8);
                }
                if(z && this.mBuilder.mNotification.icon != 0) {
                    int v2 = resources0.getDimensionPixelSize(dimen.notification_right_icon_size);
                    int v3 = resources0.getDimensionPixelSize(dimen.notification_small_icon_background_padding);
                    if(Build.VERSION.SDK_INT >= 21) {
                        Bitmap bitmap0 = this.createIconWithBackground(this.mBuilder.mNotification.icon, v2, v2 - v3 * 2, this.mBuilder.getColor());
                        remoteViews0.setImageViewBitmap(id.right_icon, bitmap0);
                    }
                    else {
                        Bitmap bitmap1 = this.createColoredBitmap(this.mBuilder.mNotification.icon, -1);
                        remoteViews0.setImageViewBitmap(id.right_icon, bitmap1);
                    }
                    remoteViews0.setViewVisibility(id.right_icon, 0);
                }
            }
            else if(z && this.mBuilder.mNotification.icon != 0) {
                remoteViews0.setViewVisibility(id.icon, 0);
                if(Build.VERSION.SDK_INT >= 21) {
                    int v4 = resources0.getDimensionPixelSize(dimen.notification_large_icon_width);
                    int v5 = resources0.getDimensionPixelSize(dimen.notification_big_circle_margin);
                    int v6 = resources0.getDimensionPixelSize(dimen.notification_small_icon_size_as_large);
                    Bitmap bitmap2 = this.createIconWithBackground(this.mBuilder.mNotification.icon, v4 - v5, v6, this.mBuilder.getColor());
                    remoteViews0.setImageViewBitmap(id.icon, bitmap2);
                }
                else {
                    Bitmap bitmap3 = this.createColoredBitmap(this.mBuilder.mNotification.icon, -1);
                    remoteViews0.setImageViewBitmap(id.icon, bitmap3);
                }
            }
            if(this.mBuilder.mContentTitle != null) {
                remoteViews0.setTextViewText(id.title, this.mBuilder.mContentTitle);
            }
            if(this.mBuilder.mContentText == null) {
                z3 = false;
            }
            else {
                remoteViews0.setTextViewText(id.text, this.mBuilder.mContentText);
                z3 = true;
            }
            boolean z4 = Build.VERSION.SDK_INT < 21 && this.mBuilder.mLargeIcon != null;
            if(this.mBuilder.mContentInfo != null) {
                remoteViews0.setTextViewText(id.info, this.mBuilder.mContentInfo);
                remoteViews0.setViewVisibility(id.info, 0);
                z3 = true;
                z4 = true;
            }
            else if(this.mBuilder.mNumber > 0) {
                int v7 = resources0.getInteger(integer.status_bar_notification_info_maxnum);
                if(this.mBuilder.mNumber > v7) {
                    String s = resources0.getString(string.status_bar_notification_info_overflow);
                    remoteViews0.setTextViewText(id.info, s);
                }
                else {
                    String s1 = NumberFormat.getIntegerInstance().format(((long)this.mBuilder.mNumber));
                    remoteViews0.setTextViewText(id.info, s1);
                }
                remoteViews0.setViewVisibility(id.info, 0);
                z3 = true;
                z4 = true;
            }
            else {
                remoteViews0.setViewVisibility(id.info, 8);
            }
            if(this.mBuilder.mSubText == null || Build.VERSION.SDK_INT < 16) {
                z5 = false;
            }
            else {
                remoteViews0.setTextViewText(id.text, this.mBuilder.mSubText);
                if(this.mBuilder.mContentText == null) {
                    remoteViews0.setViewVisibility(id.text2, 8);
                    z5 = false;
                }
                else {
                    remoteViews0.setTextViewText(id.text2, this.mBuilder.mContentText);
                    remoteViews0.setViewVisibility(id.text2, 0);
                    z5 = true;
                }
            }
            if(z5 && Build.VERSION.SDK_INT >= 16) {
                if(z1) {
                    float f = (float)resources0.getDimensionPixelSize(dimen.notification_subtext_size);
                    remoteViews0.setTextViewTextSize(id.text, 0, f);
                }
                remoteViews0.setViewPadding(id.line1, 0, 0, 0, 0);
            }
            if(this.mBuilder.getWhenIfShowing() != 0L) {
                if(!this.mBuilder.mUseChronometer || Build.VERSION.SDK_INT < 16) {
                    remoteViews0.setViewVisibility(id.time, 0);
                    long v10 = this.mBuilder.getWhenIfShowing();
                    remoteViews0.setLong(id.time, "setTime", v10);
                }
                else {
                    remoteViews0.setViewVisibility(id.chronometer, 0);
                    long v8 = this.mBuilder.getWhenIfShowing();
                    long v9 = SystemClock.elapsedRealtime();
                    remoteViews0.setLong(id.chronometer, "setBase", v8 + (v9 - System.currentTimeMillis()));
                    remoteViews0.setBoolean(id.chronometer, "setStarted", true);
                }
                z4 = true;
            }
            remoteViews0.setViewVisibility(0x7F050027, (z4 ? 0 : 8));  // id:right_side
            if(!z3) {
                v1 = 8;
            }
            remoteViews0.setViewVisibility(0x7F05001F, v1);  // id:line3
            return remoteViews0;
        }

        public Notification build() {
            return this.mBuilder == null ? null : this.mBuilder.build();
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews remoteViews0, RemoteViews remoteViews1) {
            this.hideNormalContent(remoteViews0);
            remoteViews0.removeAllViews(id.notification_main_column);
            RemoteViews remoteViews2 = remoteViews1.clone();
            remoteViews0.addView(id.notification_main_column, remoteViews2);
            remoteViews0.setViewVisibility(id.notification_main_column, 0);
            if(Build.VERSION.SDK_INT >= 21) {
                int v = this.calculateTopPadding();
                remoteViews0.setViewPadding(id.notification_main_column_container, 0, v, 0, 0);
            }
        }

        private int calculateTopPadding() {
            Resources resources0 = this.mBuilder.mContext.getResources();
            int v = resources0.getDimensionPixelSize(dimen.notification_top_pad);
            int v1 = resources0.getDimensionPixelSize(dimen.notification_top_pad_large_text);
            float f = (Style.constrain(resources0.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.3f;
            return Math.round((1.0f - f) * ((float)v) + f * ((float)v1));
        }

        private static float constrain(float f, float f1, float f2) {
            if(f < f1) {
                return f1;
            }
            return f > f2 ? f2 : f;
        }

        private Bitmap createColoredBitmap(int v, int v1, int v2) {
            Drawable drawable0 = this.mBuilder.mContext.getResources().getDrawable(v);
            int v3 = v2 == 0 ? drawable0.getIntrinsicWidth() : v2;
            if(v2 == 0) {
                v2 = drawable0.getIntrinsicHeight();
            }
            Bitmap bitmap0 = Bitmap.createBitmap(v3, v2, Bitmap.Config.ARGB_8888);
            drawable0.setBounds(0, 0, v3, v2);
            if(v1 != 0) {
                drawable0.mutate().setColorFilter(new PorterDuffColorFilter(v1, PorterDuff.Mode.SRC_IN));
            }
            drawable0.draw(new Canvas(bitmap0));
            return bitmap0;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int v, int v1) {
            return this.createColoredBitmap(v, v1, 0);
        }

        private Bitmap createIconWithBackground(int v, int v1, int v2, int v3) {
            if(v3 == 0) {
                v3 = 0;
            }
            Bitmap bitmap0 = this.createColoredBitmap(0x7F040008, v3, v1);  // drawable:notification_icon_background
            Canvas canvas0 = new Canvas(bitmap0);
            Drawable drawable0 = this.mBuilder.mContext.getResources().getDrawable(v).mutate();
            drawable0.setFilterBitmap(true);
            int v4 = (v1 - v2) / 2;
            drawable0.setBounds(v4, v4, v2 + v4, v2 + v4);
            drawable0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            drawable0.draw(canvas0);
            return bitmap0;
        }

        private void hideNormalContent(RemoteViews remoteViews0) {
            remoteViews0.setViewVisibility(id.title, 8);
            remoteViews0.setViewVisibility(id.text2, 8);
            remoteViews0.setViewVisibility(id.text, 8);
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        protected void restoreFromCompatExtras(Bundle bundle0) {
        }

        public void setBuilder(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            if(this.mBuilder != notificationCompat$Builder0) {
                this.mBuilder = notificationCompat$Builder0;
                androidx.core.app.NotificationCompat.Builder notificationCompat$Builder1 = this.mBuilder;
                if(notificationCompat$Builder1 != null) {
                    notificationCompat$Builder1.setStyle(this);
                }
            }
        }
    }

    public static final class androidx.core.app.NotificationCompat.WearableExtender implements androidx.core.app.NotificationCompat.Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 0x800005;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 0x20;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 0x40;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList mPages;

        public androidx.core.app.NotificationCompat.WearableExtender() {
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            this.mContentIconGravity = 0x800005;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        public androidx.core.app.NotificationCompat.WearableExtender(Notification notification0) {
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            this.mContentIconGravity = 0x800005;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            Bundle bundle0 = NotificationCompat.getExtras(notification0);
            Bundle bundle1 = bundle0 == null ? null : bundle0.getBundle("android.wearable.EXTENSIONS");
            if(bundle1 != null) {
                ArrayList arrayList0 = bundle1.getParcelableArrayList("actions");
                if(Build.VERSION.SDK_INT >= 16 && arrayList0 != null) {
                    Action[] arr_notificationCompat$Action = new Action[arrayList0.size()];
                    for(int v = 0; v < arr_notificationCompat$Action.length; ++v) {
                        if(Build.VERSION.SDK_INT >= 20) {
                            arr_notificationCompat$Action[v] = NotificationCompat.getActionCompatFromAction(((Notification.Action)arrayList0.get(v)));
                        }
                        else if(Build.VERSION.SDK_INT >= 16) {
                            arr_notificationCompat$Action[v] = NotificationCompatJellybean.getActionFromBundle(((Bundle)arrayList0.get(v)));
                        }
                    }
                    Collections.addAll(this.mActions, arr_notificationCompat$Action);
                }
                this.mFlags = bundle1.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent)bundle1.getParcelable("displayIntent");
                Notification[] arr_notification = NotificationCompat.getNotificationArrayFromBundle(bundle1, "pages");
                if(arr_notification != null) {
                    Collections.addAll(this.mPages, arr_notification);
                }
                this.mBackground = (Bitmap)bundle1.getParcelable("background");
                this.mContentIcon = bundle1.getInt("contentIcon");
                this.mContentIconGravity = bundle1.getInt("contentIconGravity", 0x800005);
                this.mContentActionIndex = bundle1.getInt("contentActionIndex", -1);
                this.mCustomSizePreset = bundle1.getInt("customSizePreset", 0);
                this.mCustomContentHeight = bundle1.getInt("customContentHeight");
                this.mGravity = bundle1.getInt("gravity", 80);
                this.mHintScreenTimeout = bundle1.getInt("hintScreenTimeout");
                this.mDismissalId = bundle1.getString("dismissalId");
                this.mBridgeTag = bundle1.getString("bridgeTag");
            }
        }

        public androidx.core.app.NotificationCompat.WearableExtender addAction(Action notificationCompat$Action0) {
            this.mActions.add(notificationCompat$Action0);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender addActions(List list0) {
            this.mActions.addAll(list0);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender addPage(Notification notification0) {
            this.mPages.add(notification0);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender addPages(List list0) {
            this.mPages.addAll(list0);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender clone() {
            androidx.core.app.NotificationCompat.WearableExtender notificationCompat$WearableExtender0 = new androidx.core.app.NotificationCompat.WearableExtender();
            notificationCompat$WearableExtender0.mActions = new ArrayList(this.mActions);
            notificationCompat$WearableExtender0.mFlags = this.mFlags;
            notificationCompat$WearableExtender0.mDisplayIntent = this.mDisplayIntent;
            notificationCompat$WearableExtender0.mPages = new ArrayList(this.mPages);
            notificationCompat$WearableExtender0.mBackground = this.mBackground;
            notificationCompat$WearableExtender0.mContentIcon = this.mContentIcon;
            notificationCompat$WearableExtender0.mContentIconGravity = this.mContentIconGravity;
            notificationCompat$WearableExtender0.mContentActionIndex = this.mContentActionIndex;
            notificationCompat$WearableExtender0.mCustomSizePreset = this.mCustomSizePreset;
            notificationCompat$WearableExtender0.mCustomContentHeight = this.mCustomContentHeight;
            notificationCompat$WearableExtender0.mGravity = this.mGravity;
            notificationCompat$WearableExtender0.mHintScreenTimeout = this.mHintScreenTimeout;
            notificationCompat$WearableExtender0.mDismissalId = this.mDismissalId;
            notificationCompat$WearableExtender0.mBridgeTag = this.mBridgeTag;
            return notificationCompat$WearableExtender0;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        @Override  // androidx.core.app.NotificationCompat$Extender
        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            Bundle bundle0 = new Bundle();
            if(!this.mActions.isEmpty()) {
                if(Build.VERSION.SDK_INT >= 16) {
                    ArrayList arrayList0 = new ArrayList(this.mActions.size());
                    for(Object object0: this.mActions) {
                        Action notificationCompat$Action0 = (Action)object0;
                        if(Build.VERSION.SDK_INT >= 20) {
                            arrayList0.add(androidx.core.app.NotificationCompat.WearableExtender.getActionFromActionCompat(notificationCompat$Action0));
                        }
                        else if(Build.VERSION.SDK_INT >= 16) {
                            arrayList0.add(NotificationCompatJellybean.getBundleForAction(notificationCompat$Action0));
                        }
                    }
                    bundle0.putParcelableArrayList("actions", arrayList0);
                }
                else {
                    bundle0.putParcelableArrayList("actions", null);
                }
            }
            int v = this.mFlags;
            if(v != 1) {
                bundle0.putInt("flags", v);
            }
            PendingIntent pendingIntent0 = this.mDisplayIntent;
            if(pendingIntent0 != null) {
                bundle0.putParcelable("displayIntent", pendingIntent0);
            }
            if(!this.mPages.isEmpty()) {
                bundle0.putParcelableArray("pages", ((Parcelable[])this.mPages.toArray(new Notification[this.mPages.size()])));
            }
            Bitmap bitmap0 = this.mBackground;
            if(bitmap0 != null) {
                bundle0.putParcelable("background", bitmap0);
            }
            int v1 = this.mContentIcon;
            if(v1 != 0) {
                bundle0.putInt("contentIcon", v1);
            }
            int v2 = this.mContentIconGravity;
            if(v2 != 0x800005) {
                bundle0.putInt("contentIconGravity", v2);
            }
            int v3 = this.mContentActionIndex;
            if(v3 != -1) {
                bundle0.putInt("contentActionIndex", v3);
            }
            int v4 = this.mCustomSizePreset;
            if(v4 != 0) {
                bundle0.putInt("customSizePreset", v4);
            }
            int v5 = this.mCustomContentHeight;
            if(v5 != 0) {
                bundle0.putInt("customContentHeight", v5);
            }
            int v6 = this.mGravity;
            if(v6 != 80) {
                bundle0.putInt("gravity", v6);
            }
            int v7 = this.mHintScreenTimeout;
            if(v7 != 0) {
                bundle0.putInt("hintScreenTimeout", v7);
            }
            String s = this.mDismissalId;
            if(s != null) {
                bundle0.putString("dismissalId", s);
            }
            String s1 = this.mBridgeTag;
            if(s1 != null) {
                bundle0.putString("bridgeTag", s1);
            }
            notificationCompat$Builder0.getExtras().putBundle("android.wearable.EXTENSIONS", bundle0);
            return notificationCompat$Builder0;
        }

        @RequiresApi(20)
        private static Notification.Action getActionFromActionCompat(Action notificationCompat$Action0) {
            Notification.Action.Builder notification$Action$Builder0 = new Notification.Action.Builder(notificationCompat$Action0.getIcon(), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent());
            Bundle bundle0 = notificationCompat$Action0.getExtras() == null ? new Bundle() : new Bundle(notificationCompat$Action0.getExtras());
            bundle0.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action0.getAllowGeneratedReplies());
            if(Build.VERSION.SDK_INT >= 24) {
                notification$Action$Builder0.setAllowGeneratedReplies(notificationCompat$Action0.getAllowGeneratedReplies());
            }
            notification$Action$Builder0.addExtras(bundle0);
            RemoteInput[] arr_remoteInput = notificationCompat$Action0.getRemoteInputs();
            if(arr_remoteInput != null) {
                android.app.RemoteInput[] arr_remoteInput1 = RemoteInput.fromCompat(arr_remoteInput);
                for(int v = 0; v < arr_remoteInput1.length; ++v) {
                    notification$Action$Builder0.addRemoteInput(arr_remoteInput1[v]);
                }
            }
            return notification$Action$Builder0.build();
        }

        public List getActions() {
            return this.mActions;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 0x20) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 0x40) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        public List getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setBackground(Bitmap bitmap0) {
            this.mBackground = bitmap0;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setBridgeTag(String s) {
            this.mBridgeTag = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setContentAction(int v) {
            this.mContentActionIndex = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setContentIcon(int v) {
            this.mContentIcon = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setContentIconGravity(int v) {
            this.mContentIconGravity = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setContentIntentAvailableOffline(boolean z) {
            this.setFlag(1, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setCustomContentHeight(int v) {
            this.mCustomContentHeight = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setCustomSizePreset(int v) {
            this.mCustomSizePreset = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setDismissalId(String s) {
            this.mDismissalId = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setDisplayIntent(PendingIntent pendingIntent0) {
            this.mDisplayIntent = pendingIntent0;
            return this;
        }

        private void setFlag(int v, boolean z) {
            if(z) {
                this.mFlags |= v;
                return;
            }
            this.mFlags &= ~v;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setGravity(int v) {
            this.mGravity = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setHintAmbientBigPicture(boolean z) {
            this.setFlag(0x20, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            this.setFlag(16, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            this.setFlag(0x40, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintHideIcon(boolean z) {
            this.setFlag(2, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintScreenTimeout(int v) {
            this.mHintScreenTimeout = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintShowBackgroundOnly(boolean z) {
            this.setFlag(4, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setStartScrollBottom(boolean z) {
            this.setFlag(8, z);
            return this;
        }
    }

    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 0x40;
    public static final int FLAG_GROUP_SUMMARY = 0x200;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 0x80;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 0x100;
    public static final int FLAG_NO_CLEAR = 0x20;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static Action getAction(Notification notification0, int v) {
        Bundle bundle0 = null;
        if(Build.VERSION.SDK_INT >= 20) {
            return NotificationCompat.getActionCompatFromAction(notification0.actions[v]);
        }
        if(Build.VERSION.SDK_INT >= 19) {
            Notification.Action notification$Action0 = notification0.actions[v];
            SparseArray sparseArray0 = notification0.extras.getSparseParcelableArray("android.support.actionExtras");
            if(sparseArray0 != null) {
                bundle0 = (Bundle)sparseArray0.get(v);
            }
            return NotificationCompatJellybean.readAction(notification$Action0.icon, notification$Action0.title, notification$Action0.actionIntent, bundle0);
        }
        return Build.VERSION.SDK_INT < 16 ? null : NotificationCompatJellybean.getAction(notification0, v);
    }

    @RequiresApi(20)
    static Action getActionCompatFromAction(Notification.Action notification$Action0) {
        int v1;
        RemoteInput[] arr_remoteInput1;
        android.app.RemoteInput[] arr_remoteInput = notification$Action0.getRemoteInputs();
        if(arr_remoteInput == null) {
            arr_remoteInput1 = null;
        }
        else {
            RemoteInput[] arr_remoteInput2 = new RemoteInput[arr_remoteInput.length];
            for(int v = 0; v < arr_remoteInput.length; ++v) {
                android.app.RemoteInput remoteInput0 = arr_remoteInput[v];
                arr_remoteInput2[v] = new RemoteInput(remoteInput0.getResultKey(), remoteInput0.getLabel(), remoteInput0.getChoices(), remoteInput0.getAllowFreeFormInput(), remoteInput0.getExtras(), null);
            }
            arr_remoteInput1 = arr_remoteInput2;
        }
        boolean z = Build.VERSION.SDK_INT < 24 ? notification$Action0.getExtras().getBoolean("android.support.allowGeneratedReplies") : notification$Action0.getExtras().getBoolean("android.support.allowGeneratedReplies") || notification$Action0.getAllowGeneratedReplies();
        boolean z1 = notification$Action0.getExtras().getBoolean("android.support.action.showsUserInterface", true);
        if(Build.VERSION.SDK_INT >= 28) {
            v1 = notification$Action0.getSemanticAction();
            return new Action(notification$Action0.icon, notification$Action0.title, notification$Action0.actionIntent, notification$Action0.getExtras(), arr_remoteInput1, null, z, v1, z1);
        }
        v1 = notification$Action0.getExtras().getInt("android.support.action.semanticAction", 0);
        return new Action(notification$Action0.icon, notification$Action0.title, notification$Action0.actionIntent, notification$Action0.getExtras(), arr_remoteInput1, null, z, v1, z1);
    }

    public static int getActionCount(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 19) {
            return notification0.actions == null ? 0 : notification0.actions.length;
        }
        return Build.VERSION.SDK_INT < 16 ? 0 : NotificationCompatJellybean.getActionCount(notification0);
    }

    public static int getBadgeIconType(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? 0 : notification0.getBadgeIconType();
    }

    public static String getCategory(Notification notification0) {
        return Build.VERSION.SDK_INT < 21 ? null : notification0.category;
    }

    public static String getChannelId(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? null : notification0.getChannelId();
    }

    @RequiresApi(19)
    public static CharSequence getContentTitle(Notification notification0) {
        return notification0.extras.getCharSequence("android.title");
    }

    @Nullable
    public static Bundle getExtras(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 19) {
            return notification0.extras;
        }
        return Build.VERSION.SDK_INT < 16 ? null : NotificationCompatJellybean.getExtras(notification0);
    }

    public static String getGroup(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 20) {
            return notification0.getGroup();
        }
        if(Build.VERSION.SDK_INT >= 19) {
            return notification0.extras.getString("android.support.groupKey");
        }
        return Build.VERSION.SDK_INT < 16 ? null : NotificationCompatJellybean.getExtras(notification0).getString("android.support.groupKey");
    }

    public static int getGroupAlertBehavior(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? 0 : notification0.getGroupAlertBehavior();
    }

    @RequiresApi(21)
    public static List getInvisibleActions(Notification notification0) {
        List list0 = new ArrayList();
        Bundle bundle0 = notification0.extras.getBundle("android.car.EXTENSIONS");
        if(bundle0 == null) {
            return list0;
        }
        Bundle bundle1 = bundle0.getBundle("invisible_actions");
        if(bundle1 != null) {
            for(int v = 0; v < bundle1.size(); ++v) {
                ((ArrayList)list0).add(NotificationCompatJellybean.getActionFromBundle(bundle1.getBundle(Integer.toString(v))));
            }
        }
        return list0;
    }

    public static boolean getLocalOnly(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 20) {
            return (notification0.flags & 0x100) != 0;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            return notification0.extras.getBoolean("android.support.localOnly");
        }
        return Build.VERSION.SDK_INT < 16 ? false : NotificationCompatJellybean.getExtras(notification0).getBoolean("android.support.localOnly");
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle0, String s) {
        Parcelable[] arr_parcelable = bundle0.getParcelableArray(s);
        if(!(arr_parcelable instanceof Notification[]) && arr_parcelable != null) {
            Notification[] arr_notification = new Notification[arr_parcelable.length];
            for(int v = 0; v < arr_parcelable.length; ++v) {
                arr_notification[v] = (Notification)arr_parcelable[v];
            }
            bundle0.putParcelableArray(s, arr_notification);
            return arr_notification;
        }
        return (Notification[])arr_parcelable;
    }

    public static String getShortcutId(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? null : notification0.getShortcutId();
    }

    public static String getSortKey(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 20) {
            return notification0.getSortKey();
        }
        if(Build.VERSION.SDK_INT >= 19) {
            return notification0.extras.getString("android.support.sortKey");
        }
        return Build.VERSION.SDK_INT < 16 ? null : NotificationCompatJellybean.getExtras(notification0).getString("android.support.sortKey");
    }

    public static long getTimeoutAfter(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? 0L : notification0.getTimeoutAfter();
    }

    public static boolean isGroupSummary(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 20) {
            return (notification0.flags & 0x200) != 0;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            return notification0.extras.getBoolean("android.support.isGroupSummary");
        }
        return Build.VERSION.SDK_INT < 16 ? false : NotificationCompatJellybean.getExtras(notification0).getBoolean("android.support.isGroupSummary");
    }
}

