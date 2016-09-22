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
