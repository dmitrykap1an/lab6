package general.Exceptions

class CloseSocketException : Exception() {

    override fun toString(): String {

        return "Невозможно закрыть сокет"

    }
}