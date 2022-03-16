package client.Managers


import general.AppIO.CommandSerialize
import general.AppIO.InputData
import general.Exceptions.CommandException


/**
 * Класс для обработки команд, введенных пользователем
 * и дальнейшего выполнения их
 */
class CommandFinder(private val inputData : InputData){


    fun commandSearcher() : CommandSerialize? {


                val userCommand = readLine()!!.split(" ").toList()

                val commandsLen1 : List<String> = listOf("help", "info", "show", "clear",
                     "remove_first", "history", "print_field_descending_front_man", "exit")
                val commandsLen2 : List<String> = listOf("remove_by_id", "execute_script",
                    "remove_greater", "remove_all_by_description", "count_less_than_number_of_participants")


                if(userCommand.size > 2) throw CommandException()
                userCommand.forEach { it.trim() }

        return when{

            userCommand.size == 1 && userCommand[0] in commandsLen1 -> CommandSerialize(userCommand[0]);

            userCommand.size == 2 && userCommand[0] in commandsLen2 -> CommandSerialize(userCommand[0], userCommand[1]);

            userCommand.size == 1 && userCommand[0] == "add"-> CommandSerialize(userCommand[0], musicBand = inputData.askMusicBand()!!);

            userCommand.size == 2  && userCommand[0] == "update" -> CommandSerialize(userCommand[0], userCommand[1], inputData.askMusicBand()!!)

            else -> null

        }
    }
}