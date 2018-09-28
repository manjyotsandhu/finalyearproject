import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Class for a custom cell renderer for note list items
 * 
 * @author manjyot
 *
 */
@SuppressWarnings("serial")
public class notesListItemCellRenderer extends JLabel
    implements ListCellRenderer<Object> {

  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {

    // Retrieving the variables
    notesListItem item = (notesListItem) value;
    setText(item.getNoteTitle());
    setIcon(item.getIcon());

    // Assigning styles if a list is selected
    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }

    setEnabled(list.isEnabled());
    setFont(list.getFont());
    setOpaque(true);

    return this;
  }

}
