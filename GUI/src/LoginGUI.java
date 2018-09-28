import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

/**
 * Class for the log in GUI
 * 
 * @author manjyot
 *
 */
@SuppressWarnings("serial")
public class LoginGUI extends JFrame {

  private JFrame frame;

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
          LoginGUI window = new LoginGUI();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public LoginGUI() {
    initialize();
  }

  public JFrame returnFrame() {
    return this.frame;
  }

  /**
   * Initialise the contents of the frame.
   */

  private void initialize() {

    /**
     * Frame variables
     */
    frame = new JFrame();
    frame.setResizable(false);
    frame.setIconImage(Toolkit.getDefaultToolkit()
        .getImage(LoginGUI.class.getResource("/img/logo.png")));
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setBackground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 400);
    frame.getContentPane().setLayout(null);
    globalMethods.centreWindow(frame);

    JPanel logInPanel = new JPanel();
    logInPanel.setBounds(0, 0, 494, 371);
    logInPanel.setBackground(greenColour);
    logInPanel.setLayout(new BorderLayout());
    frame.getContentPane().add(logInPanel);

    /**
     * Title section
     */
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setPreferredSize(new Dimension(500, 80));
    logInPanel.add(titlePanel, BorderLayout.NORTH);
    titlePanel.setLayout(new BorderLayout(0, 0));

    JLabel logoLabel = new JLabel("");
    logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    logoLabel
        .setIcon(new ImageIcon(LoginGUI.class.getResource("/img/logo.png")));
    titlePanel.add(logoLabel);

    /**
     * Input section
     */
    JPanel inputPanel = new JPanel();
    inputPanel.setBackground(Color.WHITE);

    logInPanel.add(inputPanel, BorderLayout.CENTER);
    inputPanel.setLayout(null);

    /**
     * Username input
     */

    // Panel
    JPanel usernamePanel = new JPanel();
    usernamePanel.setBorder(new LineBorder(lightGreyColour));
    usernamePanel.setBackground(Color.WHITE);
    usernamePanel.setBounds(116, 59, 262, 40);
    inputPanel.add(usernamePanel);
    usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    // Icon
    JLabel usernameIcon = new JLabel("");
    usernameIcon.setForeground(Color.WHITE);
    usernameIcon.setIcon(new ImageIcon(
        LoginGUI.class.getResource("/img/accountCircleIcon.png")));
    usernamePanel.add(usernameIcon);

    // Field
    JTextField usernameInput = new JTextField();
    usernameInput.setToolTipText("Required field");
    usernamePanel.add(usernameInput);
    usernameInput.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    usernameInput.setText("Username");
    usernameInput.setMargin(new Insets(10, 10, 10, 80));
    usernameInput.setBorder(new EmptyBorder(7, 15, 10, 80));
    usernameInput.setForeground(Color.GRAY);
    usernameInput.setColumns(10);

    /**
     * Password input
     */

    // Panel
    JPanel passwordPanel = new JPanel();
    passwordPanel.setBorder(new LineBorder(lightGreyColour));
    passwordPanel.setBackground(Color.WHITE);
    passwordPanel.setBounds(116, 110, 262, 40);
    inputPanel.add(passwordPanel);
    passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    // Password
    JLabel passwordIcon = new JLabel("");
    passwordIcon.setIcon(
        new ImageIcon(LoginGUI.class.getResource("/img/lockIcon.png")));
    passwordPanel.add(passwordIcon);

    // Input
    JPasswordField passwordInput = new JPasswordField();
    passwordInput.setToolTipText("Required field\r\n");
    passwordPanel.add(passwordInput);
    passwordInput.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    passwordInput.setText("Password");
    passwordInput.setMargin(new Insets(10, 10, 10, 80));
    passwordInput.setForeground(Color.GRAY);
    passwordInput.setBorder(new EmptyBorder(7, 15, 10, 80));
    passwordInput.setColumns(10);

    /**
     * Error components
     */

    // Username error
    JLabel usernameErrorIcon = new JLabel("");
    usernameErrorIcon.setVisible(false);
    usernameErrorIcon.setHorizontalAlignment(SwingConstants.CENTER);
    usernameErrorIcon.setIcon(
        new ImageIcon(LoginGUI.class.getResource("/img/errorIcon.png")));
    usernameErrorIcon.setBounds(378, 59, 44, 40);
    inputPanel.add(usernameErrorIcon);

    // Password error
    JLabel passwordErrorIcon = new JLabel("");
    passwordErrorIcon.setVisible(false);
    passwordErrorIcon.setHorizontalAlignment(SwingConstants.CENTER);
    passwordErrorIcon.setIcon(
        new ImageIcon(LoginGUI.class.getResource("/img/errorIcon.png")));
    passwordErrorIcon.setBounds(377, 110, 45, 40);
    inputPanel.add(passwordErrorIcon);

    // Error message
    JLabel errorLbl = new JLabel("Error message");
    errorLbl.setVisible(false);
    errorLbl.setForeground(new Color(220, 20, 60));
    errorLbl.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
    errorLbl.setBounds(116, 161, 262, 23);
    inputPanel.add(errorLbl);

    /**
     * Buttons
     */

    // Panel
    JPanel btnsPanel = new JPanel();
    btnsPanel.setPreferredSize(new Dimension(10, 50));
    logInPanel.add(btnsPanel, BorderLayout.SOUTH);
    btnsPanel.setLayout(new GridLayout(0, 2, 0, 0));

    // Register button
    JButton registerBtn = new JButton("Register");
    btnsPanel.add(registerBtn);
    registerBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frame.dispose();
        RegisterGUI regGUI = new RegisterGUI();
        regGUI.returnFrame().setVisible(true);
      }
    });

    registerBtn.setFocusPainted(false);
    registerBtn.setBorderPainted(false);
    registerBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
    registerBtn.setForeground(Color.WHITE);
    registerBtn.setBackground(darkGreenColour);
    registerBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));

    // Login button
    JButton signInBtn = new JButton("Sign in");
    btnsPanel.add(signInBtn);
    signInBtn.setFocusPainted(false);
    signInBtn.setBorderPainted(false);
    signInBtn.setForeground(Color.WHITE);

    signInBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        boolean usernameValidated = false;
        boolean passwordValidated = false;

        // If the username field is empty do not pass username validation
        if (usernameInput.getText().equals("")) {
          usernameValidated = false;
        } else {
          usernameValidated = true;
        }

        // If the password field is empty do not pass username validation
        if (passwordInput.getPassword().length == 0) {
          passwordValidated = false;
        } else {
          passwordValidated = true;
        }

        if (!usernameValidated && !passwordValidated) { // If both fields not
                                                        // valid
          errorLbl.setText("Please enter username and password");
          errorLbl.setVisible(true);
          usernamePanel.setBorder(new LineBorder(redColour));
          passwordPanel.setBorder(new LineBorder(redColour));
          usernameErrorIcon.setVisible(true);
          passwordErrorIcon.setVisible(true);
        } else if (!usernameValidated && passwordValidated) { // If username not
                                                              // valid
          errorLbl.setText("Please enter username");
          errorLbl.setVisible(true);
          usernamePanel.setBorder(new LineBorder(redColour));
          passwordPanel.setBorder(new LineBorder(lightGreyColour));
          usernameErrorIcon.setVisible(true);
          passwordErrorIcon.setVisible(false);
        } else if (usernameValidated && !passwordValidated) { // If password not
                                                              // valid
          errorLbl.setText("Please enter password");
          errorLbl.setVisible(true);
          usernamePanel.setBorder(new LineBorder(lightGreyColour));
          passwordPanel.setBorder(new LineBorder(redColour));
          usernameErrorIcon.setVisible(false);
          passwordErrorIcon.setVisible(true);
        } else { // If both valid
          frame.dispose();
          FinalGUI finalGUI = new FinalGUI();
          finalGUI.returnFrame().setVisible(true);
        }
      }
    });

    signInBtn.setBackground(greenColour);
    signInBtn.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
  }
}
