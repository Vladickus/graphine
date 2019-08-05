package iwine;

import java.net.ServerSocket;
import java.io.*;
import java.net.Socket;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class WineServer extends Thread
{
  final static boolean DEBUG = true;

  Thread proc;
  ServerSocket server;
  int port;
  boolean working;

  public WineServer() throws IOException
  {
    this(8088);
  }

  public WineServer(int port) throws IOException
  {
    this.port = port;
    this.working = true;
    server = new ServerSocket(port,30);
    System.out.println("start: "+server);
    start();
  }

  public void run()
  {
    try
    {
      int conNumber = 0; // счетчик заведенных Connectorov
      String conName = "connect_";

      while(working)
      {
	// кто-то подключился
	Socket clientSocket = server.accept();

	// создать для него соединение в отдельном потоке
	ConnectTh con = new ConnectTh(this,clientSocket,conName+conNumber++);
	con.setStreams();
	con.start();
	yield();
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.out.println("stop: "+server);
      stopp();
    }
    //System.out.println("stop: "+serverSocket);
  }

  public void stopp()
  {
    working = false;
    //stop();
  }

  public int getPort()
  {
    return port;
  }

  public static void main(String[] args) throws IOException
  {
    WineServer ws = new WineServer();
    System.out.println("Запущен WineServer("+ws.getPort()+")...");
    //ws.start();
  }

}
