package co.miniforge.corey.mediatracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

import static co.miniforge.corey.mediatracker.MyListActivity.mediaExtra;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 *
 * @author Elizabeth Benner
 * @version 1.0
 */
public class MediaItemDetailActivity extends AppCompatActivity {

    MediaItem updateMediaItem;
    EditText title;
    EditText description;
    EditText url;
    Button saveButton;
    View layout;
    TextView titlePrompt;
    TextView descriptionPrompt;
    TextView urlPrompt;
    TextView header;
    ThemeHelper themeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        getHolderIntent();
        getEditTexts();
        getButton();
        fillEdits();
        saveButtonListener();
        getThemeItems();
        themeHelper = new ThemeHelper(getApplicationContext());
        themeHelper.themeBackground(layout);
        TextView[] textViews = {titlePrompt, descriptionPrompt, urlPrompt, header};
        themeHelper.themeTextView(textViews);

    }

    void getThemeItems() {
        layout = findViewById(R.id.mediaItemDetailLayout);
        titlePrompt = (TextView) findViewById(R.id.titlePrompt);
        descriptionPrompt = (TextView) findViewById(R.id.descriptionPrompt);
        urlPrompt = (TextView) findViewById(R.id.urlPrompt);
        header = (TextView) findViewById(R.id.header);

    }

    /**
     * Get the data received from the intent
     */
    void getHolderIntent() {
        String extraData;
        if(getIntent().hasExtra(mediaExtra)){
            extraData = getIntent().getStringExtra(mediaExtra);
            try {
                JSONObject mediaJson = new JSONObject(extraData);
                updateMediaItem = new MediaItem(mediaJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the EditText elements from the layout
     */
    void getEditTexts() {
        title = (EditText) findViewById(R.id.mediaTitle);
        description = (EditText) findViewById(R.id.mediaDescription);
        url = (EditText) findViewById(R.id.mediaURL);
    }

    /**
     * Get the save button from the layout
     */
    void getButton() {
        saveButton = (Button) findViewById(R.id.saveButton);
    }

    /**
     * Set the text values of the EditTexts to the data pulled from the mediaItem
     */
    void fillEdits() {
        title.setText(updateMediaItem.title);
        description.setText(updateMediaItem.description);
        url.setText(updateMediaItem.url);
    }

    /**
     * Set the click listener for the save button
     */
    void saveButtonListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();

                Intent updateIntent = new Intent(getApplicationContext(), MyListActivity.class);
                updateIntent.putExtra(mediaExtra, updateMediaItem.toJson().toString());
                startActivity(updateIntent);

            }
        });
    }

    /**
     * Update the MediaItem with inputs pulled from the EditTexts
     */
    void updateItem() {
        String editTitle = title.getText().toString();
        String editDescription = description.getText().toString();
        String editURL = url.getText().toString();

        updateMediaItem.title = editTitle;
        updateMediaItem.description = editDescription;
        updateMediaItem.url = editURL;
    }



}
