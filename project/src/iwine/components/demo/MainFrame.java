package iwine.components.demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import iwine.components.*;

/**
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class MainFrame extends JFrame
{
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JSmartphonePanel smartphonePanel1 = new JSmartphonePanel();
  ScreenPanel screenPanel = new ScreenPanel(null);
  EmulateBoolean emulateBoolean = new EmulateBoolean(true);
  //Construct the frame
  public MainFrame()
  {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
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
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(310,640));
    this.setTitle("Frame Title");
    smartphonePanel1.setPanel(screenPanel);
    screenPanel.setEmulateBoolean(emulateBoolean);
    contentPane.add(smartphonePanel1,BorderLayout.CENTER);
    //contentPane.add(panel11, BorderLayout.NORTH);

    // wallpaper
    String fname = "resources/images/screen_wallpaper_243x406.png";
    smartphonePanel1.setWallpaper(fname);
    // панель отображаемая на экране смартфона
    //smartphonePanel1.setPanel(new Panel1());
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if(e.getID()==WindowEvent.WINDOW_CLOSING)
    {
      System.exit(0);
    }
  }
}
