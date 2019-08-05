package iwine;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class ConnectTh extends Thread
{
  final static boolean DEBUG = true;

  String connectName;
  Socket clientSocket;

  InputStream din;
  OutputStream out;

  WineServer serverSocket;

  public ConnectTh(WineServer serverSocket,
		   Socket clientSocket,
		   String connectName)
  {
    //super(serverSocket.list,connectName);
    super(connectName);
    setPriority(Thread.MAX_PRIORITY-2);

    this.connectName = connectName;
    this.serverSocket = serverSocket;
    this.clientSocket = clientSocket;
    //this.setStreams();

    if(DEBUG)System.out.println("ConnectTh("+this.connectName+"):");
  }

  public void start()
  {
    super.start();
  }

  public void setStreams()
  {
    try
    {
      setStreams(clientSocket.getInputStream(),clientSocket.getOutputStream());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public void setStreams(InputStream in,OutputStream out) throws IOException
  {
    this.din = in;
    this.out = out;
    /*
      if(DEBUG)System.out.println(" stream  in: "+this.din);
      if(DEBUG)System.out.println(" stream out: "+this.out);

      if(DEBUG)System.out.println("  in available: "+in.available());
     */
  }

  public void run()
  {
    if(DEBUG)System.out.println("ConnectTh.run() - beg");
    try
    {
	if(DEBUG)System.out.println("Send AVE");
	out.write("ave\n".getBytes());
	out.flush();

      DataInputStream sin = new DataInputStream(din);
      	while(sin.available()>0)
	{
	  String s = sin.readLine();
	  System.out.println("request: "+s);
	}

        // передать в выходной поток - значения

      out.write("answer\n".getBytes());
      out.flush();

      //if(DEBUG)System.out.println(" doubles: "+dd);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
	if(DEBUG)System.out.println("ConnectTh("+connectName+") finnaly...\n");
	clientSocket.close();
	stop();
      }
      catch(Exception ex)
      {
	System.out.println(this.connectName+" FATAL ERROR stop() :(");
	stop();
      }
    }
    // до сюда не доходит, run() прерывается вызовом stop()
  }
}
