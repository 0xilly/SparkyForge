/*
 * Copyright (c) 2016, Anthony Anderson
 * Copyright (c) 2016, Minecraft Forge
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
