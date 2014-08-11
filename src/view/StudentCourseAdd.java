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

import model.StudentCourse;

import dao.CourseDAO;
import dao.StudentCourseDAO;
import dao.StudentDAO;

public class StudentCourseAdd extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel studentIdLabel;
	private JLabel courseIdLabel;
	private JLabel scoreLabel;
	private JTextField studentIdField;
	private JTextField courseIdField;
	private JTextField scoreField;
	private JButton addButton;
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
	
	public JTextField getScoreField() {
		if (scoreField == null) {
			scoreField = new JTextField();
			scoreField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return scoreField;
	}
	
	public JLabel getTopLabel() {
		if(topLabel == null){
			topLabel = new JLabel("添 加 选 课 信 息",JLabel.CENTER);
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

	public JLabel getScoreLabel() {
		if(scoreLabel == null){
			scoreLabel = new JLabel("成       绩：",JLabel.CENTER);
			scoreLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return scoreLabel;
	}

	public JButton getAddButton() {
		if(addButton == null){
			addButton = new JButton("录  入");
			addButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						add();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return addButton;
	}
	
	// 保存信息
	public void add() throws SQLException {
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
				int option = 1;
				if (studentCourseDAO.isExist("学号", studentIdField.getText(),"课程编号",courseIdField.getText())) {
					option = JOptionPane.showConfirmDialog(this, "学号为 "
							+ studentIdField.getText() + " 课程编号为 "+courseIdField.getText()+" 的选课信息已存在，要覆盖吗？", "消息",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					option = 0;
				}
				// 当选择确定时执行保存操作
				if (option == 0) {
					StudentCourse studentCourse = new StudentCourse();
					studentCourse.setStudentId(studentIdField.getText());
					studentCourse.setCourseId(courseIdField.getText());
					studentCourse.setScore(scoreField.getText());
					studentCourseDAO.save(studentCourse); // 保存基本信息
					JOptionPane.showMessageDialog(this, "选课信息保存成功！", "消息",
							JOptionPane.INFORMATION_MESSAGE);
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
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getCourseIdLabel());
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getScoreLabel());
		  Box boxv2=Box.createVerticalBox();
		  boxv2.add(Box.createVerticalStrut(20));
		  boxv2.add(getStudentIdField());
		  boxv2.add(Box.createVerticalStrut(6));
		  boxv2.add(getCourseIdField());
		  boxv2.add(Box.createVerticalStrut(6));
		  boxv2.add(getScoreField());
		  Box basebox=Box.createHorizontalBox();
		  basebox.add(boxv1);
		  basebox.add(Box.createHorizontalStrut(20));
		  basebox.add(boxv2);
		  panel.setLayout(new BorderLayout());
		  panel.add(getTopLabel(), BorderLayout.NORTH);
		  panel.add(basebox, BorderLayout.CENTER);
		  panel.add(getAddButton(), BorderLayout.SOUTH);
		return panel;
	}

	public StudentCourseAdd(){
		setTitle("添加选课信息");
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
