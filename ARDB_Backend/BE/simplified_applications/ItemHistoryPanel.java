package simplified_applications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.Timer;

import utilities.FileEmailer;
import javax.swing.JLabel;

public class ItemHistoryPanel extends JPanel {

	private FileEmailer historyEmailer;
	private SimpleDateFormat sdf;
	private ItemHistoryListener ihl;
	private Timer timer;
	private String itemHistoryPath;
	private File currentItemHistory;
	private long currentItemHistoryTimeStamp;
	private JLabel lblTime;
	
	
	
	public ItemHistoryPanel() {
		
		JLabel lblItemHistoryLast = new JLabel("Item History Last Run: ");
		add(lblItemHistoryLast);
		
		sdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		ihl = new ItemHistoryListener();
		timer = new Timer(11787, ihl);

		itemHistoryPath = "Z:\\AAA-ITEM";
		currentItemHistory = new File(itemHistoryPath);
		currentItemHistoryTimeStamp = currentItemHistory.lastModified();
		
		lblTime = new JLabel(sdf.format(new Date()));
		add(lblTime);
		historyEmailer = new FileEmailer();
		timer.start();
		
	}
	
	private class ItemHistoryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File tempFile = new File(itemHistoryPath);
			long tempTimeStamp = tempFile.lastModified();
			if(tempTimeStamp != currentItemHistoryTimeStamp){
				historyEmailer.sendEmailWithAttachment("dwicker@mariettadrapery.com", "Item History Report", "Auto-generated Email", itemHistoryPath, "Item History.csv");
				currentItemHistory = tempFile;
				currentItemHistoryTimeStamp = tempFile.lastModified();
				lblTime.setText(sdf.format(new Date()));
			}
			
		}
		
	}

}
