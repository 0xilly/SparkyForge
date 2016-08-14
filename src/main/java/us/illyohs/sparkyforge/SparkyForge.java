package us.illyohs.sparkyforge;

import us.illyohs.sparkyforge.webhook.SparkyServer;

import com.google.common.eventbus.EventBus;

public class SparkyForge
{
    public static EventBus EVENT_BUS = new EventBus();

    static SparkyServer server;

    public static void main(String... args)
    {
//        SparkyForge
        server = new SparkyServer(2222);
    }
}
