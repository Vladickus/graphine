package iwine.components;

import java.beans.*;

/**
 * <p>Title: </p>
 * <p>Description: Java&trade</p>
 * <p>Copyright: Copyright &copy; 2019</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class JSmartphonePanelBeanInfo extends SimpleBeanInfo
{
  Class beanClass = JSmartphonePanel.class;
  String iconColor16x16Filename;
  String iconColor32x32Filename;
  String iconMono16x16Filename;
  String iconMono32x32Filename;

  public JSmartphonePanelBeanInfo()
  {
  }
  public PropertyDescriptor[] getPropertyDescriptors()
  {
    try
    {
      PropertyDescriptor _image = new PropertyDescriptor("image", beanClass, null, "setImage");

      PropertyDescriptor _panel = new PropertyDescriptor("panel", beanClass, null, "setPanel");
      PropertyDescriptor _wallpaper = new PropertyDescriptor("wallpaper", beanClass, null, "setWallpaper");
      PropertyDescriptor[] pds = new PropertyDescriptor[] {
        _image,
        _image,
        _panel,
        _wallpaper};
      return pds;
    }
    catch(IntrospectionException ex)
    {
      ex.printStackTrace();
      return null;
    }
  }
  public java.awt.Image getIcon(int iconKind)
  {
    switch (iconKind) {
      case BeanInfo.ICON_COLOR_16x16:
        return iconColor16x16Filename != null ? loadImage(iconColor16x16Filename) : null;
      case BeanInfo.ICON_COLOR_32x32:
        return iconColor32x32Filename != null ? loadImage(iconColor32x32Filename) : null;
      case BeanInfo.ICON_MONO_16x16:
        return iconMono16x16Filename != null ? loadImage(iconMono16x16Filename) : null;
      case BeanInfo.ICON_MONO_32x32:
        return iconMono32x32Filename != null ? loadImage(iconMono32x32Filename) : null;
    }
    return null;
  }
}
