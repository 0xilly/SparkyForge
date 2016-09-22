/*
 * Copyright (c) 2016, Anthony Anderson
 * Copyright (c) 2016, Minecraft Forge
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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

    public void restartIrcBot()
    {
        client.shutdown("Restarting");
        connect();
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
