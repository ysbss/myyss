package com.wyw.service;

import com.wyw.pojo.Borrow;

import java.util.List;
import java.util.Map;

public interface BorrowService {
    int addBorrow(Borrow borrow);
    List<Borrow> QuerryAllBorrow();
    int deleteBorrowByRidAndRbid(Map map);
}
