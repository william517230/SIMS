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
import dao.StudentCourseDAO;
import dao.StudentDAO;

public class StudentCourseDelete extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel studentIdLabel;
	private JLabel courseIdLabel;
	private JTextField studentIdField;
	private JTextField courseIdField;
	private JButton deleteButton;
	private JPanel panel;
	private JLabel topLabel;
	
	public JTextField getStudentIdField() {
		if (studentIdField == null) {
			studentIdField = new JTextField();
			studentIdField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return studentIdField;
	}
	
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
			topLabel = new JLabel("删 除 选 课 信 息",JLabel.CENTER);
			topLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					28));
			topLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return topLabel;
	}

	public JLabel getStudentIdLabel() {
		if(studentIdLabel == null){
			studentIdLabel = new JLabel("学       号：",JLabel.CENTER);
			studentIdLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			studentIdLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return studentIdLabel;
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
			deleteButton = new JButton("删  除");
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
		StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
		StudentDAO studentDAO = new StudentDAO();
		CourseDAO courseDAO = new CourseDAO();
		if (studentIdField.getText().equals("") || courseIdField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "学号和课程编号不能为空！", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if(!studentDAO.isExist("学号",studentIdField.getText())
					|| !courseDAO.isExist("课程编号",courseIdField.getText())){
				if(!studentDAO.isExist("学号",studentIdField.getText())){
					JOptionPane.showMessageDialog(this, "不存在学号为 "+studentIdField.getText()+" 的学生！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
				if(!courseDAO.isExist("课程编号",courseIdField.getText())){
					JOptionPane.showMessageDialog(this, "不存在编号为 "+courseIdField.getText()+" 的课程！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}else{
				if (studentCourseDAO.isExist("学号", studentIdField.getText(),"课程编号",courseIdField.getText())) {
					studentCourseDAO.delete(studentIdField.getText(), courseIdField.getText());
					JOptionPane.showMessageDialog(this, "选课信息删除成功！", "消息",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "不存在学号为 "+ studentIdField.getText() 
							+ " 课程编号为 "+courseIdField.getText()+" 的选课信息", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
	}
	
	public JPanel getPanel() {
		if(panel == null){
			panel = new JPanel();
		}
		Box boxv1=Box.createVerticalBox();
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getStudentIdLabel());
		  boxv1.add(Box.createVerticalStrut(25));
		  boxv1.add(getCourseIdLabel());
		  boxv1.add(Box.createVerticalStrut(25));
		  Box boxv2=Box.createVerticalBox();
		  boxv2.add(Box.createVerticalStrut(20));
		  boxv2.add(getStudentIdField());
		  boxv2.add(Box.createVerticalStrut(6));
		  boxv2.add(getCourseIdField());
		  boxv2.add(Box.createVerticalStrut(6));
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

	public StudentCourseDelete(){
		setTitle("删除选课信息");
		setSize(new Dimension(400, 240));
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
