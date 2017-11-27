package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

public class SettingsActivity extends AppCompatActivity {

    ThemeHelper themeHelper;
    Button toggleButton;
    TextView toggleText;
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getElements();

        themeHelper = new ThemeHelper(getApplicationContext());

        themeHelper.themeBackground(layout);
        themeHelper.themeTextView(toggleText);
        setClickFunctionality();


    }

    void getElements() {
        toggleButton = (Button) findViewById(R.id.toggleButton);
        toggleText = (TextView) findViewById(R.id.toggleText);
        layout = findViewById(R.id.settingsActivity);
    }

    void setClickFunctionality() {
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeHelper.enableDarkTheme(!themeHelper.darkThemeEnabled());

                themeHelper.themeBackground(layout);
                themeHelper.themeTextView(toggleText);

                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                startActivity(intent);

            }
        });
    }
}
