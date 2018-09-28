package com.example.manjyot.app1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.EditTextPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import java.util.List;

public class SettingsActivity extends AppCompatPreferenceActivity {

    private AppCompatDelegate mDelegate;

    //Global variables
    private static TasksApplication app;
    private static Context context;

    private static String globalTheme;

    //Account preference fields
    private static EditTextPreference prefEditName;
    private static EditTextPreference prefEditEmail;
    private static EditTextPreference prefEditPass;
    private static SwitchPreference prefSwitchEmail;
    private static MultiSelectListPreference prefListEmail;

    //Notification preference fields
    private static SwitchPreference prefNotifStatus;
    private static RingtonePreference prefRingtone;
    private static SwitchPreference prefNotifVibrateStatus;

    //Toast
    private static int toastDuration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();


        //Retrieving currently signed in details
        TasksApplication app = (TasksApplication) this.getApplication();
        context = app.getContext();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);

    }

    protected boolean isValidFragment(String fragmentName) {
        return SettingsFragmentAccount.class.getName().equals(fragmentName)
                || SettingsFragmentNotifications.class.getName().equals(fragmentName)
                || SettingsFragmentAbout.class.getName().equals(fragmentName)
                || SettingsFragmentHelp.class.getName().equals(fragmentName)
                || SettingsFragmentLogout.class.getName().equals(fragmentName);
    }

    public static class SettingsFragmentAccount extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            app = (TasksApplication) context.getApplicationContext();
            addPreferencesFromResource(R.xml.pref_account);
            setHasOptionsMenu(true);

            //Retrieving the account preference fields
            prefEditName = (EditTextPreference) findPreference("account_name");
            prefEditEmail = (EditTextPreference) findPreference("account_email");
            prefEditPass = (EditTextPreference) findPreference("account_pass");
            prefSwitchEmail = (SwitchPreference) findPreference("account_emailsubstatus");
            prefListEmail = (MultiSelectListPreference) findPreference("account_emailsublist");

            //Assigning current name, email and email status values
            prefEditName.setSummary(app.getSignedInName());
            prefEditName.setDefaultValue("Enter name here");
            prefEditEmail.setSummary(app.getSignedInEmail());
            prefSwitchEmail.setChecked(app.getEmailSubStatus());

            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();


            editor.putString("account_name", app.getSignedInName());
            editor.commit();

            //Edit name listener
            prefEditName.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    String newName = (String) o;
                    if (!newName.equals("")) {
                        prefEditName.setSummary(newName);
                        app.setSignedInName(newName);
                        NavigationActivity.navDrawName.setText(newName);

                        //Toast
                        CharSequence toastNameText = "Display name changed to " + newName;
                        Toast toastName = Toast.makeText(context, toastNameText, toastDuration);
                        toastName.show();
                        return true;
                    }
                    return false;
                }
            });

            //Edit email listener
            prefEditEmail.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    String newEmail = (String) o;
                    if (!newEmail.equals("")) {
                        prefEditEmail.setSummary(newEmail);
                        app.setSignedInEmail(newEmail);
                        NavigationActivity.navDrawName.setText(newEmail);

                        //Toast
                        CharSequence toastEmailText = "Email changed to " + newEmail;
                        Toast toastEmail = Toast.makeText(context, toastEmailText, toastDuration);
                        toastEmail.show();
                        return true;
                    }
                    return false;
                }
            });

            prefEditPass.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    //Toast
                    CharSequence toastPassText = "Password updated";
                    Toast toastPass = Toast.makeText(context, toastPassText, toastDuration);
                    toastPass.show();
                    return true;
                }
            });

            //Edit email status listener
            prefSwitchEmail.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    boolean newEmailStatus = (boolean) o;
                    if (newEmailStatus == true) {
                        prefSwitchEmail.setSummary("You're signed up for email updates from us!");
                        prefListEmail.setEnabled(true);
                        app.setEmailSubStatus(true);

                        //Toast
                        CharSequence toastEmailSubOnText = "Email subscription ON";
                        Toast toastEmailSub = Toast.makeText(context, toastEmailSubOnText, toastDuration);
                        toastEmailSub.show();
                    } else {
                        prefSwitchEmail.setSummary("You're not signed up for email updates from us.");
                        prefListEmail.setEnabled(false);
                        app.setEmailSubStatus(false);

                        //Toast
                        CharSequence toastEmailSubOffText = "Email subscription OFF";
                        Toast toastEmailSub = Toast.makeText(context, toastEmailSubOffText, toastDuration);
                        toastEmailSub.show();
                    }
                    return true;
                }
            });

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


    }

    public static class SettingsFragmentNotifications extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            app = (TasksApplication) context.getApplicationContext();
            addPreferencesFromResource(R.xml.pref_notifications);
            setHasOptionsMenu(true);

            //Retrieving preference fields
            prefNotifStatus = (SwitchPreference) findPreference("notif_enabled");
            prefRingtone = (RingtonePreference) findPreference("ringtone_preference_1");
            prefNotifVibrateStatus = (SwitchPreference) findPreference("vibrate_enabled");

            prefNotifStatus.setChecked(app.getNotifStatus());
            prefRingtone.setDefaultValue(app.getNotifRingtone());
            prefNotifVibrateStatus.setChecked(app.getNotifVibrateStatus());

            //Assigning current ringtone summary

            prefRingtone.setSummary(RingtoneManager.getRingtone(app.getContext(), app.getNotifRingtone()).getTitle(app.getContext()));

            prefNotifStatus.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    boolean newNotifEnabledStatus = (boolean) o;
                    if (newNotifEnabledStatus == true) {
                        prefRingtone.setEnabled(true);
                        prefNotifVibrateStatus.setEnabled(true);
                        app.setNotifStatus(true);

                        //Toast
                        CharSequence toastNotifText = "Notifications ON";
                        Toast toastNotif = Toast.makeText(context, toastNotifText, toastDuration);
                        toastNotif.show();
                    } else {
                        prefRingtone.setEnabled(false);
                        prefNotifVibrateStatus.setEnabled(false);
                        app.setNotifStatus(false);

                        //Toast
                        CharSequence toastNotifText = "Notifications OFF";
                        Toast toastNotif = Toast.makeText(context, toastNotifText, toastDuration);
                        toastNotif.show();
                    }
                    return true;
                }
            });

            prefRingtone.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    Uri newRingtone = Uri.parse(o.toString());
                    app.setNotifRingtone(newRingtone);
                    prefRingtone.setSummary((RingtoneManager.getRingtone(app.getContext(), newRingtone)).getTitle(app.getContext()));

                    //Toast
                    CharSequence toastRingtoneText = "Ringtone set as " + prefRingtone.getSummary();
                    Toast toastRingtone = Toast.makeText(context, toastRingtoneText, toastDuration);
                    toastRingtone.show();
                    return true;
                }
            });

            prefNotifVibrateStatus.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                    CharSequence toastVibrateText;
                    boolean newVibrateStatus = (boolean) o;
                    if (newVibrateStatus == true) {
                        app.setNotifVibrateStatus(true);
                        toastVibrateText = "Vibrate ON";
                    } else {
                        app.setNotifVibrateStatus(false);
                        toastVibrateText = "Vibrate OFF";
                    }

                    //Toast
                    Toast toastVibrate = Toast.makeText(context, toastVibrateText, toastDuration);
                    toastVibrate.show();
                    return true;
                }
            });
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class SettingsFragmentHelp extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            app = (TasksApplication) context.getApplicationContext();
            addPreferencesFromResource(R.xml.pref_help);
            setHasOptionsMenu(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class SettingsFragmentAbout extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            app = (TasksApplication) context.getApplicationContext();
            addPreferencesFromResource(R.xml.pref_about);
            setHasOptionsMenu(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class SettingsFragmentLogout extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
