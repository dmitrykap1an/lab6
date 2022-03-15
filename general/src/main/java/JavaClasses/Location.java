package JavaClasses;

import java.io.Serializable;

public class Location implements Serializable {
    private Long x; //Поле не может быть null
    private int y;
    private Long z; //Поле не может быть null

    public Location(Long x, int y, Long z){

        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public String toString() {
        return "x : " + x + ", y : " + y + ", z : " + z;
    }
}
