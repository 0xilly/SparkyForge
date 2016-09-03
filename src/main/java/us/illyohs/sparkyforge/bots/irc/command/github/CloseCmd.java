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

    //FIXME permchecking
    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        int id = string2int(args[1]); //Integer.parseInt(args[1]);
        try
        {
            boolean isClosed = SparkyForge.getGitbot().isIssueClosed(id);
            channel.sendMessage(String.valueOf(isClosed));
            if (!isClosed)
            {
                MessageUtils.sendIssueMessage(id,"Closed by:" + user.getNick());
                SparkyForge.getGitbot().closeIssue(id);
            }
        } catch (IOException e)
        {
            channel.sendMessage(user.getNick() + ": Error something something something");
        }

        return true;
    }
}
