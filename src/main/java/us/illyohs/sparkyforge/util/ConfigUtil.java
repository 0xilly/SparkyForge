package us.illyohs.sparkyforge.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigUtil
{

    private static Path path = Paths.get("./sparky.cfg");

    private static Config config;

    public static void init()
    {
        try
        {
            if (!Files.exists(path))
            {
                Files.createFile(path);
                Writer w = new FileWriter(new String(Files.readAllBytes(path)));
                w.write(config.toString());
                w.close();
                System.out.println(path.toFile().toString());
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Config getConf() throws IOException
    {
            return Config.fromJson(new String(Files.readAllBytes(path)));
    }


}
