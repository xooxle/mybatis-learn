package me.huqiao.learn.mybatis;

import java.io.InputStream;

import me.huqiao.learn.mybatis.dao.IOrderDao;
import me.huqiao.learn.mybatis.entity.Order;
import me.huqiao.learn.mybatis.entity.OrderDetail;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class OrderDaoTest {

	SqlSessionFactory sessionFactory;
	
	@Before
	public void setUp()throws Exception{
		InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
		sessionFactory = new SqlSessionFactoryBuilder().build(is);
		is.close();
	}
	
	
	@Test
	public void testFindById(){
		SqlSession sqlSession = sessionFactory.openSession();
		IOrderDao orderDao = sqlSession.getMapper(IOrderDao.class);
		Order order = orderDao.findById(3);
		System.out.println(order.getId() + "," + order.getCreateTime());
		System.out.println(order.getUser());
		for(OrderDetail detail : order.getDetails()){
			System.out.println(detail);
		}
		sqlSession.close();
	}
	
}
