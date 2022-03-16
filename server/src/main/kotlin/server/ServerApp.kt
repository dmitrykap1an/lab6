package server

import general.commands.*
import main.resources.commands.*
import server.Modules.ModuleOfCollectionManager
import server.Modules.ModuleOfCommandManager
import server.Modules.ModuleOfFileManager


fun main(){

    /**
     * @author Dmitry Kaplan P3112
     * Лабораторная работа №6 по программированию
     * Главная функция, которая вызывает Server
     */

    val outputData = "outputData";

    val moduleOfFileManager = ModuleOfFileManager(outputData)
    val moduleOfCollectionManager = ModuleOfCollectionManager(moduleOfFileManager)
    val moduleOfCommandManager = ModuleOfCommandManager(
        moduleOfCollectionManager,
        moduleOfFileManager ,
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
        ExitCommand()
    )
    val commandManager = moduleOfCommandManager.commandManager()
    val server = Server(commandManager, 4004, 60*10000);
    server.run();
//    Thread(server).start();
//    Thread(client).start();
}



