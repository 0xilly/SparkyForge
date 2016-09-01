package us.illyohs.sparkyforge.hooker.hooks;

import java.io.IOException;

import spark.Request;
import spark.Response;

public abstract class Hooker
{
    String name;

    public Hooker(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public abstract Object init(Request request, Response response) throws IOException;

}
