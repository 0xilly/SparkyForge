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

package us.illyohs.sparkyforge.hooker;

import java.util.ArrayList;
import java.util.List;

import us.illyohs.sparkyforge.hooker.hooks.github.GitHubHooker;
import us.illyohs.sparkyforge.hooker.hooks.Hooker;
import us.illyohs.sparkyforge.hooker.hooks.jenkins.JenkinsHooker;
import us.illyohs.sparkyforge.util.ConfigUtil;

import static spark.Spark.*;


public class HookLoader
{
    private List<Hooker> hookList = new ArrayList<>();

//    Hooker hooker = new JenkinsHooker();

    public HookLoader()
    {
        hookList.add(new GitHubHooker());
        hookList.add(new JenkinsHooker());
    }



    public void loadHooks()
    {
        System.out.println("hooks listening on server on port " +ConfigUtil.getWebHookPort());
        port(ConfigUtil.getWebHookPort());
        init();

        hookList.forEach(hooker -> post("/" + hooker.getName(), hooker::init));
    }

}
