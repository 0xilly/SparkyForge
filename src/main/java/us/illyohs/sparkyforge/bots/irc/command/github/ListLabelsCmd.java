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

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.PagedIterable;

public class ListLabelsCmd extends BaseCMD
{
    public ListLabelsCmd()
    {
        super("listlabels");
    }

    @Override
    public String getHelp()
    {
        return "Lists all the labels for the repo";
    }

    @Override
    public boolean execute(Channel channel, User user, @Nullable String... args)
    {
        ArrayList<String> ar = new ArrayList<>();
        try
        {
            if (isUserListed(user))
            {
                PagedIterable<GHLabel> labels = SparkyForge.getGitbot().getRepo().listLabels();

                labels.forEach(lab -> ar.add(lab.getName()));
                channel.sendMessage(user.getNick() + ", " + Arrays.toString(ar.toArray()).replace("[","").replace("]",""));

            } else {
                channel.sendMessage("You do not have permission for the command");
            }

        } catch (IOException e) {

        }
        return false;
    }
}
