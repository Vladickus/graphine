package iwine.components;

import javax.swing.event.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class EmulateBoolean implements ChangeListener
{
  boolean emulate;
  private transient Vector changeListeners;

  public EmulateBoolean(boolean emulate)
  {
    this.emulate = emulate;
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

  public synchronized void removeChangeListener(ChangeListener l)
  {
    if(changeListeners!=null&&changeListeners.contains(l))
    {
      Vector v = (Vector)changeListeners.clone();
      v.removeElement(l);
      changeListeners = v;
    }
  }

  public void fireStateChanged(ChangeEvent e)
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

  /**
   *
   * @param e ChangeEvent
   */
  public void stateChanged(ChangeEvent e)
  {
  }

  public boolean isValue()
  {
    return emulate;
  }

  public void setValue(boolean emulate)
  {
    this.emulate = emulate;
    update();
  }

  public void setEmulate(boolean emulate,boolean mute)
  {
    this.emulate = emulate;
    if(!mute)update();
  }

  public void update()
  {
    this.fireStateChanged(new ChangeEvent(this));
  }

}
