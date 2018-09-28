import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main GUI for the application
 * 
 * @author Manjyot Sandhu
 *
 */
public class FinalGUI {

  private JFrame frame;

  // Notes model variables
  private DefaultListModel<notesListItem> notesModel = new DefaultListModel<notesListItem>();
  private JList<notesListItem> notesList;
  private JEditorPane notesEditor;
  private JPanel notesContentPanel;
  private JPanel notesListPanel;

  // Colour variables
  public static Color greenColour = Color.decode("#679a6a");
  public static Color darkGreenColour = Color.decode("#456747");
  public static Color lightGreyColour = Color.decode("#d7d7d7");
  public static Color darkGreyColour = Color.decode("#3e403e");
  public static Color paleGreyColour = Color.decode("#e1e0e0");

  // Other variables
  private JButton menuBtn;
  private JLabel logoLbl;
  private JSeparator separator;
  private JLabel newNoteLbl;
  private JLabel saveNoteLbl;
  private JPanel leftBtnPanel;
  private JSeparator separator_1;

  // Text variables
  private String artInInterfacesContent;
  private String colourTheoryContent;
  private String personalMotivationContent;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          FinalGUI window = new FinalGUI();
          window.frame.setVisible(true);
          window.frame.setIconImage(
              new ImageIcon(FinalGUI.class.getResource("/img/logo.png"))
                  .getImage());

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public FinalGUI() {
    initialize();
  }

  /**
   * @return JFrame Frame used for this window
   */
  public JFrame returnFrame() {
    return this.frame;
  }

  /**
   * Loads text into a string
   * 
   * @param fileName
   *          File to load text from
   * @return String Returns a string with the contents of the text from file
   */
  public String loadText(String fileName) {

    String line = null;
    StringBuilder sb = new StringBuilder();

    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);

      // While there are still more lines to read
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return sb.toString();

  }

  /**
   * Initialise the contents of the frame.
   */
  private void initialize() {

    /**
     * Frame
     */
    frame = new JFrame();
    frame.setResizable(false);
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setBounds(100, 100, 800, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setTitle("Notes");
    frame.setVisible(true);
    globalMethods.centreWindow(frame);

    /**
     * Loading files and loading list model
     */

    artInInterfacesContent = loadText("./src/text/artInInterfaces.txt");
    colourTheoryContent = loadText("./src/text/colourTheory.txt");
    personalMotivationContent = loadText("./src/text/personalMotivation.txt");

    notesModel.addElement(
        new notesListItem("Art in Interfaces", artInInterfacesContent,
            new ImageIcon(getClass().getResource("/img/notesIcon.png"))));

    notesModel
        .addElement(new notesListItem("Colour Theory", colourTheoryContent,
            new ImageIcon(getClass().getResource("/img/notesIcon.png"))));
    notesModel.addElement(
        new notesListItem("Personal Motivation", personalMotivationContent,
            new ImageIcon(getClass().getResource("/img/notesIcon.png"))));

    /**
     * Top bar
     */

    /**
     * Split pane
     */
    frame.getContentPane().setLayout(new BorderLayout(0, 0));

    JSplitPane splitPane = new JSplitPane();
    splitPane.setEnabled(false);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerSize(10);
    splitPane.setBackground(Color.WHITE);
    splitPane.setBorder(null);
    splitPane.setRightComponent(notesContentPanel);
    splitPane.setLeftComponent(notesListPanel);
    frame.getContentPane().add(splitPane, BorderLayout.CENTER);

    /**
     * Notes content
     */

    notesContentPanel = new JPanel();
    notesContentPanel.setBackground(Color.WHITE);
    notesContentPanel.setLayout(new BorderLayout(0, 0));

    // Panel
    JPanel topBarPanel = new JPanel();
    notesContentPanel.add(topBarPanel, BorderLayout.NORTH);
    topBarPanel.setBackground(greenColour);
    topBarPanel.setLayout(new BorderLayout(0, 0));

    leftBtnPanel = new JPanel();
    leftBtnPanel.setOpaque(false);
    topBarPanel.add(leftBtnPanel, BorderLayout.WEST);

    // Menu btn
    menuBtn = new JButton("");
    leftBtnPanel.add(menuBtn);
    menuBtn.setMargin(new Insets(2, 30, 2, 14));
    menuBtn.setBorder(new EmptyBorder(0, 12, 0, 0));
    menuBtn.setFocusable(false);
    menuBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (splitPane.getDividerLocation() == 0) {
          splitPane.setDividerLocation(200);
        } else {
          splitPane.setDividerLocation(0);
        }
      }
    });

    menuBtn.setIcon(
        new ImageIcon(FinalGUI.class.getResource("/img/menuIcon.png")));
    menuBtn.setContentAreaFilled(false);
    menuBtn.setBorderPainted(false);

    /**
     * Notes content
     */

    // Title
    JLabel notesTitleLbl = new JLabel(
        notesModel.getElementAt(0).getNoteTitle());
    topBarPanel.add(notesTitleLbl, BorderLayout.CENTER);
    notesTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
    notesTitleLbl.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 15));
    notesTitleLbl.setForeground(Color.WHITE);

    // Content
    notesEditor = new JEditorPane();
    JScrollPane scrollPane = new JScrollPane(notesEditor);
    scrollPane.setMaximumSize(new Dimension(600, 32767));
    notesEditor.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    notesEditor.setSelectedTextColor(Color.WHITE);
    notesEditor.setSelectionColor(darkGreenColour);
    notesEditor.setForeground(darkGreyColour);
    notesContentPanel.add(scrollPane, BorderLayout.CENTER);
    notesEditor.setMargin(new Insets(20, 21, 3, 3));
    notesEditor.setCaretColor(Color.RED);
    notesEditor.setBorder(new LineBorder(new Color(255, 255, 255), 20));
    notesEditor.setText(notesModel.getElementAt(0).getNoteContent());

    /**
     * Notes list
     */

    // Panel
    notesListPanel = new JPanel();
    notesListPanel.setOpaque(false);
    notesListPanel.setBackground(Color.WHITE);
    splitPane.add(notesListPanel);
    splitPane.add(notesContentPanel);
    notesListPanel.setLayout(null);

    // Brand
    logoLbl = new JLabel("");
    logoLbl.setBounds(75, 23, 50, 50);
    logoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    logoLbl.setIcon(new ImageIcon(FinalGUI.class.getResource("/img/logo.png")));
    notesListPanel.add(logoLbl);

    // Separator
    separator = new JSeparator();
    separator.setForeground(UIManager.getColor("Button.background"));
    separator.setBounds(0, 89, 200, 2);
    separator.setMaximumSize(new Dimension(32767, 2));
    separator.setPreferredSize(new Dimension(0, 1));
    notesListPanel.add(separator);

    // New note button
    newNoteLbl = new JLabel("New");
    newNoteLbl.addMouseListener(new MouseAdapter() {

      // If mouse hovers over, change colour to green
      @Override
      public void mouseEntered(MouseEvent arg0) {
        newNoteLbl.setForeground(greenColour);
      }

      // If mouse leaves, change colour to dark grey
      @Override
      public void mouseExited(MouseEvent e) {
        newNoteLbl.setForeground(darkGreyColour);
      }

      // If mouse click, open new note GUI
      @Override
      public void mouseClicked(MouseEvent arg0) {
        NewNoteGUI newNote = new NewNoteGUI();
        newNote.returnFrame().setVisible(true);
      }
    });

    newNoteLbl.setBounds(21, 109, 25, 15);
    newNoteLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    newNoteLbl.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    notesListPanel.add(newNoteLbl);

    //Save note button
    saveNoteLbl = new JLabel("Save");
    saveNoteLbl.addMouseListener(new MouseAdapter() {
      
      //If mouse hovers over, change colour to green
      @Override
      public void mouseEntered(MouseEvent arg0) {
        saveNoteLbl.setForeground(greenColour);
      }

      //If mouse leaves, change colour to dark grey
      @Override
      public void mouseExited(MouseEvent e) {
        saveNoteLbl.setForeground(darkGreyColour);
      }
    });
    
    saveNoteLbl.setBounds(21, 135, 27, 15);
    saveNoteLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    saveNoteLbl.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    saveNoteLbl.setHorizontalAlignment(SwingConstants.CENTER);
    notesListPanel.add(saveNoteLbl);

    //Separator between options and note list
    separator_1 = new JSeparator();
    separator_1.setPreferredSize(new Dimension(0, 1));
    separator_1.setMaximumSize(new Dimension(32767, 2));
    separator_1.setForeground(SystemColor.menu);
    separator_1.setBounds(0, 173, 200, 2);
    notesListPanel.add(separator_1);

    // Notes list title
    JLabel notesListTitle = new JLabel("All notes");
    notesListTitle.setBounds(21, 186, 48, 15);
    notesListTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    notesListTitle.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    notesListTitle.setHorizontalTextPosition(SwingConstants.LEFT);
    notesListTitle.setHorizontalAlignment(SwingConstants.LEFT);
    notesListTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
    notesListPanel.add(notesListTitle);

    // Notes list
    notesList = new JList<>(notesModel);
    notesList.setBounds(33, 212, 157, 211);
    notesList.setAlignmentX(Component.LEFT_ALIGNMENT);
    notesList.setCellRenderer(new notesListItemCellRenderer());
    notesList.setOpaque(false);
    notesList.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    notesListPanel.add(notesList);

    notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    notesList.setSelectedIndex(0);

    notesList.setForeground(darkGreyColour);
    notesList.setSelectionBackground(greenColour);
    notesList.setSelectionForeground(Color.WHITE);
    notesList.setBorder(null);

    //Log out button
    JButton logoutBtn = new JButton("LOG OUT");
    logoutBtn.addMouseListener(new MouseAdapter() {
      
      //If mouse hovers over, change colour to green
      @Override
      public void mouseEntered(MouseEvent arg0) {
        logoutBtn.setForeground(greenColour);
      }

      //If mouse leaves, change colour to dark grey
      @Override
      public void mouseExited(MouseEvent e) {
        logoutBtn.setForeground(darkGreyColour);
      }
      @Override
      public void mouseClicked(MouseEvent e) {
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.returnFrame().setVisible(true);
        frame.dispose();
      }
    });
    
    logoutBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    logoutBtn.setContentAreaFilled(false);
    logoutBtn.setBorderPainted(false);
    logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);
    logoutBtn.setForeground(Color.BLACK);
    logoutBtn.setIcon(
        new ImageIcon(FinalGUI.class.getResource("/img/exitIcon.png")));
    logoutBtn.setBounds(10, 424, 115, 36);
    notesListPanel.add(logoutBtn);
    
    splitPane.setDividerLocation(0);

    
    // When note is selected, change contents in notes content panel
    notesList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent le) {
        int index = notesList.getSelectedIndex();
        notesListItem item = (notesListItem) notesModel.get(index);
        notesTitleLbl.setText(item.getNoteTitle());
        notesEditor.setText(item.getNoteContent());
      }

    });

  }
}
