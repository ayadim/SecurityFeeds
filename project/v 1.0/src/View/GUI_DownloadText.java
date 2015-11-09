/*
 * Author : ayadi Mohammed
 * email : ayadi.mohamed@outlook.com
 * Software : Security Feeds
 * version : 1.0
 */
package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Control.Control;

import javax.swing.JLabel;

public class GUI_DownloadText {

	private Control control;
	private JFrame frmExploitContent;
	private JTextArea textAreaTitle;
	private String link;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public GUI_DownloadText(Control myControl , String title , String link) {
		this.link = link;
		control = myControl;
		textAreaTitle = new JTextArea(title);
		//System.out.println(link);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmExploitContent = new JFrame();
		frmExploitContent.setTitle("Exploit");
		frmExploitContent.getContentPane().setBackground(Color.WHITE);
		frmExploitContent.setBounds(100, 100, 933, 615);
		frmExploitContent.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Main.class.getResource("/img/Security_Approved.png")));
		frmExploitContent.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 897, 439);
		frmExploitContent.getContentPane().add(scrollPane);
		
		JTextArea textAreaTxtDownload = new JTextArea();
		textAreaTxtDownload.setBorder(BorderFactory.createLineBorder(Color.decode("#c2c2c2")));
		textAreaTxtDownload.setFont(new Font("Courier New", Font.PLAIN, 12));
		textAreaTxtDownload.setForeground(Color.decode("#3b5998"));
		textAreaTxtDownload.setEditable(false);
		textAreaTxtDownload.setLineWrap(true);
		scrollPane.setViewportView(textAreaTxtDownload);
		
		
		textAreaTitle.setBounds(10, 94, 897, 22);
		textAreaTitle.setFont(new Font("Courier New", Font.BOLD, 14));
		textAreaTitle.setForeground(Color.decode("#3b5998"));
		textAreaTitle.setEditable(false);
		textAreaTitle.setLineWrap(true);
		textAreaTitle.setWrapStyleWord(true);
		textAreaTitle.setBorder(BorderFactory.createLineBorder(Color.decode("#c2c2c2")));
		frmExploitContent.getContentPane().add(textAreaTitle);
		
		JLabel lblFrameTitle = new JLabel("Exploit Content");
		lblFrameTitle.setForeground(new Color(51, 102, 153));
		lblFrameTitle.setFont(new Font("Arial", Font.BOLD, 17));
		lblFrameTitle.setBounds(395, 46, 127, 25);
		frmExploitContent.getContentPane().add(lblFrameTitle);
		
		String myContent = "";
		myContent = control.getContentFromTxt(link);
		textAreaTxtDownload.setText(myContent);
		frmExploitContent.setLocationRelativeTo(null);
		frmExploitContent.setResizable(false);
		frmExploitContent.setVisible(true);
	}
}
