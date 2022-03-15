package JavaClasses;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public enum MusicGenre implements Serializable {
    RAP,
    HIP_HOP,
    BLUES,
    POP,
    MATH_ROCK;

    public static List<String> toListString(){

        LinkedList<String> list = new LinkedList<String>();
        list.add("RAP");
        list.add("HIP_HOP");
        list.add("BLUES");
        list.add("POP");
        list.add("MATH_ROCK");

        return list;
    }

}