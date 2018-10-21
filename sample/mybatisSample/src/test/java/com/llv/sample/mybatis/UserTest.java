package com.llv.sample.mybatis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

	@Test
	public void testFindUser() throws IOException {/*
		List<User> users = DBAccess.getSqlSession().selectList("find");
		Assert.assertEquals("lewis", users.get(0).getName());
	*/}

	@Test
	public void testFindUserByJDBC() throws ClassNotFoundException, SQLException {/*
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "root");
		ResultSet rs = conn.prepareStatement("select name from user").executeQuery();
		while (rs.next()) {
			Assert.assertEquals("lewis", rs.getString(1));
		}
	*/}

}
