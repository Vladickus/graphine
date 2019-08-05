package iwine;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.Action;
import java.awt.event.ActionEvent;

/**
 *
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class CommandComboBox extends JComboBox implements ActionListener
{
  public CommandComboBox(String[] arr)
  {
    super(arr);
    this.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e)
  {
    String sCom = e.getActionCommand();
    JComboBox cb = (JComboBox)e.getSource();
    //System.out.println("tag: "+cb.getSelectedItem());
  }
}
