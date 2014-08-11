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

import model.Course;
import dao.CourseDAO;

public class CourseAdd extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel courseIdLabel;
	private JLabel courseNameLabel;
	private JLabel creditLabel;
	private JLabel creditHoursLabel;
	private JTextField courseIdField;
	private JTextField courseNameField;
	private JTextField creditField;
	private JTextField creditHoursField;
	private JButton addButton;
	private JPanel panel;
	private JLabel topLabel;
	
	public JTextField getCourseNameField() {
		if (courseNameField == null) {
			courseNameField = new JTextField();
			courseNameField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return courseNameField;
	}
	
	public JTextField getCourseIdField() {
		if (courseIdField == null) {
			courseIdField = new JTextField();
			courseIdField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return courseIdField;
	}
	
	public JTextField getCreditField() {
		if (creditField == null) {
			creditField = new JTextField();
			creditField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return creditField;
	}
	
	public JTextField getCreditHoursField() {
		if (creditHoursField == null) {
			creditHoursField = new JTextField();
			creditHoursField.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
		}
		return creditHoursField;
	}
	
	public JLabel getTopLabel() {
		if(topLabel == null){
			topLabel = new JLabel("添 加 课 程",JLabel.CENTER);
			topLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					28));
			topLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return topLabel;
	}

	public JLabel getCourseNameLabel() {
		if(courseNameLabel == null){
			courseNameLabel = new JLabel("课 程 名 称：",JLabel.CENTER);
			courseNameLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			courseNameLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return courseNameLabel;
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

	public JLabel getCreditHoursLabel() {
		if(creditHoursLabel == null){
			creditHoursLabel = new JLabel("学       时：",JLabel.CENTER);
			creditHoursLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			creditHoursLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return creditHoursLabel;
	}
	
	public JLabel getCreditLabel() {
		if(creditLabel == null){
			creditLabel = new JLabel("学       分：",JLabel.CENTER);
			creditLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			creditLabel.setAlignmentX(CENTER_ALIGNMENT);
		}
		return creditLabel;
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
		CourseDAO courseDAO = new CourseDAO();
		if (courseIdField.getText().equals("") || courseNameField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "课程编号和课程名称不能为空！", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
				int option = 1;
				if (courseDAO.isExist("课程编号",courseIdField.getText())) {
					option = JOptionPane.showConfirmDialog(this, "编号为 "+courseIdField.getText()+" 的课程已存在，要覆盖吗？", "消息",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					option = 0;
				}
				// 当选择确定时执行保存操作
				if (option == 0) {
					Course course = new Course();
					course.setCourseId(courseIdField.getText());
					course.setCourseName(courseNameField.getText());
					course.setCredit(creditField.getText());
					course.setCreditHours(creditHoursField.getText());
					courseDAO.save(course);
					JOptionPane.showMessageDialog(this, "课程信息保存成功！", "消息",
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
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getCourseNameLabel());
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getCreditLabel());
		  boxv1.add(Box.createVerticalStrut(20));
		  boxv1.add(getCreditHoursLabel());
		  Box boxv2=Box.createVerticalBox();
		  boxv2.add(Box.createVerticalStrut(20));
		  boxv2.add(getCourseIdField());
		  boxv2.add(Box.createVerticalStrut(6));
		  boxv2.add(getCourseNameField());
		  boxv2.add(Box.createVerticalStrut(6));
		  boxv2.add(getCreditField());
		  boxv2.add(Box.createVerticalStrut(6));
		  boxv2.add(getCreditHoursField());
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

	public CourseAdd(){
		setTitle("添加课程信息");
		setSize(new Dimension(400, 270));
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
