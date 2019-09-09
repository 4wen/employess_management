package com.sup.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sup.model.Employee;
import com.sup.service.EmployeeService;
import com.sup.unit.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: superS4n
 * @CreateTime: 2019-04-26 19:50
 * @Description: 处理员工 CRUD 请求
 */
@Controller
public class EmployController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 获取所有员工 使用 @ResponseBody 直接返回 json 数据
     * 以下为  前后端 不 分离
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn) {
            //1.引入pagehelper 插件 , 传入页码以及每页的大小
            PageHelper.startPage(pn,5);
            //2.从数据库中查询出所有员工
            List<Employee> employees = employeeService.getAll();
            //3.使用PageInfo包装查询出来的结果,PageInfo封装了详细的分页信息。
            PageInfo pageInfo = new PageInfo(employees,5);

            return  Msg.success().add("pageInfo",pageInfo);


    }
/*
*
     * 获取所有员工 普通的形式
     * @return
 */
    @RequestMapping("/getempswithoutajax")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model) {
        //1.引入pagehelper 插件 , 传入页码以及每页的大小
        PageHelper.startPage(pn,5);
        //2.从数据库中查询出所有员工
        List<Employee> employees = employeeService.getAll();
        //3.使用PageInfo包装查询出来的结果,PageInfo封装了详细的分页信息。
        PageInfo pageInfo = new PageInfo(employees,5);
        //4.将PageInfo传回页面
        model.addAttribute("pageInfo",pageInfo);

        return "list";
    }


    /**
     * 员工保存
     * @return  Msg json数据
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if(result.hasErrors()){
            //校验失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }

        employeeService.saveEmp(employee);
        return Msg.success();

    }

    /**
     * 检查员工姓名是否重复,可用
     * @param empName
     * @return  Msg json数据
     */
    @RequestMapping("/checkname")
    @ResponseBody
    public Msg checkName(@RequestParam("empName") String empName) {
        //先判断员工名是否符合 正则表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regx)){
            return Msg.fail().add("fail_msg","员工名必须是2-5位中文或者6-16位英文和数字的组合");
        }

        // 数据库员工名 查重
        boolean b = employeeService.checkName(empName);
        if(b){
            return Msg.success();
        }else{
            return Msg.fail().add("fail_msg","已有该员工");
        }
    }

    /**
     * 根据Id 查出员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);

        return Msg.success().add("emp",employee);
    }

    /**
     * 更新员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee) {

        employeeService.updateEmp(employee);

        return Msg.success();
    }


    /**
     * 删除员工
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmpByid(@PathVariable("ids")String ids){

        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }

}
