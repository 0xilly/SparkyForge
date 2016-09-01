package us.illyohs.sparkyforge.util;

import java.io.IOException;

import us.illyohs.sparkyforge.SparkyForge;

import org.kitteh.irc.client.library.element.User;

public class MessageUtils
{
    /**
     * We don't want to ping lex
     * @param message
     * @return
     */
    private static String lexHelper(String message)
    {
        if (message.contains("LexManos")) {
            return message.replace("LexManos", "Lex");
        } else {
            return message;
        }
    }

    /**
     *
     * @param message
     */
    public static void sendLexHandledMessageToChannel(String message)
    {
        sendMessageToChannel(lexHelper(message));
    }

    public static void sendMessageToChannel(String message)
    {
        SparkyForge.getIrcbot().getConChannel().sendMessage(message);
    }

    /**
     *
     * @param user
     * @param message
     */
    public static void sendMessageToUser(User user, String message)
    {
        user.sendMessage(message);
    }

    public static void sendPullRequestMessage(int id, String comment)
    {
        try
        {
            SparkyForge.getGitbot().getPullRequest(id).comment(comment);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
