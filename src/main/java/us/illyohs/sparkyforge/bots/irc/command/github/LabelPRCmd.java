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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import org.kohsuke.github.GHLabel;

public class LabelPRCmd extends BaseCMD
{

    public LabelPRCmd()
    {
        super("labelpr");
    }

    @Override
    public String getHelp()
    {
        return "Unease: label <repoid> <ls/rm/add>";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args)
    {
        List<String> ar = new ArrayList<>();

        try
        {

            int    id     = string2int(args[1]);
            Collection<GHLabel> labels = SparkyForge.getGitbot().getLabels(id);
            boolean doesExist = SparkyForge.getGitbot().doesLabelExist(args[3]);

            if (isUserListed(user))
            {
                if (args[2].equals("ls"))
                {
                    labels.forEach(lab -> ar.add(lab.getName()));

                    String strLab = Arrays.toString(ar.toArray()).replace("[", "").replace("]", "");
                    if (!ar.isEmpty())
                    {
                        channel.sendMessage(user.getNick() + ": The current labels are (" + strLab + ")");
                    } else
                    {
                        channel.sendMessage(user.getNick() + ": There are no labels for this issue/pr");
                    }

                } else if (args[2].equals("add"))
                {
                    labels.forEach(lab -> ar.add(lab.getName()));

                    if (doesExist)
                    {
                        String[] lbz      = new String[0];
                        String   addlabel = SparkyForge.getGitbot().getLabelFromName(args[3]).getName();
                        ar.add(addlabel);

                        lbz = ar.toArray(lbz);
                        SparkyForge.getGitbot().getPullRequest(id).setLabels(lbz);
                    } else
                    {
                        channel.sendMessage(user.getNick() + " The label: " + args[3] + ", does not exist!");
                    }

                } else if (args[2].equals("rm"))
                {
                    labels.forEach(lab -> ar.add(lab.getName()));
                    if (doesExist)
                    {
                        String[] lbz     = new String[0];
                        String   rmlabel = SparkyForge.getGitbot().getLabelFromName(args[3]).getName();
                        ar.remove(rmlabel);

                        lbz = ar.toArray(lbz);
                        SparkyForge.getGitbot().getPullRequest(id).setLabels(lbz);
                    } else
                    {
                        channel.sendMessage(user.getNick() + ", The label: " + args[3] + ", does not exist!");
                    }
                }
            }else
            {
                channel.sendMessage(user.getNick() + ", You do not have permission to use this command!");
            }

        } catch (IOException e)
        {
            channel.sendMessage("This is a pull request not an issue");
            e.printStackTrace();
        } catch (NumberFormatException e)
        {

        }

        return true;
    }

}
