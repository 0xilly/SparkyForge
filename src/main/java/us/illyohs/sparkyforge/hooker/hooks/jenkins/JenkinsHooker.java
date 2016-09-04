package us.illyohs.sparkyforge.hooker.hooks.jenkins;

import java.io.IOException;

import us.illyohs.sparkyforge.hooker.hooks.Hooker;

import com.google.common.io.ByteStreams;
import spark.Request;
import spark.Response;

public class JenkinsHooker extends Hooker
{
    public JenkinsHooker()
    {
        super("jenkins");
    }

    @Override
    public Object init(Request request, Response response) throws IOException
    {
        byte[] payload = ByteStreams.toByteArray(request.raw().getInputStream());
        String json    = new String(payload);
        return null;
    }
}
