package us.illyohs.sparkyforge.bots.irc.command;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public abstract class BaseCMD
{
    String name;

    public BaseCMD(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    abstract public String getHelp();

    public abstract void execute(Channel channel, User user, String... args);


}
