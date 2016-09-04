package us.illyohs.sparkyforge.bots.irc.command;

import javax.annotation.Nullable;
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

    protected boolean doesUserHaveGitPerms(User user, Channel channe)
    {
        String ircAcc = user.getUserString();
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

    protected String getGitHubUserName(User user)
    {
        String ircAcc = user.getName();
        try
        {
            JsonParser parser = new JsonParser();
            JsonObject jObj   = WebUtils.readJsonObjectFromURL(ConfigUtil.getPermsURL());
            if (jObj.has(ircAcc)) {
                return jObj.get(ircAcc).getAsString();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    private String getAcc(User user)
    {
        return user.getAccount().get();
    }
}
