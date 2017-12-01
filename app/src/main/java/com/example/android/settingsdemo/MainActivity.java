package com.example.android.settingsdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


//TODO:2. Create a new Settings activity (DONE)
//Select the directory that contains the main activity then New, Activity, Empty Activity and call it SettingsActivity

//TODO:3. Create a new menu resource directory (DONE)
//Select the res directory then New, Android resource directory call it menu and set resource type to menu

//TODO:4. Create a new nenu resource file (DONE)
//Select the res directory then New, menu resource file and call it settings_menu
//TODO: STAGE 2 ADD A SETTINGS FRAGMENT(DONE)
//TODO:12. Create a new java class called SettingsFragment(DONE)
//Select the directory that contains the main activity then New, Java Class and call it SettingsFragment
//TODO:13. Create a new xml resource directory (DONE)
//Select the res directory then New, Android resource directory call it xml and set resource type to xml
//TODO:14. Create a new pref xml file (DONE)
//Select the xml resource directory then New, XML resource file and call it pref_Settings
//TODO:20 Create a new arrays xml file(DONE)
//Select the values directory then New, values resource file and call it values


//TODO:23.implement SharedPreferences.OnSharedPreferenceChangeListener (DONE)
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);

        setUpDefaultSetting();
    }

    //TODO:7.Override onCreateOptionsMenu() and inflate the settings_menu resource (DONE)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our visualizer_menu layout to this menu */
        inflater.inflate(R.menu.settings_menu, menu);
        /* Return true so that the visualizer_menu is displayed in the Toolbar */
        return true;
    }

    //TODO:8.Override onOptionsItemSelected() check selected item id is Action_settings create an intent to show the SettingsActivity (DONE)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO:22.Add a method to apply the settings (DONE)
    //The hard coded strings in this method should be in the strings resource in a production app
    public void setUpDefaultSetting() {


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //TODO:24.registerOnSharedPreferenceChangeListener (DONE)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);


        Boolean isHidden = (sharedPreferences.getBoolean("showTextKey", false));
        if (isHidden == true) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
        String textViewText = sharedPreferences.getString("changeTextKey", "Hello World!");
        textView.setText(textViewText);
        String textColour = sharedPreferences.getString("colourKey", "RED");
        if (textColour.equals("RED")) {
            textView.setTextColor(Color.parseColor("#B71C1C"));
        } else if (textColour.equals("BLUE")) {
            textView.setTextColor(Color.parseColor("#3F51B5"));
        } else {
            textView.setTextColor(Color.parseColor("#1B5E20"));
        }
    }

    //TODO:25.override the OnSharedPreferenceChange method (DONE)
    // this updates ui as soon as there is a settings change
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals("showTextKey")) {
            Boolean isHidden = (sharedPreferences.getBoolean("showTextKey", false));
            if (isHidden == true) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
            }
        } else if (s.equals("changeTextKey")) {
            String textViewText = sharedPreferences.getString("changeTextKey", "Hello World!");
            textView.setText(textViewText);

        } else if (s.equals("colourKey")) {
            String textColour = sharedPreferences.getString("colourKey", "RED");
            if (textColour.equals("RED")) {
                textView.setTextColor(Color.parseColor("#B71C1C"));
            } else if (textColour.equals("BLUE")) {
                textView.setTextColor(Color.parseColor("#3F51B5"));
            } else {
                textView.setTextColor(Color.parseColor("#1B5E20"));
            }

        }

    }

    //TODO:26.clean up override the on destroy and unregisterOnSharedPreferenceChangeListener(DONE)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister VisualizerActivity as an OnPreferenceChangedListener to avoid any memory leaks.
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}
