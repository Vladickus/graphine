package iwine;

import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListModel;
import java.util.ArrayList;
import java.util.Collections;
import beans.icon.IconRes;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.DefaultListCellRenderer;

/**
*
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author Базин В.В.
 * @version 1.0
 */
public class SettingsListCellRenderer extends DefaultListCellRenderer
{
  ArrayList folderIcons;
  public SettingsListCellRenderer()
  {
    super();
    folderIcons = new ArrayList();
    //IconRes.getQuadroIcon(16,16,Color.orange);
  }

  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus)
  {
    JLabel label = this;//new JLabel();
    label.setText("123");
    if(value!=null)
    {
      ListModel model = list.getModel();
      String text = (String)model.getElementAt(index);
      label.setText(text);
      label.setOpaque(true);
    }
/*
    if(folderIcons!=null&&(index>-1)&&folderIcons.size()>index)
    {
      setIcon((Icon)folderIcons.get(index));
    }
*/
    if(isSelected)
    {
      label.setForeground(list.getSelectionForeground());
      label.setBackground(list.getSelectionBackground());
    }
    else
    {
      label.setForeground(list.getForeground());
      label.setBackground(list.getBackground());
    }

    return label;
  }

}
