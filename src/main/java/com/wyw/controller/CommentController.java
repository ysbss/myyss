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
//        ???????????????key????????????????????????pojo?????????
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
//??????????????????????????????
        if (cWord==""){
            model.addAttribute("error1","???????????????");
            return "AdminAllComment";
        }
        HttpSession session = request.getSession();
        Administer administer = administerService.searchAdminById(said);
        if (administer==null){

            model.addAttribute("error1","?????????????????????");
            return "AdminAllComment";
        }
//        ??????????????????????????????identity??????1??????????????????????????????????????????new date
        Comment comment = new Comment(said,1,cWord,new Date());
        commentService.addComment(comment);
        session.setAttribute("paid",said);
        session.setAttribute("apaid",said);
        return "redirect:/Comment/toAllComment";
    }



    @RequestMapping("/AdminQuerryCommentById")
    public String AdminQuerryCommentById(Integer submit,Model model,String searchCommentId,HttpServletRequest request) {

//????????????????????????????????????
        if (searchCommentId == "") {
            model.addAttribute("error", "??????????????????id");
            return "AdminAllComment";
        }
        int anInt = 0;
//        ??????????????????string????????????int
        if (searchCommentId != null && searchCommentId.equals("")) {
            anInt = Integer.parseInt(searchCommentId);
        }
//????????????????????????????????????
        for (int i = 0; i < searchCommentId.length(); i++) {
            if (!Character.isDigit(searchCommentId.charAt(i))) {
                model.addAttribute("error", "??????????????????id");
                return "AdminAllComment";
            }
        }
        int num = 0;
        anInt = Integer.parseInt(searchCommentId);
        List<Comment> comments = commentService.QuerryCommentById(anInt);
//        ?????????????????????????????????
        for (Comment comment : comments
        ) {
            num++;
        }
        if (num == 0) {
            model.addAttribute("error", "?????????????????????");
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

//        ????????????????????????
        if (cWord==""){
            model.addAttribute("error1","???????????????");
            model.addAttribute("sId",paid);
            return "StuAllComment";
        }
        HttpSession session = request.getSession();
        Student student = studentService.searchStudentById(paid);
//        ?????????????????????????????????????????????
        if (student==null){
            model.addAttribute("error1","??????????????????");
            model.addAttribute("sId",paid);
            return "StuAllComment";
        }

//        ??????identity?????????0
        Comment comment = new Comment(paid,0,cWord,new Date());

        commentService.addComment(comment);
        session.setAttribute("sId",paid);
        return "redirect:/Comment/toStuAllComment";
    }

    @RequestMapping("/StuQuerryCommentById")
    public  String StuQuerryCommentById(Integer sid, String sname,Model model,String searchCommentId){
//     ????????????????????????
        if (searchCommentId == "") {
            model.addAttribute("error", "??????????????????id");
            model.addAttribute("sId",sid);
            model.addAttribute("sName",sname);
            return "StuAllComment";
        }
        int anInt = 0;
//        ????????????string??????int
        if (searchCommentId != null && searchCommentId.equals("")) {
            anInt = Integer.parseInt(searchCommentId);
        }

//        ????????????????????????
        for (int i = 0; i < searchCommentId.length(); i++) {
            if (!Character.isDigit(searchCommentId.charAt(i))) {
                model.addAttribute("error", "??????????????????id");
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
//        ??????comment???????????????comment,??????????????????
        if (num == 0) {
            model.addAttribute("error", "?????????????????????");
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
