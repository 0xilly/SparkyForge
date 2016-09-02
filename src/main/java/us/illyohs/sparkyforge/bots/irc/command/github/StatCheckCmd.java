package us.illyohs.sparkyforge.bots.irc.command.github;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class StatCheckCmd extends BaseCMD
{
    public StatCheckCmd(String name)
    {
        super(name);
    }

    @Override
    public String getHelp()
    {
        return null;
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        if (isVoice(channel, user) || isOp(channel, user))
        {

        }
        return true;
    }
}
