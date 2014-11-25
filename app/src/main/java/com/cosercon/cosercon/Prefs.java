package com.cosercon.cosercon;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by adrian.badarau on 11/25/2014.
 */
public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
