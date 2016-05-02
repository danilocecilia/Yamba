package com.example.dcecilia.yamelapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by dcecilia on 28/04/2016.
 */
public class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }
}
