/*
 * Author : ayadi Mohammed
 * email : ayadi.mohamed@outlook.com
 * Software : Security Feeds
 * version : 1.0
 */
package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.net.MalformedURLException;


import model.Feed;
import model.FeedMessage;
import Control.*;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class Gui_Main {

	private JFrame frmSecurityFeeds;
	private JTable table;
	private java.util.List<FeedMessage> myListOfInfos;
	private Control c_main;
	private FeedMessage myFeedSelectedInTable;
	private JTextArea textAreaDescription;
	private JTextArea textAreaTitle;
	private JTextArea textAreaLink;
	private JTextField txtSearching;
	private JTextArea textAreaDateUpdate;
	private JButton btnDownloadTextORSeeLink;
	private Feed myFeed;
	/**
	 * Create the application.
	 */
	public Gui_Main(Control c) {
		c_main = c;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		myFeedSelectedInTable = new FeedMessage();
		myListOfInfos = new ArrayList<FeedMessage>();
		myFeed = new Feed();
		frmSecurityFeeds = new JFrame();
		frmSecurityFeeds.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Main.class.getResource("/img/Security_Approved.png")));
		frmSecurityFeeds.getContentPane().setBackground(Color.WHITE);
		frmSecurityFeeds.setBounds(100, 100, 911, 687);
		frmSecurityFeeds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSecurityFeeds.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setBackground(Color.WHITE);
		mnFile.add(mntmExit);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItemCheckForUpdate = new JMenuItem("Check For Update");
		menuItemCheckForUpdate.setBackground(Color.WHITE);
		mnNewMenu.add(menuItemCheckForUpdate);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Website");
		mntmNewMenuItem.setBackground(Color.WHITE);

		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmAbout = new JMenuItem("About");

		mntmAbout.setBackground(Color.WHITE);
		mnNewMenu.add(mntmAbout);
		frmSecurityFeeds.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 905, 638);
		frmSecurityFeeds.getContentPane().add(tabbedPane);
		
		JPanel panelExploit = new JPanel();
		panelExploit.setBorder(new CompoundBorder());
		panelExploit.setToolTipText("");
		panelExploit.setBackground(Color.WHITE);
		tabbedPane.addTab("Security Feeds", null, panelExploit, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		panelExploit.setLayout(null);
		
		JScrollPane scrollPaneForTable = new JScrollPane();
		scrollPaneForTable.setBounds(60, 323, 786, 260);
		//scrollPaneForTable.setBackground(Color.decode("#BAD9EC"));
		panelExploit.add(scrollPaneForTable);
		
		//---------------------------------- traitement de table  ------------------------------------
		
		/**
		 * create a table that containt data 
		 */
//		String[] tableColumn = {"Title","Description","Lien","Date"};
//		String[][] data= {};
		table = new JTable(){
            
			public boolean isCellEditable(int row , int column){                
				// return false pour ne  pas modifier aucun cellule
				return false;
			}
        
			public Component prepareRenderer(TableCellRenderer r,int data,int columns){
	            Component c = super.prepareRenderer(r, data, columns);       
	            
	            if(data % 2 ==0)
	                c.setBackground(Color.decode("#9ad0ed")); //#EEE0E5   || #E2EDF7 || #E9F2F9 || #b3ecec
	            else
	                c.setBackground(Color.decode("#FFFFFF"));
	            
	            if (isCellSelected(data, columns))
	                c.setBackground(Color.decode("#edb699"));	
	            
	            return c;
			}
        };
		        
        /**
         *  change jtable background 
         */
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.white);
        
        table.getTableHeader().setBackground(Color.decode("#FFFFFF"));//#BAD9EC
        table.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 13));
        table.getTableHeader().setForeground(Color.black);
        table.setFont(new Font("Courier New", Font.PLAIN, 11));
        table.setRowHeight(30);
		/**
		 *  remplire les données  à l'aide d'un tableModel
		 */

		scrollPaneForTable.setViewportView(table);				


		
		JLabel lblDescription = new JLabel("Description ");
		lblDescription.setBounds(60, 211, 110, 14);
		lblDescription.setForeground(Color.decode("#1D4469"));
		lblDescription.setFont(new Font("Arial", Font.BOLD, 14));
		panelExploit.add(lblDescription);
		
		JLabel lblTitle = new JLabel("Title ");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle.setBounds(60, 115, 65, 14);
		lblTitle.setForeground(Color.decode("#1D4469"));
		panelExploit.add(lblTitle);
		
		textAreaTitle = new JTextArea();	
		textAreaTitle.setBounds(60, 130, 631, 15);
		initialiseTextAreas(textAreaTitle);
		panelExploit.add(textAreaTitle);
		
		JLabel lblLink = new JLabel("Link ");
		lblLink.setFont(new Font("Arial", Font.BOLD, 14));
		lblLink.setBounds(60, 156, 59, 14);
		lblLink.setForeground(Color.decode("#1D4469"));
		panelExploit.add(lblLink);
		
		JScrollPane scrollPaneAreaTextForDescription = new JScrollPane();
		scrollPaneAreaTextForDescription.setBounds(60, 228, 631, 84);
		scrollPaneAreaTextForDescription.setBorder(BorderFactory.createLineBorder(Color.decode("#c2c2c2")));
		panelExploit.add(scrollPaneAreaTextForDescription);
		
		textAreaDescription = new JTextArea();
		initialiseTextAreas(textAreaDescription);
		scrollPaneAreaTextForDescription.setViewportView(textAreaDescription);

		
		JLabel lblTabTitle = new JLabel("Security Feeds");
		lblTabTitle.setFont(new Font("Arial", Font.BOLD, 19));
		lblTabTitle.setBounds(378, 22, 144, 28);
		lblTabTitle.setForeground(new Color(51, 102, 153));
		panelExploit.add(lblTabTitle);
		
		textAreaLink = new JTextArea();
		textAreaLink.setBounds(60, 172, 784, 28);			
		initialiseTextAreas(textAreaLink);
		panelExploit.add(textAreaLink);	
		
		txtSearching = new JTextField(" ");
		txtSearching.setBounds(191, 74, 302, 20);
		txtSearching.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSearching.setBorder(BorderFactory.createLineBorder(Color.decode("#c2c2c2")));
		panelExploit.add(txtSearching);
		txtSearching.setColumns(10);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.decode("#50AADA"));	
		btnSearch.setBounds(513, 74, 84, 20);
		panelExploit.add(btnSearch);
		
		JButton btnNews = new JButton("News");
		btnNews.setForeground(Color.WHITE);
		btnNews.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNews.setBackground(Color.decode("#50AADA"));
		btnNews.setBounds(607, 74, 84, 20);
		panelExploit.add(btnNews);
		
		btnDownloadTextORSeeLink = new JButton(" Exploit ");
		btnDownloadTextORSeeLink.setBackground(Color.WHITE);
		btnDownloadTextORSeeLink.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDownloadTextORSeeLink.setForeground(Color.WHITE);
		btnDownloadTextORSeeLink.setBounds(702, 228, 144, 31);
		btnDownloadTextORSeeLink.setBackground(Color.decode("#50AADA"));
		panelExploit.add(btnDownloadTextORSeeLink);
		
		textAreaDateUpdate = new JTextArea();
		textAreaDateUpdate.setBounds(702, 130, 143, 15);
		initialiseTextAreas(textAreaDateUpdate);
		panelExploit.add(textAreaDateUpdate);
		
		JPanel panelAbout = new JPanel();
		panelAbout.setToolTipText("");
		panelAbout.setBackground(Color.WHITE);
		tabbedPane.addTab("About", null, panelAbout, null);
		panelAbout.setLayout(null);
		
		JLabel lblAboutAuthor = new JLabel("About Author");
		lblAboutAuthor.setFont(new Font("Arial", Font.BOLD, 17));
		lblAboutAuthor.setForeground(new Color(51, 102, 153));
		lblAboutAuthor.setBounds(395, 244, 109, 31);
		panelAbout.add(lblAboutAuthor);
		
		JLabel lblAyadiMohammed = new JLabel("AYADI Mohammed ");
		lblAyadiMohammed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAyadiMohammed.setBounds(384, 288, 132, 14);
		panelAbout.add(lblAyadiMohammed);
		
		JLabel lblNewLabel = new JLabel("ayadi.mohamed@outlook.com");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(348, 312, 204, 17);
		panelAbout.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("https://github.com/ayadim");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(360, 343, 180, 17);
		panelAbout.add(lblNewLabel_1);
		
		JLabel lblAboutApplication = new JLabel("About Application");
		lblAboutApplication.setForeground(new Color(51, 102, 153));
		lblAboutApplication.setFont(new Font("Arial", Font.BOLD, 19));
		lblAboutApplication.setBounds(365, 22, 170, 31);
		panelAbout.add(lblAboutApplication);
		
		JLabel lblSecurityFeeds = new JLabel("Security Feeds");
		lblSecurityFeeds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSecurityFeeds.setBounds(400, 64, 100, 22);
		panelAbout.add(lblSecurityFeeds);
		
		JLabel lblVersion = new JLabel("Version ");
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVersion.setBounds(415, 97, 56, 14);
		panelAbout.add(lblVersion);
		
		JButton btnCheckForUpdate = new JButton("Check For Updates");
		btnCheckForUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCheckForUpdate.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCheckForUpdate.setBounds(373, 166, 154, 23);
		btnCheckForUpdate.setBackground(Color.decode("#50AADA"));
		btnCheckForUpdate.setForeground(Color.white);
		panelAbout.add(btnCheckForUpdate);
		
		JLabel versionValue = new JLabel("1.0");
		versionValue.setForeground(new Color(51, 102, 204));
		versionValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		versionValue.setBounds(470, 97, 31, 14);
		panelAbout.add(versionValue);
		
		JLabel lblNewLabel_2 = new JLabel("Security feeds is an security software that help you to findout security issues.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(192, 122, 516, 22);
		panelAbout.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("All rights reserved by © ayadi mohammed");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(314, 560, 272, 22);
		panelAbout.add(lblNewLabel_3);
		
		JLabel lblGreeting = new JLabel("Greeting");
		lblGreeting.setForeground(new Color(51, 102, 153));
		lblGreeting.setFont(new Font("Arial", Font.BOLD, 17));
		lblGreeting.setBounds(413, 416, 73, 31);
		panelAbout.add(lblGreeting);
		
		JLabel lblNewLabel_4 = new JLabel("To Allah and my friends in Hero Family");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(328, 458, 243, 22);
		panelAbout.add(lblNewLabel_4);
		
		//######################################################################################################
		//###################################################				####################################
		//###################################################	Listenners	####################################
		//###################################################	 			 ###################################
		//######################################################################################################
			
		//---------- menu 
		menuItemCheckForUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkForUpdate();
			}
		});
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.openLinkInBrowser("https://github.com/ayadim/SecurityFeeds");
			}
		});
		
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//---------- listeners Table 
		table.addMouseListener(new MouseAdapter() {			
			// row selected 
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {            
                	fillTheInformationInTextAreaFromRowSelectedInTable();         
                }
            }            
		});
		
		table.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 38 || e.getKeyCode()==40){
					fillTheInformationInTextAreaFromRowSelectedInTable();   					
				}
			}
		});
		
		txtSearching.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10){//if the user click into Enter key
					doTheSearchAction(true);//yes if it's about searching for exploits/files
				}
			}
		});
		
		//----------- Button 
		btnSearch.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				doTheSearchAction(true);
			}
		});
		
		btnNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doTheSearchAction(false);//
			}
		});
		
		btnDownloadTextORSeeLink.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textAreaLink.getText().trim().equals(""))
					return;
				if(isThisLinkHaveTextExtence(textAreaLink.getText().trim())){// text
					GUI_DownloadText v_showMetheContent;
					v_showMetheContent= new GUI_DownloadText(c_main,textAreaTitle.getText().trim(), textAreaLink.getText().trim());
				}else{
					Control.openLinkInBrowser(textAreaLink.getText().trim());
				}					
				//if the textarea of link is filled				
			}
		});
		
		btnCheckForUpdate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkForUpdate();
			}
		});
		
		frmSecurityFeeds.setTitle("Security Feeds");
		frmSecurityFeeds.setResizable(false);
//		frmSecurityFeeds.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("PATH/TO/YourImage.png")));
		frmSecurityFeeds.setLocationRelativeTo(null);
		frmSecurityFeeds.setVisible(true);
		
		if(!c_main.getLastestVersion().equals(Control.version))
			JOptionPane.showMessageDialog(frmSecurityFeeds,c_main.getLastestVersion()+" version is available ! ","Information",JOptionPane.INFORMATION_MESSAGE);
	}

	private void initialiseTextAreas(JTextArea textArea) {
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 11));
		textArea.setForeground(Color.decode("#3b5998"));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#c2c2c2")));
	}
	
	/*
	 * @param isThatAboutExploit  true if  exploit /files , false if news
	 */
	private void doTheSearchAction(boolean isThatAboutExploit) {		
			clearTextArea();
			myFeed = (isThatAboutExploit)?c_main.getDataForExploit(txtSearching.getText().trim())
					:c_main.getDataForNews(txtSearching.getText().trim());
			if(myFeed.getMessages().size()==0)
				return ;
			reloadTable(myFeed);	
	}
	
	private void reloadTable(Feed myFeed) {
		String[] columns = {"Title","Description","Lien","Date"};
		if(myFeed.getMessages().size()==1){
			if(myFeed.getMessages().get(0).getTitle().equals(" ")){// we get 0 result
				DefaultTableModel tableModele = new DefaultTableModel();
				table.setModel(tableModele);				
				return;
			}
		}
		myListOfInfos = myFeed.getMessages();		
		if(c_main.getDataString(myFeed.getMessages())==null)
			return;			
		DefaultTableModel tableModele = new DefaultTableModel(c_main.getDataString(myFeed.getMessages()),columns);
		table.setModel(tableModele);
		setTableColumns();
	}
	
	private void fillTheInformationInTextAreaFromRowSelectedInTable() {
		if(table.getRowCount()==0)
			return;
			myFeedSelectedInTable.setTitle(table.getValueAt(table.getSelectedRow(),0).toString());
		   myFeedSelectedInTable.setDescription(table.getValueAt(table.getSelectedRow(), 1).toString());
		   myFeedSelectedInTable.setLink(table.getValueAt(table.getSelectedRow(), 2).toString());
		   myFeedSelectedInTable.setDatePub(table.getValueAt(table.getSelectedRow(), 3).toString());
		   textAreaDescription.setText(myFeedSelectedInTable.getDescription());
		   textAreaTitle.setText(myFeedSelectedInTable.getTitle());
		   textAreaLink.setText(myFeedSelectedInTable.getLink());
		   textAreaDateUpdate.setText(myFeedSelectedInTable.getDatePub());
		   if(isThisLinkHaveTextExtence(myFeedSelectedInTable.getLink())){
			   btnDownloadTextORSeeLink.setText("Exploit");
		   }else{
			   btnDownloadTextORSeeLink.setText("See More...");
		   }
	} 
	
	private void clearTextArea(){
		textAreaDateUpdate.setText("");
		textAreaDescription.setText("");
		textAreaLink.setText("");
		textAreaTitle.setText("");
	} 
	
	private void setTableColumns() {
		TableColumnModel tcm = table.getColumnModel();		
		//title
		tcm.getColumn(0).setMinWidth(550);
		tcm.getColumn(0).setMaxWidth(550);

		for(int i=1;i<3;i++)
		{
			//description , link
			tcm.getColumn(i).setMinWidth(0);
			tcm.getColumn(i).setMaxWidth(0);		
		}		
		//DateUpdate
		tcm.getColumn(3).setMinWidth(250);
		tcm.getColumn(3).setMaxWidth(250);

	}
	
	private boolean isThisLinkHaveTextExtence(String url){		
		if(textAreaLink.getText().trim().endsWith(".txt"))
		{
			return true;
		}else{
			return false;
		}
	}
	
	private void checkForUpdate() {
		String msg ="";
		if(c_main.getLastestVersion().equals(Control.version)){
			msg ="Current version is up-to-date ! ";
		}else{		
			msg =c_main.getLastestVersion()+" version is available ! ";					
		}
		JOptionPane.showMessageDialog(frmSecurityFeeds,msg,"Information",JOptionPane.INFORMATION_MESSAGE);
	}
}
