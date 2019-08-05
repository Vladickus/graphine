package iwine.components;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class JSmartphonePanel extends JPanel
{
  Image imageFrame;
  XYLayout xyLayout = new XYLayout();
  JScreenPanel screen = new JScreenPanel();

  public JSmartphonePanel()
  {
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
    //this.setBackground(Color.PINK); //.WHITE);
    this.setLayout(xyLayout);

    // рамка - внешний корпус смартфона
    String fname = "resources/images/smartphone_300x590.png";
    setImage(fname);

    this.setPreferredSize(new Dimension(300,590));
    this.add(screen,new XYConstraints(28,74,243,406));
    this.screen.setOpaque(false);
    this.setOpaque(false);
  }

  public void paint(Graphics g)
  {
    /*g.setColor(Color.BLUE);
    int ww = this.getWidth();
    int hh = this.getHeight();
    g.drawRect(0,0,ww-1,hh-1);
    g.drawLine(ww-1,0,0,hh-1);*/

    super.paint(g);
    // нарисуем поверх панели
    if(imageFrame!=null)g.drawImage(imageFrame,0,0,null);
    else System.out.println("Image (corpus) not found...");
  }

  public void setImage(Image imageFrame)
  {
    this.imageFrame = imageFrame;
  }

  public void setImage(String fname)
  {
    imageFrame = new ImageIcon(
	JSmartphonePanel.class.getResource(fname)).getImage();

    this.imageFrame = imageFrame;
  }

  /**
   * Установить подложку на экране смартфона
   * @param fname String
   */
  public void setWallpaper(String fname)
  {
    Image image = new ImageIcon(
	JSmartphonePanel.class.getResource(fname)).getImage();

    if(image!=null)this.screen.setWallpaper(image);
    else System.out.println("File not found: "+fname);
  }

  /**
   * Установить панель, отображаемую на экране смартфона
   * @param panel JPanel
   */
  public void setPanel(JPanel panel)
  {
    this.screen.setPanel(panel);
  }

  public JPanel getPanel()
  {
    return this.screen.getPanel();
  }

}
