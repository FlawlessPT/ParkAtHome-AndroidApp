package pt.park_at_home.parkathome.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.*;
import android.preference.Preference.OnPreferenceChangeListener;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;

import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.managers.Admin;
import pt.park_at_home.parkathome.managers.Matriculas;
import pt.park_at_home.parkathome.utils.AppCompatPreferenceActivity;
import pt.park_at_home.parkathome.utils.MaxNumberMatriculasFile;
import pt.park_at_home.parkathome.utils.SimpleAlert;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity
{

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = (preference, value) ->
    {
        String stringValue = value.toString();

        if (preference instanceof ListPreference)
        {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);

            // Set the summary to reflect the new value.
            preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);

        }
        else
        {
            // For all other preferences, set the summary to the value's
            // simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context)
    {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference)
    {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            if (!super.onMenuItemSelected(featureId, item))
            {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane()
    {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName)
    {
        return PreferenceFragment.class.getName().equals(fragmentName) || SettingsLoginFragment.class.getName().equals(fragmentName); //|| MaxNumberMatriculasFragment.class.getName().equals(fragmentName);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class SettingsLoginFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_settings_login);
            setHasOptionsMenu(true);
            EditTextPreference username = (EditTextPreference) findPreference("username");
            EditTextPreference password = (EditTextPreference) findPreference("password");
            Admin admin = new Admin(getActivity());
            username.setText(admin.getUsername());
            password.setText(admin.getPassword());
            username.setSummary(admin.getUsername());
            password.setSummary(admin.getPassword());
        }

        @Override
        public void onResume()
        {
            EditTextPreference username = (EditTextPreference) findPreference("username");
            EditTextPreference password = (EditTextPreference) findPreference("password");

            username.setOnPreferenceChangeListener((preference, newValue) ->
            {
                try
                {
                    Admin admin = new Admin(getActivity());
                    username.setSummary(username.getEditText().getText());
                    admin.setUsername(username.getEditText().getText().toString());
                    username.setText(admin.getUsername());
                } catch (Exception e)
                {
                    SimpleAlert alert = new SimpleAlert(getActivity());
                    alert.setMessage(e.getMessage());
                    alert.show();
                }

                return false;
            });

            password.setOnPreferenceChangeListener((preference, newValue) ->
            {
                try
                {
                    Admin admin = new Admin(getActivity());
                    password.setSummary(password.getEditText().getText());
                    admin.setPassword(password.getEditText().getText().toString());
                    password.setText(admin.getPassword());
                } catch (Exception e)
                {
                    SimpleAlert alert = new SimpleAlert(getActivity());
                    alert.setMessage(e.getMessage());
                    alert.show();
                }

                return false;
            });
            super.onResume();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
            int id = item.getItemId();
            if (id == android.R.id.home)
            {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class MaxNumberMatriculasFragment extends PreferenceFragment
//    {
//        @Override
//        public void onCreate(Bundle savedInstanceState)
//        {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.pref_max_number_matriculas);
//            setHasOptionsMenu(true);
//            EditTextPreference max = (EditTextPreference) findPreference("max_number_matriculas");
//            Matriculas matriculas = new Matriculas(getActivity());
//            max.setText(String.valueOf(matriculas.getMaxNumberMatriculas()));
//            max.setSummary(String.valueOf(matriculas.getMaxNumberMatriculas()));
//        }
//
//        @Override
//        public void onResume()
//        {
//            EditTextPreference max = (EditTextPreference) findPreference("max_number_matriculas");
//
//            max.setOnPreferenceChangeListener((preference, newValue) ->
//            {
//                try
//                {
//                    Matriculas matriculas = new Matriculas(getActivity());
//                    matriculas.setMaxNumberMatriculas(Integer.parseInt(max.getEditText().getText().toString()));
//                    max.setText(max.getEditText().getText().toString());
//                    max.setSummary(max.getEditText().getText().toString());
//                } catch (Exception e)
//                {
//                    SimpleAlert alert = new SimpleAlert(getActivity());
//                    alert.setMessage(e.getMessage());
//                    alert.show();
//                }
//
//                return false;
//            });
//            super.onResume();
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item)
//        {
//            int id = item.getItemId();
//            if (id == android.R.id.home)
//            {
//                startActivity(new Intent(getActivity(), SettingsActivity.class));
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
}
