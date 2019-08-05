package iwine.components;

import meum.astro.components.switchpanel.SwitchingX;

/**
 * ����������� ���������� ������ (SwitchingX).switchComponent()
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author ����� �.�.
 * @version 1.0
 */
public class Pausa extends Thread
{
  SwitchingX cardPanel;
  int msec = 0;

  public Pausa(SwitchingX cardPanel,int msec)
  {
    this.msec = msec+(int)(msec*Math.random());
    this.cardPanel = cardPanel;
  }

  public void run()
  {
    try
    {
      Thread.sleep(msec);
      if(cardPanel!=null)cardPanel.switchComponent();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
