package com.wyw.service;

import com.wyw.dao.AdministerMapper;
import com.wyw.pojo.Administer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdministerServiceImpl")
public class AdministerServiceImpl implements AdministerService{

    @Autowired
    private AdministerMapper administerMapper;

    @Override
    public Administer searchAdminById(int aId) {
        return administerMapper.searchAdminById(aId);
    }
}
