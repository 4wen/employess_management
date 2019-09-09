package com.test;

import com.github.pagehelper.PageInfo;
import com.sup.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @Author: superS4n
 * @CreateTime: 2019-04-26 21:02
 * @Description: 测试CRUD请求
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springMVC.xml"})
public class MvcTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
       mockMvc =  MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public  void testPage() throws Exception {
        //模拟请求，拿到返回值
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emp/getemps").param("pn","1")).andReturn();

       //验证返回值
      MockHttpServletRequest request = result.getRequest();
      PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
      System.out.println("当前页码"+pageInfo.getPageNum());
      System.out.println("总页码"+pageInfo.getPages());
      System.out.println("总记录数"+pageInfo.getTotal());

      //获取员工数据
        List<Employee> employees = pageInfo.getList();

        for (Employee employee : employees) {
            System.out.println("ID--"+employee.getdId()+"==>姓名--"+employee.getEmpName());
        }

    }
}
