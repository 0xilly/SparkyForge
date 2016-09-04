package us.illyohs.sparkyforge.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.github.kevinsawicki.http.HttpRequest;

public class Shorteners
{

    public static URL gitIo(URL url)
    {
        try
        {
            HashMap<String, String> map = new HashMap<>();
            String target = "https://git.io";
            map.put("url", url.toExternalForm());
            return new URL(HttpRequest.post(target).form(map).header("Location"));
        } catch (MalformedURLException e)
        {
            return url;
        }
    }
}
