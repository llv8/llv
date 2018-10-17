package com.llv.sample.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAccess {
	public static SqlSession getSqlSession() throws IOException {
		SqlSessionFactoryBuilder build = new SqlSessionFactoryBuilder();
		InputStream is = DBAccess.class.getClassLoader().getResourceAsStream("config.xml");
		SqlSessionFactory factory = build.build(is);
		return factory.openSession();
	}
}
