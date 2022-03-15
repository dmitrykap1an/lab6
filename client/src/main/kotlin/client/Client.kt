package client

import general.Exceptions.CommandException
import java.io.*
import java.net.ConnectException
import java.net.Socket
import java.net.SocketException
import kotlin.system.exitProcess

class Client(commandFinder : CommandFinder, port : Int, host : String) : Runnable{


    private lateinit var clientSocket: Socket;//сокет для общения
    private lateinit var inn: BufferedReader; // поток чтения из сокета
    private lateinit var outt: ObjectOutputStream; // поток записи в сокет
    private val commandFinder : CommandFinder = commandFinder;
    private val PORT = port;
    private val HOST = host;
        companion object{ var checker = false}


    override fun run(){

        try{
            println("Клиент запущен")
            while(true) {
                try {

                    clientSocket = Socket( HOST, PORT) // запрашиваем у сервера доступ на соединение
                    inn = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                    outt = ObjectOutputStream(clientSocket.getOutputStream())
                    val command = commandFinder.commandSearcher() ?: throw CommandException()

                    outt.writeObject(command) // отправляем сообщение на сервер
                    outt.flush()
                    val serverWords = inn.readLines() // ждём, что скажет сервер
                    serverWords.forEach { println(it) } // получив - выводим на экран

                } catch (e: ConnectException) {
                            println("Связь нарушена")
                } catch (e : CommandException){
                            println("Команда не найдена")
                }catch (e : EOFException){
                            println("EOF")
                }catch (e : SocketException) {
                            println("Сервер закрыт")
                }finally {
                        clientSocket.close()
                        inn.close()
                        outt.close()
                    }
                }
        }catch (e : UninitializedPropertyAccessException){
            println("Сервер не доступен")
            exitProcess(0)
        }
    }





}