package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class DefaultBranchCmd extends BaseCMD
{
    public DefaultBranchCmd()
    {
        super("default");
    }

    @Override
    public String getHelp()
    {
        return "Gets the default branch Forge.";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        try
        {
            String branch = SparkyForge.getGitbot().getDefaultBranch();
            channel.sendMessage(user.getNick() + ": The default branch is " + branch);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
