package com.wyw.dao;

import com.wyw.pojo.Book;

import java.util.List;

public interface BookMapper {
    int addBook(Book book);
    int borrowBook(int bId);
    int backBook(int bId);
    int updateBook(Book book);
    List<Book> queryBook();
    Book queryBookById(int bId);
    List<Book> queryBookByName(String bName);
    Book SpecificQuerryBookByName(String bName);
    int deleteBookById(int bId);

}
