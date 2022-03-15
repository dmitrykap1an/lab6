package main.resources.commands

/**
 * Класс, представляющий собой команду, которая
 * выводит последние 6 команд (без их аргументов)
 */
class HistoryCommand : Command {

    constructor()
            : super("history", "выводит последние 6 команд (без их аргументов)")


}