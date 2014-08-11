package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.AbstractDAO;
//简单表格测试
public class StudentCourseSearch extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel keywordLabel = null;
	private JTextField keywordField = null;// 搜索关键字输入框
	private JLabel searchItemLabel = null;
	private JComboBox searchItemField = null;// 搜索类别输入框
	private JButton searchButton = null;// 搜索按钮
	private JPanel searchPanel = null;// 搜索面板
	String[] headers = { "学号", "姓名", "课程编号", "课程名称","成绩" };
	
	public JTextField getKeywordField() {
		if (keywordField == null) {
			keywordField = new JTextField();
		}
		return keywordField;
	}
	
	
	public JComboBox getSearchItemField() {
		if (searchItemField == null) {
			searchItemField = new JComboBox();
			searchItemField.addItem("学号");
			searchItemField.addItem("课程编号");
		}
		return searchItemField;
	}
	
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton("确 定");
			searchButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,18));
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						search();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return searchButton;
	}
	
	private JPanel getSearchPanel() {
		if (searchPanel == null) {
			searchPanel = new JPanel();
			searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.X_AXIS));
			searchItemLabel = new JLabel();
			searchItemLabel
					.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
			searchItemLabel.setText("查    询:");
			keywordLabel = new JLabel();
			keywordLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 15));
			keywordLabel.setText("关  键  字:");
			searchPanel.add(searchItemLabel);
			searchPanel.add(getSearchItemField());
			searchPanel.add(keywordLabel);
			searchPanel.add(getKeywordField());
			searchPanel.add(getSearchButton());

		}
		return searchPanel;
	}
	
	public void search() throws SQLException, IOException, Exception {
		if (keywordField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "请输入搜索关键字！", "错误",
					JOptionPane.ERROR_MESSAGE);
		} else {
			//StudentCourseDAO studentCourseDAO = new StudentCourseDAO();
			java.sql.Connection c = AbstractDAO.getConnection();
			java.sql.Statement stmt = c.createStatement();
			ResultSet searchResult;
			if (searchItemField.getSelectedItem().equals("学号")) {
				searchResult = stmt.executeQuery("select * from 选课信息表,课程表,基本信息表 " +
						"where 选课信息表.课程编号=课程表.课程编号 and 选课信息表.学号=基本信息表.学号 and 选课信息表.学号="+keywordField.getText());
			} else {
				searchResult = stmt.executeQuery("select * from 选课信息表,课程表,基本信息表 " +
						"where 选课信息表.课程编号=课程表.课程编号 and 选课信息表.学号=基本信息表.学号 and 选课信息表.课程编号="+keywordField.getText());
			}
			
			if (!searchResult.next()) {
				JOptionPane.showMessageDialog(this, "没有找到相关记录！", "消息",
						JOptionPane.INFORMATION_MESSAGE);
			}
			searchResult.last();
			int i = searchResult.getRow();
			//System.out.println("i = "+i);
			Object r[][] = new Object[i][5];
			searchResult.first();
			searchResult.previous();
			int j = 0;
			while(searchResult.next() && j<i){
				r[j][0] = searchResult.getString("学号");
				//System.out.println("学号="+searchResult.getString("学号"));
				r[j][1] = searchResult.getString("基本信息表.姓名");
				//System.out.println("student.name="+searchResult.getString("student.name"));
				r[j][2] = searchResult.getString("课程编号");
				//System.out.println("课程编号="+searchResult.getString("课程编号"));
				r[j][3] = searchResult.getString("课程表.课程名称");
				//System.out.println("课程表.课程名称="+searchResult.getString("课程表.课程名称"));
				r[j][4] = searchResult.getString("成绩");
				//System.out.println("成绩="+searchResult.getString("成绩"));
				j++;
			}
			StudentCourseSearchResult s = new StudentCourseSearchResult(r);
			s.setVisible(true);
		}
	}
	
	public StudentCourseSearch() {
		setTitle("查询选课信息");
		setSize(new Dimension(600, 90));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / 4, screenHeight / 4);
		setContentPane(getSearchPanel());
	}
}
