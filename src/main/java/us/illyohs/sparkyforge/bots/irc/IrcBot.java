/*
 *     Copyright (C) 2016  Anthony Anderson
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Library General Public
 *     License as published by the Free Software Foundation; either
 *     version 2 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Library General Public License for more details.
 *
 *     You should have received a copy of the GNU Library General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package us.illyohs.sparkyforge.bots.irc;

import us.illyohs.sparkyforge.handler.CommandHandler;

import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.element.Channel;

public class IrcBot
{
    String channel;
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
        client.getEventManager().registerEventListener(new CommandHandler());
        client.addChannel(getChannel());
        System.out.println("Connected");
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
