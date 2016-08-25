package us.illyohs.sparkyforge.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config
{
    private int webHookPort = 2222;

    private String username = "";

    private String token            = "";

    private String repository       = "";

    private String network          = "";

    private int    port             = 6666;

    private String nick             = "";

    private String password         = "";

    private String channel          = "";

    private char   commandOpperator = '~';

    public static Config fromJson(String json)
    {
        return new Gson().fromJson(json, Config.class);
    }

    public int getWebHookPort()
    {
        return webHookPort;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getToken()
    {
        return token;
    }

    public String getRepository()
    {
        return repository;
    }

    public String getNetwork()
    {
        return network;
    }

    public int getPort()
    {
        return port;
    }

    public String getNick()
    {
        return nick;
    }

    public String getPassword()
    {
        return password;
    }

    public String getChannel()
    {
        return channel;
    }

    public char getCommandOpperator()
    {
        return commandOpperator;
    }
}
