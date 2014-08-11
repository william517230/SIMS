package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import model.Student;

//学生信息数据库连接
public class StudentDAO extends AbstractDAO {

	// 按特定属性查询，返回符合条件的Student对象
	public ResultSet search(String item, String keyword) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(
					"select * from 基本信息表 where " + item + "=?");
			ps.setString(1, keyword);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}

	}

	// 保存学生信息,如果有已存在相同学号的则更新对应记录的信息
	public void save(Student student) {
		try {
			Connection conn = getConnection(); // 取得数据库连接
			String sql = null;
			PreparedStatement ps = null;
			// 如果学号不重复, 执行更新操作
			if (isExist("学号", student.getStudentId())) {
				sql = "update 基本信息表 set 学号=?, 姓名=?,性别=?,生日=? ,身份证号=?,年级=?,学院=?,专业=?,班号=?,头像路径=? where 学号=?";
				ps = conn.prepareStatement(sql);
				ps.setString(11, student.getStudentId());
				setPreparedStatementValues(ps, student);// setPreparedStatementValues方法为PreparedStatement中参数赋值
			} else {
				sql = "insert into 基本信息表(学号,姓名,性别,生日,身份证号,年级,学院,专业,班号,头像路径) values(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
				ps = conn.prepareStatement(sql);
				setPreparedStatementValues(ps, student);
			}
			ps.executeUpdate();
			ps.close();
		} catch (Exception re) {
			re.printStackTrace();
		}
	}

	// 为PreparedStatement赋值参数, 该方法是为了避免save方法给PreparedStatement
	// 代码赋值时出现重复代码而将相同的部分抽取出来放到该方法中
	public void setPreparedStatementValues(PreparedStatement ps, Student student) {
		try {
			if (student.getStudentId() != null)
				ps.setString(1, student.getStudentId());
			else
				ps.setNull(1, Types.LONGNVARCHAR);
			if (student.getName() != null)
				ps.setString(2, student.getName());
			else
				ps.setNull(2, Types.LONGNVARCHAR);
			if (student.getGender() != null)
				ps.setString(3, student.getGender());
			else
				ps.setNull(3, Types.LONGNVARCHAR);
			if (student.getBirthday() != null)
				ps.setString(4, student.getBirthday());
			else
				ps.setNull(4, Types.LONGNVARCHAR);
			if (student.getId() != null)
				ps.setString(5, student.getId());
			else
				ps.setNull(5, Types.LONGNVARCHAR);
			if (student.getGrade() != null)
				ps.setString(6, student.getGrade());
			else
				ps.setNull(6, Types.LONGNVARCHAR);
			if (student.getDepartment() != null)
				ps.setString(7, student.getDepartment());
			else
				ps.setNull(7, Types.LONGNVARCHAR);
			if (student.getMajor() != null)
				ps.setString(8, student.getMajor());
			else
				ps.setNull(8, Types.LONGNVARCHAR);
			if (student.getClassNo() != null)
				ps.setString(9, student.getClassNo());
			else
				ps.setNull(10, Types.LONGNVARCHAR);
			if (student.getImageDir() != null)
				ps.setString(10, student.getImageDir());
			else
				ps.setNull(10, Types.LONGNVARCHAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除
	public void delete(String studentId) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(
					"delete from 基本信息表 where 学号=?");
			ps.setString(1, studentId);
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
