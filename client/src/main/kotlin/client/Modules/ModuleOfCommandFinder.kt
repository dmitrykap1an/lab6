package client.Modules

import client.Managers.CommandFinder
import dev.shustoff.dikt.Create
import general.AppIO.InputData

class ModuleOfCommandFinder(private val inputData : InputData){

    @Create
    fun commandFinder() : CommandFinder;

}