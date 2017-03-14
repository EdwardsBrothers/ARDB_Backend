package be_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public abstract class ServicePanelInd extends JPanel {

	protected JLabel lblServiceName, lblStatus, lblLastUpdate;
	protected JButton btnStart, btnStop, btnPing;
	protected Timer timer;

	/**
	 * Create the panel.
	 */
	public ServicePanelInd(String serviceName) {
			
		setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		setPreferredSize(new Dimension(600, 30));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(2);
		flowLayout.setAlignment(FlowLayout.LEFT);

		lblServiceName = new JLabel(serviceName);
		lblServiceName.setPreferredSize(new Dimension(200, 25));
		lblServiceName.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblServiceName);

		lblStatus = new JLabel("Initializing");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setPreferredSize(new Dimension(75, 25));
		add(lblStatus);

		lblLastUpdate = new JLabel("Initializing");
		lblLastUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastUpdate.setPreferredSize(new Dimension(140, 25));
		add(lblLastUpdate);

		btnStart = new JButton("Start");
		btnStart.setPreferredSize(new Dimension(75, 25));
		add(btnStart);

		//btnStop = new JButton("Stop");
		//btnStop.setPreferredSize(new Dimension(75, 25));
		//add(btnStop);
		
		btnPing = new JButton("Ping");
		btnPing.setPreferredSize(new Dimension(75,25));
		add(btnPing);

	}
	
	protected void updateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		lblLastUpdate.setText(sdf.format(new Date()));
	}

}
