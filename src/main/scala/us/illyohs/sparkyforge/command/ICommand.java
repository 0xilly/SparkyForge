package us.illyohs.sparkyforge.command;



import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public interface ICommand
{
    String name();

    String help();

    void execute(User user, Channel channel, String message, String[] args);

}
