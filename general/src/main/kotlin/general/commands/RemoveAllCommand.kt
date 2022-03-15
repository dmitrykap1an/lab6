package main.resources.commands

/**
 * Класс, представляющий собой команду, которая
 * удаляет из коллекции все элементы, значение поля description которого эквивалентно заданному
 */
class RemoveAllCommand : Command {


    constructor()
            : super("remove_all_by_description description", "удаляет из коллекции все элементы, значение поля description которого эквивалентно заданному")


}