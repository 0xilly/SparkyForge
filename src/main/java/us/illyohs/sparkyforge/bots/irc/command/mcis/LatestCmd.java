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

package us.illyohs.sparkyforge.bots.irc.command.mcis;

import javax.annotation.Nullable;
import java.io.IOException;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class LatestCmd extends BaseCMD
{
    public LatestCmd()
    {
        super("latest");
    }

    @Override
    public String getHelp()
    {
        return "Usage: latest";
    }

    @Override
    public boolean execute(Channel channel, User user, @Nullable String... args) throws ArrayIndexOutOfBoundsException
    {
        String url  = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";
        String file = "http://files.minecraftforge.net/";
        try {

            JsonObject jObj = WebUtils.readJsonObjectFromURL("http://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json");

            JsonObject promos = jObj.get("promos").getAsJsonObject();
            String     latest = promos.get("latest").getAsString();
            channel.sendMessage(user.getNick() + ": The latest version of forge is " + latest + ". Go to "+ file +
                    " to download.");
        } catch (IOException e)
        {

        }

        return true;
    }
}
