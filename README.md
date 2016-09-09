# SparkyForge

This is bot to check if pull request are pointed to the default branch of a
GitHub repository.


### Hook URLS

- github `http://127.0.0.1:port/github`
- jenkins `http://127.0.0.1:port/jenkins`
  - Note the jenkins hook needs the [notification plugin](https://wiki.jenkins-ci.org/display/JENKINS/Notification+Plugin) to work.

Example sparky.properties file that will be generated by the bot on its first run.
```
IRC.CommendOperator=.
IRC.Network=irc.esper.net
IRC.NickPassword=password
GitHub.Repository=<User/Organization>/Repository
IRC.Nick=SparkyForge
IRC.Channel=#channel
GitHub.Token=
IRC.Port=6666
GitHub.PermsJsonUrl=www.foo.com/Perms.json
WebHook.Port=2222
```
Example [perms.json](https://raw.githubusercontent.com/Illyohs/SparkyForge/master/perms.json)
```
{
  "IrcAccount": {
    "irc": "IrcAccount",
    "github": "githubLogin"
  },
  "LordIllyohs": {
    "irc":"LordIllyohs",
    "github": "Illyohs"
  },
  "FooUser": {
    "irc": "FooIrcUser",
    "github": "FooGitHubUser"
  }
}
```


When adding the webhook URL to github select the `Let me select individual events` and click on on `Pull request`


### Commands
```
default: Gets the default branch Forge.
prstatus: Usage: prstatus <id>               
reopen: Usage: reopen <id>
listlabels: Lists all the labels for the repo
ismerged: Usage <PR id>
labelpr: Usage: labelpr <id> <ls/rm/add>
close: Usage: close <id>
ghstatus: Usage: ghstatus
labelissue: Usage: labelissue <id> <ls/rm/add>
latest: Usage: latest
```

---

### TODOs
  - Jenkins support __DONE__
  - Support for more GitHub [events](https://developer.github.com/webhooks/) and create an event based framework __VERSION 2.0.0__
  - Force a status check from irc __DONE__
  - Some QOL irc features (may or may not happen)
    - Check the default branch from irc __DONE__
    - Check the latest  version of forge from irc and the latest recommended build __DONE__
    - Label management from irc __DONE__
    - Check Mojang status API
    - URL shortener for github URL __DONE__
    - docs command that links to the read the docs page
