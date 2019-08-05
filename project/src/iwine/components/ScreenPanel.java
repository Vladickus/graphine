package iwine.components;

import java.applet.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import com.borland.jbcl.layout.*;
import iwine.*;
import meum.astro.components.switchpanel.*;
import iwine.GraphineStatus;

/**
 * Расположение кнопок поверх
 * цветной подложки JScreenPanel
*
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class ScreenPanel extends JPanel implements ActionListener,
    ChangeListener
{
  boolean actionSleep = false;
  GraphineStatus modelGraphine = new GraphineStatus();

  private EmulateBoolean emulateBoolean;
  AppConnectX appConnect;
  BorderLayout borderLayout1 = new BorderLayout();
  JButton jButton_shaking = new JButton();
  JSlider jSlider_temperature = new JSlider();
  JPanel jPanel_south = new JPanel();
  JTextField jTextField_temperature = new JTextField();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  CardPanel cardPanel_temperature = new CardPanel();
  JLabel jLabel_temperature = new JLabel();
  JLabel jLabel_shaking = new JLabel();
  CardPanel cardPanel_shaking = new CardPanel();
  GraphineLabel jLabel_graphine = new GraphineLabel();
  JButton jButton2 = new JButton();
  CardPanel cardPanel_status = new CardPanel();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel_north = new JPanel();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JCheckBox jCheckBox_emulate = new JCheckBox();
  JPanel jPanel_emulate = new JPanel();
  JPanel jPanel_center = new JPanel();
  JLabel jLabel_valueAlcohol = new JLabel();
  JLabel jLabel_valueSugar = new JLabel();
  JLabel jLabel_valueTemperature = new JLabel();
  JLabel jLabel_valueVolume = new JLabel();
  JPanel jPanel_values_south = new JPanel();
  VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();

  JPanel jPanel_values_01 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel_values_02 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();

  public ScreenPanel(AppConnectX appConnect)
  {
    this.appConnect = appConnect;

    try
    {
      jbInit();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception
  {
    jButton_shaking.setSelectedIcon(null);
    jButton_shaking.setText("Shaking");
    jButton_shaking.setActionCommand("shaking");
    jButton_shaking.addActionListener(this);
    this.setLayout(borderLayout1);

    //jTextField_temperature.setText("Temperature");
    jTextField_temperature.setActionCommand("temperature");
    jTextField_temperature.addActionListener(this);

    jPanel_south.setLayout(verticalFlowLayout1);
    jLabel_temperature.setText("Temper. change");

    jLabel_graphine.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_x180.png")),
	GraphineLabel.SIMPLE);

    jLabel_graphine.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_mix_x180.png")),
	GraphineLabel.shaking);

    jLabel_graphine.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_UP_x180.png")),
	GraphineLabel.TEMP_UP);

    jLabel_graphine.setIcon(
	new ImageIcon(AppGraphine.class.getResource(
	"resources/images/graphine_DN_x180.png")),
	GraphineLabel.TEMP_DN);

    jLabel_graphine.setName("graphine_smartphone");
    jLabel_graphine.setOpaque(false);
    jLabel_graphine.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel_graphine.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel_graphine.setIcon(new ImageIcon(ScreenPanel.class.getResource(
	"resources/images/graphine_x180.png")));
    jLabel_graphine.setText("");
    jLabel_graphine.setStatus(modelGraphine);
    jButton2.setText("Get status");
    jButton2.setActionCommand("status");
    jButton2.addActionListener(this);

    jPanel_north.setLayout(verticalFlowLayout2);
    jCheckBox_emulate.setOpaque(false);
    jCheckBox_emulate.setText("emulate");
    jCheckBox_emulate.addActionListener(this);
    jPanel_emulate.setOpaque(false);
    jPanel_emulate.setLayout(flowLayout1);
    jPanel_center.setOpaque(false);
    jPanel_center.setLayout(borderLayout2);
    jLabel_valueAlcohol.setForeground(Color.white);
    jLabel_valueAlcohol.setHorizontalAlignment(SwingConstants.CENTER);

    jLabel_valueSugar.setForeground(Color.white);
    jLabel_valueSugar.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel_valueSugar.setHorizontalTextPosition(SwingConstants.CENTER);

    jLabel_valueSugar.setText("      ");
    jLabel_valueAlcohol.setText("      ");
    jLabel_valueTemperature.setText("     ");
    jLabel4.setText("      ");

    jLabel_valueTemperature.setForeground(Color.white);
    jLabel_valueTemperature.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel_valueTemperature.setHorizontalTextPosition(SwingConstants.CENTER);

    jPanel_values_south.setLayout(verticalFlowLayout3);
    jPanel_values_south.setOpaque(false);

    jLabel_valueVolume.setForeground(Color.white);
    jLabel_valueVolume.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel_valueVolume.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel_valueVolume.setText("");
    jPanel_values_01.setLayout(gridLayout1);
    jPanel_values_02.setLayout(gridLayout2);
    jPanel_values_02.setOpaque(false);
    jPanel_values_01.setOpaque(false);
    this.add(jPanel_center,BorderLayout.CENTER);

    // графин
    jPanel_center.add(jLabel_graphine,BorderLayout.CENTER);

    jPanel_center.add(jPanel_emulate,BorderLayout.NORTH);
    //jPanel_emulate.add(jCheckBox_emulate,null);
    jPanel_center.add(jPanel_values_south,BorderLayout.SOUTH);
    jPanel_values_south.add(jPanel_values_02,null);
    jPanel_values_02.add(jLabel_valueSugar,null);
    jPanel_values_02.add(jLabel_valueAlcohol,null);
    jPanel_values_south.add(jPanel_values_01,null);
    jPanel_values_01.add(jLabel_valueVolume,null);
    jPanel_values_01.add(jLabel_valueTemperature,null);
    this.add(jPanel_south,BorderLayout.SOUTH);
    //jPanel1.add(jTextField1, null);
    cardPanel_temperature.addComponent(jTextField_temperature);
    cardPanel_temperature.addComponent(jLabel_temperature);
    cardPanel_temperature.setType(cardPanel_temperature.SWITCH_UP);
    cardPanel_temperature.setFast(false);
    cardPanel_temperature.addChangeListener(this);
    cardPanel_temperature.setName("temp");

    jLabel_shaking.setText("Send Shaking command");
    cardPanel_shaking.addComponent(jLabel_shaking);
    cardPanel_shaking.addComponent(jButton_shaking);
    cardPanel_shaking.setType(CardPanel.SWITCH_UP);
    cardPanel_shaking.setFast(false);
    cardPanel_shaking.addChangeListener(this);
    cardPanel_shaking.setName("shaking");

    jLabel4.setText("Get status send...");
    cardPanel_status.addComponent(jLabel4);
    cardPanel_status.addComponent(jButton2);

    cardPanel_status.setType(cardPanel_temperature.SWITCH_UP);
    cardPanel_status.setFast(false);
    cardPanel_status.addChangeListener(this);
    cardPanel_status.setName("status");

    jSlider_temperature.addChangeListener(this);
    jSlider_temperature.setName("temp_slider");
    jSlider_temperature.setEnabled(false);
    jSlider_temperature.setValue(25);
    jSlider_temperature.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
	if(e.getClickCount()>1&&cardPanel_temperature.getMainIndex()==0)
	{
	  jSlider_temperature.setFocusable(false);
	  jSlider_temperature.setEnabled(false);
	  ActionEvent ee = new ActionEvent(
	      jSlider_temperature,0,"temperature",0);

	  actionPerformed(ee);
	}
      }
    });

    jSlider_temperature.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {
	if(e.getKeyCode()==KeyEvent.VK_ENTER)
	{
	  ActionEvent ee = new ActionEvent(
	      jSlider_temperature,0,"temperature",0);

	  actionPerformed(ee);
	}
      }
    });

    jTextField_temperature.setText(jSlider_temperature.getValue()+"");
    jPanel_south.add(cardPanel_shaking,null);
    jPanel_south.add(cardPanel_temperature,null);
    jPanel_south.add(jSlider_temperature,null);
    this.add(jPanel_north,BorderLayout.NORTH);
    jPanel_north.add(cardPanel_status,null);
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

  /*public void stateChanged(ChangeEvent e)
     {
    Object o = e.getSource();


     }*/

  public void actionPerformed(ActionEvent e)
  {
    /**@todo управление Smartphon'ом*/
    if(actionSleep)return;

    String aCom = e.getActionCommand();

    if(aCom.equals("emulate"))
    {
      this.emulateBoolean.setValue(jCheckBox_emulate.isSelected());
    }
    else if(aCom.equals("status"))
    {
      cardPanel_status.switchComponent();

      try
      {
	GraphineStatus status =
	    connectApplet("server",
			  "GET-STATUS(emulator) ex Smartphone",
			  "GET-STATUS ex Smartphone",null);

	// отобразить изменения полученных значений на графине
	if(status==null)System.err.println("server error...");
	else refreshGraphine(status);

	new Pausa(cardPanel_status,1000).start();
      }
      catch(Exception ex)
      {
	ex.printStackTrace();
      }
    }
    else if(aCom.equals("shaking"))
    {
      jLabel_shaking.setText("Command Shaking send..."); // to server...");

      // определим текущее состояние "взбалтывания"
      boolean isShake = modelGraphine.isshaking();
      jButton_shaking.setText(isShake?"non Shaking":"Shaking");

      // послать графину
      GraphineStatus status =
	  connectApplet("server",
			"SET-SHAKING(emulator) ex Smartphone ="+(!isShake),
			"SET-SHAKING ex Smartphone ="+(!isShake),
			new Boolean(!isShake));

      // отобразить изменения полученных значений на графине
      if(status==null)System.err.println("server error...");
      else refreshGraphine(status);

      this.cardPanel_shaking.switchComponent();
      new Pausa(cardPanel_shaking,1000).start();
    }
    else if(aCom.equals("temperature"))
    {
      jSlider_temperature.setEnabled(false);
      jSlider_temperature.setFocusable(false);

      int temp = getValueTextField(jTextField_temperature);
      jSlider_temperature.setValue(temp);

      jLabel_temperature.setText("Temper. "+temp+
				 "°C send...");
      // послать графину
      GraphineStatus status =
	  status = connectApplet("server",
				 "SET-TEMPERATURE(emulator) ex Smartphone ="+
				 temp+"°C",
				 "SET-TEMPERATURE ex Smartphone ="+temp+"°C",
				 new Integer(temp));

      // отобразить изменения полученных значений на графине
      if(status==null)System.err.println("server error...");
      //else refreshGraphine(status);

      cardPanel_temperature.switchComponent();
    }
  }

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

  public GraphineStatus connectApplet(String appName,
				      String comand_em,
				      String command_nem,
				      Object o)
  {
    GraphineStatus status = null;

    if(appConnect!=null)
    {
      if(appConnect.isStandalone())return status;
      else
      {
	AppletContext cont = appConnect.getAppletContext();
	AppConnectX appServer = (AppConnectX)cont.getApplet(appName);

	StringBuffer sb = new StringBuffer();

	if(emulateBoolean.isValue())sb.append(comand_em);
	else sb.append(command_nem);

	if(appServer!=null)
	  status = (GraphineStatus)appServer.sendCommand(sb.toString(),o);
      }
    }

    return status;
  }

  public void sendAnswer(String causa,String answer)
  {
    if(causa.equals("temperature"))
      this.jLabel_graphine.setText(answer+"°C");
  }

  public void refreshGraphine(GraphineStatus status)
  {
    // обновить цифровые значения
    jLabel_valueAlcohol.setText("Alcohol: "+status.getValueAlcohol());
    jLabel_valueSugar.setText("Sugar: "+status.getValueSugar());
    jLabel_valueTemperature.setText("Temp: "+status.getValueTemperatura());
    jLabel_valueVolume.setText("Volume: "+status.getValueVolume());

    // установить значение температуры
    jLabel_temperature.setText(
	"Temper. "+status.getValueTemperatura()+"°C send...");
    jTextField_temperature.setText(""+status.getValueTemperatura());
    jSlider_temperature.setValue(status.getValueTemperatura());

    // влияем на отображение графина чере data-model
    modelGraphine.set(status);
    modelGraphine.update();
    jLabel_graphine.updateUI();

    boolean isShake = modelGraphine.isshaking();
    this.jButton_shaking.setText(isShake?"non Shaking":"Shaking");
  }

  private void pausa(int msec)
  {
    try
    {
      Thread.sleep(msec);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void stateChanged(ChangeEvent e)
  {
    Object o = e.getSource();

    if((o)instanceof EmulateBoolean)
    {
      EmulateBoolean em = (EmulateBoolean)o;
      emulateBoolean.setEmulate(em.isValue(),true);
    }
    else if((o)instanceof CardPanel)
    {
      CardPanel c = ((CardPanel)e.getSource());
      //System.out.println("Label switch temperature");

      if(c.getName().equals("temp"))
      {
	if(c.getMainIndex()==0)
	{
	  jSlider_temperature.setEnabled(false);
	}
	else
	{
	  jSlider_temperature.setEnabled(true);
	  jSlider_temperature.setFocusable(true);
	  jSlider_temperature.requestFocus();
	}
      }
    }
    else if((o)instanceof JSlider)
    {
      int t = ((JSlider)o).getValue();

      actionSleep = true; // отключить actionPerformed(..)
      jTextField_temperature.setText(t+"");
      actionSleep = false;
    }
  }
}
