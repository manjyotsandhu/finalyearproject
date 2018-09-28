import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import java.awt.Cursor;
import javax.swing.JCheckBox;
import java.awt.ComponentOrientation;

/**
 * Class for the new note GUI
 * 
 * @author manjyot
 *
 */
@SuppressWarnings("serial")
public class NewNoteGUI extends JFrame {

  // GUI Variables
  private JFrame frame;
  private JTextField noteTitleInputField;
  private JLabel errorNewNote;
  private JLabel errorNewNoteTitle;
  private JPanel noteNameInputPanel;

  // Colour variables
  public static Color greenColour = Color.decode("#679a6a");
  public static Color darkGreenColour = Color.decode("#456747");
  public static Color lightGreyColour = Color.decode("#d7d7d7");
  public static Color darkGreyColour = Color.decode("#3e403e");
  public static Color redColour = Color.decode("#d14647");

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          NewNoteGUI window = new NewNoteGUI();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * @return JFrame Returns current frame
   */
  public JFrame returnFrame() {
    return this.frame;
  }

  /**
   * Create the application.
   */
  public NewNoteGUI() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {

    /**
     * Frame
     */
    frame = new JFrame();
    frame.setResizable(false);
    frame.setIconImage(Toolkit.getDefaultToolkit()
        .getImage(RegisterGUI.class.getResource("/img/logo.png")));
    frame.getContentPane().setBackground(Color.WHITE);
    frame.getContentPane().setLayout(null);
    frame.setBounds(100, 100, 450, 300);
    frame.setSize(500, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    globalMethods.centreWindow(frame);

    JPanel containerPanel = new JPanel();
    containerPanel.setBounds(0, 0, 494, 371);
    containerPanel.setBackground(Color.WHITE);
    containerPanel.setPreferredSize(new Dimension(500, 280));
    frame.getContentPane().add(containerPanel);
    containerPanel.setLayout(new BorderLayout(0, 0));

    /**
     * Title section
     */

    // Panel
    JPanel titlePanel = new JPanel();
    containerPanel.add(titlePanel, BorderLayout.NORTH);
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setPreferredSize(new Dimension(500, 80));
    SpringLayout sl_titlePanel = new SpringLayout();
    titlePanel.setLayout(sl_titlePanel);

    // Title
    JLabel newNoteTitle = new JLabel("New note");
    sl_titlePanel.putConstraint(SpringLayout.NORTH, newNoteTitle, 47,
        SpringLayout.NORTH, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.WEST, newNoteTitle, 48,
        SpringLayout.WEST, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.SOUTH, newNoteTitle, -10,
        SpringLayout.SOUTH, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.EAST, newNoteTitle, -293,
        SpringLayout.EAST, titlePanel);
    newNoteTitle.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 17));
    titlePanel.add(newNoteTitle);

    /**
     * Input section
     */

    // Panel
    JPanel inputPanel = new JPanel();
    containerPanel.add(inputPanel);
    inputPanel.setBackground(Color.WHITE);
    inputPanel.setPreferredSize(new Dimension(500, 200));
    inputPanel.setLayout(null);

    /**
     * New name input section
     */

    // Panel
    noteNameInputPanel = new JPanel();
    noteNameInputPanel.setBorder(new LineBorder(lightGreyColour));
    noteNameInputPanel.setBounds(147, 11, 268, 42);
    noteNameInputPanel.setBackground(Color.WHITE);
    noteNameInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    inputPanel.add(noteNameInputPanel);
    FlowLayout fl_noteNameInputPanel = (FlowLayout) noteNameInputPanel
        .getLayout();
    fl_noteNameInputPanel.setVgap(10);
    fl_noteNameInputPanel.setHgap(10);
    fl_noteNameInputPanel.setAlignment(FlowLayout.LEFT);

    // Title
    JLabel titleLbl = new JLabel("Title");
    titleLbl.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 13));
    titleLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    titleLbl.setLabelFor(noteNameInputPanel);
    titleLbl.setBounds(10, 11, 125, 42);
    inputPanel.add(titleLbl);

    // Icon
    JLabel newNoteIcon = new JLabel();
    newNoteIcon.setIcon(
        new ImageIcon(NewNoteGUI.class.getResource("/img/notesIcon.png")));
    noteNameInputPanel.add(newNoteIcon);

    // Field
    noteTitleInputField = new JTextField();
    noteNameInputPanel.add(noteTitleInputField);
    noteTitleInputField.setForeground(Color.GRAY);
    noteTitleInputField.setBackground(Color.WHITE);
    noteTitleInputField.setBorder(null);
    noteTitleInputField
        .setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    noteTitleInputField.setHorizontalAlignment(SwingConstants.LEFT);
    noteTitleInputField.setColumns(18);

    // Error icon
    errorNewNote = new JLabel("");
    errorNewNote.setVisible(false);
    errorNewNote.setBounds(428, 11, 44, 42);
    inputPanel.add(errorNewNote);
    errorNewNote.setIcon(
        new ImageIcon(RegisterGUI.class.getResource("/img/errorIcon.png")));

    // Error message
    errorNewNoteTitle = new JLabel("Note title not entered");
    errorNewNoteTitle.setVisible(false);
    errorNewNoteTitle.setForeground(redColour);
    errorNewNoteTitle.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 11));
    errorNewNoteTitle.setBounds(147, 53, 278, 24);
    inputPanel.add(errorNewNoteTitle);

    /**
     * Privacy input
     */

    // Title
    JLabel privateLbl = new JLabel("Privacy");
    privateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    privateLbl.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 13));
    privateLbl.setBounds(12, 88, 125, 42);
    inputPanel.add(privateLbl);

    // Radio buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setBounds(new Rectangle(0, 0, 0, 42));
    FlowLayout flowLayout = (FlowLayout) radioPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    radioPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    radioPanel.setOpaque(false);
    radioPanel.setBounds(147, 88, 325, 42);
    inputPanel.add(radioPanel);

    JRadioButton privateRadioBtn = new JRadioButton("Private");
    radioPanel.add(privateRadioBtn);
    privateRadioBtn.setSelected(true);
    privateRadioBtn.setOpaque(false);
    privateRadioBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));

    JRadioButton publicRadioBtn = new JRadioButton("Public");
    radioPanel.add(publicRadioBtn);
    publicRadioBtn.setOpaque(false);
    publicRadioBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));

    JRadioButton passwordRadioBtn = new JRadioButton("Password protected");
    radioPanel.add(passwordRadioBtn);
    passwordRadioBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    passwordRadioBtn.setOpaque(false);

    ButtonGroup radioBtnGroup = new ButtonGroup();
    radioBtnGroup.add(privateRadioBtn);
    radioBtnGroup.add(publicRadioBtn);
    radioBtnGroup.add(passwordRadioBtn);

    /**
     * Collaborative input
     */

    // Title
    JLabel collabLbl = new JLabel("Collaborative");
    collabLbl.setHorizontalAlignment(SwingConstants.RIGHT);
    collabLbl.setBounds(12, 141, 125, 42);
    inputPanel.add(collabLbl);
    collabLbl.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 13));
    collabLbl.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    // Checkbox
    JCheckBox collabCheckbox = new JCheckBox("");
    collabCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
    collabCheckbox.setBounds(147, 141, 30, 42);
    inputPanel.add(collabCheckbox);
    collabCheckbox.setOpaque(false);

    /**
     * Buttons section
     */

    // Panel
    JPanel btnsPanel = new JPanel();
    btnsPanel.setOpaque(false);
    btnsPanel.setPreferredSize(new Dimension(10, 50));
    containerPanel.add(btnsPanel, BorderLayout.SOUTH);
    btnsPanel.setLayout(new GridLayout(0, 2, 0, 0));

    // Back button
    JButton newNoteBackBtn = new JButton("Cancel");
    newNoteBackBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    btnsPanel.add(newNoteBackBtn);
    newNoteBackBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frame.dispose();
      }
    });
    newNoteBackBtn.setBackground(Color.GRAY);
    newNoteBackBtn.setBorderPainted(false);
    newNoteBackBtn.setForeground(Color.WHITE);

    // Confirm button
    JButton newNoteConfirmBtn = new JButton("Create");
    btnsPanel.add(newNoteConfirmBtn);
    newNoteConfirmBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (noteTitleInputField.getText().equals("")) {
          noteNameInputPanel.setBorder(new LineBorder(redColour));
          errorNewNote.setVisible(true);
          errorNewNoteTitle.setVisible(true);
        } else {
          frame.dispose();
        }
      }
    });
    newNoteConfirmBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    newNoteConfirmBtn.setForeground(Color.WHITE);
    newNoteConfirmBtn.setBackground(greenColour);
    newNoteConfirmBtn.setBorder(null);
    newNoteConfirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

  }
}
