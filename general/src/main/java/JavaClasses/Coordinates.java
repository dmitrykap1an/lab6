package JavaClasses;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private Long x; //Максимальное значение поля: 220, Поле не может быть null
    private double y;

    public Coordinates(Long x, double y){

        this.x = x;
        this.y = y;

    }


    @Override
    public String toString(){

        return "x : " + x + ", y : " + y;
    }
}