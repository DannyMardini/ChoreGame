package com.mardinistudios.choregame.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andeecoba on 4/26/16.
 */
public class DataStub {

    public static List<Chore> GetChores()
    {
        List<Chore> data = new ArrayList<Chore>();

        String[] chores = new String[] {
                "Do the dishes",
                "Take out the trash",
                "clean up after the dog",
                "Make the oats",
                "Water the plants"
        };

        int[] points = new int[] {
                3,
                2,
                1,
                1,
                1
        };

        for (int i = 0; i < chores.length; i++) {
            String name = chores[i];
            int point = points[i];
            Chore chore = new Chore(name, point);
            data.add(chore);
        }

        return data;
    }
}
