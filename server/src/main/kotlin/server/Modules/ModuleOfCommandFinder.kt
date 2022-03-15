package server.Modules

import dev.shustoff.dikt.Create
import dev.shustoff.dikt.ProvideSingle
import dev.shustoff.dikt.UseModules
import client.CommandFinder
import general.AppIO.InputData
import server.CollectionManager
import server.CommandManager


@UseModules(ModuleOfCommandManager::class)
class ModuleOfCommandFinder(private val moduleOfCommandManager: ModuleOfCommandManager, private val collectionManager: CollectionManager, private val inputData : InputData){


    @ProvideSingle
    private fun commandManager() : CommandManager;


    @Create
    fun commandFinder() : CommandFinder;

}