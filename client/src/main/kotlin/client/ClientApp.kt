package client


import general.AppIO.InputData


fun main(){

    val inputData = InputData()
    val commandFinder = CommandFinder(inputData)
    val client = Client(commandFinder, 4004, "localhost");
    client.run()

}