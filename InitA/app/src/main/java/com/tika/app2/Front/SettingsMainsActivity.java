package com.tika.app2.Front;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.tika.app2.R;

public class SettingsMainsActivity extends PreferenceActivity {

    private static OnPreferenceChangeListener prefsListener = (pref, value) -> {
        String valueString = value.toString();

        if (pref instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) pref;
            int index = listPreference.findIndexOfValue(valueString);

            pref.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
        }
        return true;
    };

    // ----------------------------------------------------
    // Android Lifecycle
    // ----------------------------------------------------



    //-----------------------------------------------------
    // Preferences related methods
    //-----------------------------------------------------
    private static void bindPreferenceSummaryToValue(Preference pref) {
        pref.setOnPreferenceChangeListener(prefsListener);

        prefsListener.onPreferenceChange(pref, PreferenceManager
                .getDefaultSharedPreferences(pref.getContext())
                .getString(pref.getKey(), ""));
    }

    // ----------------------------------------------------
    // Helper functions
    // ----------------------------------------------------
    private void setToolbar() {
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.toolbar, root, false);
        root.addView(toolbar, 0);
        toolbar.setTitle(R.string.settings);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}