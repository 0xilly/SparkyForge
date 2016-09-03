package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class StatCheckCmd extends BaseCMD
{
    public StatCheckCmd()
    {
        super("status");
    }

    @Override
    public String getHelp()
    {
        return "Usage: Forces the bot to do a status check";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        try
        {
            int id = string2int(args[1]); //Integer.parseInt(idstring);
            if (isVoice(channel, user) || isOp(channel, user))
            {
                boolean isPointedToDefault = SparkyForge.getGitbot().isPointedToDefualt(id);
                String title = SparkyForge.getGitbot().getPullRequestTitle(id);
                SparkyForge.getGitbot().setStatus(id, isPointedToDefault);
                if (isPointedToDefault)
                {
                    channel.sendMessage(user.getNick() + ": Pull Request(" + title + ") is pointed to default");
                }
                else {
                    channel.sendMessage(user.getNick() + ": Pull Request(" + title + ") is NOT pointed to default");
                }
            }
        } catch (IOException e)
        {

        }
        return true;
    }
}
