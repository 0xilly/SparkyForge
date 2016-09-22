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
