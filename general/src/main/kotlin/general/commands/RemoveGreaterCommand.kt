package general.commands

import main.resources.commands.Command

/**
 * Класс, представляющий собой команду, которая
 * удаляет из коллекции все элементы, превышающие заданный
 */
class RemoveGreaterCommand : Command {

    constructor()
            : super("remove_greater name", "удаляет из коллекции все элементы, превышающие заданный(параметр name - название группы)")

}