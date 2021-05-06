package zerodev.p074_preferencescode;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class PrefActivity extends PreferenceActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(rootScreen);

        CheckBoxPreference cnb1 = new CheckBoxPreference(this);
        cnb1.setKey("cnb1");
        cnb1.setTitle("Checkbox 1");
        cnb1.setSummaryOn("Description of checkbox 1 on");
        cnb1.setSummaryOff("Description of checkbox 1 off");
        rootScreen.addPreference(cnb1);

        ListPreference list = new ListPreference(this);
        list.setKey("list");
        list.setTitle("List");
        list.setSummary("Description of list");
        list.setEntries(R.array.entries);
        list.setEntryValues(R.array.entry_values);
        rootScreen.addPreference(list);

        CheckBoxPreference cnb2 = new CheckBoxPreference(this);
        cnb2.setKey("cnb2");
        cnb2.setTitle("CheckBox 2");
        cnb2.setSummary("Description of checkbox 2");
        rootScreen.addPreference(cnb2);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);
        screen.setKey("screen");
        screen.setTitle("Screen");
        screen.setSummary("Description of checkbox 3");

        final CheckBoxPreference cnb3 = new CheckBoxPreference(this);
        cnb3.setKey("cnb3");
        cnb3.setTitle("CheckBox 3");
        cnb3.setSummary("Description of cjeckbox 3");
        screen.addPreference(cnb3);

        PreferenceCategory categ1 = new PreferenceCategory(this);
        categ1.setKey("categ1");
        categ1.setTitle("Category 1");
        categ1.setSummary("Description of category 1");
        screen.addPreference(categ1);

        CheckBoxPreference cnb4 = new CheckBoxPreference(this);
        cnb4.setKey("cnb4");
        cnb4.setTitle("Checkbox 4");
        cnb4.setSummary("Description of checkbox 4");
        categ1.addPreference(cnb4);

        final PreferenceCategory categ2 = new PreferenceCategory(this);
        categ2.setKey("categ2");
        categ2.setTitle("Category 2");
        categ2.setSummary("Description of category 2");
        screen.addPreference(categ2);

        CheckBoxPreference cnb5 = new CheckBoxPreference(this);
        cnb5.setKey("cnb5");
        cnb5.setTitle("cnb5");
        cnb5.setSummary("Description of Checkbox 5");
        screen.addPreference(cnb5);

        rootScreen.addPreference(screen);
        list.setDependency("cnb1");
        screen.setDependency("cnb2");

        categ2.setEnabled(cnb3.isChecked());
        cnb3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(cnb3.isChecked());
                return false;
            }
        });
    }
}
