package com.sup.service;

import com.sup.dao.DepartmentMapper;
import com.sup.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: superS4n
 * @CreateTime: 2019-04-29 09:02
 * @Description: ${Description}
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
