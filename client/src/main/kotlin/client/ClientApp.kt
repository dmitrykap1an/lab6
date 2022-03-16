package client


import client.Modules.ModuleOfCommandFinder
import general.AppIO.InputData


fun main(){

    val inputData = InputData()
    val moduleOfCommandFinder = ModuleOfCommandFinder(inputData)
    val commandFinder = moduleOfCommandFinder.commandFinder()
    val client = Client(commandFinder, 4004, "localhost");
    client.run()

}