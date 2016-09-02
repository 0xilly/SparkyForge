package us.illyohs.sparkyforge.bots.irc.command;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

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

    abstract public boolean execute(Channel channel, User user, String... args) throws IOException;

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

}
