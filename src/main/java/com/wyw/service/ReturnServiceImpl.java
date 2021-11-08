package com.wyw.service;

import com.wyw.dao.ReturnMapper;
import com.wyw.pojo.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ReturnServiceImpl")
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    public int addReturn(Return ret) {
        return returnMapper.addReturn(ret);
    }

    @Override
    public int deleteReturnByIdAndName(Map map) {
        return returnMapper.deleteReturnByIdAndName(map);
    }


    @Override
    public int updateReturn(Return ret) {
        return returnMapper.updateReturn(ret);
    }

    @Override
    public List<Return> QuerryAllReturn() {
        return returnMapper.QuerryAllReturn();
    }

    @Override
    public List<Return> QuerryReturnByName(String brName) {
        return returnMapper.QuerryReturnByName(brName);
    }

    @Override
    public Return QuerryReturnByIdAndName(Map map) {
        return returnMapper.QuerryReturnByIdAndName(map);
    }

    @Override
    public List<Return> QuerryReturnById(int brId) {
        return returnMapper.QuerryReturnById(brId);
    }
}
