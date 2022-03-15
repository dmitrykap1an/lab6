package general.commands

import main.resources.commands.Command


/**
 * Класс, представляющий собой команду, которая
 * выводит количество элементов, значение поля numberOfParticipants которых меньше заданного
 */
class CountCommand : Command {

    constructor()
            : super("count_less_than_number_of_participants numberOfParticipants", "выводит количество элементов, значение поля numberOfParticipants которых меньше заданного(numberOfParticipants - целое число)")

}