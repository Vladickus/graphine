package iwine;

import java.applet.*;
import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import beans.util.events.*;
import com.borland.jbcl.layout.*;
import meumlay.layouts.*;
import java.awt.event.*;
import beans.util.look.Nimbus;
import beans.util.look.SorbonnaMetalTheme;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class AppServer extends Applet implements AppConnectX,Runnable,
    PropertyCodeListener,ActionListener
{
  private boolean isStandalone = false;
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JEditorPane jEditorPane1 = new JEditorPane();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel(); // = new CommandComboBox(new String[]{});// = new CommandComboBox(new String[]{});

  boolean working = !true;
  Thread th = null;
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  JPanel jPanel5 = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JPanel jPanel6 = new JPanel();
  JButton jButton_clear = new JButton();
  private transient Vector propertyCodeListeners;
  LabeledPairLayout labeledPairLayout1 = new LabeledPairLayout();
  JLabel jLabel_host = new JLabel();
  JTextField jTextField_host = new JTextField();
  //Get a parameter value
  public String getParameter(String key,String def)
  {
    return isStandalone?System.getProperty(key,def):
	(getParameter(key)!=null?getParameter(key):def);
  }

  //Construct the applet
  public AppServer()
  {
    if(!Nimbus.install())SorbonnaMetalTheme.install();
  }

  //Initialize the applet
  public void init()
  {
    try
    {
      jbInit();

      if(working)
      {
	if(th!=null)
	{
	  th.stop();
	  th = null;
	}

	th = new Thread(this);
	th.start();
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception
  {
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,
			       new Color(142,142,142));
    titledBorder1 = new TitledBorder(border1,"HTTP Server");
    border2 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"HTTP Server"),BorderFactory.createEmptyBorder(10,10,10,10));
    this.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    jScrollPane1.setDoubleBuffered(false);

    String[] fromComands =
	{"get-temperature"};
    String[] toCommands =
	{"temperture"};

    jPanel1.setBorder(border2);
    jPanel3.setLayout(verticalFlowLayout1);
    jButton_clear.setActionCommand("clear");
    jButton_clear.setText("Clear");
    jButton_clear.addActionListener(this);
    jPanel5.setLayout(labeledPairLayout1);
    jLabel_host.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_host.setHorizontalTextPosition(SwingConstants.RIGHT);
    jLabel_host.setText("Host:");
    jTextField_host.setBackground(UIManager.getColor("Panel.background"));
    //jTextField_host.setBackground(SystemColor.info);
    jTextField_host.setEditable(false);
    this.add(jPanel1,BorderLayout.CENTER);
    jPanel1.add(jScrollPane1,BorderLayout.CENTER);
    jPanel1.add(jPanel2,BorderLayout.SOUTH);
    jPanel1.add(jPanel3,BorderLayout.NORTH);
    jPanel3.add(jPanel5,null);
    jPanel5.add(jLabel_host,LabeledPairLayout.LABEL);
    jPanel5.add(jTextField_host,LabeledPairLayout.FIELD);
    this.add(jPanel6,BorderLayout.SOUTH);
    jPanel6.add(jButton_clear,null);
    jScrollPane1.getViewport().add(jEditorPane1,null);
  }

  public void startServer(int port)
  {
    try
    {
      //sphinx = new Sphinx();
      //sphinx.start(port);
      jTextField_host.setEnabled(true);
      //jTextField_host.setBackground(UIManager.getColor("Panel.background"));
      jTextField_host.setBackground(SystemColor.info);
      jTextField_host.setText("localhost:"+port);
      working = true;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void stopServer()
  {
    jTextField_host.setEnabled(false);
    jTextField_host.setBackground(UIManager.getColor("Panel.background"));
    //jTextField_host.setBackground(SystemColor.info);
    jTextField_host.setText("none(server stopped...)");
    working = false;
  }

  //Start the applet
  /*public void start()
     {
    if(th!=null)
    {
      if(!working)
      {
    th.start();
    working = true;
      }
    }
     }*/

  //Stop the applet
  /*public void stop()
     {
    if(th!=null)
    {
      th.stop();
      working = false;
    }
     }*/

  //Destroy the applet
  public void destroy()
  {
  }

  //Get Applet information
  public String getAppletInfo()
  {
    return "Applet Information";
  }

  //Get parameter info
  public String[][] getParameterInfo()
  {
    return null;
  }

  //Main method
  public static void main(String[] args)
  {
    AppServer applet = new AppServer();
    applet.isStandalone = true;
    Frame frame;
    frame = new Frame();
    frame.setTitle("Applet Frame");
    frame.add(applet,BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(100,120);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width-frame.getSize().width)/2,
		      (d.height-frame.getSize().height)/2);
    frame.setVisible(true);
  }

  /**
   * run
   */
  public void run()
  {
    int count = 0;
    try
    {
      while(working)
      {
	String text = jEditorPane1.getText();
	text += "sec: "+count+"\n";
	this.jEditorPane1.setText(text);
	count++;

	Thread.sleep(1000);
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public synchronized void removePropertyCodeListener(PropertyCodeListener l)
  {
    if(propertyCodeListeners!=null&&propertyCodeListeners.contains(l))
    {
      Vector v = (Vector)propertyCodeListeners.clone();
      v.removeElement(l);
      propertyCodeListeners = v;
    }
  }

  public synchronized void addPropertyCodeListener(PropertyCodeListener l)
  {
    Vector v = propertyCodeListeners==null?new Vector(2):
	(Vector)propertyCodeListeners.clone();
    if(!v.contains(l))
    {
      v.addElement(l);
      propertyCodeListeners = v;
    }
  }

  protected void firePropertyCode(PropertyCodeEvent e)
  {
    if(propertyCodeListeners!=null)
    {
      Vector listeners = propertyCodeListeners;
      int count = listeners.size();
      for(int i = 0; i<count; i++)
      {
	((PropertyCodeListener)listeners.elementAt(i)).propertyCode(e);
      }
    }
  }

  public void propertyCode(PropertyCodeEvent e)
  {
  }

  /**
   * Принимаем сроки для отображения на editorPane
   *
   * @param string String
   * @param o Object
   * @return Object
   */
  public Object sendCommand(String string,Object o)
  {
    GraphineStatus status = null;

    if(working)
    {
      // вывести команду на экран
      String printString = jEditorPane1.getText()+"\n"+string;
      jEditorPane1.setText(printString);

      if(!isStandalone())
      {
	AppletContext cont = getAppletContext();
	AppConnectX appGraphine = (AppConnectX)cont.getApplet("graphine");

	if(appGraphine!=null)
	status=(GraphineStatus)  appGraphine.sendCommand(string,o);
      }
    }

    return status;
  }

  public void answerToSmartphone(String answer)
  {
    if(isStandalone)return;
    else
    {
      AppletContext cont = getAppletContext();
      AppDevice appDevice = (AppDevice)cont.getApplet("device");

      // запустить www-server в апплете AppServer
      //if(appServer!=null)appServer.startServer(ws.getPort());
      StringBuffer sb = new StringBuffer();
      sb.append("ANS to Panel1");
      if(appDevice!=null)appDevice.sendAnswer("temperature",sb.toString());
    }

  }

  /**
   * sendAnswer
   *
   * @param causa String
   * @param answer String
   */
  public void sendAnswer(String causa,String answer)
  {
  }

  public boolean isStandalone()
  {
    return this.isStandalone;
  }

  public void setStandalone(boolean standalone)
  {
    this.isStandalone = standalone;
  }

  public boolean isWorking()
  {
    return working;
  }

  public void setWorking(boolean working)
  {
    this.working = working;
  }

  public void actionPerformed(ActionEvent e)
  {
    String aCom = e.getActionCommand();

    if(aCom.equals("clear"))
    {
      this.jEditorPane1.setText("");
    }
  }

}
