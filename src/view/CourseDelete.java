package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.CourseDAO;

public class CourseDelete extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel courseIdLabel;
	private JTextField courseIdField;
	private JButton deleteButton;
	private JPanel panel;
	private JLabel topLabel;
	
	public JTextField getCourseIdField() {
		if (courseIdField == null) {
			courseIdField = new JTextField();
			courseIdField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return courseIdField;
	}
	
	
	public JLabel getTopLabel() {
		if(topLabel == null){
			topLabel = new JLabel("删 除 课 程 信 息",JLabel.CENTER);
			topLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					28));
			topLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return topLabel;
	}

	public JLabel getCourseIdLabel() {
		if(courseIdLabel == null){
			courseIdLabel = new JLabel("课 程 编 号：",JLabel.CENTER);
			courseIdLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			courseIdLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return courseIdLabel;
	}

	public JButton getDeleteButton() {
		if(deleteButton == null){
			deleteButton = new JButton("删    除");
			deleteButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						delete();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return deleteButton;
	}
	
	// 保存信息
	public void delete() throws SQLException {
		CourseDAO courseDAO = new CourseDAO();
		if (courseIdField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "课程编号不能为空！", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
				int option = 1;
				if (courseDAO.isExist("课程编号",courseIdField.getText())) {
					option = JOptionPane.showConfirmDialog(this, "确定要删除吗？", "消息",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "不存在编号为 "+courseIdField.getText()+" 的课程！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
				// 当选择确定时执行保存操作
				if (option == 0) {
					courseDAO.delete(courseIdField.getText());
					JOptionPane.showMessageDialog(this, "课程信息删除成功！", "消息",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
	}
	
	public JPanel getPanel() {
		if(panel == null){
			panel = new JPanel();
		}
		Box boxv1=Box.createVerticalBox();
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getCourseIdLabel());
		  Box boxv2=Box.createVerticalBox();
		  boxv2.add(Box.createVerticalStrut(20));
		  boxv2.add(getCourseIdField());
		  Box basebox=Box.createHorizontalBox();
		  basebox.add(boxv1);
		  basebox.add(Box.createHorizontalStrut(20));
		  basebox.add(boxv2);
		  panel.setLayout(new BorderLayout());
		  panel.add(getTopLabel(), BorderLayout.NORTH);
		  panel.add(basebox, BorderLayout.CENTER);
		  panel.add(getDeleteButton(), BorderLayout.SOUTH);
		return panel;
	}

	public CourseDelete(){
		setTitle("删除课程信息");
		setSize(new Dimension(400, 170));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / 4, screenHeight / 4);
		setContentPane(getPanel());
	}
	
}
