package us.illyohs.sparkyforge.webhook.github;

import com.google.gson.Gson;

public class GitHookManager
{
    private String sig;
    private String evenType;
    private byte[] payload;

    public GitHookManager(String sig, String eventType, byte[] payload)
    {
        this.sig = sig;
        this.evenType = eventType;
        this.payload = payload;
        dumpPayLoad();
    }

    public void dumpPayLoad()
    {
//        System.out.println(getSig());
        Gson gson;


//        String s = new String(getPayload());
//        System.out.println(s);
        System.out.println(getEvenType());
    }

    public void EventDispatcher(String evenType)
    {
        switch (evenType)
        {
            case "push": ;
                break;
            case "issue":
                break;
            case "pull":
                break;
            case "pull_request":
        }
    }

    public String getEvenType()
    {
        return evenType;
    }

    public String getSig()
    {
        return sig;
    }

    public byte[] getPayload()
    {
        return payload;
    }
}
