package iwine;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import beans.util.events.*;
import java.util.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import iwine.components.*;
import javax.swing.event.*;
import iwine.components.EmulateBoolean;
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
public class AppDevice extends Applet implements PropertyCodeListener,
    AppConnectX,ChangeListener
{
  ScreenPanel smartphoneScreenPanel;

  public boolean isStandalone = false;
  private transient Vector propertyCodeListeners;
  BorderLayout borderLayout1 = new BorderLayout();
  JSmartphonePanel smartphonePanel1 = new JSmartphonePanel();
  private EmulateBoolean emulateBoolean;

  //JScreenPanel screen = new JScreenPanel();

  //Get a parameter value
  public String getParameter(String key,String def)
  {
    return isStandalone?System.getProperty(key,def):
	(getParameter(key)!=null?getParameter(key):def);
  }

  //Construct the applet
  public AppDevice()
  {
    if(!Nimbus.install())SorbonnaMetalTheme.install();
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
    setLayout(borderLayout1);
    /*int ww = screen.getWidth();
       int hh = screen.getHeight();
       add(screen,new XYConstraints(28,74,ww,hh));*/
    this.add(smartphonePanel1,BorderLayout.CENTER);

    emulateBoolean = new EmulateBoolean(false);

    smartphoneScreenPanel = new ScreenPanel(this);
    smartphoneScreenPanel.setOpaque(false);
    smartphoneScreenPanel.setEmulateBoolean(emulateBoolean);
    smartphonePanel1.setPanel(smartphoneScreenPanel);

    smartphonePanel1.setWallpaper(
	"resources/images/screen_wallpaper_243x406.png");
  }

  public void sendAnswer(String causa,String answer)
  {
    smartphoneScreenPanel.sendAnswer(causa,answer);
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

  public boolean isStandalone()
  {
    return this.isStandalone;
  }

  public void setStandalone(boolean standalone)
  {
    this.isStandalone = standalone;
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
    return "";
  }

  public EmulateBoolean getEmulateBoolean()
  {
    return emulateBoolean;
  }

  public void setEmulateBoolean(EmulateBoolean emulateBoolean)
  {
    this.emulateBoolean = emulateBoolean;

    if(emulateBoolean!=null)
      emulateBoolean.addChangeListener(this);
  }

  public void stateChanged(ChangeEvent e)
  {
    Object o = e.getSource();

    if((o)instanceof EmulateBoolean)
    {
      EmulateBoolean em = (EmulateBoolean)o;
      emulateBoolean.setEmulate(em.isValue(),true);
    }
  }
}
