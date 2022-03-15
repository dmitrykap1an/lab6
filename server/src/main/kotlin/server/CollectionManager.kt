package server

import JavaClasses.MusicBand
import client.FileManager
import general.AppIO.InputData
import general.Exceptions.EmptyArgumentException
import java.time.LocalDateTime
import java.util.*
import kotlin.math.max

/**
 * Класс для работы с коллекцией музыкальных групп
 * @param lastSaveTime время последнего сохранения коллекции в файл
 * @param lastInitTime время последнего создания элемента коллекции
 */
class CollectionManager(private val fileManager: FileManager, private val inputData : InputData) {

    private var collectionOfMusicBands: MutableList<MusicBand> = LinkedList()
    private lateinit var lastSaveTime: LocalDateTime;
    private lateinit var lastInitTime: LocalDateTime;


    init{
        addFileCommandsToCollection()
    }

    fun update(id: String, musicBand: MusicBand?, list: List<String> = listOf()) {

        try {

            val newId = id.toInt()
            if(newId > collectionOfMusicBands.size || newId < 0){


            } else {
                for (i in collectionOfMusicBands.indices) {

                    if (collectionOfMusicBands[i].id == newId) {

                        if (list.isEmpty()) {

                            collectionOfMusicBands[i] = musicBand!!;
                            break;

                        } else {

                            val localParam = inputData.askMusicBand(list)
                            if (localParam != null)
                                collectionOfMusicBands[i] = localParam;
                        };

                        collectionOfMusicBands.stream().sorted(compareBy { it.name })

                    }
                }
            }
        } catch (e : NumberFormatException) {
            Server.outt.write("Данная строка не является числом")
        }catch (e : IllegalArgumentException) {
                Server.outt.write("Данная строка не является числом")
        }catch (e : NullPointerException){

        }finally {
            Server.outt.flush();
        }
}

    private fun addFileCommandsToCollection(){


        val collection = fileManager.collectionReader("bootData");
        var mx = 0;
        collection.forEach {
            mx = max(it.id, mx)
            collectionOfMusicBands.add(it)
        }
        MusicBand.setId(mx + 1)
        collectionOfMusicBands.stream().sorted(compareBy { it.name });
        lastInitTime = LocalDateTime.now();


    }

    fun info(){

        try {
            Server.outt.write(
                "Date of init : $lastInitTime\n" +
                    "Date of last save : $lastSaveTime\n" +
                    "Type : ${collectionOfMusicBands::class.simpleName}\n" +
                    "Count of elements : ${collectionOfMusicBands.size}\n"
            )


        }
        catch (e : UninitializedPropertyAccessException){

            try{
                Server.outt.write(
                    "Date of init : $lastInitTime\n" +
                        "Date of last save : the collection wasn't saved to a file\n" +
                        "Type : ${collectionOfMusicBands::class.simpleName}\n" +
                        "Count of elements : ${collectionOfMusicBands.size}\n"

                )
                Server.outt.flush()

            }
            catch (e : UninitializedPropertyAccessException){

                Server.outt.write(

                        "Date of init : the collection is empty\n" +
                            "Date of last save : the collection wasn't saved to a file\n" +
                            "Type : ${collectionOfMusicBands::class.simpleName}\n" +
                            "Count of elements : ${collectionOfMusicBands.size}\n"
                )

            }

        }
        finally {
            Server.outt.flush();
        }

    }

    fun show(){

        if (collectionOfMusicBands.size > 0) {
            collectionOfMusicBands.stream().forEach {
                Server.outt.write(it.toString() + "\n")
                Server.outt.flush();
            }
        }
        else {
            Server.outt.write("Коллекция пуста\n")
            Server.outt.flush();
        }

    }

    fun add(command : MusicBand?, list : List<String> = listOf()){

        val musicBand : MusicBand? = command ?: inputData.askMusicBand(list);

        if(musicBand != null)
            collectionOfMusicBands.add(musicBand);
            collectionOfMusicBands.stream().sorted(compareBy { it.name })
            lastInitTime = LocalDateTime.now();

    }

    fun clear(){

        collectionOfMusicBands.clear();
        Server.outt.write("Коллекция очищена")
        Server.outt.flush();

    }

    fun remove(id : String){

        try {

            val newId = id.toInt()
            if(newId <= collectionOfMusicBands.size) {

                for (i in collectionOfMusicBands.indices) {

                    if (collectionOfMusicBands[i].id == newId) {

                        collectionOfMusicBands.remove(collectionOfMusicBands[i])
                        Server.outt.write("Элемент удален")
                        break;
                    }

                    if(i == collectionOfMusicBands.size - 1) Server.outt.write("Id не найден")
                }
            } else Server.outt.write("Id не найден")

        } catch (e : NumberFormatException){
            Server.outt.write("Данная строка не является числом")
        }catch (e : IllegalArgumentException){
            Server.outt.write("Данная строка не является числом")
        }finally {
            Server.outt.flush();
        }

    }

    fun save(){

        fileManager.collectionWriter(collectionOfMusicBands);
        lastSaveTime = LocalDateTime.now();


    }


    fun removeFirst(){

        if(collectionOfMusicBands.size > 0) {

            collectionOfMusicBands.remove(collectionOfMusicBands[0]);
            Server.outt.write("Первый элемент удален")
        }
        else {
            Server.outt.write("Невозможно удалить первый элемент - коллекция пуста")
        }

        Server.outt.flush();

    }

    fun removeAllByDescription(description : String){

        var cnt = 0;

            for (i in collectionOfMusicBands.indices) {

                if (collectionOfMusicBands[i].description.equals(description)) {

                    collectionOfMusicBands.remove(collectionOfMusicBands[i]);
                    cnt++

                }
            }

        when {

            cnt == 0 -> Server.outt.write("Элемент по описанию не найден")

            cnt == 1 -> Server.outt.write("Элемент удален")

            cnt >= 2 -> Server.outt.write("Элементы удалены")

        }
        Server.outt.flush();
    }


    fun removeGreater(name : String){


            try {


                if(collectionOfMusicBands.isEmpty()) throw EmptyArgumentException()
                for(i in collectionOfMusicBands.indices) {


                    if (collectionOfMusicBands[i].name == name) {

                        collectionOfMusicBands = collectionOfMusicBands.subList(0, i + 1);
                        Server.outt.write("Элемент(ы) удален(ы)")
                        Server.outt.flush()
                        break;
                    }

                    if(i == collectionOfMusicBands.size - 1) Server.outt.write("Элемент не найден")
                }

            }catch (e : EmptyArgumentException) {
                Server.outt.write("Имя не найдено")
            }finally{
                Server.outt.flush();
            }
    }



    fun countLessThan(numberOfParticipants : String){

        try {

            val newNumber = numberOfParticipants.toLong();
            var cnt = 0;
            for(i in collectionOfMusicBands.indices){

                if(collectionOfMusicBands[i].numberOfParticipants < newNumber){

                    cnt++;
                }
            }
            Server.outt.write(cnt)
        }catch (e : NumberFormatException){
            Server.outt.write("Данная строка не является числом")
        }catch (e : IllegalArgumentException){
            Server.outt.write("Данная строка не является числом")
        }finally {
            Server.outt.flush();
        }
    }

    fun printlnFrontManDescending(){

        val newCollection = collectionOfMusicBands.asReversed();
        for(i in newCollection.indices){

            Server.outt.write(newCollection[i].frontMan.toString());
            Server.outt.flush();

        }
    }

}