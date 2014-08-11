package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
//病人图片展示Panel
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private Image image = null;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		g2.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
