package iwine;

import javax.swing.event.*;
import java.util.*;
import java.awt.Color;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class GraphineStatus implements Runnable
{
  Color color;

  int valueVolume; //         объём
  int valueAlcohol; //        содержание спирта
  int valueSugar; //          содержание сахара
  int valueTemperature; //    температура

  boolean shaking;
  boolean tempUP;
  boolean tempDN;

  int valueNewTemperatura; // старая температура
  Thread th;

  Object valueContent;
  private transient Vector changeListeners; //   тип: красное, белое... машиное обучение

  public GraphineStatus()
  {
    color = Color.decode("#BCFEED"); //Color.WHITE;

    shaking = false;
    tempUP = false;
    tempDN = false;
    valueVolume = 10; //         объём
    valueAlcohol = 25; //        содержание спирта
    valueSugar = 5; //          содержание сахара
    //this.setValueTemperature(30);
    valueTemperature=33;//    температура

    valueContent = null; //   тип: красное, белое... машиное обучение
  }

  public void set(GraphineStatus newStatus)
  {
    this.color = newStatus.getColor();
    this.valueVolume = newStatus.valueVolume; //         объём
    this.valueAlcohol = newStatus.valueAlcohol; //        содержание спирта
    this.valueSugar = newStatus.valueSugar; //          содержание сахара

    this.shaking = newStatus.shaking;
    this.tempUP = newStatus.tempUP;
    this.tempDN = newStatus.tempDN;

    this.setValueTemperature(newStatus.valueTemperature);
  }

  public int getValueAlcohol()
  {
    return valueAlcohol;
  }

  public Object getValueContent()
  {
    return valueContent;
  }

  public int getValueSugar()
  {
    return valueSugar;
  }

  public int getValueTemperatura()
  {
    return valueTemperature;
  }

  public int getValueVolume()
  {
    return valueVolume;
  }

  public void setValueAlcohol(int valueAlcohol)
  {
    this.valueAlcohol = valueAlcohol;
  }

  public void setValueContent(Object valueContent)
  {
    this.valueContent = valueContent;
  }

  public void setValueSugar(int valueSugar)
  {
    this.valueSugar = valueSugar;
  }

  public void setValueTemperature(int newTemp)
  {
    boolean volatileTemp = (this.valueTemperature!=newTemp);

    if(volatileTemp&&(changeListeners!=null&&changeListeners.size()>0))
    {
      this.valueNewTemperatura = newTemp;

      this.tempUP = (this.valueTemperature<newTemp);
      this.tempDN = (this.valueTemperature>newTemp);

      if(th!=null)
      {
	th.stop();
	th = null;
      }

      th = new Thread(this);
      th.start();
    }
    else this.valueTemperature = newTemp;
  }

  public void run()
  {
    try
    {
      // оповестить всех до изменений
      this.fireStateChanged(new ChangeEvent(this));

      Thread.sleep(3000);
      this.tempUP = false;
      this.tempDN = false;
      this.valueTemperature = valueNewTemperatura;

      // оповестить всех после изменений
      this.fireStateChanged(new ChangeEvent(this));
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void setValueVolume(int valueVolume)
  {
    this.valueVolume = valueVolume;
  }

  public boolean isTempUP()
  {
    return tempUP;
  }

  public boolean isTempDN()
  {
    return tempDN;
  }

  public boolean isshaking()
  {
    return shaking;
  }

  public void setshaking(boolean shaking)
  {
    this.shaking = shaking;
  }

  public void update()
  {
    this.fireStateChanged(new ChangeEvent(this));
  }

  public void setTempUP(boolean temp)
  {
    this.tempUP = temp;
  }

  public void setTempDN(boolean temp)
  {
    this.tempDN = temp;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    sb.append("      valueVolume: ");
    sb.append(valueVolume);
    sb.append("\n");

    sb.append("     valueAlcohol: ");
    sb.append(valueAlcohol);
    sb.append("\n");

    sb.append(" valueTemperatura: ");
    sb.append(valueTemperature);
    sb.append("\n");

    sb.append("          shaking: ");
    sb.append(shaking);
    sb.append("\n");

    sb.append("           tempUP: ");
    sb.append(tempUP);
    sb.append("\n");

    sb.append("           tempDN: ");
    sb.append(tempDN);
    sb.append("\n");

    sb.append("       valueSugar: ");
    sb.append(valueSugar);
//sb.append("\n");

    return sb.toString();
  }

  public synchronized void removeChangeListener(ChangeListener l)
  {
    if(changeListeners!=null&&changeListeners.contains(l))
    {
      Vector v = (Vector)changeListeners.clone();
      v.removeElement(l);
      changeListeners = v;
    }
  }

  public synchronized void addChangeListener(ChangeListener l)
  {
    Vector v = changeListeners==null?new Vector(2):
	(Vector)changeListeners.clone();
    if(!v.contains(l))
    {
      v.addElement(l);
      changeListeners = v;
    }
  }

  protected void fireStateChanged(ChangeEvent e)
  {
    if(changeListeners!=null)
    {
      Vector listeners = changeListeners;
      int count = listeners.size();
      for(int i = 0; i<count; i++)
      {
	((ChangeListener)listeners.elementAt(i)).stateChanged(e);
      }
    }
  }

  public Color getColor()
  {
    return color;
  }

  public void setColor(Color color)
  {
    this.color = color;
    update();
  }

}
