package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Ladebalken extends Thread {

	private JProgressBar fortschrittsBalken;
	private JPanel panel;
	private JFrame frame;
	private int progress = 0;

	public Ladebalken() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fortschrittsBalken = new JProgressBar(SwingConstants.HORIZONTAL);
		frame = new JFrame("Daten werden geladen");
		panel = new JPanel();
		Dimension prefSize = fortschrittsBalken.getPreferredSize();
		prefSize.width = 500;
		prefSize.height = 50;
				fortschrittsBalken.setPreferredSize(prefSize);
				
		fortschrittsBalken.setVisible(true);
		fortschrittsBalken.setValue(progress);
		fortschrittsBalken.setStringPainted(true);
		fortschrittsBalken.setBounds(0,0,400,50);
		panel.add(fortschrittsBalken);
		panel.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.setLocation((int) (screenSize.getWidth()/2.0)-(frame.getWidth()/2),(int) (screenSize.getHeight()/2.0)-frame.getHeight());
		frame.setBounds(frame.getX(),frame.getY(),600,100);
		frame.setVisible(true);


	}

	public void kill() {
		this.frame.dispose();
		super.interrupt();
	}

	
	public void setProgress(int d){
		if(d <= 0 || d > 100) throw new IllegalArgumentException("Ungültiger Wert im Fortschrittsupdate");
		progress+=d;
		if(progress > 100) throw new IllegalArgumentException("Ungültiger Wert im Fortschrittsupdate");
		fortschrittsBalken.setValue(progress);
		this.fortschrittsBalken.setString(String.format("Fortschritt: %d%%",progress));
	}
	
}
