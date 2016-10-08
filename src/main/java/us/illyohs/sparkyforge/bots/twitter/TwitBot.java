package us.illyohs.sparkyforge.bots.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Created by illyohs on 10/8/16.
 */
public class TwitBot
{

    Twitter twitter;

    public TwitBot(String conSecret, String conKey, String accToken, String accTokenSecret)
    {
        twitter                 = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(conKey, conSecret);
        AccessToken accessToken = new AccessToken(accToken, accTokenSecret);
        twitter.setOAuthAccessToken(accessToken);
    }

    public void postTweet(String message)
    {
        try {
            twitter.updateStatus(message);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}
