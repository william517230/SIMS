package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class StudentCourseSearchResult extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	
	public StudentCourseSearchResult(final Object[][] cells) {
		setTitle("查看选课信息");
		setSize(new Dimension(600, 400));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / 4, screenHeight / 4);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());				//设置为边界布局管理器
		panel.add(new JScrollPane(getTable(cells)), BorderLayout.CENTER);
		JButton button = new JButton("打印表格");				//打印表格按钮
		button.addActionListener(new ActionListener() {		//为按钮添加监听器
			public void actionPerformed(ActionEvent event) {
				try {
					getTable(cells).print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(button, BorderLayout.SOUTH);
		setContentPane(panel);
	}
	//创建表格方法
	public JTable getTable(Object[][] cells) {
		if (table == null) {
			//为表格准备头部信息
			String[] headers = { "学号", "姓名", "课程编号", "课程名称","成绩" };
			table = new JTable(cells, headers);
		}
		return table;
	}
	
}
