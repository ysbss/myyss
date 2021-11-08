package com.wyw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyw.pojo.Administer;
import com.wyw.pojo.Student;
import com.wyw.service.AdministerService;
import com.wyw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("Administer")
public class AdministerController {

    @Qualifier("AdministerServiceImpl")
    @Autowired
    private AdministerService administerService;

    @Qualifier("StudentServiceImpl")
    @Autowired
    private StudentService studentService;

    @RequestMapping("/toAdminLogin")
    public String toAdminLogin(){
        return "AdminLogin";
    }

    @RequestMapping("/toAdminFuc")
    public String toAdminFunction(Model model, Integer submit, String password){


//        判断前端传来的值是不是空值
        if (submit==null){
            model.addAttribute("noner","id为空");
            return "AdminLogin";
        }
//        判断前端传来的值是不是空值
        if (password==null){
            model.addAttribute("noner","密码为空");
            return "AdminLogin";
        }
//        通过id查询数据库有没有这个管理员，没有就提示用户
        Administer administer = administerService.searchAdminById(submit);
        if(administer==null){
            model.addAttribute("noner","未查找到用户");
            return "AdminLogin";
        }
//        提示密码
        else if (!administer.getAPassword().equals(password)){
            model.addAttribute("noner","密码错误");
            return "AdminLogin";
        }
        model.addAttribute("aid",submit);
        model.addAttribute("apwd",password);
        return "AdminFunction";
    }

    @RequestMapping("/backAdminFuc")
    public String backAdminFuc(Integer submit, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("aid",submit);
        return "AdminFunction";
    }

    @RequestMapping("/toAllStu/{aid}")
    public String ShowAllStu(HttpServletRequest request,@PathVariable("aid") int ad,Model model, @RequestParam(value = "pageNum", required = true, defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<Student> students = studentService.ShowAllByBounds();
        HttpSession session = request.getSession();
        PageInfo pageInfo = new PageInfo(students);
        model.addAttribute("pageStudents", students);
        model.addAttribute("pageInfo", pageInfo);
        session.setAttribute("stuAid",ad);
        return "AllStu";
    }



    @RequestMapping("/toAddStu")
    public String toAddStu(Model model,int aid) {
        model.addAttribute("paid",aid);
        return "AddStu";
    }

    @RequestMapping("/addStu")
    public String addStu(Model model, Student student,int aid) {

//        判断前端增加的学生没有空值传入
        if (student.getSId() == null) {
            model.addAttribute("error", "输入的id不能为空");
            model.addAttribute("paid",aid);
            return "AddStu";
        }
        if (student.getSName() == "") {
            model.addAttribute("error", "注册的读者姓名不能为空");
            model.addAttribute("paid",aid);
            return "AddStu";
        }
        if (student.getSPassword() == "") {
            model.addAttribute("error", "注册的密码不能为空");
            model.addAttribute("paid",aid);
            return "AddStu";
        }
        if (student.getSGender() == null) {
            model.addAttribute("error", "注册的性别不能为空");
            model.addAttribute("paid",aid);
            return "AddStu";
        } else {
//判断注册的是否在数据库内被注册过
            List<Student> studentList = studentService.ShowAllByBounds();
            for (int i = 0; i < studentList.size(); i++) {
                if (student.getSId().equals(studentList.get(i).getSId()) ) {
                    model.addAttribute("error", "注册的id已经被注册过了");
                    model.addAttribute("paid",aid);
                    return "AddStu";
                }
            }
            studentService.addStudent(student);
            return "redirect:/Administer/toAllStu/"+aid;
        }
    }


    @RequestMapping("/AdminToUpdateStu/{sId}")
    public String toAdminUpdateStu(Model model, @PathVariable("sId") int id,int aid){
        Student student = studentService.searchStudentById(id);
        model.addAttribute("pageStudent",student);
        model.addAttribute("paid",aid);
        return "AdminUpdateStu";
    }

    @RequestMapping("/AdminUpdateStu")
    public  String AdminUpdateStu(Student student,Model model,int aid){
        studentService.updateStudent(student);

        return "redirect:/Administer/toAllStu/"+aid;
    }



    @RequestMapping("/AdminDeleteStu/{sid}")
    public  String AdminDeleteStu(@PathVariable("sid") int id,int aid){
        studentService.deleteStudentById(id);
        return "redirect:/Administer/toAllStu/"+aid;
    }

    @RequestMapping("/querryStuByName")
    public  String querryStuByName(Model model,String searchStuName){
        List<Student> studentList = studentService.querryStuByName(searchStuName);
        int num=0;
        for (Student stu:studentList
             ) {
            num++;
        }
//        判断前端是否输入空值
        if (searchStuName==""){
            model.addAttribute("error","要查询的人名不能为空");
            return "AllStu";
        }
//        判断数据库内是否有这个人
        if (num==0){
            model.addAttribute("error","未找到你要查询的人名");
            return "AllStu";
        }

model.addAttribute("pageStudents",studentList);
        return "AllStu";
    }
}
