package server.Modules

import dev.shustoff.dikt.Create
import client.Managers.FileManager

class ModuleOfFileManager(val outputData : String) {

    @Create
    fun fileManager() : FileManager;

}