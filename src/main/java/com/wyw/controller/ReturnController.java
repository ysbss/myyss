package com.wyw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyw.pojo.Book;
import com.wyw.pojo.Borrow;
import com.wyw.pojo.Return;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("Return")
public class ReturnController {

    @Autowired
    @Qualifier("ReturnServiceImpl")
    private ReturnService returnService;

    @Autowired
    @Qualifier("BorrowServiceImpl")
    private BorrowService borrowService;

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentService studentService;



    @RequestMapping("/toBookReturn/{aid}")
    public  String toBookReturn(HttpServletRequest request, Model model, @PathVariable("aid") int ad, @RequestParam(value = "pageNum", required = true, defaultValue = "1") int pageNum){
//        管理员点击的话进入开始进行判断.

        List<Borrow> borrows = borrowService.QuerryAllBorrow();
        List<Return> ret=returnService.QuerryAllReturn();
        HttpSession session = request.getSession();
        Date date = new Date();
        PageHelper.startPage(pageNum, 3);
//        开始循环编例借阅表
        for (int i=0;i<borrows.size();i++){
//            long millSecs=date.getTime()-borrows.get(i).getRbDate().getTime();
//           标志状态,每次循环自动置为0
            int flag=0;
//            得到当前borrow表中即借阅表
            Book book1 = bookService.queryBookById(borrows.get(i).getRbId());
//            这个判断就表示如果当前时间比应当归还时间要大于60天的话,那么就表示要对这个借阅信息进行催还处理
            if ((date.getTime()-borrows.get(i).getRpDate().getTime())/(3600*24*1000)-1>60){
//                这个循环是遍历归还表,看是否已经对上一层的符合条件的借阅信息放置在了催还信息表内
                for (int j=0;j<ret.size();j++){
//                    这个表示当前借阅信息已经放置在了信息表内
                    if (ret.get(j).getBrId().equals(borrows.get(i).getRId())&&ret.get(j).getBrbName().equals(book1.getBName()))
                    {
//                        将flag状态置为1,并退出此次循环
                        flag=1;
                        break;
                    }
                }
//                如果flag不为1表示当前借阅信息符合大于60天且尚未添加入催还表中
                if (flag==0){
                    Book book = bookService.queryBookById(borrows.get(i).getRbId());
//                    利用borrow表和student表自动生成
                    Return aReturn = new Return(borrows.get(i).getRId(),borrows.get(i).getRName(),book.getBName(),book.getBPrice(),borrows.get(i).getRbDate());
                    returnService.addReturn(aReturn);
                }
            }

        }

        List<Return> returns = returnService.QuerryAllReturn();
        PageInfo pageInfo = new PageInfo(returns);
        model.addAttribute("pageReturns", returns);
        model.addAttribute("pageInfo", pageInfo);
        session.setAttribute("stuAid",ad);
        return "BookReturn";
    }

    @RequestMapping("/querryReturnByName")
    public  String querryReturnByName(Model model,String searchRetBName){
        List<Return> returns = returnService.QuerryReturnByName(searchRetBName);
        int num = 0;

        for (Return ret:returns
             ) {
            num++;
        }
//       判断前端传入值是否为空
        if (searchRetBName==""){
            model.addAttribute("error", "要查询的催还信息不能为空");
            return "BookReturn";
        }
//        数据库里没有这个值
        if (num==0){
            model.addAttribute("error", "未找到你要查询的催还信息");
            return "BookReturn";
        }
        model.addAttribute("pageReturns",returns);
        return "BookReturn";
    }

    @RequestMapping("/deleteReturnById/{pid}/{pbrbname}")
    public String deleteReturnByIdAndName(@PathVariable("pid") int id,@PathVariable("pbrbname") String name,int aid){
        HashMap map = new HashMap();
        map.put("brId",id);
        map.put("brbName",name);
        Book book = bookService.SpecificQuerryBookByName(name);
        HashMap map1 = new HashMap();
        map1.put("rId",id);
        map1.put("rbId",book.getBId());
//        System.out.println(book.getBId());
        bookService.backBook(book.getBId());
        returnService.deleteReturnByIdAndName(map);
        borrowService.deleteBorrowByRidAndRbid(map1);
        return "redirect:/Return/toBookReturn/"+aid;
    }


    @RequestMapping("/toAddReturnBook")
    public  String toAddReturn(Model model,int aid){
        model.addAttribute("paid",aid);
        return "AddReturn";
    }

    @RequestMapping("/AddReturnBook")
    public String AddReturnBook(Return ret,Model model,int aid){
//       判断是否传入空值
        if (ret.getBrId()==null){
            model.addAttribute("error","添加的的借阅人学生id不能为空");
            model.addAttribute("paid",aid);
            return "AddReturn";
        }

        if (ret.getBrName()==""){
            model.addAttribute("error","添加的借阅人姓名不能为空");
            model.addAttribute("paid",aid);
            return "AddReturn";
        }

        if (ret.getBrbName()==""){
            model.addAttribute("error","添加的借阅书号不能为空");
            model.addAttribute("paid",aid);
            return "AddReturn";
        }
        if (ret.getBrPrice()==null){
            model.addAttribute("error","添加的价格不能为空");
            model.addAttribute("paid",aid);
            return "AddReturn";
        }
        List<Return> returns = returnService.QuerryAllReturn();
        for (int i=0;i<returns.size();i++){
//            System.out.println(ret.getBrId()+"="+returns.get(i).getBrId());
//            System.out.println(ret.getBrbName()+"="+returns.get(i).getBrbName());
//            判断催还信息表内是否有该催还信息
            if ((ret.getBrId().equals(returns.get(i).getBrId()))&&(ret.getBrbName().equals(returns.get(i).getBrbName()))){
                model.addAttribute("error","已经发布过该读者的该书借阅信息");
                model.addAttribute("paid",aid);
                return "AddReturn";
            }
        }
        returnService.addReturn(ret);
        return "redirect:/Return/toBookReturn/"+aid;
    }

    @RequestMapping("/toUpdateReturn/{brId}/{brbName}")
    public String toUpdateReturn(Model model,@PathVariable("brId") int brid,@PathVariable("brbName") String brbname,int aid){
        HashMap map = new HashMap();
        map.put("brId",brid);
        map.put("brbName",brbname);
        Return ret = returnService.QuerryReturnByIdAndName(map);
        model.addAttribute("pageReturn",ret);
        model.addAttribute("paid",aid);
        return "updateReturn" ;
    }

    @RequestMapping("/updateReturnBook")
    public String updateReturnBook(Return ret,int aid){

        returnService.updateReturn(ret);

        return "redirect:/Return/toBookReturn/"+aid;
    }

@RequestMapping("/toSelfReturn/{pid}/{ppwd}")
    public String toSelfReturn(HttpServletRequest request,Model model,@PathVariable("pid") int id,@PathVariable("ppwd") String pwd){
        List<Return> returns = returnService.QuerryReturnById(id);
    Student student = studentService.searchStudentById(id);
    HttpSession session = request.getSession();
    session.setAttribute("pageSid",id);
    session.setAttribute("pagePwd",pwd);

    int num=0;
    for (Return ret:returns
         ) {
        num++;
    }
//判断是否有催还信息
    if (num!=0){
        model.addAttribute("pageReturns",returns);
    }

    else {
        model.addAttribute("error","您没有催还信息");
    }

    return "selfReturn";
}


    @RequestMapping("/backBook/{sid}/{bname}")
    public String backBook(Model model,@PathVariable("sid") int id,@PathVariable("bname") String name,int psId,String psPwd){
//        获取在book表中的书对象
        Book book = bookService.SpecificQuerryBookByName(name);
//      为了删除借阅表做准备
        HashMap map1 = new HashMap();
        map1.put("rId",id);
        map1.put("rbId",book.getBId());
//        为删除催还表做准备
        HashMap map2 = new HashMap();
        map2.put("brId",id);
        map2.put("brbName",name);
        bookService.backBook(book.getBId());
        returnService.deleteReturnByIdAndName(map2);
        borrowService.deleteBorrowByRidAndRbid(map1);
        return "redirect:/Return/toSelfReturn/"+psId+"/"+psPwd;
    }

}
