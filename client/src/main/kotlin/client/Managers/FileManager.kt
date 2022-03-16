package client.Managers


import JavaClasses.MusicBand
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import general.Exceptions.EmptyFileException
import general.Exceptions.FileNotTxt
import java.io.*
import java.lang.System.*
import java.lang.reflect.Type
import java.util.*
import kotlin.NoSuchElementException


class FileManager(outputData : String) {


    private val gson : Gson = Gson();
    private val output: String = outputData;


    /**
     * Функция для считывания скрипта из json файла через переменную окружения
     * Использовалась библиотека Gson от Google
     * @return коллекцию команд
     * @param envV переменная окружения
     * @throws JsonParseException если файл назначения пуст
     * @throws FileNotFoundException если файл не был найден
     */
    fun collectionReader(boot : String): MutableList<MusicBand> {

        if (getenv(boot) != null) try {

            Scanner(File(getenv(boot))).use { collectionRead ->

                val collection : MutableList<MusicBand>;
                val collectionType : Type = object : TypeToken<MutableList<MusicBand>>() {}.type;
                collection = gson.fromJson(collectionRead.nextLine().trim(), collectionType);
                return collection

            }
        } catch (e : FileNotFoundException) {
            println("Загрузочный файл не найден!")
        } catch (e : EmptyFileException) {
            println("Загрузочный файл пуст!")
        } catch (e : JsonParseException) {
            println("В загрузочном файле не обнаружена необходимая коллекция!")
        } catch (e : NullPointerException) {
            println("В загрузочном файле не обнаружена необходимая коллекция!")
        } catch (e : IllegalStateException) {
            println("Непредвиденная ошибка!")
        }catch (e : NoSuchElementException){
            println("Элементы не найдены")
        } else println("Переменная окружения с загрузочным файлом не найдена!")
        return LinkedList<MusicBand>()
    }


    /**
     * Функция для записи данных из коллекции в json-файл
     * Использовалась библиотека Gson от Google
     * @throws IOException если загрузочный файл не может быть открыт или
     * @param collection коллекция, состоящая из данных музыкальной группы
     * он является директорией
     */
    fun collectionWriter(collection: MutableList<MusicBand>) {

        if (getenv(output) != null) {
            try {
                BufferedWriter(FileWriter(File(getenv(output)))).use {
                    it.write(gson.toJson(collection));
                    println("Коллекция успешна сохранена в файл!");
                }
            } catch (e : IOException) {
                println("Загрузочный файл является директорией/не может быть открыт!")
            }

        } else println("Переменная окружения с загрузочным файлом не найдена!")
    }

    /**
     * Функция для считывания команд из файла
     * @throws FileNotFoundException если загрузочный файл не найден
     * @param fileName полное имя файла
     */
    fun scripReader(fileName : String) : List<String> {


        if (fileName.contains(Regex("\\w+\\.txt"))) {


            try {

                return BufferedReader(InputStreamReader(FileInputStream(fileName))).use { it.readLines() }

            } catch (e: FileNotFoundException) {
                println("Файл не найден")
            } catch (e: FileNotTxt) {
                println("Не обнаружен txt файл")
            }
            return LinkedList<String>()

        }
        return LinkedList<String>()
    }

    override fun toString(): String {
        return "FileManager - класс для работы с текстовыми файлами"
    }

};





