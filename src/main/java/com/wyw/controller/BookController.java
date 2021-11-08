package com.wyw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyw.pojo.Book;
import com.wyw.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;


@Controller
@RequestMapping("Book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook/{aid}")
    public String toAllBook(HttpServletRequest request, Model model, @PathVariable("aid") int ad, @RequestParam(value = "pageNum",required = true,defaultValue = "1")int pageNum){
        PageHelper.startPage(pageNum,3);
        List<Book> books=bookService.queryBook();
        HttpSession session = request.getSession();
        PageInfo pageInfo=new PageInfo(books);
        model.addAttribute("pageBook",books);
        model.addAttribute("pageInfo",pageInfo);
        session.setAttribute("stuAid",ad);
        return "AllBook";
    }

    @RequestMapping("/toUpdateBook/{bId}")
    public String toUpdateBook(@PathVariable("bId") int id,Model model,int aid){
        Book book = bookService.queryBookById(id);
        model.addAttribute("pageBook",book);
        model.addAttribute("paid",aid);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Book book,Model model,int aid){
        bookService.updateBook(book);
        return "redirect:/Book/allBook/"+aid;
    }

    @RequestMapping("/queryBookByName")
    public  String queryBookByName(Model model,String searchBookName){
        List<Book> books = bookService.queryBookByName(searchBookName);
        int num=0;
        for (Book bo:books
             ) {
             num++;
        }
//        判断前端是否输入空值
        if (searchBookName==""){
            model.addAttribute("error","要查询的书名不能为空");
            return "AllBook";
        }
//        未找到要查询的书籍，返回页面并提醒
        if (num==0){
            model.addAttribute("error","未找到你要查询的书籍");
            return "AllBook";
        }
        model.addAttribute("pageBook",books);
        return "AllBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddBook(Model model,int aid){
        model.addAttribute("paid",aid);
        return "addBook";
    }

    @RequestMapping("/AddBook")
    public String AddBook(Book book,Model model,int aid){

//判断前端传值是否为空
        if (book.getBId()==null)
        {

            model.addAttribute("error","输入的书id不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        if (book.getBName()=="")
        {

            model.addAttribute("error","输入的书名不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        if (book.getBIsbn()=="")
        {
            model.addAttribute("error","输入的ISBN码不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        if (book.getBAuthor()=="")
        {
            model.addAttribute("error","输入的作者不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        if (book.getBPublisher()=="")
        {
            model.addAttribute("error","输入的出版社不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        if (book.getBPrice()==null){
            model.addAttribute("error","输入的价格不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        if (book.getBNum()==null)
        {
            model.addAttribute("error","输入的库存数量不能为空");
            model.addAttribute("paid",aid);
            return "addBook";
        }
        else {
//            判断数据库内是否已经有这本书
            List<Book> books = bookService.queryBook();
            for (int i=0;i<books.size();i++){
                if (book.getBId().equals(books.get(i).getBId())){
                    model.addAttribute("error","id已被注册");
                    model.addAttribute("paid",aid);
                    return "addBook";
                }
            }

        }
        bookService.addBook(book);
        return "redirect:/Book/allBook/"+aid;
    }

    @RequestMapping("deleteBook/{bId}")
    public  String deleteBookById(@PathVariable("bId") int id,int aid){
        bookService.deleteBookById(id);
        return "redirect:/Book/allBook/"+aid;
    }
}



