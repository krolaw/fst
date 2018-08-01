import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krolaw
 */
public class SamplePanel extends JPanel {
			BufferedImage grid;
			public SamplePanel() {
				setPreferredSize(new Dimension(80, 80));
			}
			@Override
			public void paint(Graphics g) {
				if (grid == null) {
					grid = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics b = grid.getGraphics();
					b.setColor(Color.lightGray);
					b.fillRect(0, 0, grid.getWidth(), grid.getHeight());
					b.setColor(Color.WHITE);
					final int blockSize = 5;
					for (int x = 0; x < grid.getWidth() / blockSize; x += 2) {
						b.fillRect(x * blockSize, 0, blockSize, grid.getHeight());
					}
				}
				g.drawImage(grid, 0, 0, null);
				g.setColor(FST.fontColour);
				g.setFont(FST.font);
				FontMetrics fontMetrics = g.getFontMetrics();
				g.drawString(OptionsConfigPanel.sampleText, (grid.getWidth() - fontMetrics.stringWidth(OptionsConfigPanel.sampleText)) / 2,
					(grid.getHeight() + fontMetrics.getAscent() - fontMetrics.getDescent()) / 2);
			}
}
