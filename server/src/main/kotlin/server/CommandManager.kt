package server

import client.Client
import general.commands.*
import general.AppIO.CommandSerialize
import main.resources.commands.*


/**
 * Класс для работы с командами, введенными пользователем
 * Хранит все команды и историю последних 6 введенных команд
 */
class CommandManager(
    private val helpCommand: HelpCommand,
    private val addCommand: AddCommand,
    private val clearCommand: ClearCommand,
    private val countCommand: CountCommand,
    private val executeScriptCommand: ExecuteScriptCommand,
    private val historyCommand: HistoryCommand,
    private val infoCommand: InfoCommand,
    private val printFieldCommand: PrintFieldCommand,
    private val removeAllCommand: RemoveAllCommand,
    private val removeByID: RemoveByID,
    private val removeFirstCommand: RemoveFirstCommand,
    private val removeGreaterCommand: RemoveGreaterCommand,
    private val showCommand: ShowCommand,
    private val updateIDCommand: UpdateIDCommand,
    private val exitCommand : ExitCommand,
    private val collectionManager: CollectionManager,
    private val fileManager: FileManager
) {

    private val MAXLENGTH = 6;
    private var commands : MutableList<Command> = mutableListOf();
    private var commandsHistory : MutableList<String> = mutableListOf();


    init {
        commands.add(helpCommand)
        commands.add(addCommand)
        commands.add(clearCommand)
        commands.add(countCommand)
        commands.add(executeScriptCommand)
        commands.add(historyCommand)
        commands.add(infoCommand)
        commands.add(printFieldCommand)
        commands.add(removeAllCommand)
        commands.add(removeByID)
        commands.add(removeFirstCommand)
        commands.add(removeGreaterCommand)
        commands.add(showCommand)
        commands.add(updateIDCommand)
        commands.add(exitCommand)
    }


    fun addToHistory(command : String){

            if(commandsHistory.size == 6) {

                commandsHistory = commandsHistory.subList(1, MAXLENGTH)
                commandsHistory.add(command);

            }
            else commandsHistory.add(command);

    }

    private fun getCommandsString() : MutableList<String>{

        val commandsString = mutableListOf<String>();
        for(i in commands.indices){

            commandsString.add(commands[i].getNameCommand().split(' ')[0])

        }

        return commandsString;

    }

    private fun help(){


            for(i in commands.indices){

                Server.outt.write(commands[i].toString() + "\n")

        }
        Server.outt.flush()
    }


    private fun executeScript(fileName : String){

        val list =  fileManager.scripReader(fileName);
        var i = 0;
        while(i < list.size){

            val localList = list[i].split(' ')
            if(localList[0] in getCommandsString()){


                when{

                    localList[0] == "add" && localList.size == 1-> {
                        collectionManager.add(null, list.subList(i + 1, i + 12));
                        i += 12;
                    };

                    localList[0] == "update" && localList.size == 2 -> {

                        collectionManager.update(localList[1], null, list.subList(i + 1, i + 12))
                        i += 12;

                    }

                    else ->{

                        if(localList.size == 1) launchCommand(CommandSerialize(localList[0]))
                        else launchCommand(CommandSerialize(localList[0], localList[1]))
                        i += 1;

                    }
                }
            }
        }
    }

    fun launchCommand(userCommand: CommandSerialize){

        when{

            userCommand.getNameCommand() == "help"-> help()

            userCommand.getNameCommand() == "info" ->  collectionManager.info()

            userCommand.getNameCommand() == "show" ->  collectionManager.show()

            userCommand.getNameCommand() == "add" -> collectionManager.add(userCommand.getMusicBand())

            userCommand.getNameCommand() == "update" -> collectionManager.update(userCommand.getCommandArgument()!!, userCommand.getMusicBand())

            userCommand.getNameCommand() == "remove_by_id" -> collectionManager.remove(userCommand.getCommandArgument()!!)

            userCommand.getNameCommand() == "clear" -> collectionManager.clear()

            userCommand.getNameCommand() == "execute_script" -> executeScript(userCommand.getCommandArgument()!!);


            userCommand.getNameCommand() == "exit" -> {

                Server.outt.write("Программа завершена")
                Server.outt.flush()
                save();

                Client.checker = true;

            }

            userCommand.getNameCommand() == "remove_first" -> collectionManager.removeFirst()

            userCommand.getNameCommand() == "remove_greater" -> collectionManager.removeGreater(userCommand.getCommandArgument()!!);

            userCommand.getNameCommand() == "history" -> history();

            userCommand.getNameCommand() == "remove_all_by_description" -> collectionManager.removeAllByDescription(userCommand.getCommandArgument()!!);

            userCommand.getNameCommand() == "count_less_than_number_of_participants" -> collectionManager.countLessThan(userCommand.getCommandArgument()!!);

            userCommand.getNameCommand() == "print_field_descending_front_man" -> collectionManager.printlnFrontManDescending();

            else -> {
                Server.outt.write("Команда не найдена")
                Server.outt.flush()
                Server.logger.info("CommandManager :Команда не обнаружена");
            }



        }
    }

    fun save(){

        collectionManager.save()

    }

    private fun history(){

        commandsHistory.forEach{
            Server.outt.write(it + "\n");
        }
        Server.outt.flush();
    }

}