package us.illyohs.sparkyforge.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigUtil
{
    private static Path conf = Paths.get("sparky.properties");
    private static Properties props = new Properties();

    public ConfigUtil()
    {
        loadProps();
    }

    private static void loadProps()
    {
        OutputStream out;
        if (!Files.exists(conf))
        {
            try
            {
                System.out.println(conf.getFileName() + " Has not been found now creating a new one");
                props.setProperty("GitHub.Token", "token");
                props.setProperty("GitHub.Repository", "repo");

                props.setProperty("IRC.Network", "irc.esper.net");
                props.setProperty("IRC.Port", "6666");
                props.setProperty("IRC.Nick", "Sparky");
                props.setProperty("IRC.Password", "12345");
                props.setProperty("IRC.Channel", "#channel");
//        props.setProperty("IRC.CommendOperator", "~")

                props.setProperty("WebHook.Port", "2222");
                out = Files.newOutputStream(conf);

                props.store(out, "Sparky conf");
                out.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
            System.out.println("Please fill in sparky.properties with the correct information then restart!");
        }
    }

    public static String getPropFromKey(String key)
    {
        try
        {
            InputStream in = Files.newInputStream(conf);
            props.load(in);
            return props.getProperty(key);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getGHToken()
    {
        return getPropFromKey("GitHub.Token");
    }

    public static String getGHRepo()
    {
        return getPropFromKey("GitHub.Repository");
    }

    public static String getIrcNetwork()
    {
        return getPropFromKey("IRC.Network");
    }
    public static String getIrcNick()
    {
        return getPropFromKey("IRC.Nick");
    }

    public static String getIrcPass()
    {
        return getPropFromKey("IRC.Password");
    }

    public static String getIrcChannel()
    {
        return getPropFromKey("IRC.Channel");
    }

    public static int getIrcPort()
    {
        return Integer.parseInt(getPropFromKey("IRC.Port"));
    }

    public static int getWebHookPort()
    {
        return Integer.parseInt(getPropFromKey("WebHook.Port"));
    }
}
