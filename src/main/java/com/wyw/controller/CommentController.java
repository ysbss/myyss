package com.wyw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyw.pojo.Administer;
import com.wyw.pojo.Comment;
import com.wyw.pojo.Student;
import com.wyw.service.AdministerService;
import com.wyw.service.CommentService;
import com.wyw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("Comment")
public class CommentController {

    @Qualifier("CommentServiceImpl")
    @Autowired
    private CommentService commentService;

    @Qualifier("AdministerServiceImpl")
    @Autowired
    private AdministerService administerService;

    @Qualifier("StudentServiceImpl")
    @Autowired
    private StudentService studentService;

    @RequestMapping("/toAllComment")
    public String toAdminAllComment(Integer submit,Model model,@RequestParam(value = "pageNum",required = true,defaultValue = "1")int pageNum){
        PageHelper.startPage(pageNum,3);
        List<Comment> comments = commentService.QuerryAllComment();
        PageInfo pageInfo=new PageInfo(comments);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageComment",comments);

        model.addAttribute("paid",submit);
        model.addAttribute("apaid",submit);
        return "AdminAllComment";
    }





    @RequestMapping("/AdminDeleteComment/{cid}/{cword}")
    public String AdminDeleteComment(@PathVariable("cid") int pcid,@PathVariable("cword") String pcword){
        HashMap map = new HashMap();
        map.put("cId",pcid);
        map.put("cWord",pcword);
        commentService.deleteCommentByIdAmdWord(map);
        return "redirect:/Comment/toAllComment";
    }

    @RequestMapping("/toAdminUpdateComment/{uCid}/{uCword}")
    public String toAdminUpdateComment(int aid,Model model,@PathVariable("uCid") int pcid,@PathVariable("uCword") String pcword){
        HashMap map = new HashMap();
//        添加的参数key值建议与数据库名pojo名一致
        map.put("cId",pcid);
        map.put("cWord",pcword);
        Comment comment = commentService.SpecificQuerryCommentByIdAndWord(map);
        model.addAttribute("upComment",comment);
        model.addAttribute("paid",aid);
        return "AdminUpdateComment";
    }

    @RequestMapping("/AdminUpdateComment")
    public String AdminUpdateComment(Comment comment){
commentService.updateComment(comment);
        return "redirect:/Comment/toAllComment";
    }



    @RequestMapping("/AdminAddComment")
    public  String AdminAddComment(Model model,Integer said,String cWord,HttpServletRequest request){
//判断前端是否传入空值
        if (cWord==""){
            model.addAttribute("error1","请输入发言");
            return "AdminAllComment";
        }
        HttpSession session = request.getSession();
        Administer administer = administerService.searchAdminById(said);
        if (administer==null){

            model.addAttribute("error1","没有这个管理员");
            return "AdminAllComment";
        }
//        由于是管理员所以固定identity设为1，而发言日期是当下立即生成故new date
        Comment comment = new Comment(said,1,cWord,new Date());
        commentService.addComment(comment);
        session.setAttribute("paid",said);
        session.setAttribute("apaid",said);
        return "redirect:/Comment/toAllComment";
    }



    @RequestMapping("/AdminQuerryCommentById")
    public String AdminQuerryCommentById(Integer submit,Model model,String searchCommentId,HttpServletRequest request) {

//判断前端输入的值是否为空
        if (searchCommentId == "") {
            model.addAttribute("error", "不要输入空的id");
            return "AdminAllComment";
        }
        int anInt = 0;
//        将前端传入的string值转化为int
        if (searchCommentId != null && searchCommentId.equals("")) {
            anInt = Integer.parseInt(searchCommentId);
        }
//判断传入的值是否为纯数字
        for (int i = 0; i < searchCommentId.length(); i++) {
            if (!Character.isDigit(searchCommentId.charAt(i))) {
                model.addAttribute("error", "请按规范输入id");
                return "AdminAllComment";
            }
        }
        int num = 0;
        anInt = Integer.parseInt(searchCommentId);
        List<Comment> comments = commentService.QuerryCommentById(anInt);
//        判断数据库是否有这个人
        for (Comment comment : comments
        ) {
            num++;
        }
        if (num == 0) {
            model.addAttribute("error", "此人未发布留言");
            return "AdminAllComment";
        }
        HttpSession session = request.getSession();
        model.addAttribute("pageComment", comments);
        session.setAttribute("paid",anInt);
        session.setAttribute("apaid",submit);
        return "AdminAllComment";
    }



    @RequestMapping("/toStuAllComment")
    public String toStuAllComment(Integer sid, String sname,Model model,@RequestParam(value = "pageNum",required = true,defaultValue = "1")int pageNum){
        PageHelper.startPage(pageNum,3);
        List<Comment> comments = commentService.QuerryAllComment();
        PageInfo pageInfo=new PageInfo(comments);
        model.addAttribute("sId",sid);
        model.addAttribute("sName",sname);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageComment",comments);

        return "StuAllComment";
    }


    @RequestMapping("/StuAddComment")
    public String StuAddComment(Model model, Integer paid, String cWord, HttpServletRequest request){

//        判断是否传入空值
        if (cWord==""){
            model.addAttribute("error1","请输入发言");
            model.addAttribute("sId",paid);
            return "StuAllComment";
        }
        HttpSession session = request.getSession();
        Student student = studentService.searchStudentById(paid);
//        判断是否在数据库是否有这个学生
        if (student==null){
            model.addAttribute("error1","没有这个学生");
            model.addAttribute("sId",paid);
            return "StuAllComment";
        }

//        学生identity固定为0
        Comment comment = new Comment(paid,0,cWord,new Date());

        commentService.addComment(comment);
        session.setAttribute("sId",paid);
        return "redirect:/Comment/toStuAllComment";
    }

    @RequestMapping("/StuQuerryCommentById")
    public  String StuQuerryCommentById(Integer sid, String sname,Model model,String searchCommentId){
//     判断是否传入空值
        if (searchCommentId == "") {
            model.addAttribute("error", "不要输入空的id");
            model.addAttribute("sId",sid);
            model.addAttribute("sName",sname);
            return "StuAllComment";
        }
        int anInt = 0;
//        将传入的string转为int
        if (searchCommentId != null && searchCommentId.equals("")) {
            anInt = Integer.parseInt(searchCommentId);
        }

//        判断是否为纯数字
        for (int i = 0; i < searchCommentId.length(); i++) {
            if (!Character.isDigit(searchCommentId.charAt(i))) {
                model.addAttribute("error", "请按规范输入id");
                model.addAttribute("sId",sid);
                model.addAttribute("sName",sname);
                return "StuAllComment";
            }
        }
        int num = 0;
        anInt = Integer.parseInt(searchCommentId);
        List<Comment> comments = commentService.QuerryCommentById(anInt);
        for (Comment comment : comments
        ) {
            num++;
        }
//        判断comment是否有这个comment,没有的话提示
        if (num == 0) {
            model.addAttribute("error", "此人未发布留言");
            model.addAttribute("sId",sid);
            model.addAttribute("sName",sname);
            return "StuAllComment";
        }

        model.addAttribute("sId",sid);
        model.addAttribute("sName",sname);
        model.addAttribute("pageComment", comments);

        return "StuAllComment";
    }


}
