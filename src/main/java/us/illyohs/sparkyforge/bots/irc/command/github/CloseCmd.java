package us.illyohs.sparkyforge.bots.irc.command.github;

import javax.annotation.Nullable;
import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.util.MessageUtils;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class CloseCmd extends BaseCMD
{
    public CloseCmd()
    {
        super("close");
    }

    @Override
    public String getHelp()
    {
        return "Closes a issue pr pull request. Usage: close <id>";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {

        int id = string2int(args[1]); //Integer.parseInt(args[1]);
        try
        {
            if (isUserListed(user))
            {
                boolean isClosed = SparkyForge.getGitbot().isIssueClosed(id);
                if (!isClosed)
                {

                    MessageUtils.sendIssueMessage(id, "Closed by: @" + getGitHubUserName(user));
                    SparkyForge.getGitbot().closeIssue(id);
                    channel.sendMessage(user.getNick() +", closed issue");

                } else
                {
                    channel.sendMessage(user.getNick() + ", Issue is already closed");
                }

            } else {
                channel.sendMessage(user.getNick() + ", You do not have permission to use this command!");
            }
        } catch (IOException e)
        {
            channel.sendMessage(user.getNick() + ", Error something something something");
        } catch (NumberFormatException e)
        {

        }

        return true;
    }
}
