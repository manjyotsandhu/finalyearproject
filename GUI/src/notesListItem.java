import javax.swing.ImageIcon;

/**
 * Class to store a note list item
 * @author manjyot
 *
 */
public class notesListItem {
  private String noteTitle;
  private String noteContent;
  private ImageIcon icon;
  
  /**
   * Constructor
   * @param noteTitle Title of note
   * @param noteContent Contents of note
   * @param icon Icon for note
   */
  public notesListItem(String noteTitle, String noteContent, ImageIcon icon) {
    this.noteTitle = noteTitle;
    this.noteContent = noteContent;
    this.icon = icon;
  }

  /**
   * @return the noteTitle
   */
  public String getNoteTitle() {
    return noteTitle;
  }

  /**
   * @param noteTitle the noteTitle to set
   */
  public void setNoteTitle(String noteTitle) {
    this.noteTitle = noteTitle;
  }

  /**
   * @return the icon
   */
  public ImageIcon getIcon() {
    return icon;
  }

  /**
   * @param icon the icon to set
   */
  public void setIcon(ImageIcon icon) {
    this.icon = icon;
  }

  /**
   * @return the noteContent
   */
  public String getNoteContent() {
    return noteContent;
  }

  /**
   * @param noteContent the noteContent to set
   */
  public void setNoteContent(String noteContent) {
    this.noteContent = noteContent;
  }
  
  
}
