package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.StudentCourse;

public class StudentCourseDAO extends AbstractDAO{
			// 按特定属性查询，返回符合条件的Course对象
			public ResultSet search(String item, String keyword) {
				try {
					PreparedStatement ps = getConnection().prepareStatement(
							"select * from 选课信息表  where " + item + "=?");
					ps.setString(1, keyword);
					ResultSet rs = ps.executeQuery();
					return rs;
				} catch (Exception re) {
					re.printStackTrace();
					return null;
				}
			}
			
			// 按特定属性查询，返回符合条件的Course对象
			public ResultSet search(String item1, String keyword1,String item2, String keyword2) {
				try {
					PreparedStatement ps = getConnection().prepareStatement(
							"select * from 选课信息表  where " + item1 + "=?"+"and "+ item2 + "=?");
					ps.setString(1, keyword1);
					ps.setString(2, keyword2);
					ResultSet rs = ps.executeQuery();
					return rs;
				} catch (Exception re) {
					re.printStackTrace();
					return null;
				}
			}
			
			// 为PreparedStatement赋值参数, 该方法是为了避免save方法给PreparedStatement
			// 代码赋值时出现重复代码而将相同的部分抽取出来放到该方法中
			public void setPreparedStatementValues(PreparedStatement ps, StudentCourse studentCourse) {
				try {
					if (studentCourse.getStudentId() != null)
						ps.setString(1, studentCourse.getStudentId());
					else
						ps.setNull(1, Types.LONGNVARCHAR);
					if (studentCourse.getCourseId() != null)
						ps.setString(2, studentCourse.getCourseId());
					else
						ps.setNull(2, Types.LONGNVARCHAR);
					if (studentCourse.getScore() != null)
						ps.setString(3, studentCourse.getScore());
					else
						ps.setNull(3, Types.LONGNVARCHAR);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// 保存成绩信息,如果有已存在相同学号的则更新对应记录的信息
			public void save(StudentCourse studentCourse) {
				try {
					Connection conn = getConnection(); // 取得数据库连接
					String sql = null;
					PreparedStatement ps = null;
					// 如果重复, 执行更新操作
					if (isExist("学号", studentCourse.getStudentId(),"课程编号",studentCourse.getCourseId())) {
						sql = "update 选课信息表 set 学号=?, 课程编号=?,成绩=? where 学号=? and 课程编号=?";
						ps = conn.prepareStatement(sql);
						ps.setString(4, studentCourse.getStudentId());
						ps.setString(5, studentCourse.getCourseId());
						setPreparedStatementValues(ps, studentCourse);// setPreparedStatementValues方法为PreparedStatement中参数赋值
					} else {
						sql = "insert into 选课信息表(学号,课程编号,成绩) values(?, ?, ?)";
						ps = conn.prepareStatement(sql);
						setPreparedStatementValues(ps, studentCourse);
					}
					ps.executeUpdate();
					ps.close();
				} catch (Exception re) {
					re.printStackTrace();
				}
			}
			
			// 删除
			public void delete(String studentId,String courseId) {
				try {
					PreparedStatement ps = getConnection().prepareStatement(
							"delete from 选课信息表  where 学号=? and 课程编号=?");
					ps.setString(1, studentId);
					ps.setString(2, courseId);
					ps.executeUpdate();
					ps.close();
				} catch (Exception re) {
					re.printStackTrace();
				}
			}
			
			// 判断某个属性值是否重复
			public boolean isExist(String item, String keyword) throws SQLException {
				ResultSet result;
				result = search(item, keyword);
				if (result.next()) {
					result.previous();
					return true;
				} else
					return false;
			}
			
			// 判断某个属性值是否重复
			public boolean isExist(String item1, String keyword1,String item2, String keyword2) throws SQLException {
				ResultSet result;
				result = search(item1, keyword1,item2, keyword2);
				if (result.next()) {
					result.previous();
					return true;
				} else
					return false;
			}
}
