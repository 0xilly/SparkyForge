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

package us.illyohs.sparkyforge.hooker.hooks.github;

import java.io.IOException;
import java.net.URL;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.util.MessageUtils;
import us.illyohs.sparkyforge.util.Shorteners;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;

import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHUser;

public class GitHubDispatcher
{

    public GitHubDispatcher(String json)
    {
        JsonObject jObj   = WebUtils.readJsonObjectFromString(json);

        String action     = jObj.get("action").getAsString();
        int    id         = jObj.get("number").getAsInt();
        eventMatcher(action, id);
    }

    public void eventMatcher(String action, int id)
    {
        switch (action)
        {
            case "opened": handleOpen(id);
                break;
            case "reopened": handleReOpend(id);
                break;
            case "closed": handleClosed(id);
                break;
            case "synchronize": handleSync(id);
                break;
            default:
                break;
        }
    }

    private void handleOpen(int id)
    {
        try {
            GHPullRequest pr           = SparkyForge.getGitbot().getPullRequest(id);
            String defaultBranch       = SparkyForge.getGitbot().getDefaultBranch();
            boolean isPointedToDefault = SparkyForge.getGitbot().isPointedToDefualt(id);
            GHUser user = pr.getUser();
            URL url = pr.getHtmlUrl();

            String gitComment = "@" + user.getLogin() + " Your pull request is not pointed to the default branch __" +
                    defaultBranch + "__. Please retarget to __" + defaultBranch + "__. if you want this to be mered!";

            String ircNonMerge = "[GitHub] "+ user.getName() + "(" + user.getLogin() + ") has just open pull request: "+
                    pr.getTitle() +", that is NOT pointed to the current default branch " + defaultBranch +
                    " and WON'T BE merged! " + Shorteners.gitIo(url);

            String ircCanMerge ="[GitHub] "+ user.getName() + "(" + user.getLogin() + ") has just open pull request: "
                    + pr.getTitle() + ", that is  pointed to the current default branch (" + defaultBranch +
                    ") and CAN BE merged! " + Shorteners.gitIo(url);


            if (!isPointedToDefault)
            {
                SparkyForge.getGitbot().setStatus(id, false);
                MessageUtils.sendMessageToChannel(ircNonMerge);
                MessageUtils.sendIssueMessage(id, gitComment);

            } else
            {
                SparkyForge.getGitbot().setStatus(id, true);
                MessageUtils.sendLexHandledMessageToChannel(ircCanMerge);
            }
            SparkyForge.getGitbot().setStatus(id, isPointedToDefault);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void handleReOpend(int id)
    {
        try
        {
            GHPullRequest pr    = SparkyForge.getGitbot().getPullRequest(id);
            String        title = pr.getTitle();
            URL           url   = pr.getHtmlUrl();

            String message = "[GitHub] Pull Request: (" + title + "), has been reopened! " + Shorteners.gitIo(url);
            MessageUtils.sendLexHandledMessageToChannel(message);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void handleSync(int id)
    {

        try {

            boolean isPointedToDefault = SparkyForge.getGitbot().isPointedToDefualt(id);

            SparkyForge.getGitbot().setStatus(id, isPointedToDefault);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void handleClosed(int id)
    {
        try {
            GHPullRequest pr    = SparkyForge.getGitbot().getPullRequest(id);
            String        title = pr.getTitle();
            URL           url   = pr.getHtmlUrl();
            MessageUtils.sendLexHandledMessageToChannel("[GitHub] Pull Request: (" + title + "), has been Closed! " + Shorteners.gitIo(url));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
