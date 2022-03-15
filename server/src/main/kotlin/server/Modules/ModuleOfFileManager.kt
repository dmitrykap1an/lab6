package server.Modules

import dev.shustoff.dikt.Create
import server.FileManager

class ModuleOfFileManager(val outputData : String) {

    @Create
    fun fileManager() : FileManager;

}