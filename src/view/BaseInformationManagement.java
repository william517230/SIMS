package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;

import model.Student;
import dao.StudentDAO;

//学生信息显示窗口
public class BaseInformationManagement extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel = null;
	private JTextField nameField = null;// 姓名输入框
	private JLabel studentIdLabel = null;
	private JTextField studentIdField = null;// 学号输入框
	private JLabel genderLabel = null;
	private JComboBox genderField = null;// 性别输入框
	private JLabel birthdayLabel = null;
	private JTextField birthdayField = null;// 生日输入框
	private JLabel idLabel = null;
	private JTextField idField = null;// 身份证号输入框
	private JLabel gradeLabel = null;
	private JTextField gradeField = null;// 年级输入框
	private JLabel departmentLabel = null;
	private JTextField departmentField = null;// 学院输入框
	private JLabel majorLabel = null;
	private JTextField majorField = null;// 专业输入框
	private JLabel classNoLabel = null;
	private JTextField classNoField = null;// 班号输入框
	private JLabel keywordLabel = null;
	private JTextField keywordField = null;// 搜索关键字输入框
	private JLabel searchItemLabel = null;
	private JComboBox searchItemField = null;// 搜索类别输入框
	private JPanel firstLinePanel = null; // 第一行面板
	private JPanel secondLinePanel = null;// 第二行面板
	private JPanel thirdLinePanel = null; // 第三行面板
	private JPanel fourthLinePanel = null;// 第四行面板
	private JPanel fifthLinePanel = null; // 第五行面板
	private JPanel sixthLinePanel = null;// 第六行面板
	private JPanel seventhLinePanel = null; // 第七行面板
	private JPanel imageControlPanel = null; // 图片与“浏览”按钮存放面板
	private JPanel studentInfoPanel = null;
	private JPanel studentBasicInfoPanel = null;// 基本信息面板
	private JPanel operationPanel = null;// 操作面板
	private Image photo = null;
	private JButton fileChooseButton; // 图片选择按钮
	private JButton saveButton = null;// 更新按钮
	private JButton deleteButton = null;// 删除按钮
	private JButton searchButton = null;// 搜索按钮
	private JButton previousButton = null;// 返回按钮
	private JButton nextButton = null;// 查看下一条结果按钮
	private JFileChooser fileChooser;
	private JFrame parentFrame;
	private ImagePanel photoPanel = null; // 图片面板
	private String imageDir = "";
	private StudentDAO studentDAO;
	private Student student;
	private ResultSet searchResult = null;

	public JTextField getNameField() {
		if (nameField == null) {
			nameField = new JTextField();
			nameField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return nameField;
	}

	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public JTextField getStudentIdField() {
		if (studentIdField == null) {
			studentIdField = new JTextField();
			studentIdField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return studentIdField;
	}

	public JTextField getIdField() {
		if (idField == null) {
			idField = new JTextField();
			idField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return idField;
	}

	public JTextField getBirthdayField() {
		if (birthdayField == null) {
			birthdayField = new JTextField();
			birthdayField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return birthdayField;
	}

	public JTextField getGradeField() {
		if (gradeField == null) {
			gradeField = new JTextField();
			gradeField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return gradeField;
	}

	public JTextField getDepartmentField() {
		if (departmentField == null) {
			departmentField = new JTextField();
			departmentField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return departmentField;
	}

	public JTextField getMajorField() {
		if (majorField == null) {
			majorField = new JTextField();
			majorField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return majorField;
	}

	public JTextField getClassNoField() {
		if (classNoField == null) {
			classNoField = new JTextField();
			classNoField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return classNoField;
	}

	public JTextField getKeywordField() {
		if (keywordField == null) {
			keywordField = new JTextField();
			keywordField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));
		}
		return keywordField;
	}

	public JComboBox getGenderField() {
		if (genderField == null) {
			genderField = new JComboBox();
			genderField.addItem("");
			genderField.addItem("男");
			genderField.addItem("女");
			genderField.setFont(new java.awt.Font("楷体", java.awt.Font.BOLD, 16));

		}
		return genderField;
	}

	public JComboBox getSearchItemField() {
		if (searchItemField == null) {
			searchItemField = new JComboBox();
			searchItemField.addItem("姓名");
			searchItemField.addItem("学号");
		}
		return searchItemField;
	}

	public ImagePanel getPhotoPanel() {
		if (photoPanel == null) {
			photoPanel = new ImagePanel();
		}
		return photoPanel;
	}

	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("保存/更新");
			saveButton
					.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 18));
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						save();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return saveButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton("删除此记录");
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

	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton("确 定");
			searchButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						search();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			});
		}
		return searchButton;
	}

	private JButton getPreviousButton() {
		if (previousButton == null) {
			previousButton = new JButton("上一条结果");
			previousButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			previousButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previous();
				}
			});
		}
		return previousButton;
	}

	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton("下一条结果");
			nextButton
					.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 18));
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						next();
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e2) {
						e2.printStackTrace();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			});
		}
		return nextButton;
	}

	public JButton getFileChooseButton() {
		if (fileChooseButton == null) {
			fileChooseButton = new JButton("更 换 照 片");
			fileChooseButton.setFont(new java.awt.Font("新宋体",
					java.awt.Font.BOLD, 15));
			fileChooseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 显示打开文件对话框
					int result = getFileChooser().showOpenDialog(parentFrame);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = getFileChooser().getSelectedFile();
						imageDir = file.getAbsolutePath();
						try {
							photo = ImageIO.read(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						photoPanel.setImage(photo);
						photoPanel.repaint();
					}
				}
			});
		}
		return fileChooseButton;
	}

	public JFileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileFilter() {
				public boolean accept(File file) {
					return file.getName().toLowerCase().endsWith(".jpg")
							|| file.getName().toLowerCase().endsWith(".gif")
							|| file.isDirectory();
				}

				public String getDescription() {
					return "*.JPG, *.GIF";
				}
			});
		}
		return fileChooser;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	// 基本信息第一行信息面板
	private JPanel getFirstLinePanel() {
		if (firstLinePanel == null) {
			firstLinePanel = new JPanel();
			firstLinePanel.setLayout(new BoxLayout(firstLinePanel,
					BoxLayout.X_AXIS));
			firstLinePanel.add(nameLabel);
			firstLinePanel.add(getNameField());
			firstLinePanel.add(genderLabel);
			firstLinePanel.add(getGenderField());
		}
		return firstLinePanel;
	}

	// 基本信息第二行信息面板
	private JPanel getSecondLinePanel() {
		if (secondLinePanel == null) {
			secondLinePanel = new JPanel();
			secondLinePanel.setLayout(new BoxLayout(secondLinePanel,
					BoxLayout.X_AXIS));
			secondLinePanel.add(studentIdLabel);
			secondLinePanel.add(getStudentIdField());
			secondLinePanel.add(gradeLabel);
			secondLinePanel.add(getGradeField());
		}
		return secondLinePanel;
	}

	// 基本信息第三行信息面板
	private JPanel getThirdLinePanel() {
		if (thirdLinePanel == null) {
			thirdLinePanel = new JPanel();
			thirdLinePanel.setLayout(new BoxLayout(thirdLinePanel,
					BoxLayout.X_AXIS));
			thirdLinePanel.add(departmentLabel);
			thirdLinePanel.add(getDepartmentField());
			thirdLinePanel.add(majorLabel);
			thirdLinePanel.add(getMajorField());
		}
		return thirdLinePanel;
	}

	// 基本信息第四行信息面板
	private JPanel getFourthLinePanel() {
		if (fourthLinePanel == null) {
			fourthLinePanel = new JPanel();
			fourthLinePanel.setLayout(new BoxLayout(fourthLinePanel,
					BoxLayout.X_AXIS));
			fourthLinePanel.add(classNoLabel);
			fourthLinePanel.add(getClassNoField());
			fourthLinePanel.add(birthdayLabel);
			fourthLinePanel.add(getBirthdayField());
		}
		return fourthLinePanel;
	}

	// 基本信息第五行信息面板
	private JPanel getFifthLinePanel() {
		if (fifthLinePanel == null) {
			fifthLinePanel = new JPanel();
			fifthLinePanel.setLayout(new BoxLayout(fifthLinePanel,
					BoxLayout.X_AXIS));
			fifthLinePanel.add(idLabel);
			fifthLinePanel.add(getIdField());
		}
		return fifthLinePanel;
	}

	// 第六行面板
	private JPanel getSixthLinePanel() {
		if (sixthLinePanel == null) {
			sixthLinePanel = new JPanel();
			sixthLinePanel.setLayout(new BoxLayout(sixthLinePanel,
					BoxLayout.X_AXIS));
			sixthLinePanel.add(searchItemLabel);
			sixthLinePanel.add(getSearchItemField());
			sixthLinePanel.add(keywordLabel);
			sixthLinePanel.add(getKeywordField());
			sixthLinePanel.add(getSearchButton());

		}
		return sixthLinePanel;
	}

	// 第七行面板
	private JPanel getSeventhLinePanel() {
		if (seventhLinePanel == null) {
			seventhLinePanel = new JPanel();
			seventhLinePanel.setLayout(new BoxLayout(seventhLinePanel,
					BoxLayout.X_AXIS));
			seventhLinePanel.add(getSaveButton());
			seventhLinePanel.add(getDeleteButton());
			seventhLinePanel.add(getPreviousButton());
			seventhLinePanel.add(getNextButton());
		}
		return seventhLinePanel;
	}

	// 学生基本信息面板
	private JPanel getStudentInfoPanel() {
		if (studentInfoPanel == null) {
			studentInfoPanel = new JPanel();
			studentInfoPanel.setLayout(new BoxLayout(getStudentInfoPanel(),
					BoxLayout.Y_AXIS));
			studentInfoPanel.setPreferredSize(new Dimension(600, 150));
			studentInfoPanel.add(getFirstLinePanel());
			studentInfoPanel.add(getSecondLinePanel());
			studentInfoPanel.add(getThirdLinePanel());
			studentInfoPanel.add(getFourthLinePanel());
		}
		return studentInfoPanel;
	}

	// 操作面板
	private JPanel getOperationPanel() {
		if (operationPanel == null) {
			operationPanel = new JPanel();
			operationPanel.setLayout(new BoxLayout(getOperationPanel(),
					BoxLayout.Y_AXIS));
			operationPanel.setPreferredSize(new Dimension(150, 150));
			operationPanel.add(getFifthLinePanel());
			operationPanel.add(getSixthLinePanel());
			operationPanel.add(getSeventhLinePanel());
		}
		return operationPanel;
	}

	// 图片、浏览按钮摆放面板
	private JPanel getImageControlPanel() {
		if (imageControlPanel == null) {
			imageControlPanel = new JPanel();
			imageControlPanel.setLayout(new BorderLayout());
			imageControlPanel.setBorder(BorderFactory.createEtchedBorder());
			imageControlPanel.add(getPhotoPanel(), BorderLayout.CENTER);
			imageControlPanel.add(getFileChooseButton(), BorderLayout.SOUTH);
		}
		return imageControlPanel;
	}

	// 基本信息与照片面板
	private JPanel getStudentBasicInfoPanel() {
		if (studentBasicInfoPanel == null) {
			studentBasicInfoPanel = new JPanel();
			studentBasicInfoPanel.setLayout(new BoxLayout(
					getStudentBasicInfoPanel(), BoxLayout.X_AXIS));
			studentBasicInfoPanel.add(getStudentInfoPanel());
			studentBasicInfoPanel.add(getImageControlPanel());
		}
		return studentBasicInfoPanel;
	}

	// 保存信息
	public void save() throws SQLException {
		studentDAO = new StudentDAO();
		if (studentIdField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "学号不能为空！", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int option = 1;
			if (studentDAO.isExist("学号", studentIdField.getText())) {
				option = JOptionPane.showConfirmDialog(this, "将覆盖学号为"
						+ studentIdField.getText() + "的学生的信息，您确定要这样做吗？", "消息",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				option = JOptionPane.showConfirmDialog(this, "确定要创建学号为"
						+ studentIdField.getText() + "的学生的信息吗？", "消息",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			}
			// 当选择确定时执行保存操作
			if (option == 0) {
				student = new Student();
				student.setStudentId(studentIdField.getText());
				student.setName(nameField.getText());
				student.setGender(genderField.getSelectedItem().toString());
				student.setBirthday(birthdayField.getText());
				student.setId(idField.getText());
				student.setGrade(gradeField.getText());
				student.setDepartment(departmentField.getText());
				student.setMajor(majorField.getText());
				student.setClassNo(classNoField.getText());
				while(imageDir.equals("")){
					JOptionPane.showMessageDialog(this, "请上传照片！", "消息",
							JOptionPane.INFORMATION_MESSAGE);
					int result = getFileChooser().showOpenDialog(parentFrame);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = getFileChooser().getSelectedFile();
						imageDir = file.getAbsolutePath();
						try {
							photo = ImageIO.read(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						photoPanel.setImage(photo);
						photoPanel.repaint();
					}
				}
				student.setImageDir(imageDir);
				studentDAO.save(student); // 保存基本信息
				JOptionPane.showMessageDialog(this, "学生信息保存成功！", "消息",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void delete() throws SQLException {
		String studentId = searchResult.getString("学号");
		int option = JOptionPane.showConfirmDialog(this, "确定删除学号为" + studentId
				+ "的学生的信息吗？", "消息", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		// 当选择确定时执行删除操作
		if (option == 0) {
			studentDAO.delete(studentId);
			JOptionPane.showMessageDialog(this, "学生信息删除成功！", "消息",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void search() throws SQLException, IOException {
		if (keywordField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "请输入搜索关键字！", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
			studentDAO = new StudentDAO();
			String searchItem;
			if (searchItemField.getSelectedItem().equals("学号")) {
				searchItem = "学号";
			} else {
				searchItem = "姓名";
			}
			searchResult = studentDAO
					.search(searchItem, keywordField.getText());
			if (searchResult.next()) {
				nameField.setText(searchResult.getString("姓名"));
				studentIdField.setText(searchResult.getString("学号"));
				birthdayField.setText(searchResult.getString("生日"));
				idField.setText(searchResult.getString("身份证号"));
				gradeField.setText(searchResult.getString("年级"));
				departmentField.setText(searchResult.getString("学院"));
				majorField.setText(searchResult.getString("专业"));
				classNoField.setText(searchResult.getString("班号"));
				genderField.setSelectedItem(searchResult.getString("性别"));
				photo = ImageIO.read(new File(searchResult
						.getString("头像路径")));
				photoPanel.setImage(photo);
				photoPanel.repaint();
				getSaveButton().setEnabled(true);
				getDeleteButton().setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this, "没有找到相关记录！", "消息",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (searchResult.next()) {
				searchResult.previous();
				getNextButton().setEnabled(true);
			} else {
				searchResult.previous();
				getNextButton().setEnabled(false);
			}
			if (searchResult.previous()) {
				searchResult.next();
				getPreviousButton().setEnabled(true);
			} else {
				searchResult.next();
				getPreviousButton().setEnabled(false);
			}
		}
	}

	public void previous() {
		try {
			if (searchResult.previous()) {
				nameField.setText(searchResult.getString("姓名"));
				studentIdField.setText(searchResult.getString("学号"));
				birthdayField.setText(searchResult.getString("生日"));
				idField.setText(searchResult.getString("身份证号"));
				gradeField.setText(searchResult.getString("年级"));
				departmentField.setText(searchResult.getString("学院"));
				majorField.setText(searchResult.getString("专业"));
				classNoField.setText(searchResult.getString("班号"));
				genderField.setSelectedItem(searchResult.getString("性别"));
				photo = ImageIO.read(new File(searchResult
						.getString("头像路径")));
				photoPanel.setImage(photo);
				photoPanel.repaint();
				if (searchResult.next()) {
					searchResult.previous();
					getNextButton().setEnabled(true);
				} else {
					searchResult.previous();
					getNextButton().setEnabled(false);
				}
				if (searchResult.previous()) {
					searchResult.next();
					getPreviousButton().setEnabled(true);
				} else {
					searchResult.next();
					getPreviousButton().setEnabled(false);
				}
			} else {
				JOptionPane.showMessageDialog(this, "没有找到相关记录！", "消息",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void next() throws HeadlessException, SQLException, IOException {
		if (searchResult.next()) {
			nameField.setText(searchResult.getString("姓名"));
			studentIdField.setText(searchResult.getString("学号"));
			birthdayField.setText(searchResult.getString("生日"));
			idField.setText(searchResult.getString("身份证号"));
			gradeField.setText(searchResult.getString("年级"));
			departmentField.setText(searchResult.getString("学院"));
			majorField.setText(searchResult.getString("专业"));
			classNoField.setText(searchResult.getString("班号"));
			genderField.setSelectedItem(searchResult.getString("性别"));
			photo = ImageIO.read(new File(searchResult.getString("头像路径")));
			photoPanel.setImage(photo);
			photoPanel.repaint();
			if (searchResult.next()) {
				searchResult.previous();
				getNextButton().setEnabled(true);
			} else {
				searchResult.previous();
				getNextButton().setEnabled(false);
			}
			if (searchResult.previous()) {
				searchResult.next();
				getPreviousButton().setEnabled(true);
			} else {
				searchResult.next();
				getPreviousButton().setEnabled(false);
			}
		} else {
			JOptionPane.showMessageDialog(this, "没有找到相关记录！", "消息",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public BaseInformationManagement() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("学生信息管理系统");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / 4, screenHeight / 4);
		studentIdLabel = new JLabel();
		studentIdLabel
				.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		studentIdLabel.setText("学    号:");
		nameLabel = new JLabel();
		nameLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		nameLabel.setText("姓    名:");
		genderLabel = new JLabel();
		genderLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		genderLabel.setText("性    别:");
		birthdayLabel = new JLabel();
		birthdayLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		birthdayLabel.setText("生    日:");
		idLabel = new JLabel();
		idLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		idLabel.setText("身 份 证:");
		gradeLabel = new JLabel();
		gradeLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		gradeLabel.setText("年    级:");
		departmentLabel = new JLabel();
		departmentLabel
				.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		departmentLabel.setText("学    院:");
		majorLabel = new JLabel();
		majorLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		majorLabel.setText("专    业:");
		classNoLabel = new JLabel();
		classNoLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		classNoLabel.setText("班    号:");
		searchItemLabel = new JLabel();
		searchItemLabel
				.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		searchItemLabel.setText("查    询:");
		keywordLabel = new JLabel();
		keywordLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
		keywordLabel.setText("关  键  字:");
		getPreviousButton().setEnabled(false);
		getNextButton().setEnabled(false);
		getDeleteButton().setEnabled(false);
		getSaveButton().setEnabled(true);
		this.setLayout(new GridLayout(2, 1));
		this.add(getStudentBasicInfoPanel());
		this.add(getOperationPanel());
		this.setSize(600, 380);
		this.setResizable(false);
	}

}
