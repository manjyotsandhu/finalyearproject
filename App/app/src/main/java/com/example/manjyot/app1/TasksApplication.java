package com.example.manjyot.app1;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by manjyot on 16/03/2018.
 */

public class TasksApplication extends Application {

    private static Context context;

    private String signedInName = "John";
    private String signedInEmail = "example@email.com";
    private boolean emailSubStatus = true;

    private boolean notifStatus = true;
    private Uri notifRingtone = Settings.System.DEFAULT_RINGTONE_URI;
    private boolean notifVibrateStatus = true;

    public void onCreate() {
        super.onCreate();
        TasksApplication.context = getApplicationContext();
        resetVariables();
    }

    public void resetVariables() {
        this.signedInName = "John";
        this.signedInEmail = "example@email.com";
        this.emailSubStatus = true;

        this.notifStatus = true;
        this.notifRingtone = Settings.System.DEFAULT_RINGTONE_URI;
        this.notifVibrateStatus = true;
    }

    public void logIn(String name, String email) {
        this.signedInName = name;
        this.signedInEmail = email;
        this.emailSubStatus = true;

        this.notifStatus = true;
        this.notifRingtone = Settings.System.DEFAULT_RINGTONE_URI;
        this.notifVibrateStatus = true;
    }

    public static Context getContext() {
        return TasksApplication.context;
    }

    public String getSignedInName() {
        return signedInName;
    }

    public void setSignedInName(String name) {
        signedInName = name;
    }

    public String getSignedInEmail() {
        return signedInEmail;
    }

    public void setSignedInEmail(String email) {
        signedInEmail = email;
    }

    public boolean getEmailSubStatus() {
        return emailSubStatus;
    }

    public void setEmailSubStatus(boolean emailSubStatus) {
        this.emailSubStatus = emailSubStatus;
    }

    public boolean getNotifStatus() {
        return notifStatus;
    }

    public void setNotifStatus(boolean notifStatus) {
        this.notifStatus = notifStatus;
    }

    public Uri getNotifRingtone() {
        return notifRingtone;
    }

    public void setNotifRingtone(Uri notifRingtone) {
        this.notifRingtone = notifRingtone;
    }

    public boolean getNotifVibrateStatus() {
        return notifVibrateStatus;
    }

    public void setNotifVibrateStatus(boolean notifVibrateStatus) {
        this.notifVibrateStatus = notifVibrateStatus;
    }

}
