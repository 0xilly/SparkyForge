package us.illyohs.sparkyforge;

import us.illyohs.sparkyforge.bots.irc.IrcBot;
import us.illyohs.sparkyforge.hooker.HookLoader;
import us.illyohs.sparkyforge.util.ConfigUtil;
import us.illyohs.sparkyforge.util.GitHubHelper;

public class SparkyForge
{
    private static GitHubHelper gitbot;
    private static IrcBot       ircbot;
    private static HookLoader loader = new HookLoader();



    public static void main(String... args)
    {
        ircbot = new IrcBot(
                ConfigUtil.getIrcNetwork(),
                ConfigUtil.getIrcPort(),
                ConfigUtil.getIrcNick(),
                ConfigUtil.getIrcPass(),
                ConfigUtil.getIrcChannel()
        );

        ircbot.connect();

    }

    public static IrcBot getIrcbot()
    {
        return ircbot;
    }

    public static GitHubHelper getGitbot()
    {
        return new GitHubHelper(ConfigUtil.getGHToken(), ConfigUtil.getGHRepo());
    }
}
