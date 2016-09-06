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

package us.illyohs.sparkyforge.bots.irc.command;

import javax.annotation.Nullable;
import javax.jws.soap.SOAPBinding.Use;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import us.illyohs.sparkyforge.util.ConfigUtil;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;
import org.kitteh.irc.client.library.element.mode.ChannelUserMode;

public abstract class BaseCMD
{
    String name;

    public BaseCMD(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    abstract public String getHelp();

    abstract public boolean execute(Channel channel, User user, @Nullable String... args) throws IOException, ArrayIndexOutOfBoundsException;

    protected boolean isOp(Channel channel, User user)
    {
        Optional<SortedSet<ChannelUserMode>> userModes = channel.getUserModes(user);
        if (userModes.isPresent()) {
            Set<ChannelUserMode> modes = userModes.get();
            for (ChannelUserMode mode : modes) {
                if (mode.getNickPrefix() == '@') {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isVoice(Channel channel, User user)
    {
        Optional<SortedSet<ChannelUserMode>> userModes = channel.getUserModes(user);
        if (userModes.isPresent()) {
            Set<ChannelUserMode> modes = userModes.get();
            for (ChannelUserMode mode : modes) {
                if (mode.getNickPrefix() == '+') {
                    return true;
                }
            }
        }
        return false;
    }

    protected String args2String(int start, String... args)
    {
        String newString = "";
        for (int i = start; i < args.length; i++)
        {
            newString += args[i] + " ";
        }
        return newString.trim();
    }

    protected int string2int(String str)
    {
        return Integer.parseInt(str);
    }

    protected String getAccount(User user)
    {
        return user.getAccount().get();
    }

    protected boolean doesUserHaveGitPerms(User user, Channel channe)
    {
        String ircAcc = user.getAccount().get();
        try
        {
            JsonParser parser = new JsonParser();
            JsonObject jObj   = WebUtils.readJsonObjectFromURL(ConfigUtil.getPermsURL());
            if (jObj.has(ircAcc)) {
                return true;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean isUserListed(User user) throws IOException
    {
        return WebUtils.readJsonObjectFromURL(ConfigUtil.getPermsURL()).has(user.getAccount().get());
    }

    protected String getGitHubUserName(User user)
    {
        try
        {
            String ircAcc = user.getAccount().get();
            JsonObject jObj   = WebUtils.readJsonObjectFromURL(ConfigUtil.getPermsURL());
            if (jObj.has(ircAcc)) {

                JsonObject accObj   = jObj.get(ircAcc).getAsJsonObject();
                String     ghLogin  = accObj.get("github").getAsString();
                return ghLogin;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected String getIrcUserName(User user)
    {
        try
        {
            String ircAcc = user.getAccount().get();
            JsonObject jObj   = WebUtils.readJsonObjectFromURL(ConfigUtil.getPermsURL());
            if (jObj.has(ircAcc)) {

                JsonObject accObj   = jObj.get(ircAcc).getAsJsonObject();
                String     ghLogin  = accObj.get("irc").getAsString();
                return ghLogin;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }



}
