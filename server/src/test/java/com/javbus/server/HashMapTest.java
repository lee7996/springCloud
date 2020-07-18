package com.javbus.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * HashMap遍历
 * @author Lee
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)  // 模块启动类
@WebAppConfiguration
@ContextConfiguration
public class HashMapTest {

	@Autowired
	private WebApplicationContext context; // 用于controller测试
	private MockMvc mvc;
	
	@Before
	public void setUp() throws Exception {
	        mvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .build();
	}
	/*
	 * 在测试类上添加注解@Rollback，同时测试方法加上注解@Transactional。
	 * 如果不希望回滚 将rollback改为false即可
	 * 
	 * 注意：我们大多数的时候是往控制层controller传递的不是一个简单的参数，可能是一个Map或一个实体类，发现怎么都传不过去，解决方法如下：
	 * 在发送请求的时候，我们使用json的参数格式 
	 */
	@Test
	public void contextLoads() throws Exception {
	    MvcResult 
	//groupManager访问路径
	//param传入参数
	result=mvc.perform(MockMvcRequestBuilders.post("/groupManager").param("pageNum","1").param("pageSize","10")).andReturn();
	    MockHttpServletResponse response = result.getResponse();
	    String content = response.getContentAsString();
//	    List<JtInfoDto> jtInfoDtoList = GsonUtils.toObjects(content, new TypeToken<List<JtInfoDto>>() {}.getType());
//	    for(JtInfoDto infoDto : jtInfoDtoList){
//	        System.out.println(infoDto.getJtCode());
//	    }
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 以上为controller测试
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("3", "value3");
		map.put("4", "value4");
		map.put(null, null);
		
		// 迭代器 EntrySet
		System.out.println("EntrySet >>>>>>>>>>>>>");
		Iterator<Map.Entry<String, String>> iterES = map.entrySet().iterator();
		while (iterES.hasNext()) {
			Map.Entry<String, String> entry = iterES.next();
			System.out.print(entry.getKey() + " : ");
			System.out.println(entry.getValue());
		}
		
		// 迭代器 KeySet
		System.out.println("KeySet >>>>>>>>>>>>>");
		Iterator<String> iterKS = map.keySet().iterator();
		while (iterKS.hasNext()) {
			String key = (String) iterKS.next();
			System.out.print(key + " : ");
			System.out.println(map.get(key));
		}
		
		// ForEach EntrySet
		System.out.println("ForEach EntrySet >>>>>>>>>>>>>");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getKey() + " : ");
			System.out.println(entry.getValue());
		}
		
		// ForEach KeySet
		System.out.println("ForEach KeySet >>>>>>>>>>>>>");
		for (String key : map.keySet()) {
			System.out.print(key + " : ");
			System.out.println(map.get(key));
		}
		
		// Lambda
		System.out.println("Lambda >>>>>>>>>>>>>");
		map.forEach((key, value) -> {
			System.out.print(key + " : ");
			System.out.println(value);
		});
		
		// Streams API 单线程
		System.out.println("Streams API 单线程 >>>>>>>>>>>>>");
		map.entrySet().stream().forEach((entry) -> {
			System.out.print(entry.getKey() + " : ");
			System.out.println(entry.getValue());
		});
		
		// Streams API 多线程
		System.out.println("Streams API 多线程 >>>>>>>>>>>>>");
		map.entrySet().parallelStream().forEach((entry) -> {
			System.out.print(entry.getKey() + " : ");
			System.out.println(entry.getValue());
		});
		
	}
}
