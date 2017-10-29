package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.model.MediaItem;

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

    MediaItem item;
    EditText title;
    EditText description;
    EditText url;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        String extraData;
        if(getIntent().hasExtra("extraTag")){
            extraData = getIntent().getStringExtra("extraTag");
            try {
                JSONObject mediaJson = new JSONObject(extraData);
                item = new MediaItem(mediaJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        getEditTexts();
        getButton();
        fillEdits();
        saveButtonListener();

    }

    void getEditTexts() {
        title = (EditText) findViewById(R.id.mediaTitle);
        description = (EditText) findViewById(R.id.mediaDescription);
        url = (EditText) findViewById(R.id.mediaURL);
    }

    void getButton() {
        saveButton = (Button) findViewById(R.id.saveButton);
    }

    void fillEdits() {
        title.setText(item.title);
        description.setText(item.description);
        url.setText(item.url);
    }

    void saveButtonListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTitle = title.getText().toString();
                String editDescription = description.getText().toString();
                String editURL = url.getText().toString();

                item.title = editTitle;
                item.description = editDescription;
                item.url = editURL;

                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                intent.putExtra("mediaExtra", item.toJson().toString());
                startActivity(intent);

            }
        });
    }

}
