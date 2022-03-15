package general.commands

import main.resources.commands.Command


/**
 * Класс, представляющий собой команду, которая
 * обновляет значение элемента коллекции, id которого равен заданному
 */
class UpdateIDCommand : Command {

    constructor()
            : super("update id", "обновляет значение элемента коллекции, id которого равен заданному(id - целое число, большее, чем 0)")

}