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
