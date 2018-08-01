import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author  krolaw
 */
public class Config extends javax.swing.JFrame implements Runnable {
	
	/** Creates new form Config */
	public Config()  {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
		initComponents();
		
		try { // Load instructions
			aboutPane.setText(new Scanner(this.getClass().getResource("About.html").openStream()).useDelimiter("\\Z").next().replaceAll("\\#VERSION\\#", FST.VERSION));			
		} catch (IOException ex) {}
		if(FST.configWindow==2) setExtendedState(ICONIFIED);
		setVisible(true);
		FST.canSave = true;
		if(System.currentTimeMillis()/1000L > FST.nextUpdate) {
			new Thread(this).start();
		}
	}
	
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jTabbedPane1 = new javax.swing.JTabbedPane();
    jScrollPane2 = new javax.swing.JScrollPane();
    aboutPane = new javax.swing.JEditorPane();
    optionsConfigPanel1 = new OptionsConfigPanel();
    messageConfigPanel1 = new MessageConfigPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Free Subliminal Text");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    jTabbedPane1.setMinimumSize(new java.awt.Dimension(400, 180));
    jTabbedPane1.setOpaque(true);
    jTabbedPane1.setPreferredSize(new java.awt.Dimension(400, 346));

    jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 300));
    jScrollPane2.setVerifyInputWhenFocusTarget(false);

    aboutPane.setContentType("text/html");
    aboutPane.setEditable(false);
    aboutPane.setAutoscrolls(false);
    aboutPane.setMaximumSize(new java.awt.Dimension(2147483647, 300));
    aboutPane.setPreferredSize(new java.awt.Dimension(200, 200));
    jScrollPane2.setViewportView(aboutPane);

    jTabbedPane1.addTab("About", jScrollPane2);

    optionsConfigPanel1.setMinimumSize(new java.awt.Dimension(340, 300));
    optionsConfigPanel1.setPreferredSize(new java.awt.Dimension(300, 300));
    jTabbedPane1.addTab("Options", optionsConfigPanel1);

    messageConfigPanel1.setPreferredSize(new java.awt.Dimension(244, 200));
    jTabbedPane1.addTab("Messages", messageConfigPanel1);

    getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

    pack();
  }// </editor-fold>//GEN-END:initComponents

	final private static String [] WINDOW_CLOSE_OPTIONS = { "Quit", "Minimize", "Cancel" };
	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		switch(JOptionPane.showOptionDialog(this, "Closing window will quit program. Sure?",
			"FST - Quit?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, WINDOW_CLOSE_OPTIONS, WINDOW_CLOSE_OPTIONS[1])) {
		case 0: FST.save(); System.exit(0);
		case 1: FST.minimise();
		}
	}//GEN-LAST:event_formWindowClosing
	
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JEditorPane aboutPane;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTabbedPane jTabbedPane1;
  private MessageConfigPanel messageConfigPanel1;
  private OptionsConfigPanel optionsConfigPanel1;
  // End of variables declaration//GEN-END:variables
	
	@Override
	public void setTitle(String text) {
		super.setTitle(text+" "+FST.VERSION);
	}

        @Override
	public void run() {
		try {
			URL url = new URL("http://richard.warburton.it/fst/version.php");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String currentVersion = br.readLine();
			if(FST.VERSION.compareTo(currentVersion) < 0) {
				JOptionPane.showMessageDialog(null, "A new Free Subliminal Text update has been released.\n" +
					"Please visit http://richard.warburton.it/fst", "FST - New Update Available",
					JOptionPane.INFORMATION_MESSAGE);
			}
			FST.nextUpdate = System.currentTimeMillis()/1000L+30L*86400L;
			FST.save();
		} catch (IOException ex) {
			System.out.println("Error: "+ex);
		}
	}
}
