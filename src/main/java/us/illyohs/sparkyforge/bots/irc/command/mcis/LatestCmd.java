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
        return "Gets the latest version of forge";
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
