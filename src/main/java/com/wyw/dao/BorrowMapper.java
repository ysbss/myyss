package com.wyw.dao;

import com.wyw.pojo.Borrow;

import java.util.List;
import java.util.Map;

public interface BorrowMapper {
    int addBorrow(Borrow borrow);

    List<Borrow> QuerryAllBorrow();

    int deleteBorrowByRidAndRbid(Map map);

}
