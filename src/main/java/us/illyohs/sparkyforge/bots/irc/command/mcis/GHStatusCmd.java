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

public class GHStatusCmd extends BaseCMD
{
    public GHStatusCmd()
    {
        super("ghstatus");
    }

    @Override
    public String getHelp()
    {
        return "Usage: ghstatus";
    }

    @Override
    public boolean execute(Channel channel, User user, @Nullable String... args) throws IOException, ArrayIndexOutOfBoundsException
    {
        github(channel, user);
        return true;
    }

    private void github(Channel channel, User user) throws IOException
    {

        JsonObject jObj    = WebUtils.readJsonObjectFromURL("https://status.github.com/api/last-message.json");
        String     status  = jObj.get("status").getAsString();
        String     message = jObj.get("body").getAsString();

        channel.sendMessage("[GitHub] status: " + status + ", Message: " + message);

    }

}
