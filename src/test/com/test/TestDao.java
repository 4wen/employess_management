package com.test;

import com.sup.dao.DepartmentMapper;
import com.sup.dao.EmployeeMapper;
import com.sup.model.Department;
import com.sup.model.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

/**
 * @Author: superS4n
 * @CreateTime: 2019-04-26 16:16
 * @Description: 使用spring单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDao {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;

    /**
     * 测试 DepartmentMapper
     */
    @Test
    public void testCRUD() {
       // System.out.println(departmentMapper);

//      //1.插入几个部门
      departmentMapper.insertSelective(new Department(null,"产品部"));

/*      //2.测试插入员工
        Employee employee = new Employee();
        employee.setEmpName("周周");
        employee.setGender("M");
        employee.setEmail("zhouzhou@sup.com");
        employee.setdId(1);
        employeeMapper.insertSelective(employee);

*/

/*        //3.批量插入
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 10; i++) {
            String uid = UUID.randomUUID().toString().substring(0,5)+i;

            mapper.insertSelective(new Employee(null,uid,"M",uid+"@sup.com",1));
        }*/

       /*List<Employee> employees =  employeeMapper.selectByExampleWithDept(null);
        for (Employee employee : employees) {
            System.out.println(employee);
        }*/

    }



}
