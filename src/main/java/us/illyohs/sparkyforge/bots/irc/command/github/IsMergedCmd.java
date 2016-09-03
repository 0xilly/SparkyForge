package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class IsMergedCmd extends BaseCMD
{
    public IsMergedCmd()
    {
        super("ismerged");
    }

    @Override
    public String getHelp()
    {
        return "usage <PR id>";
    }



    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        int id = string2int(args[1]); //Integer.parseInt(args[1]);

        try
        {
            if (SparkyForge.getGitbot().isMerged(id)) {
                System.out.println("The pr is merged");
                channel.sendMessage(user.getNick() + ": PR is merged");
            } else {
                System.out.println("The pr is not merged");
                channel.sendMessage(user.getNick() + ": The Pr is not merged");
            }
        } catch (IOException e) {
            channel.sendMessage( user.getNick() + ": That is an issue not a pr... YOU FOOL!");
        }
        return true;
    }
}
