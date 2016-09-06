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

    public static void sendIssueMessage(int id, String comment)
    {
        try
        {
            SparkyForge.getGitbot().getIssue(id).comment(comment);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    public static void sendIssueMessage(int id, String comment)
//    {
//        try
//        {
//            SparkyForge.getGitbot().getIssue(id).comment(comment);
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
