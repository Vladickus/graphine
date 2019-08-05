package iwine.components.demo;

import javax.swing.UIManager;
import java.awt.*;
import beans.util.look.SorbonnaMetalTheme;

/**
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class Application
{
  boolean packFrame = false;

  //Construct the application
  public Application()
  {
    MainFrame frame = new MainFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
    {
      frame.pack();
    }
    else
    {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
    {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args)
  {
    SorbonnaMetalTheme.install();
    new Application();
  }
}
