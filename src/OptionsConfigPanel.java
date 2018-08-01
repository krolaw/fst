import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.text.JTextComponent;

/**
 *
 * @author  krolaw
 */
public class OptionsConfigPanel extends javax.swing.JPanel {
	//private JPanel samplePanel;
	private final static String SAMPLE_TEXT = "FST Sample Text";
	public static String sampleText;
	
	protected static KeyAdapter digitsOnly = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			if(!Character.isDigit(e.getKeyChar())) {
				e.consume();
			}
		}
	};
	
	public static int integerFieldHandler(int minimum, JTextComponent com, int current) {
		try {
			int temp = Integer.parseInt(com.getText());
			if(temp<minimum) throw new NumberFormatException();
			return temp;
		} catch(NumberFormatException ne) {
			com.setText(Integer.toString(current));
			return current;
		}
	}

	/** Creates new form FontConfigPanel */
	public OptionsConfigPanel() {
		initComponents();
		sampleText = FST.orderMessage(SAMPLE_TEXT);
		
		sizeField.addKeyListener(digitsOnly);
		GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		familyCombo.setModel(new DefaultComboBoxModel(gEnv.getAvailableFontFamilyNames()));
		Color o = FST.fontColour;
		Color c = new Color(o.getRed(), o.getGreen(), o.getBlue());
		colorButton.setBackground(c);
		//colorButton.setForeground(c);
		familyCombo.setSelectedItem(FST.font.getFamily());
		sizeField.setText(Integer.toString(FST.font.getSize()));
		alphaSlider.setValue(FST.fontColour.getAlpha());
		
		xMarginField.addKeyListener(digitsOnly);
		yMarginField.addKeyListener(digitsOnly);
		displayField.addKeyListener(digitsOnly);
		delayField.addKeyListener(digitsOnly);

		xMarginField.setText(Integer.toString(FST.marginX));
		yMarginField.setText(Integer.toString(FST.marginY));
		displayField.setText(Integer.toString(FST.display));
		delayField.setText(Integer.toString(FST.delay));
		placementX.setSelectedIndex(FST.placementX);
		placementY.setSelectedIndex(FST.placementY);
		styleCombo.setSelectedIndex(FST.font.getStyle());
		wordOrderCombo.setSelectedIndex(FST.wordOrder);
		letterOrderCombo.setSelectedIndex(FST.letterOrder);
		updatesCheckBox.setSelected(FST.checkUpdates);
		minimizeCheckBox.setSelected(FST.configWindow==2);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jPanel2 = new javax.swing.JPanel();
    familyLabel = new javax.swing.JLabel();
    familyCombo = new javax.swing.JComboBox();
    sizeLabel = new javax.swing.JLabel();
    colorLabel = new javax.swing.JLabel();
    colorButton = new javax.swing.JButton();
    alphaLabel = new javax.swing.JLabel();
    alphaSlider = new javax.swing.JSlider();
    jLabel1 = new javax.swing.JLabel();
    wordOrderCombo = new javax.swing.JComboBox();
    jLabel2 = new javax.swing.JLabel();
    letterOrderCombo = new javax.swing.JComboBox();
    FontLabel = new javax.swing.JLabel();
    OrderLabel = new javax.swing.JLabel();
    DisplayLabel = new javax.swing.JLabel();
    PositionLabel = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    sizeField = new javax.swing.JTextField();
    jLabel10 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    styleCombo = new javax.swing.JComboBox();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    xMarginField = new javax.swing.JTextField();
    jLabel12 = new javax.swing.JLabel();
    jPanel4 = new javax.swing.JPanel();
    yMarginField = new javax.swing.JTextField();
    jLabel13 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    displayField = new javax.swing.JTextField();
    jLabel14 = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    delayField = new javax.swing.JTextField();
    jLabel15 = new javax.swing.JLabel();
    placementX = new javax.swing.JComboBox();
    placementY = new javax.swing.JComboBox();
    jLabel16 = new javax.swing.JLabel();
    jPanel7 = new javax.swing.JPanel();
    minimizeCheckBox = new javax.swing.JCheckBox();
    updatesCheckBox = new javax.swing.JCheckBox();
    samplePanel1 = new SamplePanel();

    setPreferredSize(new java.awt.Dimension(0, 0));
    setLayout(new java.awt.BorderLayout());

    jPanel2.setMinimumSize(new java.awt.Dimension(340, 275));
    jPanel2.setPreferredSize(new java.awt.Dimension(340, 275));
    jPanel2.setLayout(new java.awt.GridBagLayout());

    familyLabel.setFont(new java.awt.Font("SansSerif", 0, 13));
    familyLabel.setText("Family");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(familyLabel, gridBagConstraints);

    familyCombo.setFont(new java.awt.Font("SansSerif", 0, 13));
    familyCombo.setMinimumSize(new java.awt.Dimension(120, 27));
    familyCombo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fontListener(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(familyCombo, gridBagConstraints);

    sizeLabel.setFont(new java.awt.Font("SansSerif", 0, 13));
    sizeLabel.setText("Size");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(sizeLabel, gridBagConstraints);

    colorLabel.setFont(new java.awt.Font("SansSerif", 0, 13));
    colorLabel.setText("Color");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(colorLabel, gridBagConstraints);

    colorButton.setBackground(new java.awt.Color(0, 0, 0));
    colorButton.setFont(new java.awt.Font("SansSerif", 0, 13));
    colorButton.setText("    ");
    colorButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    colorButton.setOpaque(true);
    colorButton.setPreferredSize(new java.awt.Dimension(30, 20));
    colorButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        colorButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
    jPanel2.add(colorButton, gridBagConstraints);

    alphaLabel.setFont(new java.awt.Font("SansSerif", 0, 13));
    alphaLabel.setText("Alpha");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(alphaLabel, gridBagConstraints);

    alphaSlider.setMaximum(255);
    alphaSlider.setMaximumSize(new java.awt.Dimension(32767, 15));
    alphaSlider.setMinimumSize(new java.awt.Dimension(36, 15));
    alphaSlider.setPreferredSize(new java.awt.Dimension(100, 20));
    alphaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        alphaSliderStateChanged(evt);
      }
    });
    alphaSlider.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(java.awt.event.MouseEvent evt) {
        alphaSliderMouseReleased(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(alphaSlider, gridBagConstraints);

    jLabel1.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel1.setText("Word");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel1, gridBagConstraints);

    wordOrderCombo.setFont(new java.awt.Font("SansSerif", 0, 13));
    wordOrderCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Forward", "Reverse", "Rnd Inner", "Random", "Join" }));
    wordOrderCombo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        wordOrderComboActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(wordOrderCombo, gridBagConstraints);

    jLabel2.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel2.setText("Letter");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel2, gridBagConstraints);

    letterOrderCombo.setFont(new java.awt.Font("SansSerif", 0, 13));
    letterOrderCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Forward", "Reverse", "Rnd Inner", "Random" }));
    letterOrderCombo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        letterOrderComboActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(letterOrderCombo, gridBagConstraints);

    FontLabel.setFont(new java.awt.Font("SansSerif", 1, 13));
    FontLabel.setText("Font");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(FontLabel, gridBagConstraints);

    OrderLabel.setFont(new java.awt.Font("SansSerif", 1, 13));
    OrderLabel.setText("Order");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(OrderLabel, gridBagConstraints);

    DisplayLabel.setFont(new java.awt.Font("SansSerif", 1, 13));
    DisplayLabel.setText("Timing");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(DisplayLabel, gridBagConstraints);

    PositionLabel.setFont(new java.awt.Font("SansSerif", 1, 13));
    PositionLabel.setText("Position");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(PositionLabel, gridBagConstraints);

    jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

    sizeField.setColumns(4);
    sizeField.setFont(new java.awt.Font("SansSerif", 0, 13));
    sizeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    sizeField.setMinimumSize(new java.awt.Dimension(30, 28));
    sizeField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fontListener(evt);
      }
    });
    sizeField.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        sizeFieldFocusLost(evt);
      }
    });
    jPanel1.add(sizeField);

    jLabel10.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel10.setText("pt");
    jPanel1.add(jLabel10);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jPanel1, gridBagConstraints);

    jLabel3.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel3.setText("On");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel3, gridBagConstraints);

    jLabel4.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel4.setText("Off");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel4, gridBagConstraints);

    jLabel5.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel5.setText("Style");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel5, gridBagConstraints);

    styleCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Plain", "Bold", "Italic", "BoldItalic" }));
    styleCombo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fontListener(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(styleCombo, gridBagConstraints);

    jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13));
    jLabel6.setText("Margin");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jLabel6, gridBagConstraints);

    jLabel7.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel7.setText("X");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel7, gridBagConstraints);

    jLabel8.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel8.setText("Y");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel8, gridBagConstraints);

    jLabel9.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel9.setText("X");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel9, gridBagConstraints);

    jLabel11.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel11.setText("Y");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel2.add(jLabel11, gridBagConstraints);

    jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

    xMarginField.setColumns(4);
    xMarginField.setFont(new java.awt.Font("SansSerif", 0, 13));
    xMarginField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    xMarginField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        xMarginFieldActionPerformed(evt);
      }
    });
    xMarginField.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        xMarginFieldFocusLost(evt);
      }
    });
    jPanel3.add(xMarginField);

    jLabel12.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel12.setText("px");
    jPanel3.add(jLabel12);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jPanel3, gridBagConstraints);

    jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

    yMarginField.setColumns(4);
    yMarginField.setFont(new java.awt.Font("SansSerif", 0, 13));
    yMarginField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    yMarginField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        yMarginFieldActionPerformed(evt);
      }
    });
    yMarginField.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        yMarginFieldFocusLost(evt);
      }
    });
    jPanel4.add(yMarginField);

    jLabel13.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel13.setText("px");
    jPanel4.add(jLabel13);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jPanel4, gridBagConstraints);

    jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

    displayField.setColumns(4);
    displayField.setFont(new java.awt.Font("SansSerif", 0, 13));
    displayField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    displayField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        displayFieldActionPerformed(evt);
      }
    });
    displayField.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        displayFieldFocusLost(evt);
      }
    });
    jPanel5.add(displayField);

    jLabel14.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel14.setText("ms");
    jPanel5.add(jLabel14);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jPanel5, gridBagConstraints);

    jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

    delayField.setColumns(4);
    delayField.setFont(new java.awt.Font("SansSerif", 0, 13));
    delayField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    delayField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delayFieldActionPerformed(evt);
      }
    });
    delayField.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        delayFieldFocusLost(evt);
      }
    });
    jPanel6.add(delayField);

    jLabel15.setFont(new java.awt.Font("SansSerif", 0, 13));
    jLabel15.setText("ms");
    jPanel6.add(jLabel15);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jPanel6, gridBagConstraints);

    placementX.setFont(new java.awt.Font("SansSerif", 0, 13));
    placementX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Random", "Left", "Center", "Right" }));
    placementX.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        placementXActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(placementX, gridBagConstraints);

    placementY.setFont(new java.awt.Font("SansSerif", 0, 13));
    placementY.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Random", "Top", "Center", "Bottom" }));
    placementY.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        placementYActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(placementY, gridBagConstraints);

    jLabel16.setFont(new java.awt.Font("SansSerif", 1, 13));
    jLabel16.setText("Startup");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(jLabel16, gridBagConstraints);

    minimizeCheckBox.setText("Minimize");
    minimizeCheckBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        minimizeCheckBoxActionPerformed(evt);
      }
    });
    jPanel7.add(minimizeCheckBox);

    updatesCheckBox.setText("Check Updates Every 30 Days");
    updatesCheckBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        updatesCheckBoxActionPerformed(evt);
      }
    });
    jPanel7.add(updatesCheckBox);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    jPanel2.add(jPanel7, gridBagConstraints);

    add(jPanel2, java.awt.BorderLayout.CENTER);
    add(samplePanel1, java.awt.BorderLayout.PAGE_START);
  }// </editor-fold>//GEN-END:initComponents
	private void alphaSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_alphaSliderStateChanged
		Color o = FST.fontColour;
		FST.fontColour = new Color(o.getRed(), o.getGreen(), o.getBlue(), alphaSlider.getValue());
		samplePanel1.repaint();
                FST.settingsChanged();
	}//GEN-LAST:event_alphaSliderStateChanged

	private void colorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorButtonActionPerformed
		Color o = FST.fontColour;
		Color c = JColorChooser.showDialog(this, "FST Font Color",
			new Color(o.getRed(), o.getGreen(), o.getBlue()));
		if (c != null) {
			FST.fontColour = new Color(c.getRed(), c.getGreen(), c.getBlue(), o.getAlpha());
			colorButton.setBackground(c);
			//colorButton.setForeground(c);
			samplePanel1.repaint();
			FST.settingsChanged();
		}
	}//GEN-LAST:event_colorButtonActionPerformed

	private void fontListener(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontListener
		try {
			FST.font = new Font(familyCombo.getSelectedItem().toString(), styleCombo.getSelectedIndex(), Integer.parseInt(sizeField.getText()));
			samplePanel1.repaint();
			FST.settingsChanged();
		} catch (NumberFormatException ne) {
			sizeField.setText(Integer.toString(FST.font.getSize()));
		}
	}//GEN-LAST:event_fontListener

	private void xMarginFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xMarginFieldActionPerformed
		FST.marginX = integerFieldHandler(0,xMarginField,FST.marginY);
		FST.settingsChanged();
	}//GEN-LAST:event_xMarginFieldActionPerformed

	private void xMarginFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_xMarginFieldFocusLost
		xMarginFieldActionPerformed(null);
	}//GEN-LAST:event_xMarginFieldFocusLost

	private void yMarginFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yMarginFieldActionPerformed
		FST.marginY = integerFieldHandler(0,yMarginField,FST.marginY);
		FST.settingsChanged();
	}//GEN-LAST:event_yMarginFieldActionPerformed

	private void yMarginFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yMarginFieldFocusLost
		yMarginFieldActionPerformed(null);
	}//GEN-LAST:event_yMarginFieldFocusLost

	private void displayFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_displayFieldFocusLost
		displayFieldActionPerformed(null);
}//GEN-LAST:event_displayFieldFocusLost

	private void delayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayFieldActionPerformed
		FST.delay = integerFieldHandler(0,delayField,FST.delay);
		FST.settingsChanged();
}//GEN-LAST:event_delayFieldActionPerformed

	private void delayFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_delayFieldFocusLost
		delayFieldActionPerformed(null);
}//GEN-LAST:event_delayFieldFocusLost

	private void placementXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placementXActionPerformed
		FST.placementX = placementX.getSelectedIndex();
		FST.settingsChanged();
	}//GEN-LAST:event_placementXActionPerformed

	private void placementYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placementYActionPerformed
		FST.placementY = placementY.getSelectedIndex();
		FST.settingsChanged();
}//GEN-LAST:event_placementYActionPerformed

	private void sizeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sizeFieldFocusLost
		fontListener(null);
	}//GEN-LAST:event_sizeFieldFocusLost

	private void wordOrderComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordOrderComboActionPerformed
		FST.wordOrder = wordOrderCombo.getSelectedIndex();
		sampleText = FST.orderMessage(SAMPLE_TEXT);
		samplePanel1.repaint();
		FST.settingsChanged();
}//GEN-LAST:event_wordOrderComboActionPerformed

	private void letterOrderComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_letterOrderComboActionPerformed
		FST.letterOrder = letterOrderCombo.getSelectedIndex();
		sampleText = FST.orderMessage(SAMPLE_TEXT);
		samplePanel1.repaint();
		FST.settingsChanged();
	}//GEN-LAST:event_letterOrderComboActionPerformed

	private void displayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayFieldActionPerformed
		FST.display = integerFieldHandler(1,displayField,FST.display);
		FST.settingsChanged();
	}//GEN-LAST:event_displayFieldActionPerformed

	private void minimizeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeCheckBoxActionPerformed
		FST.configWindow = minimizeCheckBox.isSelected() ? 2 : 1;
		FST.settingsChanged();
	}//GEN-LAST:event_minimizeCheckBoxActionPerformed

	private void updatesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesCheckBoxActionPerformed
		FST.checkUpdates = updatesCheckBox.isSelected();
		FST.settingsChanged();
	}//GEN-LAST:event_updatesCheckBoxActionPerformed

	private void alphaSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alphaSliderMouseReleased
		FST.settingsChanged();
	}//GEN-LAST:event_alphaSliderMouseReleased
	
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel DisplayLabel;
  private javax.swing.JLabel FontLabel;
  private javax.swing.JLabel OrderLabel;
  private javax.swing.JLabel PositionLabel;
  private javax.swing.JLabel alphaLabel;
  private javax.swing.JSlider alphaSlider;
  private javax.swing.JButton colorButton;
  private javax.swing.JLabel colorLabel;
  private javax.swing.JTextField delayField;
  private javax.swing.JTextField displayField;
  private javax.swing.JComboBox familyCombo;
  private javax.swing.JLabel familyLabel;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JPanel jPanel7;
  private javax.swing.JComboBox letterOrderCombo;
  private javax.swing.JCheckBox minimizeCheckBox;
  private javax.swing.JComboBox placementX;
  private javax.swing.JComboBox placementY;
  private SamplePanel samplePanel1;
  private javax.swing.JTextField sizeField;
  private javax.swing.JLabel sizeLabel;
  private javax.swing.JComboBox styleCombo;
  private javax.swing.JCheckBox updatesCheckBox;
  private javax.swing.JComboBox wordOrderCombo;
  private javax.swing.JTextField xMarginField;
  private javax.swing.JTextField yMarginField;
  // End of variables declaration//GEN-END:variables
}