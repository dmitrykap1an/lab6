package JavaClasses;

import java.io.Serializable;
import java.time.LocalDate;


public class MusicBand implements Serializable {
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Long numberOfParticipants;
    private String description;
    private MusicGenre genre;
    private Person frontMan;
    private static int counter = 1;

   public MusicBand(String name, Coordinates coordinates, Long numberOfParticipants,
              String description, MusicGenre genre, Person frontMan){

        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.numberOfParticipants = numberOfParticipants;
        this.description = description;
        this.genre = genre;
        this.frontMan = frontMan;
        id = counter++;

    }

    public static void setId(int id){

       counter = id;

    }


    public int getId(){

        return id;
    }

    public String getName(){

        return name;

    }

    public String getDescription(){

        return description;

    }

    public Long getNumberOfParticipants(){

        return numberOfParticipants;

    }


    public Person getFrontMan(){

        return frontMan;

    }


    @Override
    public String toString(){

        return "Id группы : " + id +
                "\nНазвание группы : " + name +
                "\nДата создания элемента : " +  creationDate.toString() +
                "\nКоординаты местоположения группы : " + coordinates.toString() +
                "\nКоличество участников : " + numberOfParticipants +
                "\nОписание группы : " + description +
                "\nМузыкальный жанр : " + genre.toString() +
                "\nСолист : " + frontMan;
    }
}
