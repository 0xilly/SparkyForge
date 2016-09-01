package us.illyohs.sparkyforge.bots.github;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import static org.kohsuke.github.GHCommitState.*;


public class GitHubBot
{
    String repo;
    GitHub gitHub;

    public GitHubBot(String token, String repo)
    {
        this.repo = repo;
        try
        {
            this.gitHub = GitHub.connectUsingOAuth(token);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public GHRepository getRepo() throws IOException
    {
        return gitHub.getRepository(repo);
    }

    public String getDefualtBranch() throws IOException
    {
        return getRepo().getDefaultBranch();
    }

    public GHPullRequest getPullRequest(int id) throws IOException
    {
        return getRepo().getPullRequest(id);
    }

    public String getPullRequestTitle(int id) throws IOException
    {
        return getPullRequest(id).getTitle();
    }

    public GHUser getPulAuthor(int id) throws IOException
    {
        return getPullRequest(id).getUser();
    }

    public GHIssueState isOpen(int id) throws IOException
    {
        return getPullRequest(id).getState();
    }

    public boolean isMerged(int id) throws IOException
    {
        return getPullRequest(id).isMerged();
    }

    public URL getPullRequestURL(int id) throws IOException
    {
        return getPullRequest(id).getHtmlUrl();
    }

    public boolean isPointedToDefualt(int id) throws IOException
    {
        return Objects.equals(getDefualtBranch(), getPullRequest(id).getBase().getRef());
    }

    public boolean haveIMadeAComment(int id) throws IOException
    {
        for(GHIssueComment i: getPullRequest(id).getComments()) {
            if (i.getUser() == gitHub.getMyself()) {
                return true;
            }
        }
        return false;
    }

    public void setStatus(int id, boolean isGood) throws IOException
    {
        GHPullRequest pr = getPullRequest(id);
        for (GHPullRequestCommitDetail i: pr.listCommits().asList())
        {
            String sha1 = i.getSha();
            if (!isGood) {
                getRepo().createCommitStatus(sha1, FAILURE, "", "Is not pointed to the default branch");
            } else {
                getRepo().createCommitStatus(sha1, SUCCESS, "", "Is pointed to the default branch");
            }
        }
    }
}
