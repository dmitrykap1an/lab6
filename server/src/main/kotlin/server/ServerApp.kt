package server

import client.FileManager
import general.AppIO.InputData
import general.commands.*
import main.resources.commands.*


fun main(){

    /**
     * @author Dmitry Kaplan P3112
     * Лабораторная работа №6 по программированию
     * Главная функция, которая вызывает Server
     */

    val outputData = "outputData";

    val fileManager = FileManager(outputData)
    val inputData = InputData()
    val collectionManager = CollectionManager(fileManager, inputData)
    val commandManager = CommandManager(
        HelpCommand(),
        AddCommand(),
        ClearCommand(),
        CountCommand(),
        ExecuteScriptCommand(),
        HistoryCommand(),
        InfoCommand(),
        PrintFieldCommand(),
        RemoveAllCommand(),
        RemoveByID(),
        RemoveFirstCommand(),
        RemoveGreaterCommand(),
        ShowCommand(),
        UpdateIDCommand(),
        ExitCommand(),
        collectionManager,
        fileManager
    )
    val server = Server(commandManager, 4004, 60*10000);
    server.run();
//    Thread(server).start();
//    Thread(client).start();
}



