package server

import client.Client
import general.AppIO.InputData
import general.commands.*
import main.resources.commands.*
import server.Modules.*

fun main(){

    val moduleOfFileManager = ModuleOfFileManager("outputData")
    val moduleCollectionManager = ModuleOfCollectionManager(moduleOfFileManager)
    val moduleOfCommandManager = ModuleOfCommandManager(
        moduleCollectionManager,
        moduleOfFileManager,
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
    )
    val inputData = InputData()
    val collectionManager = moduleCollectionManager.collectionManager()
    val commandFinder = ModuleOfCommandFinder(moduleOfCommandManager, collectionManager, inputData).commandFinder()
    val client = Client(commandFinder, 4004, "localhost");
    client.run()

}