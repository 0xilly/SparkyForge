package us.illyohs.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import us.illyohs.sparkyforge.SparkyForge;

import org.kohsuke.github.GHLabel;
import org.kohsuke.github.PagedIterable;

public class LabelTest
{
    static ArrayList<String> ar = new ArrayList<>();
    public static void main(String... args) throws IOException
    {
        PagedIterable<GHLabel> labels = SparkyForge.getGitbot().getRepo().listLabels();

        labels.forEach(lab -> ar.add(lab.getName()));

        System.out.println(Arrays.toString(ar.toArray()));
        ar.clear();
        if (ar.isEmpty())
        {
            System.out.println("empty");
        }
    }

}
