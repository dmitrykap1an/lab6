package general.AppIO

import JavaClasses.MusicBand
import java.io.Serializable

class CommandSerialize : Serializable {

    private  val commandName : String;
    private val commandArgument : String?;
    private val musicBand : MusicBand?;


    constructor(commandName : String, commandArguments : String, musicBand : MusicBand){

        this.commandName = commandName;
        this.commandArgument = commandArguments;
        this.musicBand = musicBand;

    }

    constructor(commandName: String, musicBand : MusicBand){

        this.commandName = commandName;
        this.commandArgument = null;
        this.musicBand = musicBand;

    }
    constructor(commandName: String){

        this.commandName = commandName;
        this.commandArgument = null;
        this.musicBand = null;
    }
    constructor(commandName: String, commandArgument: String){

        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.musicBand = null;
    }





    fun getNameCommand() : String{

        return commandName;

    }

    fun getCommandArgument() : String?{

        return commandArgument;

    }

    fun getMusicBand() : MusicBand{

        return musicBand!!

    }

    override fun toString(): String {

        return "CommandSerialize($commandName, $commandArgument)";

    }

}