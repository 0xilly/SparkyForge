package us.illyohs.sparkyforge.bots.irc.command.github;

import javax.annotation.Nullable;
import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.util.MessageUtils;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class ReopenCmd extends BaseCMD
{
    public ReopenCmd()
    {
        super("reopen");
    }

    @Override
    public String getHelp()
    {
        return "Reopens a Issue/PR, Usage: reopen <id>";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        int id = string2int(args[1]); // Integer.parseInt(args[1]);
        try
        {
            if (isUserListed(user))
            {
                boolean isClosed = SparkyForge.getGitbot().isIssueClosed(id);
                if (isClosed)
                {
                    MessageUtils.sendIssueMessage(id, "Reopened by: @" + getGitHubUserName(user));
                    SparkyForge.getGitbot().reopenIssue(id);
                    channel.sendMessage(user.getNick() + "re-opened issue");
                } else
                {
                    channel.sendMessage(user.getNick() + ", Issue is already open");
                }
            } else {
                channel.sendMessage(user.getNick() + ", You do not have permission to use this command!");
            }
        } catch (IOException e)
        {
            channel.sendMessage(user.getNick() + ": Error something something something");
        } catch (NumberFormatException e)
        {

        }
        return false;
    }
}
