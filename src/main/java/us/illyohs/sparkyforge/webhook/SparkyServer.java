package us.illyohs.sparkyforge.webhook;

import us.illyohs.sparkyforge.webhook.github.GitHookManager;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkyServer
{
//    private int     port    = 3567;
    private static String  hookURL = "/sparkyhook/";

    public Gson gson;

    public SparkyServer(int port)
    {

        Spark.port(port);
        Spark.init();
        Github();
    }


    public static void Github()
    {
        Spark.post(hookURL + "/github", (request, response) -> {
            String sig       = request.headers("X-Hub-Signature");
            String eventType = request.headers("X-GitHub-Event");
            byte[] json      = ByteStreams.toByteArray(request.raw().getInputStream());

            return new GitHookManager(sig, eventType, json);
        });
    }

//    public static handle
}