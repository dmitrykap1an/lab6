package general.commands

import main.resources.commands.Command

/**
 * Класс, представляющий собой команду, которая
 * выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
class ShowCommand : Command {

   constructor() : super("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении")

}