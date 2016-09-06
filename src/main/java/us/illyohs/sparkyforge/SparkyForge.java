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

package us.illyohs.sparkyforge;

import us.illyohs.sparkyforge.bots.irc.IrcBot;
import us.illyohs.sparkyforge.hooker.HookLoader;
import us.illyohs.sparkyforge.util.ConfigUtil;
import us.illyohs.sparkyforge.util.GitHubHelper;

public class SparkyForge
{
    private static GitHubHelper gitbot;
    private static IrcBot       ircbot;
    private static HookLoader loader = new HookLoader();



    public static void main(String... args)
    {
        ircbot = new IrcBot(
                ConfigUtil.getIrcNetwork(),
                ConfigUtil.getIrcPort(),
                ConfigUtil.getIrcNick(),
                ConfigUtil.getIrcPass(),
                ConfigUtil.getIrcChannel()
        );

        ircbot.connect();

    }

    public static IrcBot getIrcbot()
    {
        return ircbot;
    }

    public static GitHubHelper getGitbot()
    {
        return new GitHubHelper(ConfigUtil.getGHToken(), ConfigUtil.getGHRepo());
    }
}
