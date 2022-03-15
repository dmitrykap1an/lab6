package server.Modules

import main.resources.commands.*
import dev.shustoff.dikt.*
import general.commands.*
import server.CollectionManager
import server.CommandManager
import server.FileManager


@UseModules(ModuleOfCollectionManager::class, ModuleOfFileManager::class)
class ModuleOfCommandManager(
    private val moduleOfCollectionManager: ModuleOfCollectionManager,
    private val moduleOfFileManager: ModuleOfFileManager,
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
//    private val saveCommand: SaveCommand,
    private val showCommand: ShowCommand,
    private val updateIDCommand: UpdateIDCommand,
    private val exitCommand : ExitCommand,
) {

    @Create
    fun commandManager() : CommandManager

    @ProvideSingle
    private fun collectionManager() : CollectionManager;

    @ProvideSingle
    private fun fileManager() : FileManager;


}