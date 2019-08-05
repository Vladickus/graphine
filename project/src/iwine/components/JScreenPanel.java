package iwine.components;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class JScreenPanel extends JPanel
{
  Image imageFrame;
  BorderLayout borderLayout = new BorderLayout();
  private JPanel panel;

  public JScreenPanel()
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

  private void jbInit() throws Exception
  {
    this.setPreferredSize(new Dimension(243,406));
    this.setLayout(borderLayout);
    this.setOpaque(false);
  }

  public void setText(String text)
  {
  }

  public void paint(Graphics g)
  {
    // вызовет paintComponent(..)
    super.paint(g);

    // рисует поверх компонент
    /*g.setColor(Color.GREEN);
    int ww = 243;
    int hh = 406;
    g.drawRect(0,0,ww-1,hh-1);
    g.drawLine(0,0,ww,hh);*/
  }

  public void paintComponent(Graphics g)
  {
    // рисуем wallpaper
    if(imageFrame!=null)g.drawImage(imageFrame,0,0,null);
    else System.out.println("Image (wallpaper) not found...");
  }

  public void setWallpaper(Image imageFrame)
  {
    this.imageFrame = imageFrame;
  }

  public void setWallpaper(String fname)
  {
    this.imageFrame = new ImageIcon(
	JScreenPanel.class.getResource(fname)).getImage();
  }

  public JPanel getPanel()
  {
    return panel;
  }

  /**
   * Установить панель, отображаемую на экране смартфона
   * @param panel JPanel
   */
  public void setPanel(JPanel panel)
  {
    if(panel!=null)
    {
      if(this.panel!=null)remove(this.panel);

      this.panel = panel;
      this.panel.setOpaque(false);
      add(this.panel,BorderLayout.CENTER);
      setOpaque(false);
      this.updateUI();
    }
  }

}
