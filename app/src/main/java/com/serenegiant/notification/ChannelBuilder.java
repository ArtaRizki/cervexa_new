package com.serenegiant.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.media.AudioAttributes;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import com.serenegiant.utils.BuildCheck;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class ChannelBuilder {
    private static final boolean DEBUG = false;
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    public static final Set<Integer> IMPORTANCE;
    public static final Set<Integer> NOTIFICATION_VISIBILITY;
    private static final String TAG = ChannelBuilder.class.getSimpleName();
    private int argb;
    private AudioAttributes audioAttributes;
    private boolean bypassDnd;
    private String channelId;
    private boolean createIfExists;
    private String description;
    private String groupId;
    private String groupName;
    private int importance;
    private boolean lights;
    private int lockscreenVisibility;
    private final Context mContext;
    private CharSequence name;
    private boolean showBadge;
    private Uri sound;
    private boolean vibration;
    private long[] vibrationPattern;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Importance {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    protected NotificationChannel setupNotificationChannel(NotificationChannel notificationChannel) {
        return notificationChannel;
    }

    protected NotificationChannelGroup setupNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup) {
        return notificationChannelGroup;
    }

    static {
        HashSet hashSet = new HashSet();
        IMPORTANCE = hashSet;
        Collections.addAll(hashSet, Integer.valueOf(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED), 0, 1, 2, 3, 4);
        HashSet hashSet2 = new HashSet();
        NOTIFICATION_VISIBILITY = hashSet2;
        Collections.addAll(hashSet2, 1, 0, -1);
    }

    public static ChannelBuilder getBuilder(Context context, String str) {
        NotificationChannel notificationChannel = ((NotificationManager) context.getSystemService("notification")).getNotificationChannel(str);
        if (notificationChannel != null) {
            ChannelBuilder channelBuilder = new ChannelBuilder(context, str, notificationChannel.getName(), notificationChannel.getImportance());
            channelBuilder.setLockscreenVisibility(notificationChannel.getLockscreenVisibility()).setBypassDnd(notificationChannel.canBypassDnd()).setShowBadge(notificationChannel.canShowBadge()).setDescription(notificationChannel.getDescription()).setLightColor(notificationChannel.getLightColor()).setVibrationPattern(notificationChannel.getVibrationPattern()).enableLights(notificationChannel.shouldShowLights()).enableVibration(notificationChannel.shouldVibrate()).setSound(notificationChannel.getSound(), notificationChannel.getAudioAttributes()).setGroup(notificationChannel.getGroup(), null).setCreateIfExists(true);
            return channelBuilder;
        }
        return new ChannelBuilder(context, str, null, 0);
    }

    public static List<String> updateNotificationChannel(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    readEntryOne(context, xml, arrayList);
                }
            }
        } catch (IOException e) {
            Log.d(TAG, "IOException", e);
        } catch (XmlPullParserException e2) {
            Log.d(TAG, "XmlPullParserException", e2);
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void readEntryOne(android.content.Context r21, org.xmlpull.v1.XmlPullParser r22, java.util.List<java.lang.String> r23) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 652
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        return;
    }

    public ChannelBuilder(Context context) {
        this(context, DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_ID, 0, null, null);
    }

    public ChannelBuilder(Context context, String str, CharSequence charSequence, int i) {
        this(context, str, charSequence, i, null, null);
    }

    public ChannelBuilder(Context context, String str, CharSequence charSequence, int i, String str2, String str3) {
        this.channelId = DEFAULT_CHANNEL_ID;
        this.importance = 0;
        this.lockscreenVisibility = 0;
        this.bypassDnd = false;
        this.showBadge = true;
        this.argb = 0;
        this.createIfExists = true;
        this.mContext = context;
        this.channelId = TextUtils.isEmpty(str) ? DEFAULT_CHANNEL_ID : str;
        this.name = charSequence;
        this.importance = i;
        this.groupId = str2;
        this.groupName = str3;
    }

    public String toString() {
        return "ChannelBuilder{channelId='" + this.channelId + "', name=" + ((Object) this.name) + ", importance=" + this.importance + ", lockscreenVisibility=" + this.lockscreenVisibility + ", bypassDnd=" + this.bypassDnd + ", showBadge=" + this.showBadge + ", description='" + this.description + "', argb=" + this.argb + ", lights=" + this.lights + ", vibrationPattern=" + Arrays.toString(this.vibrationPattern) + ", vibration=" + this.vibration + ", sound=" + this.sound + ", audioAttributes=" + this.audioAttributes + ", groupId='" + this.groupId + "', groupName='" + this.groupName + "', createIfExists=" + this.createIfExists + '}';
    }

    public NotificationChannel build() {
        if (BuildCheck.isOreo()) {
            return createNotificationChannel(this.mContext);
        }
        return null;
    }

    public ChannelBuilder setId(String str) {
        if (TextUtils.isEmpty(str)) {
            str = DEFAULT_CHANNEL_ID;
        }
        this.channelId = str;
        return this;
    }

    public String getId() {
        return this.channelId;
    }

    public ChannelBuilder setName(CharSequence charSequence) {
        this.name = charSequence;
        return this;
    }

    public CharSequence getName() {
        return this.name;
    }

    public ChannelBuilder setImportance(int i) {
        this.importance = i;
        return this;
    }

    public int getImportance() {
        return this.importance;
    }

    public ChannelBuilder setLockscreenVisibility(int i) {
        this.lockscreenVisibility = i;
        return this;
    }

    public int getLockscreenVisibility() {
        return this.lockscreenVisibility;
    }

    public ChannelBuilder setBypassDnd(boolean z) {
        this.bypassDnd = z;
        return this;
    }

    public boolean canBypassDnd() {
        return this.bypassDnd;
    }

    public ChannelBuilder setDescription(String str) {
        this.description = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public ChannelBuilder setLightColor(int i) {
        this.argb = i;
        return this;
    }

    public int getLightColor() {
        return this.argb;
    }

    public ChannelBuilder enableLights(boolean z) {
        this.lights = z;
        return this;
    }

    public boolean shouldShowLights() {
        return this.lights;
    }

    public ChannelBuilder setShowBadge(boolean z) {
        this.showBadge = z;
        return this;
    }

    public boolean canShowBadge() {
        return this.showBadge;
    }

    public ChannelBuilder setVibrationPattern(long[] jArr) {
        this.vibration = jArr != null && jArr.length > 0;
        this.vibrationPattern = jArr;
        return this;
    }

    public long[] getVibrationPattern() {
        return this.vibrationPattern;
    }

    public ChannelBuilder enableVibration(boolean z) {
        this.vibration = z;
        return this;
    }

    public boolean shouldVibrate() {
        long[] jArr;
        return this.vibration && (jArr = this.vibrationPattern) != null && jArr.length > 0;
    }

    public ChannelBuilder setSound(Uri uri, AudioAttributes audioAttributes) {
        this.sound = uri;
        this.audioAttributes = audioAttributes;
        return this;
    }

    public Uri getSound() {
        return this.sound;
    }

    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public ChannelBuilder setGroup(String str, String str2) {
        this.groupId = str;
        this.groupName = str2;
        return this;
    }

    public String getGroup() {
        return this.groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public ChannelBuilder setCreateIfExists(boolean z) {
        this.createIfExists = z;
        return this;
    }

    public boolean isCreateIfExists() {
        return this.createIfExists;
    }

    protected NotificationChannel createNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(this.channelId);
        if (this.createIfExists || notificationChannel == null) {
            if (this.importance == 0) {
                this.importance = 3;
            }
            if (notificationChannel == null) {
                notificationChannel = new NotificationChannel(this.channelId, this.name, this.importance);
            }
            notificationChannel.setImportance(this.importance);
            notificationChannel.setLockscreenVisibility(this.lockscreenVisibility);
            notificationChannel.setBypassDnd(this.bypassDnd);
            notificationChannel.setShowBadge(this.showBadge);
            notificationChannel.setLightColor(this.argb);
            notificationChannel.enableLights(this.lights);
            notificationChannel.setVibrationPattern(this.vibrationPattern);
            notificationChannel.enableVibration(this.vibration);
            notificationChannel.setSound(this.sound, this.audioAttributes);
        }
        if (notificationChannel != null) {
            if (!TextUtils.isEmpty(this.groupId)) {
                createNotificationChannelGroup(context, this.groupId, this.groupName);
            }
            notificationChannel.setName(this.name);
            notificationChannel.setDescription(this.description);
            notificationChannel.setGroup(this.groupId);
            notificationManager.createNotificationChannel(setupNotificationChannel(notificationChannel));
        }
        return notificationChannel;
    }

    protected void createNotificationChannelGroup(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        NotificationChannelGroup notificationChannelGroup = null;
        Iterator<NotificationChannelGroup> it = notificationManager.getNotificationChannelGroups().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            NotificationChannelGroup next = it.next();
            if (str.equals(next.getId())) {
                notificationChannelGroup = next;
                break;
            }
        }
        if (notificationChannelGroup == null) {
            if (TextUtils.isEmpty(str2)) {
                str2 = str;
            }
            notificationManager.createNotificationChannelGroup(setupNotificationChannelGroup(new NotificationChannelGroup(str, str2)));
        }
    }
}

