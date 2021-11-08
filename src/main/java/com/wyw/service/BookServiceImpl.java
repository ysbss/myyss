package com.wyw.service;

import com.wyw.dao.BookMapper;
import com.wyw.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BookServiceImpl")
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int addBook(Book book) { return bookMapper.addBook(book);
    }

    @Override
    public int borrowBook(int bId) {
        return bookMapper.borrowBook(bId);
    }

    @Override
    public int backBook(int bId) {
        return bookMapper.backBook(bId);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<Book> queryBook() {
        return bookMapper.queryBook();
    }

    @Override
    public Book queryBookById(int bId) {
        return bookMapper.queryBookById(bId);
    }

    @Override
    public List<Book> queryBookByName(String bName) {
        return bookMapper.queryBookByName(bName);
    }

    @Override
    public int deleteBookById(int bId) {
        return bookMapper.deleteBookById(bId);
    }

    @Override
    public Book SpecificQuerryBookByName(String bName) {
        return bookMapper.SpecificQuerryBookByName(bName);
    }
}
