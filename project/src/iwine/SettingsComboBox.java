package iwine;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
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
public class SettingsComboBox extends JComboBox
{
  public SettingsComboBox()
  {
    super();

    //SettingsListCellRenderer renderer = new SettingsListCellRenderer();
    IconLabelCell renderer = new IconLabelCell();
    setRenderer(renderer);

    String[] items =
	{"Bordo","Liquor","Noname"};
    setModel(new DefaultComboBoxModel(items));
  }
  public Color getColor()
  {
    Color color=null;
    String item = (String) this.getSelectedItem();

    if(item.equals("Bordo"))color=Color.decode("#AD0000");// red
    if(item.equals("Liquor"))color=Color.decode("#CCFF33");// orange
    if(item.equals("Noname"))color=Color.decode("#00B864");// green

    return color;
  }
}
