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

public class LabelissueCmd extends BaseCMD
{

    public LabelissueCmd()
    {
        super("labelissue");
    }

    @Override
    public String getHelp()
    {
        return "Usage: labelissue <id> <rm/add>";
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
                if (args[2].equals("add"))
                {
                    labels.forEach(lab -> ar.add(lab.getName()));

                    if (doesExist)
                    {
                        String[] lbz      = new String[0];
                        String   addlabel = SparkyForge.getGitbot().getLabelFromName(args[3]).getName();
                        ar.add(addlabel);

                        lbz = ar.toArray(lbz);
                        SparkyForge.getGitbot().getIssue(id).setLabels(lbz);
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
                        SparkyForge.getGitbot().getIssue(id).setLabels(lbz);
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
            channel.sendMessage("This is an issue not an pull request");

        } catch (NumberFormatException e)
        {
            channel.sendMessage("error with args");
        }

        return true;
    }

}
