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

    public static JsonObject readJObjFromURL(String url) throws IOException
    {
//        JsonReader jreader = new JsonReader(p).setLenient(true);
        JsonParser parser = new JsonParser();
        return parser.parse(readFromURL(url)).getAsJsonObject();
    }

}
