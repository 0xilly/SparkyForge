package us.illyohs.sparkyforge.bots.irc.command.github;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class LabelCmd extends BaseCMD
{

    String[] lables = {
            "Bug",
            "Vanilla Bug",
            "BreakingChange",
            "Confirmed",
            "Cleanup",
            "Fluids" ,
            "Needs Update",
            "Performance",
            "Regression",
            "RFC",
            "Superseded"};

    public LabelCmd(String name)
    {
        super(name);
    }

    @Override
    public String getHelp()
    {
        return null;
    }

    @Override
    public void execute(Channel channel, User user, String... args)
    {

    }
}
