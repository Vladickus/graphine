package iwine;

import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import beans.icon.IconRes;
import java.awt.Color;
import java.util.ArrayList;

/**
 *  Renderer для JComboBox
*
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class IconLabelCell extends DefaultListCellRenderer
{
  final static boolean DEBUG = !true;

  ArrayList folderIcons;
  Icon emptyIcon;

  public IconLabelCell()
  {
    super();
    emptyIcon = IconRes.getEmptyIcon(16,16);
    //emptyIcon = IconRes.getNullIcon(16,16);
    folderIcons = new ArrayList();
    folderIcons.add(IconRes.getQuadroIcon(16,16,Color.decode("#AD0000")));
    folderIcons.add(IconRes.getQuadroIcon(16,16,Color.decode("#CCFF33")));
    folderIcons.add(IconRes.getQuadroIcon(16,16,Color.decode("#00B864")));
  }


  public Component getListCellRendererComponent(
      JList list,Object value,
      int index,boolean isSelected,
      boolean cellHasFocus)
  {
    Component comp = super.getListCellRendererComponent(
	list,value,index,isSelected,cellHasFocus);

    if(DEBUG)System.out.println("Value: "+value);

    if((value)instanceof JLabel)
    {
      JLabel vLabel = (JLabel)value;
      JLabel label = (JLabel)comp;
      label.setText(vLabel.getText());
      label.setIcon(vLabel.getIcon());
      return label;
    }
    else if((value)instanceof String)
    {
      JLabel label = (JLabel)comp;
      label.setText((String)value);

      if(index==-1)
      {
	// при закрытом списке
      }
      else if(index>=0)
      {
	// при открытом списке
	if(folderIcons!=null&&folderIcons.size()>index)
	{
	  label.setIcon((Icon)folderIcons.get(index));
	}
	else label.setIcon(emptyIcon);
      }

      return label;
    }
    else
    {
      return comp;
    }
  }

}
