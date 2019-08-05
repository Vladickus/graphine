package iwine;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import beans.util.events.*;
import java.util.*;
import javax.swing.border.*;
import meumlay.layouts.*;
import javax.swing.event.*;
import meum.astro.components.switchpanel.*;
import beans.util.look.SorbonnaMetalTheme;
import beans.util.look.Nimbus;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class AppSettings extends Applet implements
    ActionListener,ChangeListener,AppConnectX
{
  boolean actionSleep = false;
  WineServer ws;
  GraphineStatus localStatus = null; // = new GraphineStatus();
  static private boolean isStandalone = false;
  JToggleButton jToggleButton_shaking = new JToggleButton();
  SettingsComboBox jComboBox_color = new SettingsComboBox();

  private transient Vector propertyCodeListeners;
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  LabeledPairLayout labeledPairLayout1 = new LabeledPairLayout();
  JLabel jLabel_color = new JLabel();
  JLabel jLabel_alcohol = new JLabel();
  JLabel jLabel_volume = new JLabel();
  JTextField jTextField_alcohol = new JTextField();
  JTextField jTextField_volume = new JTextField();
  JPanel jPanel_empty = new JPanel();
  JSlider jSlider1 = new JSlider();
  JLabel jLabel_shaking = new JLabel();
  Border border3;
  JLabel jLabel_temp = new JLabel();
  JTextField jTextField_temp = new JTextField();
  JLabel jLabel_port = new JLabel();
  JLabel jLabel_port_off = new JLabel();
  JTextField jTextField_port = new JTextField();
  CardPanel cardPanel_port = new CardPanel();
  JButton jButton_serverStop = new JButton();
  JLabel jLabel_sugar = new JLabel();
  JTextField jTextField_sugar = new JTextField();
  JButton jButton_server_off = new JButton();

  //Get a parameter value
  public String getParameter(String key,String def)
  {
    return isStandalone?System.getProperty(key,def):
	(getParameter(key)!=null?getParameter(key):def);
  }

  //Construct the applet
  public AppSettings()
  {
    if(!Nimbus.install())SorbonnaMetalTheme.install();

    if(localStatus==null)initStatus();
  }

  public void initStatus()
  {
    try
    {
      AppletContext cont = getAppletContext();
      if(cont!=null)
      {
	AppGraphine appGraphine = (AppGraphine)cont.getApplet("graphine");
	if(appGraphine!=null)setStatus(appGraphine.getStatus());
      }
    }
    catch(Exception ex)
    {
      System.err.println("error initStatus()");
    }
  }

  //Initialize the applet
  public void init()
  {
    try
    {if(localStatus==null)initStatus();
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
    border1 = BorderFactory.createEtchedBorder(Color.white,
					       new Color(142,142,142));
    titledBorder1 = new TitledBorder(border1,"Settings for Graphine");
    border2 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"Settings for Graphine"),BorderFactory.createEmptyBorder(10,10,10,10));
    border3 = BorderFactory.createEmptyBorder(10,0,20,0);

    jToggleButton_shaking.setText("OFF");
    jToggleButton_shaking.setActionCommand("set-shaking");
    jToggleButton_shaking.addActionListener(this);

    this.setLayout(borderLayout1);
    jPanel1.setBorder(border2);
    jPanel1.setLayout(labeledPairLayout1);
    jLabel_color.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_color.setHorizontalTextPosition(SwingConstants.LEADING);
    jLabel_color.setText("Color:");
    jLabel_alcohol.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_alcohol.setText("Alcohol:");
    jLabel_volume.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_volume.setText("Volume:");
    jTextField_alcohol.setText("25");
    jTextField_alcohol.setActionCommand("alcohol");
    jTextField_alcohol.addActionListener(this);
    jTextField_volume.setText("1000");
    jLabel_shaking.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_shaking.setText("Shaking:");
    jSlider1.setBorder(border3);
    jLabel_temp.setText("Temperature:");

    jTextField_temp.setActionCommand("set-temperature");
    jTextField_temp.addActionListener(this);
    jTextField_temp.setText("25");
    jLabel_port.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_port.setHorizontalTextPosition(SwingConstants.RIGHT);
    jLabel_port.setText("Server port:");
    jLabel_sugar.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel_sugar.setHorizontalTextPosition(SwingConstants.RIGHT);
    jLabel_sugar.setText("Sugar:");
    jTextField_sugar.setText("5");
    jTextField_sugar.setActionCommand("sugar");
    jTextField_sugar.addActionListener(this);

    this.add(jPanel1,BorderLayout.CENTER);

    jButton_serverStop.setText("Server stop");
    jTextField_port.setText("8083");
    jTextField_port.setActionCommand("port-number");
    jTextField_port.addActionListener(this);
    jLabel_port_off.setText("...server is off");

    jButton_server_off.setText("Server OFF");
    jButton_server_off.setActionCommand("server-off");
    jButton_server_off.addActionListener(this);

    cardPanel_port.setMainComponent(jButton_server_off); //0
    cardPanel_port.setMainComponent(jLabel_port_off); //1
    cardPanel_port.setMainComponent(jTextField_port); //2
    cardPanel_port.setCheckMask(true);
    cardPanel_port.addInvisibleIndex(0);
    cardPanel_port.setFast(false);
    cardPanel_port.setType(cardPanel_port.SWITCH_UP);
    cardPanel_port.addChangeListener(this);

    jLabel_port.addMouseListener(cardPanel_port.getMouseAdapter());

    jPanel1.add(jLabel_port,LabeledPairLayout.LABEL);
    jPanel1.add(cardPanel_port,LabeledPairLayout.FIELD);

    jPanel1.add(jLabel_color,LabeledPairLayout.LABEL);
    jPanel1.add(jComboBox_color,LabeledPairLayout.FIELD);

    jPanel1.add(jLabel_alcohol,LabeledPairLayout.LABEL);
    jPanel1.add(jTextField_alcohol,LabeledPairLayout.FIELD);

    jPanel1.add(jLabel_volume,LabeledPairLayout.LABEL);
    jPanel1.add(jTextField_volume,LabeledPairLayout.FIELD);

    jTextField_volume.setActionCommand("set-volume");
    jTextField_volume.addActionListener(this);

    jPanel1.add(jPanel_empty,LabeledPairLayout.LABEL);
    jPanel1.add(jSlider1,LabeledPairLayout.FIELD);
    jSlider1.addChangeListener(this);

    jPanel1.add(jLabel_shaking,LabeledPairLayout.LABEL);
    jPanel1.add(jToggleButton_shaking,LabeledPairLayout.FIELD);
    jPanel1.add(jLabel_temp,LabeledPairLayout.LABEL);
    jPanel1.add(jTextField_temp,LabeledPairLayout.FIELD);
    jPanel1.add(jLabel_sugar,LabeledPairLayout.LABEL);
    jPanel1.add(jTextField_sugar,LabeledPairLayout.FIELD);

    jComboBox_color.setActionCommand("set-color");
    jComboBox_color.addActionListener(this);

    jSlider1.setValue(38);
    jComboBox_color.setSelectedIndex(2);
    jTextField_temp.setText("11");
    check();
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

  void jToggleButton_shaking_actionPerformed(ActionEvent e)
  {
    AppletContext cont = this.getAppletContext();

    AppGraphine app1 = (AppGraphine)cont.getApplet("graphine");
    Applet app2 = cont.getApplet("TestApplet2");

    if(app1!=null)app1.setWineColor(Color.RED);
  }

  /* public synchronized void removePropertyCodeListener(PropertyCodeListener l)
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
   }*/

  /*public int getVolumeInteger()
     {
    try
    {
      int volume = Integer.parseInt(jTextField_volume.getText());
      return volume;
    }
    catch(Exception ex)
    {
      //ex.printStackTrace();
      return 100;
    }
     }*/

  public int getValueTextField(JTextField tField)
  {
    try
    {
      int temp = Integer.parseInt(tField.getText());
      temp = (temp<0)?0:temp;
      temp = (temp>100)?100:temp;
      tField.setText(""+temp);
      return temp;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
      return 35;
    }
  }

  /*public int getTemperatureInteger()
     {
    try
    {
      int temp = Integer.parseInt(jTextField_temp.getText());
      temp = (temp<0)?0:temp;
      temp = (temp>100)?100:temp;
      return temp;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
      return 35;
    }
     }*/
  public void updateFields(GraphineStatus status)
  {
    this.actionSleep = true;

    this.jTextField_temp.setText(""+status.getValueTemperatura());
    jToggleButton_shaking.setSelected(status.isshaking());
    jToggleButton_shaking.setText(status.isshaking()?"ON":"OFF");

    this.actionSleep = false;
  }

  public void actionPerformed(ActionEvent e)
  {
    if(actionSleep)return;

    String aCom = e.getActionCommand();

    if(aCom.equals("server-off"))
    {
      String text = "WineServer stop...";
      jLabel_port_off.setText(text);
      jLabel_port_off.setForeground(Color.BLACK);

      if(this.isStandalone)return;
      else
      {
	AppletContext cont = getAppletContext();
	AppServer appServer = (AppServer)cont.getApplet("server");

	// остановить www-server в апплете AppServer
	if(appServer!=null)appServer.stopServer();
      }
      cardPanel_port.clearMask();
      cardPanel_port.addInvisibleIndex(0);
      cardPanel_port.switchComponent();
    }
    else if(aCom.equals("sugar"))
    {
      check();
    }
    else if(aCom.equals("alcohol"))
    {
      check();
    }
    else if(aCom.equals("port-number"))
    {
      int port = 0;
      String sNumberPort = jTextField_port.getText();
      if(sNumberPort.length()>0)
      {
	port = Integer.parseInt(sNumberPort);

	String text = "WineServer start on "+port+" port";
	jLabel_port_off.setText(text);
	jLabel_port_off.setForeground(Color.BLACK);
      }
      else
      {
	String text = "WineServer stop...";
	jLabel_port_off.setText(text);
	jLabel_port_off.setForeground(Color.BLACK);
      }

      if(this.isStandalone)return;
      else
      {
	AppletContext cont = getAppletContext();
	//AppGraphine appGraphine = (AppGraphine)cont.getApplet("graphine");
	//if(appGraphine!=null)setStatus(appGraphine.getStatus());

	AppServer appServer = (AppServer)cont.getApplet("server");

	// запустить www-server в апплете AppServer
	//if(appServer!=null)appServer.startServer(ws.getPort());
	if(appServer!=null)
	{
	  if(port==0)appServer.stopServer();
	  else appServer.startServer(port);
	}
      }

      cardPanel_port.clearMask();
      cardPanel_port.addInvisibleIndex(2);
      cardPanel_port.switchComponent();
    }
    else if(aCom.equals("set-temperature"))
    {
      int temp = this.getValueTextField(jTextField_temp);
      localStatus.setValueTemperature(temp);
      //check();
    }
    else if(aCom.equals("set-shaking"))
    {
      check();
    }
    else if(aCom.equals("set-volume")) // нополненность графина
    {
      check();

    }
    else if(aCom.equals("set-color")) // JComboBox
    {
      check();
    }
  }

  /**
   * Собрать сведения о состоянии переменных
   * из полей и изменить status графина
   */
  public void check()
  {
    if(localStatus==null)return;
    //GraphineStatus localStatus = new GraphineStatus();

    localStatus.setValueSugar(getValueTextField(jTextField_sugar));
    boolean pressed = jToggleButton_shaking.isSelected();
    jToggleButton_shaking.setText(pressed?"ON":"OFF");
    localStatus.setshaking(pressed);

    localStatus.setColor(jComboBox_color.getColor());
    localStatus.setValueAlcohol(getValueTextField(jTextField_alcohol));
    //localStatus.setValueSugar(getValueTextField());
    localStatus.setValueVolume(getValueTextField(jTextField_volume));
    jSlider1.setValue(localStatus.getValueVolume());

    localStatus.setValueTemperature(getValueTextField(jTextField_temp));

    updateGraphine(localStatus);
  }

  /*public void setTemperature(int temp)
     {
    temp = (temp<0)?0:temp;
    temp = (temp>100)?100:temp;

    this.jTextField_temp.setText(""+temp);
    //this.jSlider1.setValue(volume);

    this.localStatus.setValueTemperature(temp);
    updateGraphine(localStatus);
     }*/

  /*public void setVolume(int volume)
     {
    volume = (volume<0)?0:volume;
    volume = (volume>100)?100:volume;

    this.jTextField_volume.setText(""+volume);
    this.jSlider1.setValue(volume);

    this.localStatus.setValueVolume(volume);
    updateGraphine(localStatus);
     }*/

  public void updateGraphine(GraphineStatus status)
  {
    if(this.isStandalone)return;

    AppletContext cont = getAppletContext();
    AppGraphine appGraphine = (AppGraphine)cont.getApplet("graphine");

    if(appGraphine!=null)
    {
      // получим data-model status
      GraphineStatus st = appGraphine.getStatus();
      // воздействуем на неё
      st.set(status); // изменить
      st.update(); // оповестить подписчиков
    }
  }

  public void stateChanged(ChangeEvent e)
  {
    Object o = e.getSource();

    if((o)instanceof GraphineStatus)
    {
      GraphineStatus status = (GraphineStatus)o;
      updateFields(status);
    }
    else if((o)instanceof JSlider)
    {
      JSlider sl = (JSlider)o;
      int value = sl.getValue();
      jTextField_volume.setText(value+"");
      check();
    }
    /*else if((o)instanceof CardPanel)
       {
      CardPanel c = (CardPanel)o;
      if(c.equals(cardPanel_port))
      {
      if(c.getMainIndex()==0)
      {
      //jTextField_port.setForeground(Color.ORANGE);
      //this.jTextField_port.requestFocus();
      }
      }
       }*/
  }

  public static void main(String[] args)
  {
    if(!Nimbus.install())SorbonnaMetalTheme.install();

    AppSettings applet = new AppSettings();
    applet.isStandalone = true;
    Frame frame;
    frame = new Frame();
    frame.setTitle("Applet Graphine");
    frame.add(applet,BorderLayout.CENTER);
    applet.init();
    applet.start();

    frame.setSize(500,420);
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

    new iwine.components.demo.Application();
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
   * isStandalone
   *
   * @return boolean
   */
  public boolean isStandalone()
  {
    return this.isStandalone;
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

  /**
   * sendCommand
   *
   * @param command String
   * @param o Object
   * @return Object
   */
  public Object sendCommand(String command,Object o)
  {
    if(command.equals("UPDATE-FIELDS"))
    {
      updateFields((GraphineStatus)o);
    }

    return o;
  }

  public void setStatus(GraphineStatus status)
  {
    if(status!=null&&this.localStatus==null)
    {
      status.addChangeListener(this);
      status.update();
    }

    this.localStatus = status;
  }
}
