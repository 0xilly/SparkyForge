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

    //FIXME add premchecks
    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        int id = string2int(args[1]); // Integer.parseInt(args[1]);
        try
        {
            boolean isClosed = SparkyForge.getGitbot().isIssueClosed(id);
            channel.sendMessage(String.valueOf(isClosed));
            if (isClosed)
            {
                MessageUtils.sendIssueMessage(id,"Reopened by:" + user.getNick());
                SparkyForge.getGitbot().reopenIssue(id);
            }

        } catch (IOException e)
        {
            channel.sendMessage(user.getNick() + ": Error something something something");
        }
        return false;
    }
}
