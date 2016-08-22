package us.illyohs.sparkyforge.command;

import org.pircbotx.UserLevel;

public interface ICommand
{
    String name();

    String help();

    void execute(String... args);

    UserLevel getPermLevel();

}
