package iwine;

import java.applet.AppletContext;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public interface AppConnectX
{
  public void sendAnswer(String causa,String answer);
  public boolean isStandalone();
  public void setStandalone(boolean standalone);
  public AppletContext getAppletContext();
  public Object sendCommand(String command,Object o);
}
