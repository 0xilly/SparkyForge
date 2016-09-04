package us.illyohs.sparkyforge.hooker.hooks.github;

import java.io.IOException;

import us.illyohs.sparkyforge.hooker.hooks.Hooker;


import com.google.common.io.ByteStreams;
import spark.Request;
import spark.Response;

public class GitHubHooker extends Hooker
{
    public GitHubHooker()
    {
        super("github");
    }

    @Override
    public Object init(Request request, Response response) throws IOException
    {
        String sig     = request.headers("X-Hub-Signature");
        String event   = request.headers("X-GitHub-GitEvent");
        byte[] payload = ByteStreams.toByteArray(request.raw().getInputStream());
        String json    = new String(payload);
        return new GitHubDispatcher(json);
    }

}
