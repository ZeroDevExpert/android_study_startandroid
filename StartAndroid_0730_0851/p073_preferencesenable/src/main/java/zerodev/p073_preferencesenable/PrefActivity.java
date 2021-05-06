package zerodev.p073_preferencesenable;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.util.Log;

public class PrefActivity extends PreferenceActivity{
    CheckBoxPreference cnb3;
    PreferenceCategory categ2;

    final static String LOG_TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
        Log.d(LOG_TAG,"Pref activity on create");


        cnb3 = (CheckBoxPreference) findPreference("cnb3");
        categ2 = (PreferenceCategory) findPreference("categ2");
        categ2.setEnabled(cnb3.isChecked());

        cnb3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
             public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(cnb3.isChecked());
                return false;
            }
        });
    }
}
