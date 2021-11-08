package com.wyw.service;

import com.wyw.pojo.Book;

import java.util.List;

public interface BookService {
    int addBook(Book book);
    int borrowBook(int bId);
    int backBook(int bId);
    int updateBook(Book book);
    List<Book> queryBook();
    Book queryBookById(int bId);
    List<Book> queryBookByName(String bName);
    int deleteBookById(int bId);
    Book SpecificQuerryBookByName(String bName);
}
