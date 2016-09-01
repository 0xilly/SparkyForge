package us.illyohs.sparkyforge.util;

import java.io.IOException;
import java.net.URL;

import us.illyohs.sparkyforge.SparkyForge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHUser;

public class GitHubPullActionHelper
{

    public GitHubPullActionHelper(String json)
    {
        JsonParser parser = new JsonParser();
        JsonObject jObj   = parser.parse(json).getAsJsonObject();

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
            String defualtBranch       = SparkyForge.getGitbot().getDefualtBranch();
            boolean isPointedToDefault = SparkyForge.getGitbot().isPointedToDefualt(id);
            GHUser user = pr.getUser();
            URL url = pr.getHtmlUrl();

            String gitComment = "@" + user.getLogin() + " Your pull request is not pointed to the default branch __" +
                    defualtBranch + "__. Please retarget to __" + defualtBranch + "__. if you want this to be mered!";

            String ircNonMerge = user.getName() + "(" + user.getLogin() + ") has just open pull request: "+ pr.getTitle()
                    +", that is NOT pointed to the current default branch " + defualtBranch +
                    " and WON'T BE merged! " + url;

            String ircCanMerge = user.getName() + "(" + user.getLogin() + ") has just open pull request: "
                    + pr.getTitle() + ", that is  pointed to the current default branch " + defualtBranch +
                    " and CAN BE merged! " + url;


            if (!isPointedToDefault)
            {
                SparkyForge.getGitbot().setStatus(id, false);
                MessageUtils.sendMessageToChannel(ircNonMerge);
                MessageUtils.sendPullRequestMessage(id, gitComment);

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
            GHPullRequest pr = SparkyForge.getGitbot().getPullRequest(id);
            String title     = pr.getTitle();
            URL url          = pr.getHtmlUrl();

            String message = "Pull Request: " + title + ", has been reopened! " + url;
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
            GHPullRequest pr = SparkyForge.getGitbot().getPullRequest(id);
            String title     = pr.getTitle();
            URL url          = pr.getHtmlUrl();
            MessageUtils.sendLexHandledMessageToChannel("Pull Request: " + title + ", has been Closed! " + url);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
