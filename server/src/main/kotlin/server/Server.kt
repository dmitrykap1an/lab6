package server

import general.AppIO.CommandSerialize
import general.Exceptions.CloseSocketException
import org.apache.logging.log4j.LogManager
import java.io.*
import java.net.*


class Server(commandManager: CommandManager, port : Int, soTimeOut : Int) : Runnable{

    private lateinit var clientSocket : Socket//сокет для общения
    private var commandManager : CommandManager = commandManager
    private  val PORT : Int = port
    private val soTimeOut : Int = soTimeOut;
    private var server : ServerSocket? = null// серверный сокет
    companion object{
        internal val logger  = LogManager.getLogger(Server::class)
        internal lateinit var inn : ObjectInputStream;// поток чтения из сокета
        internal lateinit var outt : BufferedWriter;// поток записи в сокет
    }

    override fun run() {

            try {
                openServer()

                while (true) {
                    connectToClient()

                    try {
                        outt = BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream()))
                        inn = ObjectInputStream(clientSocket.getInputStream())
                        val command = inn.readObject() as CommandSerialize;
                        logger.info("Команда принята")
                        commandManager.addToHistory(command.getNameCommand())
                        commandManager.launchCommand(command)

                    } catch (e: EOFException) {

                    }catch (e : SocketException){

                    } finally {
                        clientSocket.close()
                        inn.close()
                        outt.close()
                    }
                }

            }catch (e : SocketException){
                logger.error("Потеряно соединение")
            } finally{
                serverStop()
            }
    }

    private fun serverStop() {
        try {
            logger.info("Завершение работы сервера...")
            println("Завершение работы сервера...")
            if (server == null) throw CloseSocketException()
            server!!.close()
            println("Работа сервера успешно завершена.")
            logger.info("Работа сервера успешно завершена.")
        } catch (exception: CloseSocketException) {
            println("Невозможно завершить работу сервера : сервер изначально был закрыт!")
            logger.error("Невозможно завершить работу сервера : сервер изначально был закрыт!")
        } catch (exception: IOException) {
            println("Произошла ошибка при завершении работы сервера!")
            logger.error("Произошла ошибка при завершении работы сервера!")
        }
    }

    private fun openServer() {
        try {
            logger.info("Запуск сервера...")
            println("Запуск сервера...")
            server = ServerSocket(PORT)
            server!!.soTimeout = soTimeOut
            logger.info("Сервер успешно запущен.")
            println("Сервер успешно запущен.")
        } catch (exception: IOException) {
            logger.fatal("Произошла ошибка при попытке использовать порт '$PORT'!")
            println("Произошла ошибка при попытке использовать порт '$PORT'!")
        }
    }

    /**
     * Connecting to client.
     */
    private fun connectToClient(){
        try {
            logger.info("Прослушивание порта '$PORT'...")
            clientSocket = server!!.accept()
            logger.info("Соединение с клиентом успешно установлено.")
            println("Соединение с клиентом успешно установлено.")

        } catch (exception: SocketTimeoutException) {
            logger.warn("Превышено время ожидания подключения!")
            println("Превышено время ожидания подключения!")
        } catch (exception: IOException){
            logger.error("Произошла ошибка при соединении с клиентом!")
            println("Произошла ошибка при соединении с клиентом!")
        }
    }
}



