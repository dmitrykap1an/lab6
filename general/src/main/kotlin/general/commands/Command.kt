package main.resources.commands

/**
 * Базовый класс для всех команд
 */
abstract class Command(name : String, description : String) {

    private val name : String;
    private val description : String;

    init{

        this.name = name;
        this.description = description;

    }

    fun getNameCommand() = name;


    override fun toString(): String {

        return "$name : $description"

    }

}