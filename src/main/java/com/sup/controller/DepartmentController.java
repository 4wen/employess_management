package com.sup.controller;


import com.sup.model.Department;
import com.sup.service.DepartmentService;
import com.sup.unit.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Author: superS4n
 * @CreateTime: 2019-04-29 09:00
 * @Description: 部门控制器
 *
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门信息
     * @return
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> departments = departmentService.getDepts();

        return Msg.success().add("departments",departments);
    }




}
