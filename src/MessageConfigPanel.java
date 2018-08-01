/*
 * MessageConfigPanel.java
 *
 * Created on June 9, 2008, 1:55 AM
 */
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author  krolaw
 */
public class MessageConfigPanel extends javax.swing.JPanel {

	Map<String, List<String>> suggestions;
	/** Creates new form MessageConfigPanel */
	public MessageConfigPanel() {
		
		initComponents();
                
                messageOrderBox.setSelectedIndex(FST.messageOrder);
                
		StringBuilder sb = new StringBuilder();
		for (String msg : FST.messages) {
			sb.append(msg).append("\n");
		}
		messagesText.setText(sb.toString());
		final TransferHandler messagesTransferHandler = messagesText.getTransferHandler();

		messagesText.setTransferHandler(new TransferHandler(null) {

			@Override
			public boolean canImport(JComponent jc, DataFlavor[] df) {
				return messagesTransferHandler.canImport(jc, df);
			}

			@Override
			public boolean importData(JComponent com, Transferable t) {
				try {
					for (DataFlavor dl : t.getTransferDataFlavors()) {
						if (dl.isMimeTypeEqual("text/plain") && t.getTransferData(dl) instanceof String) {
							messagesText.append(String.format("%n%s", (String) t.getTransferData(dl)));
							messagesTextFocusLost(null);
							return true;
						}
					}
					return messagesTransferHandler.importData(com,t);
				} catch (UnsupportedFlavorException ex) {
					JOptionPane.showMessageDialog(null, "Flavor");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "IO");
				}
				return false;
			}

		});

		suggestions = new HashMap<String, List<String>>();
		try {
			Document suggestionXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getClass().getResource("Suggestions.xml").openStream());
			NodeList suggestionGroups = suggestionXML.getElementsByTagName("group");
			for (int x = 0; x < suggestionGroups.getLength(); x++) {
				Element groupXML = (Element) suggestionGroups.item(x);
				List<String> opt = new ArrayList<String>();
				suggestions.put(groupXML.getAttribute("title"), opt);
				NodeList optItems = groupXML.getElementsByTagName("opt");
				for (int y = 0; y < optItems.getLength(); y++) {
					opt.add(optItems.item(y).getTextContent());
				}
				Collections.sort(opt);
			}
			List<String> groups = new ArrayList<String>(suggestions.keySet());
			Collections.sort(groups);
			suggestionsCombo.setModel(new DefaultComboBoxModel(groups.toArray()));
			suggestionsList.setModel(new DefaultComboBoxModel(suggestions.get(groups.get(0)).toArray()));
		} catch (IOException ex) {
		} catch (SAXException ex) {
		} catch (ParserConfigurationException ex) {
		}
	}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        suggestionsList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        suggestionsCombo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesText = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        messageOrderBox = new javax.swing.JComboBox();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        suggestionsList.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        suggestionsList.setDragEnabled(true);
        suggestionsList.setVisibleRowCount(5);
        jScrollPane2.setViewportView(suggestionsList);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setFocusable(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel2.setText("Suggestions:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 20));
        jPanel3.add(jLabel2);

        suggestionsCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestionsComboActionPerformed(evt);
            }
        });
        jPanel3.add(suggestionsCombo);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        messagesText.setColumns(20);
        messagesText.setRows(5);
        messagesText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                messagesTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                messagesTextFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(messagesText);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setFocusable(false);
        jPanel4.setMinimumSize(new java.awt.Dimension(128, 27));
        jPanel4.setPreferredSize(new java.awt.Dimension(132, 27));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel1.setText("Your Messages:");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 20));
        jPanel4.add(jLabel1);

        messageOrderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Display Random", "Display Sequential" }));
        messageOrderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageOrderBoxActionPerformed(evt);
            }
        });
        jPanel4.add(messageOrderBox);

        jPanel1.add(jPanel4, java.awt.BorderLayout.NORTH);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

	
	private void messagesTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_messagesTextFocusLost

		FST.messages.clear();
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader read = new BufferedReader(new StringReader(messagesText.getText()));
			String msg;
			while (null != (msg = read.readLine())) {
				if (msg.length() > 0) {
					FST.messages.add(msg);
					sb.append(String.format("%s%n", msg));
				}
			}
		} catch (IOException ex) {}//GEN-LAST:event_messagesTextFocusLost
finally {
			messagesText.setText(sb.toString());
			//FST.save();
                        FST.settingsChanged();
			//FST.settingsChanged = true;
		}
	}     

	private void suggestionsComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suggestionsComboActionPerformed
		suggestionsList.setModel(new DefaultComboBoxModel(suggestions.get(suggestionsCombo.getSelectedItem()).toArray()));
	}//GEN-LAST:event_suggestionsComboActionPerformed

	private void messageOrderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageOrderBoxActionPerformed
		FST.messageOrder = messageOrderBox.getSelectedIndex();
		FST.settingsChanged();
}//GEN-LAST:event_messageOrderBoxActionPerformed

    private void messagesTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_messagesTextFocusGained
        FST.settingsChanged();
    }//GEN-LAST:event_messagesTextFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox messageOrderBox;
    private javax.swing.JTextArea messagesText;
    private javax.swing.JComboBox suggestionsCombo;
    private javax.swing.JList suggestionsList;
    // End of variables declaration//GEN-END:variables
}
