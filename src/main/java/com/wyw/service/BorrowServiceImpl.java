package com.wyw.service;

import com.wyw.dao.BorrowMapper;
import com.wyw.pojo.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("BorrowServiceImpl")
public class BorrowServiceImpl implements BorrowService{

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public int addBorrow(Borrow borrow) {
        return borrowMapper.addBorrow(borrow);
    }

    @Override
    public List<Borrow> QuerryAllBorrow() {
        return borrowMapper.QuerryAllBorrow();
    }

    @Override
    public int deleteBorrowByRidAndRbid(Map map) {
        return borrowMapper.deleteBorrowByRidAndRbid(map);
    }
}
