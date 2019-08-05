package iwine.components;

import java.awt.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class IWinePolygon extends Polygon
{
  int lastX;
  int lastY;

  int lastDX;
  int lastDY;

  public IWinePolygon()
  {
  }

  public Point getLastPoint()
  {
    return new Point(lastX,lastY);
  }

  public Point getLastStep()
  {
    return new Point(lastDX,lastDY);
  }

  public void addPoint(int x,int y)
  {
    super.addPoint(x,y);

    this.lastX = x;
    this.lastY = y;
  }

  public void addStep(int dx,int dy)
  {
    lastDX = lastX+dx;
    lastDY = lastY+dy;

    addPoint(lastDX,lastDY);
  }

  public void addFirstPoint()
  {
    addPoint(xpoints[0],ypoints[0]);
  }

  public void paintPoints(Graphics g,Color color)
  {
    Color oldColor = g.getColor();
    g.setColor(color);

    for(int n = 0; n<xpoints.length; n++)
    {
      g.fillRect(xpoints[n],ypoints[n],1,1);
    }

    g.setColor(oldColor);
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    for(int n = 0; n<xpoints.length; n++)
    {
      sb.append("addPoint(");
      sb.append(xpoints[n]);
      sb.append(",");
      sb.append(ypoints[n]);
      sb.append(");");
    }

    return sb.toString();
  }

}
