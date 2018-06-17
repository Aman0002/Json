package com.example.manoj.json;

import com.example.manoj.json.Model.ItuneStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by manoj on 09-05-2018.
 */

public class JsonItuneParse {
    public static ItuneStuff getItunesStuff(String url)throws JSONException
    {
        ItuneStuff itunestuff=new ItuneStuff();
        JSONObject itunestuffjsonObject=new JSONObject(url);
        JSONArray   resultsJSonArray= itunestuffjsonObject.getJSONArray("results");
        JSONObject artistObject=resultsJSonArray.getJSONObject(0);
        itunestuff.setArtistname(getString("artistName",artistObject));
        itunestuff.setType(getString("wrapperType",artistObject));
        itunestuff.setKind(getString("kind",artistObject));
        itunestuff.setCollectionname(getString("collectionName",artistObject));
        itunestuff.setTrackname(getString("trackName",artistObject));
        itunestuff.setArtistviewurl(getString("artworkUrl100",artistObject));
        return itunestuff;
    }



    private static JSONObject getJsonObject(String tagname,JSONObject jsonObject)throws JSONException
    {
        return jsonObject.getJSONObject(tagname);
    }
    private static String getString(String tagname, JSONObject jsonObject)throws JSONException
    {
        return jsonObject.getString(tagname);
    }
    private static float getfloat(String tagname,JSONObject jsonObject)throws JSONException
    {
           return (float) jsonObject.getDouble(tagname);
    }
    private static int getInt(String tagname,JSONObject jsonObject)throws JSONException
    {
        return jsonObject.getInt(tagname);
    }
    private static  boolean getboolean(String tagname,JSONObject jsonObject)throws JSONException
    {
        return jsonObject.getBoolean(tagname);
    }
}
