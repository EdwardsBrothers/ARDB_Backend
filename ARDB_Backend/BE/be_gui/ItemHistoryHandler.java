package be_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;

import Archive.ServicePanelInd;

public class ItemHistoryHandler extends ServicePanelInd {
	/*
	 * 
	 */

	private String itemHistoryPath;
	private File currentItemHistory;
	private long currentItemHistoryTimeStamp;
	private SimpleDateFormat sdf;
	private ItemHistoryListener ihl;

	public ItemHistoryHandler() {
		super("Item History Report");
		sdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		ihl = new ItemHistoryListener();
		timer = new Timer(11787, ihl);

		itemHistoryPath = "Z:\\AAA-ITEM";
		currentItemHistory = new File(itemHistoryPath);
		currentItemHistoryTimeStamp = currentItemHistory.lastModified();

		timer.start();
		lblStatus.setText("Running");
		lblLastUpdate.setText(sdf.format(new Date()));
	}

	private class ItemHistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File tempItemHistory = new File(itemHistoryPath);
			long tempTimeStamp = tempItemHistory.lastModified();
			if (tempTimeStamp != currentItemHistoryTimeStamp) {
				currentItemHistory = new File(itemHistoryPath);
				currentItemHistoryTimeStamp = currentItemHistory.lastModified();
				tempItemHistory.renameTo(
						new File("R:\\Installation\\Item History Reports\\" + sdf.format(new Date()) + ".csv"));

			}

		}

	}
}
