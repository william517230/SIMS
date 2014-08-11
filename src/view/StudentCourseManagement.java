package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class StudentCourseManagement extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel topLabel;
	private JButton searchButton = null;// 查看按钮
	private JButton saveButton = null;// 更新按钮
	private JButton updateButton = null;// 修改按钮
	private JButton deleteButton = null;// 删除按钮
	
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("添 加 选 课 信 息");
			saveButton
					.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 18));
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentCourseAdd sca = new StudentCourseAdd();
					sca.setVisible(true);
				}
			});
		}
		return saveButton;
	}
	
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton("删 除 选 课 信 息");
			deleteButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentCourseDelete scd = new StudentCourseDelete();
					scd.setVisible(true);
				}
			});
		}
		return deleteButton;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton("查 看 选 课 信 息");
			searchButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentCourseSearch scs = new StudentCourseSearch();
					scs.setVisible(true);
					}
			});
		}
		return searchButton;
	}
	
	private JButton getUpdateButton() {
		if (updateButton == null) {
			updateButton = new JButton("修 改 选 课 信 息");
			updateButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentCourseUpdate scu = new StudentCourseUpdate();
					scu.setVisible(true);
					}
			});
		}
		return updateButton;
	}
	
	public JPanel getPanel() {
		if(panel == null){
			panel = new JPanel();
		}
		GridLayout layout = new GridLayout(5,1,10,30);
		panel.setLayout(layout);
		topLabel = new JLabel("选 课 信 息 管 理",JLabel.CENTER);
		topLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 30));
		topLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(topLabel);
		panel.add(getSearchButton());
		panel.add(getSaveButton());
		panel.add(getUpdateButton());
		panel.add(getDeleteButton());
		return panel;
	}

	public StudentCourseManagement(){
		setTitle("选课信息管理");
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
