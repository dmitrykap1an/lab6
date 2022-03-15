package general.Exceptions

class FileNotTxt : Exception() {

    override fun toString(): String {

        return "файл неверного формата"

    }
}