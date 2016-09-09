/*
 *     Copyright (C) 2016  Anthony Anderson
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Library General Public
 *     License as published by the Free Software Foundation; either
 *     version 2 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Library General Public License for more details.
 *
 *     You should have received a copy of the GNU Library General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

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
                props.setProperty("GitHub.Repository", "<user/org>/repo");
                props.setProperty("GitHub.PermsJsonUrl", "www.foo.com/Perms.json");

                props.setProperty("IRC.Network", "irc.esper.net");
                props.setProperty("IRC.Port", "6666");
                props.setProperty("IRC.Nick", "Sparky");
                props.setProperty("IRC.NickPassword", "12345");
                props.setProperty("IRC.Channel", "#channel");
                props.setProperty("IRC.CommendOperator", ".");

                props.setProperty("WebHook.Port", "2222");
                out = Files.newOutputStream(conf);

                props.store(out, "Sparky conf");
                out.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
            System.out.println("Please fill in sparky.properties with the correct information then restart!");
            System.exit(1);
        }
    }

    private static String getPropFromKey(String key)
    {
        loadProps();
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
        return getPropFromKey("IRC.NickPassword");
    }

    public static String getIrcChannel()
    {
        return getPropFromKey("IRC.Channel");
    }

    public static String getIrcCommendOperator()
    {
        return getPropFromKey("IRC.CommendOperator");
    }

    public static String getPermsURL()
    {
        return getPropFromKey("GitHub.PermsJsonUrl");
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
