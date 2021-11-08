package com.wyw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyw.pojo.Book;
import com.wyw.pojo.Borrow;
import com.wyw.pojo.Student;
import com.wyw.service.BookService;
import com.wyw.service.BorrowService;
import com.wyw.service.ReturnService;
import com.wyw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("Student")
public class StudentController {

    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentService studentService;

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @Autowired
    @Qualifier("BorrowServiceImpl")
    private BorrowService borrowService;

    @Autowired
    @Qualifier("ReturnServiceImpl")
    private ReturnService returnService;

    @RequestMapping("/toStuLogin")
    public String toStuLogin() {

        return "StuLogin";
    }

    @RequestMapping("/selfinfo")
    public String toSelfInfo(Model model, Integer submit, String password) {
//        判断前端传入值是否为空
        if (submit == null) {
            model.addAttribute("noner", "id为空");
            return "StuLogin";
        }
        if (password == null) {
            model.addAttribute("noner", "密码为空");
            return "StuLogin";
        }
//判断输人的是否能在数据库查找到
        Student student = studentService.searchStudentById(submit);
        if (student == null) {
            model.addAttribute("noner", "未查找到用户");
            return "StuLogin";
        } else if (!student.getSPassword().equals(password)) {
            model.addAttribute("noner", "密码错误");
            return "StuLogin";
        }
        model.addAttribute("info", student);
        return "selfInfo";
    }


    @RequestMapping("/toUpdateStudent/{rId}")
    public String toUpdateStudent(Model model, @PathVariable("rId") int id) {
        Student student = studentService.searchStudentById(id);
        model.addAttribute("updateStu", student);
        return "updateStudent";
    }


    @RequestMapping("/updateStudent")
    public String updateStudent(Student student, Model model) {
        studentService.updateStudent(student);
        studentService.searchStudentById(student.getSId());
        model.addAttribute("info", student);
//        带有条件的不需要重定向，直接指向它的虚拟地址即可.具体对比可与EX9工程下的controller里的updateProvider对比,redirect指向虚拟地址，return直接指向jsp
        return "selfInfo";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "RegisterStudent";
    }

    @RequestMapping("/registerStu")
    public String registerStu(Student student, Model model) {
//判断注册的学生不能传入空值
        if (student.getSId() == null) {

            model.addAttribute("error", "输入的id不能为空");
            return "RegisterStudent";
        }
        if (student.getSName() == "") {
            model.addAttribute("error", "注册的读者姓名不能为空");
            return "RegisterStudent";
        }
        if (student.getSPassword() == "") {
            model.addAttribute("error", "注册的密码不能为空");
            return "RegisterStudent";
        }
        if (student.getSGender() == null) {
            model.addAttribute("error", "注册的性别不能为空");
            return "RegisterStudent";
        } else {
//判断数据库中是否已有这个学生被注册过
            List<Student> studentList = studentService.ShowAllByBounds();
            for (int i = 0; i < studentList.size(); i++) {
                if (student.getSId().equals(studentList.get(i).getSId()) ) {
                    model.addAttribute("error", "注册的id已经被注册过了");
                    return "RegisterStudent";
                }
            }
            studentService.addStudent(student);
            return "StuLogin";
        }

    }

    @RequestMapping("/toStuAllBook")
    public String toStuAllBook(Integer sid, String sname, Model model, @RequestParam(value = "pageNum", required = true, defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<Book> books = bookService.queryBook();
        PageInfo pageInfo = new PageInfo(books);
        model.addAttribute("sId", sid);
        model.addAttribute("sName", sname);
        model.addAttribute("pageBook", books);
        model.addAttribute("pageInfo", pageInfo);
        return "stuAllBook";
    }

    @RequestMapping("backSelfInfo")
    public String backSelfInfo(int psId,Model model){
        Student student = studentService.searchStudentById(psId);
        model.addAttribute("info", student);
        return "selfInfo";
    }

    @RequestMapping("/queryStuBookByName/{sId}/{sName}")
    public String queryStuBookByName(Model model, String searchBookName, @PathVariable("sId") Integer sid, @PathVariable("sName") String sname) {
        List<Book> books = bookService.queryBookByName(searchBookName);
        int num = 0;
        for (Book bo : books
        ) {
            num++;
        }
//        判断前端是否输入空值
        if (searchBookName == "") {
            model.addAttribute("error", "要查询的书名不能为空");
            return "stuAllBook";
        }
//        编例数据库未找到要查询你的书籍
        if (num == 0) {
            model.addAttribute("error", "未找到你要查询的书籍");
            return "stuAllBook";
        }
        model.addAttribute("pageBook", books);
        model.addAttribute("sId", sid);
        model.addAttribute("sName", sname);
        return "stuAllBook";
    }


    @RequestMapping("/borrowBook/{bid}/{sId}/{sName}")
    public String borrowBook(Model model, @PathVariable("bid") int bid, @PathVariable("sId") Integer sid, @PathVariable("sName") String sname) throws UnsupportedEncodingException {
        bookService.borrowBook(bid);
        Date borrowDate = new Date();
        Calendar now = Calendar.getInstance();
//        利用calendar对象对时间进行操作
        now.setTime(borrowDate);
        now.add(Calendar.MONTH, 2);
//        如果用户一点击借书,那么借书时间就是用户点击时间,而归还时间就是规定两个月之后
        Date returnDate = now.getTime();
//        生成一个借阅对象,将值传入
        Borrow bor = new Borrow(sid, sname, bid, borrowDate, returnDate);
        List<Borrow> borrows = borrowService.QuerryAllBorrow();
        for (int i=0;i<borrows.size();i++){
//            我的数据库规定一个人对一本书只能借一本,不能多借,但可以借不同种类的书,如果要借同一本书那么就报错
            if ((bor.getRbId().equals(borrows.get(i).getRbId()))&&(bor.getRId().equals(borrows.get(i).getRId()))){
                model.addAttribute("error","您已经借过该书了，请勿重复借同一本书");

                return "stuAllBook";
            }
        }
        borrowService.addBorrow(bor);
        Student student = studentService.searchStudentById(sid);
        model.addAttribute("info", student);
        sname= URLEncoder.encode(sname,"UTF-8");
        return "redirect:/Student/toStuAllBook?sid="+sid+"&sname="+sname;
    }


//    @RequestMapping("/backBook/{sid}/{bname}")
//    public String backBook(Model model,@PathVariable("sid") int id,@PathVariable("bname") String name,int psId,String psPwd){
////        获取在book表中的书对象
//        Book book = bookService.SpecificQuerryBookByName(name);
////      为了删除借阅表做准备
//        HashMap map1 = new HashMap();
//        map1.put("rId",id);
//        map1.put("rbId",book.getBId());
////        为删除催还表做准备
//        HashMap map2 = new HashMap();
//        map2.put("brId",id);
//        map2.put("brbName",name);
//        bookService.backBook(book.getBId());
//        returnService.deleteReturnByIdAndName(map2);
//        borrowService.deleteBorrowByRidAndRbid(map1);
//        return "redirect:/Student/selfinfo/?submit="+psId+"&password="+psPwd;
//    }





}