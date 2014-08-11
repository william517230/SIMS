package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Index extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel topLabel;
	private JButton bimButton = null;
	private JButton scButton = null;
	private JButton cButton = null;
	
	private JButton getBimButton() {
		if (bimButton == null) {
			bimButton = new JButton("基 本 信 息 管 理");
			bimButton
					.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 18));
			bimButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BaseInformationManagement bim = new BaseInformationManagement();
					bim.setVisible(true);
				}
			});
		}
		return bimButton;
	}
	
	private JButton getCButton() {
		if (cButton == null) {
			cButton = new JButton("课 程 信 息 管 理");
			cButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			cButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CourseManagement cm = null;
					try {
						cm = new CourseManagement();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cm.setVisible(true);
				}
			});
		}
		return cButton;
	}
	
	private JButton getScButton() {
		if (scButton == null) {
			scButton = new JButton("选 课 信 息 管 理");
			scButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			scButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentCourseManagement scm = new StudentCourseManagement();
					scm.setVisible(true);
					}
			});
		}
		return scButton;
	}
	
	public JPanel getPanel() throws SQLException, IOException, Exception {
		if(panel == null){
			panel = new JPanel();
		}
		GridLayout layout = new GridLayout(4,1,10,30);
		panel.setLayout(layout);
		topLabel = new JLabel("学 生 信 息 管 理 系 统",JLabel.CENTER);
		topLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 30));
		topLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(topLabel);
		panel.add(getBimButton());
		panel.add(getCButton());
		panel.add(getScButton());
		return panel;
	}

	public Index() throws SQLException, IOException, Exception{
		setTitle("学生信息管理系统——首页");
		setSize(new Dimension(400, 400));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / 4, screenHeight / 4);
		setContentPane(getPanel());
	}
	
}
