package us.illyohs.sparkyforge;

import us.illyohs.sparkyforge.bots.github.GitHubBot;
import us.illyohs.sparkyforge.bots.irc.IrcBot;
import us.illyohs.sparkyforge.hooker.HookLoader;
import us.illyohs.sparkyforge.util.ConfigUtil;

public class SparkyForge
{
    private static GitHubBot gitbot;
    private static IrcBot ircbot;
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

        loader.loadHooks();
    }

    public static IrcBot getIrcbot()
    {
        return ircbot;
    }

    public static GitHubBot getGitbot()
    {
        return new GitHubBot(ConfigUtil.getGHToken(), ConfigUtil.getGHRepo());
    }
}
