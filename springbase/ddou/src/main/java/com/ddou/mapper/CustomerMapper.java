package com.ddou.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.ddou.pojo.Customer;

public interface CustomerMapper {

	@Update("create table customer(id int not null primary key auto_increment,name varchar(40) not null,phone varchar(40) not null,email varchar(80) not null,password varchar(40))ENGINE=InnoDB DEFAULT CHARSET=utf8;")
	void schema();

	@Select({ "select * from customer where id = #{id}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR) })

	public Customer getById(int id);

	@Select("select count(*) from customer")
	long count();

	@Insert({ "insert into customer (name,phone,email,password)", "values (#{name}, #{phone},#{email},#{password})" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int add(Customer customer);

	@Delete({ "delete from customer where id = #{id}" })
	public void delete(int id);

	@Update({ "update customer", "set name = #{name}", "where id = #{id}" })
	public int update(Customer customer);
}
