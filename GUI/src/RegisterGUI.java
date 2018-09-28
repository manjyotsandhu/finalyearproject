import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Class for the register GUI
 * 
 * @author manjyot
 *
 */
@SuppressWarnings("serial")
public class RegisterGUI extends JFrame {

  // GUI Variables
  private JFrame frame;
  private JTextField usernameInputField;
  private JPasswordField passwordInputField;
  private JPasswordField confirmPasswordInputField;
  private JLabel errorLblUsername;
  private JLabel errorLblPass;
  private JLabel errorTextUsername;
  private JLabel errorTextPass;
  private JPanel usernameInputPanel;
  private JPanel passwordInputPanel;
  private JPanel confirmPasswordInputPanel;

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
          RegisterGUI window = new RegisterGUI();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * @return Returns current JFrame
   */
  public JFrame returnFrame() {
    return this.frame;
  }

  /**
   * Create the application.
   */
  public RegisterGUI() {
    initialize();
  }

  /**
   * Graphically displays a username error
   */
  public void showUsernameError() {
    errorTextUsername.setVisible(true);
    errorLblUsername.setVisible(true);
  }

  /**
   * Hides graphical username error
   */
  public void hideUsernameError() {
    errorTextUsername.setVisible(false);
    errorLblUsername.setVisible(false);
  }

  /**
   * Graphically displays password error
   */
  public void showPasswordError() {
    errorTextPass.setVisible(true);
    errorLblPass.setVisible(true);
  }

  /**
   * Hides graphical password error
   */
  public void hidePasswordError() {
    errorTextPass.setVisible(true);
    errorLblPass.setVisible(true);
  }

  /**
   * Validates the username and password fields
   * 
   * @return Returns true if both fields are valid
   */
  @SuppressWarnings("deprecation")
  public boolean validateFields() {

    boolean usernameValidated = false;
    boolean passwordValidated = false;

    // If no username entered then invalid
    if (usernameInputField.getText().equals("")) {
      usernameInputPanel.setBorder(new LineBorder(redColour));
      usernameValidated = false;
      showUsernameError();
    } else { // If username valid
      usernameInputPanel.setBorder(new LineBorder(lightGreyColour));
      usernameValidated = true;
      hideUsernameError();
    }

    if (passwordInputField.getPassword().length == 0) { // If no password
                                                        // entered
      errorTextPass.setText("Password not entered");
      passwordInputPanel.setBorder(new LineBorder(redColour));
      confirmPasswordInputPanel.setBorder(new LineBorder(redColour));
      passwordValidated = false;
      showPasswordError();
    } else if (confirmPasswordInputField.getPassword().length == 0) { // If no
                                                                      // confirm
                                                                      // password
                                                                      // entered
      errorTextPass.setText("Please confirm password");
      passwordInputPanel.setBorder(new LineBorder(redColour));
      confirmPasswordInputPanel.setBorder(new LineBorder(redColour));
      passwordValidated = false;
      showPasswordError();
    } else if (!passwordInputField.getText()
        .equals(confirmPasswordInputField.getText())) { // If passwords are not
                                                        // equal
      errorTextPass.setText("Passwords do not match");
      passwordInputPanel.setBorder(new LineBorder(redColour));
      confirmPasswordInputPanel.setBorder(new LineBorder(redColour));
      passwordValidated = false;
      showPasswordError();
    } else { // If passwords are valid
      passwordInputPanel.setBorder(new LineBorder(lightGreyColour));
      confirmPasswordInputPanel.setBorder(new LineBorder(lightGreyColour));
      passwordValidated = true;
      hidePasswordError();
    }

    // If both fields are valid
    if (usernameValidated && passwordValidated) {
      hideUsernameError();
      hidePasswordError();
      return true;
    }

    return false;
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
     * Title
     */

    // Panel
    JPanel titlePanel = new JPanel();
    containerPanel.add(titlePanel, BorderLayout.NORTH);
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setPreferredSize(new Dimension(500, 80));
    SpringLayout sl_titlePanel = new SpringLayout();
    titlePanel.setLayout(sl_titlePanel);

    // Title
    JLabel registerTitle = new JLabel("Register");
    sl_titlePanel.putConstraint(SpringLayout.NORTH, registerTitle, 29,
        SpringLayout.NORTH, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.EAST, registerTitle, -294,
        SpringLayout.EAST, titlePanel);
    registerTitle.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 17));
    titlePanel.add(registerTitle);

    // Subtitle
    JLabel registerDescTitle = new JLabel(
        "Register an account with us to save notes on the go!");
    sl_titlePanel.putConstraint(SpringLayout.WEST, registerTitle, 0,
        SpringLayout.WEST, registerDescTitle);
    sl_titlePanel.putConstraint(SpringLayout.SOUTH, registerTitle, -6,
        SpringLayout.NORTH, registerDescTitle);
    sl_titlePanel.putConstraint(SpringLayout.NORTH, registerDescTitle, 58,
        SpringLayout.NORTH, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.WEST, registerDescTitle, 47,
        SpringLayout.WEST, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.SOUTH, registerDescTitle, -8,
        SpringLayout.SOUTH, titlePanel);
    sl_titlePanel.putConstraint(SpringLayout.EAST, registerDescTitle, -105,
        SpringLayout.EAST, titlePanel);
    registerDescTitle.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    titlePanel.add(registerDescTitle);

    /**
     * Input panel
     */

    // Panel
    JPanel inputPanel = new JPanel();
    containerPanel.add(inputPanel);
    inputPanel.setBackground(Color.WHITE);
    inputPanel.setPreferredSize(new Dimension(500, 200));
    inputPanel.setLayout(null);

    // Username input
    usernameInputPanel = new JPanel();
    usernameInputPanel.setBorder(new LineBorder(lightGreyColour));
    usernameInputPanel.setBounds(48, 11, 346, 42);
    usernameInputPanel.setBackground(Color.WHITE);
    usernameInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    inputPanel.add(usernameInputPanel);
    FlowLayout flowLayout_1 = (FlowLayout) usernameInputPanel.getLayout();
    flowLayout_1.setVgap(10);
    flowLayout_1.setHgap(10);
    flowLayout_1.setAlignment(FlowLayout.LEFT);

    JLabel usernameIcon = new JLabel();
    usernameIcon.setIcon(new ImageIcon(
        RegisterGUI.class.getResource("/img/accountCircleIcon.png")));
    usernameInputPanel.add(usernameIcon);

    usernameInputField = new JTextField();
    usernameInputField.setForeground(Color.GRAY);
    usernameInputField.setBackground(Color.WHITE);
    usernameInputField.setBorder(null);
    usernameInputField
        .setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    usernameInputField.setHorizontalAlignment(SwingConstants.LEFT);
    usernameInputField.setText("Username");
    usernameInputPanel.add(usernameInputField);
    usernameInputField.setColumns(25);

    // Password input
    passwordInputPanel = new JPanel();
    passwordInputPanel.setBorder(new LineBorder(lightGreyColour));
    FlowLayout flowLayout = (FlowLayout) passwordInputPanel.getLayout();
    flowLayout.setVgap(10);
    flowLayout.setHgap(10);
    flowLayout.setAlignment(FlowLayout.LEFT);
    passwordInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    passwordInputPanel.setBounds(48, 86, 346, 42);
    passwordInputPanel.setBackground(Color.WHITE);
    inputPanel.add(passwordInputPanel);

    JLabel passwordIcon = new JLabel("");
    passwordIcon.setIcon(
        new ImageIcon(RegisterGUI.class.getResource("/img/lockIcon.png")));
    passwordInputPanel.add(passwordIcon);

    passwordInputField = new JPasswordField();
    passwordInputField.setForeground(Color.GRAY);
    passwordInputField.setBorder(null);
    passwordInputField
        .setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    passwordInputField.setText("Password");
    passwordInputField.setColumns(25);
    passwordInputPanel.add(passwordInputField);

    confirmPasswordInputPanel = new JPanel();
    confirmPasswordInputPanel.setBorder(new LineBorder(lightGreyColour));
    FlowLayout flowLayout_2 = (FlowLayout) confirmPasswordInputPanel
        .getLayout();
    flowLayout_2.setVgap(10);
    flowLayout_2.setHgap(10);
    flowLayout_2.setAlignment(FlowLayout.LEFT);
    confirmPasswordInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    confirmPasswordInputPanel.setBounds(48, 139, 346, 42);
    confirmPasswordInputPanel.setBackground(Color.WHITE);
    inputPanel.add(confirmPasswordInputPanel);

    JLabel confirmPasswordIcon = new JLabel("");
    confirmPasswordIcon.setIcon(
        new ImageIcon(RegisterGUI.class.getResource("/img/lockIcon.png")));
    confirmPasswordInputPanel.add(confirmPasswordIcon);

    confirmPasswordInputField = new JPasswordField();
    confirmPasswordInputField.setForeground(Color.GRAY);
    confirmPasswordInputField.setBorder(null);
    confirmPasswordInputField
        .setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    confirmPasswordInputField.setText("Password");
    confirmPasswordInputField.setColumns(25);
    confirmPasswordInputPanel.add(confirmPasswordInputField);

    // Error components
    errorLblUsername = new JLabel("");
    errorLblUsername.setVisible(false);
    errorLblUsername.setBounds(404, 11, 44, 42);
    inputPanel.add(errorLblUsername);
    errorLblUsername.setIcon(
        new ImageIcon(RegisterGUI.class.getResource("/img/errorIcon.png")));

    errorLblPass = new JLabel("");
    errorLblPass.setVisible(false);
    errorLblPass.setIcon(
        new ImageIcon(RegisterGUI.class.getResource("/img/errorIcon.png")));
    errorLblPass.setBounds(404, 86, 37, 42);
    inputPanel.add(errorLblPass);

    errorTextUsername = new JLabel("Username not entered");
    errorTextUsername.setVisible(false);
    errorTextUsername.setForeground(redColour);
    errorTextUsername.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 11));
    errorTextUsername.setBounds(48, 64, 346, 14);
    inputPanel.add(errorTextUsername);

    errorTextPass = new JLabel("");
    errorTextPass.setForeground(redColour);
    errorTextPass.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 11));
    errorTextPass.setBounds(48, 188, 346, 14);
    inputPanel.add(errorTextPass);

    /**
     * Buttons
     */

    // Panel
    JPanel btnsPanel = new JPanel();
    btnsPanel.setOpaque(false);
    btnsPanel.setPreferredSize(new Dimension(10, 50));
    containerPanel.add(btnsPanel, BorderLayout.SOUTH);
    btnsPanel.setLayout(new GridLayout(0, 2, 0, 0));

    // Back button
    JButton registerBackBtn = new JButton("Cancel");
    registerBackBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    btnsPanel.add(registerBackBtn);
    registerBackBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frame.dispose();
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.returnFrame().setVisible(true);
      }
    });
    registerBackBtn.setBackground(Color.GRAY);
    registerBackBtn.setBorderPainted(false);
    registerBackBtn.setForeground(Color.WHITE);

    // Confirm button
    JButton registerConfirmBtn = new JButton("Register");
    btnsPanel.add(registerConfirmBtn);
    registerConfirmBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (validateFields() == true) {
          frame.dispose();
          FinalGUI finalGUI = new FinalGUI();
          finalGUI.returnFrame().setVisible(true);
        }
      }
    });

    registerConfirmBtn
        .setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    registerConfirmBtn.setForeground(Color.WHITE);
    registerConfirmBtn.setBackground(greenColour);
    registerConfirmBtn.setBorder(null);
    registerConfirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
  }
}
