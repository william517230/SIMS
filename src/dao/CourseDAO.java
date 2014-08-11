package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.Course;

public class CourseDAO extends AbstractDAO{
	// 按特定属性查询，返回符合条件的Course对象
		public ResultSet search(String item, String keyword) {
			try {
				PreparedStatement ps = getConnection().prepareStatement(
						"select * from 课程表 where " + item + "=?");
				ps.setString(1, keyword);
				ResultSet rs = ps.executeQuery();
				return rs;
			} catch (Exception re) {
				re.printStackTrace();
				return null;
			}
		}
		
		// 为PreparedStatement赋值参数, 该方法是为了避免save方法给PreparedStatement
		// 代码赋值时出现重复代码而将相同的部分抽取出来放到该方法中
		public void setPreparedStatementValues(PreparedStatement ps, Course course) {
			try {
				if (course.getCourseId() != null)
					ps.setString(1, course.getCourseId());
				else
					ps.setNull(1, Types.LONGNVARCHAR);
				if (course.getCourseName() != null)
					ps.setString(2, course.getCourseName());
				else
					ps.setNull(2, Types.LONGNVARCHAR);
				if (course.getCredit() != null)
					ps.setString(3, course.getCredit());
				else
					ps.setNull(3, Types.LONGNVARCHAR);
				if (course.getCreditHours() != null)
					ps.setString(4, course.getCreditHours());
				else
					ps.setNull(4, Types.LONGNVARCHAR);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 保存课程信息,如果有已存在相同学号的则更新对应记录的信息
		public void save(Course course) {
			try {
				Connection conn = getConnection(); // 取得数据库连接
				String sql = null;
				PreparedStatement ps = null;
				// 如果课程号不重复, 执行更新操作
				if (isExist("课程编号", course.getCourseId())) {
					sql = "update 课程表 set 课程编号=?, 课程名称=?,学分=?,学时=? where 课程编号=?";
					ps = conn.prepareStatement(sql);
					ps.setString(5, course.getCourseId());
					setPreparedStatementValues(ps, course);// setPreparedStatementValues方法为PreparedStatement中参数赋值
				} else {
					sql = "insert into 课程表(课程编号,课程名称,学分,学时) values(?, ?, ?, ?)";
					ps = conn.prepareStatement(sql);
					setPreparedStatementValues(ps, course);
				}
				ps.executeUpdate();
				ps.close();
			} catch (Exception re) {
				re.printStackTrace();
			}
		}
		
		// 删除
		public void delete(String courseId) {
			try {
				PreparedStatement ps = getConnection().prepareStatement(
						"delete from 课程表  where 课程编号=?");
				ps.setString(1, courseId);
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
}
