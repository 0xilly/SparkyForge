package us.illyohs.sparkyforge.util;

public class Configuration
{
    public int    WebHookPort;
    public String GitHubUserName;
    public String GitHubToken;
    public String GitHubRepository;
    public String IrcNetwork;
    public int    IrcPort;
    public String IrcName;
    public String IrcNickPass;
    public String IrcChannel;
    public String CommandOperator;

    public Configuration(int webHookPort, String gitHubUserName, String gitHubToken, String gitHubRepository, String ircNetwork, int ircPort, String ircName, String ircNickPass, String ircChannel, String commandOperator)
    {
        WebHookPort = webHookPort;
        GitHubUserName = gitHubUserName;
        GitHubToken = gitHubToken;
        GitHubRepository = gitHubRepository;
        IrcNetwork = ircNetwork;
        IrcPort = ircPort;
        IrcName = ircName;
        IrcNickPass = ircNickPass;
        IrcChannel = ircChannel;
        CommandOperator = commandOperator;
    }
}
