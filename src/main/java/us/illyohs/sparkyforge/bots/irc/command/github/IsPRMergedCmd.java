package us.illyohs.sparkyforge.bots.irc.command.github;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class IsPRMergedCmd extends BaseCMD
{
    public IsPRMergedCmd()
    {
        super("ismerged");
    }

    @Override
    public String getHelp()
    {
        return "usage <PR id>";
    }

    @Override
    public void execute(Channel channel, User user, String... args)
    {
//        channel.g
    }
}
