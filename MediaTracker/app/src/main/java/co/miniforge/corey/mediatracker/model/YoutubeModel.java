package co.miniforge.corey.mediatracker.model;


import org.json.JSONObject;

/**
 * Created by ebenn on 11/18/2017.
 */

public class YoutubeModel extends MediaItem {

    public String channel;
    public String publishDate;

    public YoutubeModel(JSONObject jsonObject) {
        super(jsonObject);
    }

}
