package us.illyohs.sparkyforge.command;

import org.pircbotx.UserLevel;

public interface ICommand
{
    void name();

    void help();

    void execute();

    UserLevel getLevel();

}
