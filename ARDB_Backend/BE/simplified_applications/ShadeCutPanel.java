package simplified_applications;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import be_gui.Cut;
import utilities.FileEmailer;
import javax.swing.JButton;

public class ShadeCutPanel extends JPanel {

	private int shadeCutsPrinted;
	private String dateText;
	private Timer timer;
	private SimpleDateFormat sdf;
	private ShadeCutPanelListener cpl;
	
	private String customCutFilePath;
	private File currentCustomCutFile, tempCustomCutFile;
	private long currentCustomCutTimeStamp, tempCustomCutTimeStamp;
	private ArrayList<Cut> currentCustomCuts, currentShadeCuts;
	private JLabel lblCustomCutTime, lblShadeCuts;
	
	private FileEmailer cutSender;
	private JButton btnPinger;
	
	/**
	 * Create the panel.
	 */
	public ShadeCutPanel() {
		cpl = new ShadeCutPanelListener();
		timer = new Timer(3769, cpl);
		timer.start();
		
		customCutFilePath = "jotfile";     //"Z:\\AAA-P2CSTM";
		currentCustomCutFile = new File(customCutFilePath);
		currentCustomCutTimeStamp = currentCustomCutFile.lastModified();
		
		tempCustomCutFile = new File(customCutFilePath);
		tempCustomCutTimeStamp = tempCustomCutFile.lastModified();
		
		cutSender = new FileEmailer();
		
		setLayout(new BorderLayout(0,0));
		sdf = new SimpleDateFormat("hh:mm");
		
		dateText = sdf.format(new Date());
		
		JPanel TextPanel = new JPanel();
		add(TextPanel, BorderLayout.NORTH);
		TextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCustomCutsLastPrinted = new JLabel("Custom Cuts Last Printed:");
		lblCustomCutsLastPrinted.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		TextPanel.add(lblCustomCutsLastPrinted);
		
		lblCustomCutTime = new JLabel(dateText);
		lblCustomCutTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextPanel.add(lblCustomCutTime);
		
		JLabel lblShadeCutsPrinted = new JLabel("Shade Cuts Printed:");
		lblShadeCutsPrinted.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		TextPanel.add(lblShadeCutsPrinted);
		
		lblShadeCuts = new JLabel("" + shadeCutsPrinted );
		lblShadeCuts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextPanel.add(lblShadeCuts);
		
		btnPinger = new JButton("Pinger");
		btnPinger.addActionListener(cpl);
		add(btnPinger, BorderLayout.SOUTH);

	}
	
	
	
	private void parseCuts(File cutFile){
		ArrayList<Cut> all, shades;
		all = new ArrayList<Cut>();
		shades = new ArrayList<Cut>();
				
		try {
			Scanner scan = new Scanner(cutFile);
			scan.useDelimiter(new String(new char[] { 12 }));
			while (scan.hasNext()){
				Cut c = new Cut(scan.next());
				if(c.isCut()){
					all.add(c);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		private class ShadeCutPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnPinger)){
				parseCuts(currentCustomCutFile);
			}
			
		}
		
	}
		
	}
