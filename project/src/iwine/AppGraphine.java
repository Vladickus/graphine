package iwine;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.WindowListener;
import beans.util.events.*;
import java.util.*;
import javax.swing.event.*;
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
public class AppGraphine extends Applet implements PropertyCodeListener,
    ActionListener,AppConnectX
{
  private boolean isStandalone = false;
  GraphineLabel graphineLabel = new GraphineLabel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  Border border1;
  private transient Vector propertyCodeListeners;
  Icon[] icons =
      {null,null,null};
  //JComboBox jComboBox1 = new JComboBox();
  GraphineStatus status;
  //Get a parameter value
  public String getParameter(String key,String def)
  {
    return isStandalone?System.getProperty(key,def):
	(getParameter(key)!=null?getParameter(key):def);
  }

  //Construct the applet
  public AppGraphine()
  {
    if(!Nimbus.install())SorbonnaMetalTheme.install();

    status = new GraphineStatus();

    Runtime.getRuntime().gc();
    Runtime.getRuntime().gc();
    Runtime.getRuntime().gc();
  }

  //Initialize the applet
  public void init()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception
  {
    border1 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"Graphine"),BorderFactory.createEmptyBorder(10,10,10,10));

    graphineLabel.setName("graphine_applet");
    graphineLabel.setStatus(status);
    graphineLabel.setHorizontalAlignment(SwingConstants.CENTER);
    graphineLabel.setHorizontalTextPosition(SwingConstants.CENTER);

    graphineLabel.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_x180.png")),
	GraphineLabel.SIMPLE);

    graphineLabel.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_mix_x180.png")),
	GraphineLabel.shaking);

    graphineLabel.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_UP_x180.png")),
	GraphineLabel.TEMP_UP);

    graphineLabel.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_DN_x180.png")),
	GraphineLabel.TEMP_DN);

    graphineLabel.setText("     ");
    graphineLabel.setOpaque(true);
    this.setLayout(borderLayout1);
    jPanel2.setLayout(borderLayout2);
    jPanel2.setBorder(border1);
    this.add(jPanel2,BorderLayout.CENTER);
    jPanel2.add(graphineLabel,BorderLayout.CENTER);

  }

  public void updateLabel()
  {
    this.graphineLabel.repaint();
  }

  /*
    public void setIcon(Icon icon,int n)
    {
      n = (n<0)?0:n;
      n = (n>=icons.length)?icons.length-1:n;

      jLabel_icon.setIcon(icon,n);
      jLabel_icon.repaint();
    }*/

  public void setVolume(int volume)
  {
    this.graphineLabel.setVolume(volume);
  }

  public void setWineColor(Color color)
  {
    //this.jLabel1.setBackground(color);
    graphineLabel.setWineColor(color);
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
  } //Main method

  public static void main(String[] args)
  {
    AppGraphine applet = new AppGraphine();
    applet.isStandalone = true;
    Frame frame;
    frame = new Frame();
    frame.setTitle("Applet Graphine");
    frame.add(applet,BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,360);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width-frame.getSize().width)/2,
		      (d.height-frame.getSize().height)/2);
    frame.setVisible(true);

    frame.addWindowListener(new WindowListener()
    {
      public void windowActivated(WindowEvent e)
      {
      }

      public void windowClosed(WindowEvent e)
      {
      }

      public void windowClosing(WindowEvent e)
      {
	// закрыть окно
	((Frame)e.getSource()).dispose();
	System.exit(0);
      }

      public void windowDeactivated(WindowEvent e)
      {
      }

      public void windowDeiconified(WindowEvent e)
      {
      }

      public void windowIconified(WindowEvent e)
      {
      }

      public void windowOpened(WindowEvent e)
      {
      }
    });
  }

  public void propertyCode(PropertyCodeEvent e)
  {
    int prop = e.getPropertyName();

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

  public void actionPerformed(ActionEvent e)
  {
    Object o = e.getSource();
    if((o)instanceof JComboBox)
    {
      JComboBox cBox = (JComboBox)o;
      String item = (String)cBox.getSelectedItem();
      if(item.equals("uno"))
      {
	//this.setWineColor();
      }
    }
  }

  public GraphineStatus getStatus()
  {
    return status;
  }

  public void setStatus(GraphineStatus status)
  {
    this.status = status;
  }

  /**
   * isStandalone
   *
   * @return boolean
   */
  public boolean isStandalone()
  {
    return this.isStandalone;
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

  /**
   * sendCommand
   *
   * @param command String
   * @param o Object
   * @return Object
   */
  public Object sendCommand(String command,Object o)
  {
    if(command.startsWith("SET-COLOR"))
    {
      Color color = (Color)o;
      this.status.setColor(color);
    }
    else if(command.startsWith("SET-VOLUME"))
    {
      Integer newVolume = (Integer)o;
      this.status.setValueVolume(newVolume.intValue());
    }
    else if(command.startsWith("SET-TEMPERATURE"))
    {
      Integer newTemp = (Integer)o;
      this.status.setValueTemperature(newTemp.intValue());
    }
    else if(command.startsWith("SET-SHAKING"))
    {
      Boolean isShaking = (Boolean)o;
      this.status.setshaking(isShaking.booleanValue());
    }
    else if(command.startsWith("SET-STATUS"))
    {
      GraphineStatus newStatus = (GraphineStatus)o;
      this.status.set(newStatus);
    }
    else if(command.startsWith("GET-STATUS"))
    {
    }

    /*if(!isStandalone())
    {
      AppletContext cont = getAppletContext();
      AppConnectX appSettings = (AppConnectX)cont.getApplet("settings");

      // обновить поля в AppSettings
      if(appSettings!=null)
	appSettings.sendCommand("UPDATE-FIELDS",status);
    }*/

    this.status.update();
    return this.status;
  }

  /**
   * setStandalone
   *
   * @param standalone boolean
   */
  public void setStandalone(boolean standalone)
  {
    this.isStandalone = standalone;
  }

}
