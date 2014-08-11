package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dao.AbstractDAO;

public class CourseManagement extends JFrame{
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
			saveButton = new JButton("添 加 课 程 信 息");
			saveButton
					.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 18));
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CourseAdd ca = new CourseAdd();
					ca.setVisible(true);
				}
			});
		}
		return saveButton;
	}
	
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton("删 除 课 程 信 息");
			deleteButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CourseDelete cd = new CourseDelete();
					cd.setVisible(true);
				}
			});
		}
		return deleteButton;
	}

	public void search() throws SQLException, IOException, Exception {
		java.sql.Connection c = AbstractDAO.getConnection();
		java.sql.Statement stmt = c.createStatement();
		ResultSet searchResult;
		searchResult = stmt.executeQuery("select * from 课程表");
		if (!searchResult.next()) {
			JOptionPane.showMessageDialog(this, "没有找到相关记录！", "消息",
					JOptionPane.INFORMATION_MESSAGE);
		}
		searchResult.last();
		int i = searchResult.getRow();
		//System.out.println("i = "+i);
		Object r[][] = new Object[i][4];
		searchResult.first();
		searchResult.previous();
		int j = 0;
		while(searchResult.next() && j<i){
			r[j][0] = searchResult.getString("课程编号");
			r[j][1] = searchResult.getString("课程名称");
			r[j][2] = searchResult.getString("学分");
			r[j][3] = searchResult.getString("学时");
			j++;
		}
		CourseSearchResult s = new CourseSearchResult(r);
		s.setVisible(true);
	}

	
	private JButton getSearchButton() throws SQLException, IOException, Exception  {
		if (searchButton == null) {
			searchButton = new JButton("查 看 课 程 信 息");
			searchButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						search();
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
				}
			});
		}
		return searchButton;
	}
	
	private JButton getUpdateButton() {
		if (updateButton == null) {
			updateButton = new JButton("修 改 课 程 信 息");
			updateButton.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD,
					18));
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CourseUpdate cu = new CourseUpdate();
					cu.setVisible(true);
					}
			});
		}
		return updateButton;
	}
	
	public JPanel getPanel() throws SQLException, IOException, Exception {
		if(panel == null){
			panel = new JPanel();
		}
		GridLayout layout = new GridLayout(5,1,10,30);
		panel.setLayout(layout);
		topLabel = new JLabel("课 程 信 息 管 理",JLabel.CENTER);
		topLabel.setFont(new java.awt.Font("新宋体", java.awt.Font.BOLD, 30));
		topLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(topLabel);
		panel.add(getSearchButton());
		panel.add(getSaveButton());
		panel.add(getUpdateButton());
		panel.add(getDeleteButton());
		return panel;
	}

	public CourseManagement() throws SQLException, IOException, Exception{
		setTitle("课程信息管理");
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
