package com.sup.service;

import com.sup.dao.EmployeeMapper;
import com.sup.model.Employee;
import com.sup.model.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: superS4n
 * @CreateTime: 2019-04-26 19:57
 * @Description: 服务层
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检查员工名 是否已经存在
     * @param empName
     * @return 返回true或者false
     */
    public boolean checkName(String empName){
        EmployeeExample example = new EmployeeExample();

        EmployeeExample.Criteria criteria = example.createCriteria();

        criteria.andEmpNameEqualTo(empName);

        long count = employeeMapper.countByExample(example);

        return count == 0;
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 员工更新 根据主键有选择的更新
     * @param employee
     */
    public void updateEmp(Employee employee){

        employeeMapper.updateByPrimaryKeySelective(employee);

    }

    /**
     * 删除员工
     */
    public void deleteEmp(Integer id){
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> del_ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();

        criteria.andEmpIdIn(del_ids);

        employeeMapper.deleteByExample(example);
    }
}
