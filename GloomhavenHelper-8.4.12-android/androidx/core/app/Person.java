package androidx.core.app;

import android.app.Person.Builder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

public class Person {
    public static class Builder {
        @Nullable
        IconCompat mIcon;
        boolean mIsBot;
        boolean mIsImportant;
        @Nullable
        String mKey;
        @Nullable
        CharSequence mName;
        @Nullable
        String mUri;

        public Builder() {
        }

        Builder(Person person0) {
            this.mName = person0.mName;
            this.mIcon = person0.mIcon;
            this.mUri = person0.mUri;
            this.mKey = person0.mKey;
            this.mIsBot = person0.mIsBot;
            this.mIsImportant = person0.mIsImportant;
        }

        @NonNull
        public Person build() {
            return new Person(this);
        }

        @NonNull
        public Builder setBot(boolean z) {
            this.mIsBot = z;
            return this;
        }

        @NonNull
        public Builder setIcon(@Nullable IconCompat iconCompat0) {
            this.mIcon = iconCompat0;
            return this;
        }

        @NonNull
        public Builder setImportant(boolean z) {
            this.mIsImportant = z;
            return this;
        }

        @NonNull
        public Builder setKey(@Nullable String s) {
            this.mKey = s;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence0) {
            this.mName = charSequence0;
            return this;
        }

        @NonNull
        public Builder setUri(@Nullable String s) {
            this.mUri = s;
            return this;
        }
    }

    private static final String ICON_KEY = "icon";
    private static final String IS_BOT_KEY = "isBot";
    private static final String IS_IMPORTANT_KEY = "isImportant";
    private static final String KEY_KEY = "key";
    private static final String NAME_KEY = "name";
    private static final String URI_KEY = "uri";
    @Nullable
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;
    @Nullable
    String mKey;
    @Nullable
    CharSequence mName;
    @Nullable
    String mUri;

    Person(Builder person$Builder0) {
        this.mName = person$Builder0.mName;
        this.mIcon = person$Builder0.mIcon;
        this.mUri = person$Builder0.mUri;
        this.mKey = person$Builder0.mKey;
        this.mIsBot = person$Builder0.mIsBot;
        this.mIsImportant = person$Builder0.mIsImportant;
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Person fromAndroidPerson(@NonNull android.app.Person person0) {
        Builder person$Builder0 = new Builder().setName(person0.getName());
        return person0.getIcon() == null ? person$Builder0.setIcon(null).setUri(person0.getUri()).setKey(person0.getKey()).setBot(person0.isBot()).setImportant(person0.isImportant()).build() : person$Builder0.setIcon(IconCompat.createFromIcon(person0.getIcon())).setUri(person0.getUri()).setKey(person0.getKey()).setBot(person0.isBot()).setImportant(person0.isImportant()).build();
    }

    @NonNull
    public static Person fromBundle(@NonNull Bundle bundle0) {
        Bundle bundle1 = bundle0.getBundle("icon");
        Builder person$Builder0 = new Builder().setName(bundle0.getCharSequence("name"));
        return bundle1 == null ? person$Builder0.setIcon(null).setUri(bundle0.getString("uri")).setKey(bundle0.getString("key")).setBot(bundle0.getBoolean("isBot")).setImportant(bundle0.getBoolean("isImportant")).build() : person$Builder0.setIcon(IconCompat.createFromBundle(bundle1)).setUri(bundle0.getString("uri")).setKey(bundle0.getString("key")).setBot(bundle0.getBoolean("isBot")).setImportant(bundle0.getBoolean("isImportant")).build();
    }

    @Nullable
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getKey() {
        return this.mKey;
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    @Nullable
    public String getUri() {
        return this.mUri;
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public android.app.Person toAndroidPerson() {
        Person.Builder person$Builder0 = new Person.Builder().setName(this.getName());
        return this.getIcon() == null ? person$Builder0.setIcon(null).setUri(this.getUri()).setKey(this.getKey()).setBot(this.isBot()).setImportant(this.isImportant()).build() : person$Builder0.setIcon(this.getIcon().toIcon()).setUri(this.getUri()).setKey(this.getKey()).setBot(this.isBot()).setImportant(this.isImportant()).build();
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle0 = new Bundle();
        bundle0.putCharSequence("name", this.mName);
        bundle0.putBundle("icon", (this.mIcon == null ? null : this.mIcon.toBundle()));
        bundle0.putString("uri", this.mUri);
        bundle0.putString("key", this.mKey);
        bundle0.putBoolean("isBot", this.mIsBot);
        bundle0.putBoolean("isImportant", this.mIsImportant);
        return bundle0;
    }
}

