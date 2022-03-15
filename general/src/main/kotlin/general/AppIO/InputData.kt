package general.AppIO

import JavaClasses.*
import general.Exceptions.EmptyArgumentException
import general.Exceptions.IllegalValueException

import java.util.*

/**
 *  Класс для ввода данных о музыкальной группе
 */
class InputData{

    private val MAX_X = 220
    private val MIN_HEIGHT = 1;

    private fun askName(): String {
        var name: String
        while (true) {

            try {
                println("Введите название группы :")
                name = readLine()!!.trim()
                if (name == "") throw EmptyArgumentException()
                break;
            } catch (e : NoSuchElementException) {
                println("Название группы не распознано!")
            } catch (e : EmptyArgumentException) {
                println("Название группы не может быть пустым!")
            } catch (e : IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return name
    }

    private fun askX(): Long {

        var strX: String;
        var x: Long;

            while (true) {

                try {
                    println("Введите координату X < ${MAX_X + 1} :")
                    strX = readLine()!!.trim()
                    x = strX.toLong();
                    if(x > MAX_X) throw IllegalValueException();
                    break
                } catch (e : NoSuchElementException) {
                    println("Координата X не распознана!")
                } catch (e : NumberFormatException) {
                    println("Координата X должна быть представлена числом!")
                } catch (e : NullPointerException) {
                    println("Непредвиденная ошибка!")
                }catch (e : IllegalValueException){
                    println("Значение больше, чем $MAX_X!")
                } catch (e : IllegalStateException) {
                    println("Непредвиденная ошибка!")
                }
            }
            return x
        }

    private fun askY(): Double{

        var strY: String
        var y: Double
        while (true) {

            try {
                println("Введите координату Y : ")
                strY = readLine()!!.trim()
                y = strY.toDouble();
                break;
            } catch (e : NoSuchElementException) {
                println("Координата Y не распознана!")
            } catch (e : NumberFormatException) {
                println("Координата Y должна быть представлена числом!")
            } catch (e : NullPointerException) {
                println("Непредвиденная ошибка!")
            } catch (exception: IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return y
    }

    private fun askCoordinates(): Coordinates {
        val x: Long = askX()
        val y: Double = askY()
        return Coordinates(x, y)
    }

    private fun askX2(): Long {
        var strX: String;
        var x: Long;
        while (true) {

            try {
                println("Введите местоположение по координате X :")
                strX = readLine()!!.trim()
                x = strX.toLong();
                break
            } catch (e : NoSuchElementException) {
                println("Координата X не распознана!")
            } catch (e : NumberFormatException) {
                println("Координата X должна быть представлена числом!")
            } catch (e : NullPointerException) {
                println("Непредвиденная ошибка!")
            } catch (e : IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return x
    }


    private fun askY2() : Int{

            var strY : String
            var y : Int
            while (true) {

                try {
                    println("Введите местоположение по координате Y : ")
                    strY = readLine()!!.trim()
                    y = strY.toInt();
                    break;
                } catch (e : NoSuchElementException) {
                    println("Координата Y не распознана!")
                } catch (e : NumberFormatException) {
                    println("Координата Y должна быть представлена числом!")
                } catch (exception: IllegalStateException) {
                    println("Непредвиденная ошибка!")
                }
            }
            return y
    }


    private fun askZ(): Long{

        var strZ : String;
        var z : Long;
        while (true) {

            try {
                println("Введите местоположение по координате Z : ")
                strZ = readLine()!!.trim()
                z = strZ.toLong();
                break;
            } catch (e : NoSuchElementException) {
                println("Координата Z не распознана!")
            } catch (e : NumberFormatException) {
                println("Координата Z должна быть представлена числом!")
            } catch (e : NullPointerException) {
                println("Непредвиденная ошибка!")
            } catch (exception: IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return z
    }

    private fun askLocation() : Location {

        val x = askX2();
        val y = askY2();
        val z = askZ();

        return Location(x, y, z);

    }

    private fun askHeight() : Int{

        var strHeight : String;
        var height : Int;
        while (true) {

            try {
                println("Введите рост : ")
                strHeight = readLine()!!.trim()
                height = strHeight.toInt();
                if(height < MIN_HEIGHT) throw IllegalValueException()
                break;
            } catch (e : NoSuchElementException) {
                println("Рост не распознан!")
            } catch (e : NumberFormatException) {
                println("Рост должен быть представлен числом!")
            }catch (e : IllegalValueException){
                println("Рост должен быть не меньше, чем $MIN_HEIGHT")
            } catch (e : NullPointerException) {
                println("Непредвиденная ошибка!")
            } catch (exception: IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return height;
    }

    private fun askNameOfFrontMan(): String {
        var name: String
        while (true) {

            try {
                println("Введите имя солиста :")
                name = readLine()!!.trim()
                if (name == "") throw EmptyArgumentException()
                break;
            } catch (e : NoSuchElementException) {
                println("Имя не распознано!")
            } catch (e : EmptyArgumentException) {
                println("Имя не может быть пустым!")
            } catch (e : IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return name
    }

    private fun askPerson() : Person {

        val name = askNameOfFrontMan();
        val height = askHeight();
        val location : Location = askLocation();

        return Person(name, height, location);

    }


    private fun askNumberOfParticipants() : Long{

        var strNumber : String;
        var number : Long;
        while (true) {

            try {
                println("Введите количество участников(участников должно быть не меньше, чем $MIN_HEIGHT) : ")
                strNumber = readLine()!!.trim()
                number = strNumber.toLong();
                if(number < MIN_HEIGHT) throw IllegalValueException()
                break;
            } catch (e : NoSuchElementException) {
                println("Введите количество участников не распознано!")
            } catch (e : NumberFormatException) {
                println("Введите количество участников должно быть представлено числом!")
            }catch (e : IllegalValueException){
                println("Участников должно быть не меньше, чем $MIN_HEIGHT")
            } catch (e : NullPointerException) {
                println("Непредвиденная ошибка!")
            } catch (e : IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return number;
    }

    private fun askDescription() : String{

        var description : String
        while (true) {

            try {
                println("Введите описание:")
                description = readLine()!!.trim()
                break;
            } catch (e : NoSuchElementException) {
                println("Имя не распознано!")
            } catch (e : IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return description
    }

    private fun askMusicGenre(): MusicGenre {
        var strGenre: String
        var genre: MusicGenre;

        while (true) {
            try {
                println("Список музыкальных жанров - " + MusicGenre.toListString())
                println("Введите музыкальный жанр:")
                strGenre = readLine()!!.trim();
                genre = MusicGenre.valueOf(strGenre.uppercase());
                break
            } catch (e : NoSuchElementException) {
                println("Музыкальный жанр не распознан!")
            } catch (e : IllegalArgumentException) {
                println("Музыкального жанра нет в списке!")
            } catch (e : IllegalStateException) {
                println("Непредвиденная ошибка!")
            }
        }
        return genre;
    }

    fun askMusicBand(list : List<String> = listOf()) : MusicBand? {

        if (list.isEmpty()) {

            val name = askName();
            val coordinates: Coordinates = askCoordinates();
            val numberOfParticipants = askNumberOfParticipants();
            val description = askDescription();
            val genre = askMusicGenre();
            val frontMan = askPerson();
            println("Музыкальная группа успешно добавлена")

            return MusicBand(
                name,
                coordinates,
                numberOfParticipants,
                description,
                genre,
                frontMan
            );

        } else{

            try{

                val name = list[0];
                val coordinates = Coordinates(list[1].toLong(), list[2].toDouble())
                val numberOfParticipants = list[3].toLong();
                val description = list[4]
                val genre = MusicGenre.valueOf(list[5].uppercase());
                val frontMan = Person(
                    list[6],
                    list[7].toInt(),
                    Location(list[8].toLong(), list[9].toInt(), list[10].toLong())
                )
                println("Музыкальная группа успешно добавлена")

                return MusicBand(
                    name,
                    coordinates,
                    numberOfParticipants,
                    description,
                    genre,
                    frontMan
                )

            }catch (e : NumberFormatException){
                println("Недопустимое значение")
                return null
            }

        }
    }


    override fun toString(): String {
        return "InputData (класс для запросов пользователю)"
    }

}