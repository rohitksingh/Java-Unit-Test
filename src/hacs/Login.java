package hacs;

import javax.swing.*;

import hacs.UserInfoItem.USER_TYPE;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Title: HACS Description: Copyright: Copyright (c) 2002 Company: msu
 * 
 * @author Zhang ji Zhu Wei
 * @version 1.0
 * @author mjfindler
 * @version 2.0 Update to Java 8
 * @author rsingh92
 * @version 3.0 refactoring
 */

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	boolean m_bExit = false;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JButton loginButton = new JButton();
	JButton buttonExit = new JButton();
	JTextField userNameText = new JTextField();
	JPasswordField passwordText = new JPasswordField();
	JRadioButton studentRadio = new JRadioButton();
	JRadioButton instructorRadio = new JRadioButton();
	ButtonGroup buttonGroup1 = new ButtonGroup();

	private String userBox = null;
	private USER_TYPE userType = USER_TYPE.Student; // default to Student

	/**
	 * Login constructor
	 */
	public Login() {
		try {
			jbInit();
			setSize(300, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize resource
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		jLabel1.setText("UserName");
		jLabel1.setBounds(new Rectangle(26, 52, 80, 18));
		jLabel2.setText("Password");
		jLabel2.setBounds(new Rectangle(23, 119, 80, 18));
		loginButton.setText("Login");
		loginButton.setBounds(new Rectangle(31, 212, 85, 28));
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginButton_actionPerformed(e);
			}
		});
		buttonExit.setText("Exit");
		buttonExit.setBounds(new Rectangle(180, 211, 97, 28));
		buttonExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonExit_actionPerformed(e);
			}
		});
		userNameText.setBounds(new Rectangle(119, 52, 144, 22));
		passwordText.setBounds(new Rectangle(118, 119, 147, 22));
		studentRadio.setSelected(true);
		studentRadio.setText("Student");
		studentRadio.setBounds(new Rectangle(37, 164, 103, 26));
		instructorRadio.setText("Instructor");
		instructorRadio.setBounds(new Rectangle(177, 162, 103, 26));
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(loginButton, null);
		this.getContentPane().add(buttonExit, null);
		this.getContentPane().add(userNameText, null);
		this.getContentPane().add(passwordText, null);
		this.getContentPane().add(studentRadio, null);
		this.getContentPane().add(instructorRadio, null);
		buttonGroup1.add(studentRadio);
		buttonGroup1.add(instructorRadio);
	}

	/**
	 * Perfroms login
	 * 
	 * @param e
	 */
	void loginButton_actionPerformed(ActionEvent e) {
		BufferedReader file;
		m_bExit = false;
		System.out.println("login clicked");
		try {
			if (studentRadio.isSelected() == true)//// student
			{
				userType = USER_TYPE.Student; /// 0 for student
				file = new BufferedReader(new FileReader("/src/StuInfo.txt"));
			} else// instructor
			{
				System.out.println("login clicked2");
				userType = USER_TYPE.Instructor; // 1 for instructor
				file = new BufferedReader(new FileReader("/src/InsInfor.txt"));
			}
			System.out.println("login clicked 3");
			userBox = userNameText.getText();
				
			String PasswordBox = new String(passwordText.getPassword());
			String LoginName = null;
			String aline = null, UserName = null, Password = null;
			while ((aline = file.readLine()) != null) {
				UserName = getUserName(aline);
				Password = getPassword(aline);
				if (UserName.compareTo(userBox) == 0 && Password.compareTo(PasswordBox) == 0)
					LoginName = UserName;
			}
			if (LoginName != null) {
				this.setVisible(false);
			}
		} catch (Exception ee) {
            System.out.println("Exslkdlskd");
		}

	}

	/**
	 * get the user name from aline UserName:Password
	 * 
	 * @param aline
	 * @return String
	 */
	private String getUserName(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(0, Sep);
	}

	/**
	 * Gets the password from aline UserName:Password
	 * 
	 * @param aline
	 * @return String
	 */
	private String getPassword(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(Sep + 1, aline.length());
	}

	/**
	 * Gets the UserName of the login
	 * 
	 * @return String
	 */
	public String getUserName() {
		return userBox;
	}

	/**
	 * Gets the userType of the login
	 * 
	 * @return USER_TYPE
	 */
	public USER_TYPE getUserType() {
		return userType;
	}

	/**
	 * Returns isExit
	 * 
	 * @return boolean
	 */
	public boolean isExit() {
		return m_bExit;
	}

	/**
	 * Performs exit action
	 * 
	 * @param e
	 */
	void buttonExit_actionPerformed(ActionEvent e) {
		m_bExit = true;
		setVisible(false);
	}
}