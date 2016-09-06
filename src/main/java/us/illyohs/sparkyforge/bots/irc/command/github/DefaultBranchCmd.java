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

package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class DefaultBranchCmd extends BaseCMD
{
    public DefaultBranchCmd()
    {
        super("default");
    }

    @Override
    public String getHelp()
    {
        return "Gets the default branch Forge.";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        try
        {
            String branch = SparkyForge.getGitbot().getDefaultBranch();
            channel.sendMessage(user.getNick() + ": The default branch is " + branch);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
