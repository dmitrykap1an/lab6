package general.Exceptions

class CommandException : Exception() {

    override fun toString(): String {
        return "Ошибка ввода команды"
    }
}