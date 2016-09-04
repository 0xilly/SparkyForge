package us.illyohs.sparkyforge.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;

public class WebUtils
{


    public static String readFromURL(String url) throws IOException
    {
        InputStream in = new URL(url).openStream();
        return IOUtils.toString(in);
    }

    public static JsonObject readJsonObjectFromString(String json)
    {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }

    public static JsonObject readJsonObjectFromURL(String url) throws IOException
    {
        return readJsonObjectFromString(readFromURL(url));
    }

}
