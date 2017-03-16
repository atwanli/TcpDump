package com.mysoft.test.mybatisplus;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springmvc.model.User;
import com.baomidou.springmvc.service.IUserService;

/**  
* @Title: TestMybatisPlug.java
* @Package com.mysoft.test.mybatisplus
* @Description: TODO()
* @author wwl 
* @date 2016年11月28日 下午2:33:20
* @version V1.0  
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml" })
public class TestMybatisPlug {

	@Resource
	private IUserService userServiceImpl;
	@Test 
	public void sava(){
		Page page=new Page();
//		List<User> userList=userServiceImpl.selectList(null);
		page.setCurrent(0);
		page.setSize(2);
		Page p=userServiceImpl.selectPage(page);
		System.out.println(p);
	}
}

