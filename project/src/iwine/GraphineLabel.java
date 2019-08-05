package iwine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.Rectangle2D.Float;
import iwine.components.IWinePolygon;
import java.util.ArrayList;

/**
*
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class GraphineLabel extends JLabel implements ChangeListener
{
  final static boolean DEBUG = true;

  final public static int SIMPLE = 0;
  final public static int shaking = 1;
  final public static int TEMP_UP = 2;
  final public static int TEMP_DN = 3;

  //static
  Icon[] icons =
      {null,null,null,null};
  GraphineStatus status;
  Color wineColor;
  int volume; // % заполненности

  java.util.List list = new ArrayList();
  IWinePolygon pBase;
  Point nullPoint;
  int mx,my;

  public GraphineLabel()
  {
    setOpaque(false);
    wineColor = Color.decode("#9F004B");
    //this.setIcon(IconRes.getNullIcon(50,100));

    this.volume = 100;
    this.setOpaque(true);
    /*this.addMouseListener(new MouseAdapter()
       {
      public void mousePressed(MouseEvent e)
      {
      mx = e.getX();
      my = e.getY();

      mx -= nullPoint.x;
      my -= nullPoint.y;
      //System.out.println(": "+status);
      Point r = pBase.getLastStep();
      System.out.println(": "+mx+","+my+" ("+r.x+","+r.y+")");
      repaint();
      }
       }
       );*/
    /*this.addMouseMotionListener(new MouseMotionAdapter()
       {
      public void mouseMoved(MouseEvent e)
      {
      mx = e.getX();
      my = e.getY();

      if(nullPoint!=null)
      {
      mx -= nullPoint.x;
      my -= nullPoint.y;
      //System.out.println(": "+status);
      if(pBase!=null)
      {
     Point r = pBase.getLastStep();
     if(r!=null)
       System.out.println(": "+mx+","+my+" ("+r.x+","+r.y+")");
     repaint();
      }
      }
      }
       });*/
  }

  public void setText(String text)
  {
  }

  public void setIcon(Icon icon,int n)
  {
    n = (n<0)?0:n;
    n = (n>=icons.length)?icons.length-1:n;

    this.icons[n] = icon;
    if(n==0)this.setIcon(icons[0]);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    paintWine(g);
  }

  private void paintWine(Graphics g)
  {
    Icon icon = this.getIcon(); // установленная картинка

    if(icon!=null)
    {
      int wIcon = icon.getIconWidth();
      int hIcon = icon.getIconHeight();

      int ww = this.getWidth();
      int hh = this.getHeight();

      int xIcon = (ww-wIcon)/2;
      int yIcon = (hh-hIcon)/2;
      // установить левый верхний угол картинки
      nullPoint = new Point(xIcon,yIcon);

      int dx = 42;
      int dy = 153;
      int w1 = 98;
      int h1 = 113;

      int volume = (status==null)?0:status.getValueVolume();
      //volume = 100;
      volume = (volume<1)?2:volume;
      int tIcon = (int)((h1)*volume/100);

      // debug
      //g.setColor(Color.RED);
      //g.drawRect(xIcon,yIcon,wIcon-1,hIcon-1);

      IWinePolygon shapeVolume = getMask(xIcon,yIcon,dx,dy,h1);

      Graphics2D g2 = (Graphics2D)g;
      g2.setColor((status==null)?Color.decode("#BCFEED"):status.getColor());
      g2.setClip(shapeVolume);

      // верхняя кромка
      g2.fillRect(xIcon+dx,yIcon+dy-tIcon,w1,tIcon);
      // нижняя кромка
      g2.drawRect(xIcon+dx,yIcon+dy,w1,0);

      Image image = ((ImageIcon)getIcon()).getImage();
      g.drawImage(image,xIcon,yIcon,null);

      if(status!=null) // если нужно -> пузырьки
      {
	if(status.isshaking()&&icons[shaking]!=null)
	{
	  image = ((ImageIcon)icons[shaking]).getImage();
	  g.drawImage(image,xIcon,yIcon,null);
	}

	if(status.isTempUP()&&icons[TEMP_UP]!=null) // нагревание
	{
	  image = ((ImageIcon)icons[TEMP_UP]).getImage();
	  g.drawImage(image,xIcon,yIcon,null);
	}
	else if(status.isTempDN()&&icons[TEMP_DN]!=null) // охлаждение
	{
	  image = ((ImageIcon)icons[TEMP_DN]).getImage();
	  g.drawImage(image,xIcon,yIcon,null);
	}
      }
      //g2.setClip(oldClip);
      //shapeVolume.paintPoints(g2,Color.ORANGE);
    }

    if(DEBUG)System.out.println(
	"\n----- GraphineLabel(paint): "+getName()+"\n"+status);
  }

  private IWinePolygon getMask(int xIcon,int yIcon,int dx,int dy,int h1)
  {
    pBase = new IWinePolygon();

    pBase.addPoint(xIcon+dx+13,yIcon+dy);
    pBase.addStep(-2,-7); // 1
    pBase.addStep(-5,-9); // 2
    pBase.addStep(-6,-12); // 3
    pBase.addStep(0,-26); // 4
    pBase.addStep(10,-24); // 5
    pBase.addStep(3,-15); // 6
    pBase.addStep(-2,-12); // 7
    pBase.addPoint(xIcon+dx+7,yIcon+dy-h1-1); // левый-верх
    pBase.addPoint(xIcon+dx+75,yIcon+dy-h1-1); // прав-верх
    pBase.addStep(5,16); // 1
    pBase.addStep(12,23); // 2
    pBase.addStep(5,22); // 3
    pBase.addStep(-3,16); // 4
    pBase.addStep(-4,10); // 5
    pBase.addStep(-5,12); // 6
    pBase.addStep(0,15); // 7
    //pBase.addStep(,);
    pBase.addPoint(xIcon+dx+85,yIcon+dy); // правый-низ
    pBase.addFirstPoint();

    return pBase;
  }

  public int getVolume()
  {
    return volume;
  }

  public void setVolume(int volume)
  {
    this.volume = volume;
    this.repaint();
  }

  public void setWineColor(Color wineColor)
  {
    this.wineColor = wineColor;
  }

  public Color getWineColor()
  {
    return wineColor;
  }

  public GraphineStatus getStatus()
  {
    return status;
  }

  public void setStatus(GraphineStatus status)
  {
    this.status = status;

    if(status!=null)
      status.addChangeListener(this);
  }

  public void stateChanged(ChangeEvent e)
  {
    Object o = e.getSource();

    if((o)instanceof GraphineStatus)
    {
      //super.setText(this.status.getValueAlcohol()+"");
      //System.out.println("\nGraphineLabel:\n"+this);
      //status.setTempDN(true);
      this.repaint();
    }
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    String name = getName();
    if(name!=null)
    {
      sb.append(name);
      sb.append(":\n");
    }

    sb.append(status.toString());

    return sb.toString();
  }

}
