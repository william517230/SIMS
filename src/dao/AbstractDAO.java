package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//抽象dao类,负责connection维护以及其他的通用方法
public abstract class AbstractDAO {
	private static Connection connection = null;

	// 与数据库建立连接
	public static Connection getConnection() throws SQLException,
			java.lang.ClassNotFoundException {
		if (connection == null) {
			String url = "jdbc:mysql://localhost:3306/sims";
			Class.forName("com.mysql.jdbc.Driver");
			String userName = "root";
			String password = "";
			connection = DriverManager.getConnection(url, userName, password);
		}
		return connection;
	}

	// 销毁connection
	public static void destroyConnection() {
		try {
			getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 以拼接sql的方式实现多条件查询sql
	protected String addCriteria(String sql, String criteria) {
		if (criteria.indexOf("null") == -1) {// 如果criteria中出现null字串,说明不需要以该条件查询
			if (sql.indexOf("where") != -1) {// 如果sql有where字串说明字串已经有至少一个查询条件后面的条件应以and串接
				return sql + " and " + criteria;
			} else {
				return sql + " where " + criteria;
			}
		} else {
			return sql;
		}
	}
}
