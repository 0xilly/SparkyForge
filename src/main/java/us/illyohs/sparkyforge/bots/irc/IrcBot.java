package us.illyohs.sparkyforge.bots.irc;

import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.element.Channel;

public class IrcBot
{
    String channel;
    int    port;

    Client client;

    public IrcBot(String network, int port, String name, String password, String channel)
    {
        this.channel = channel;

        client = Client.builder()
                .serverHost(network)
                .serverPort(port)
                .nick(name)
                .name(name)
                .user(name)
                .secure(false)
                .build();

    }

    public void connect()
    {
        client.addChannel(getChannel());
    }

    public Client getClient()
    {
        return client;
    }


    public String getChannel()
    {
        return channel;
    }


    public Channel getConChannel()
    {
        return client.getChannel(getChannel()).get();
    }
}
