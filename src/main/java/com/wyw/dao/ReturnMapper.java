package com.wyw.dao;

import com.wyw.pojo.Return;

import java.util.List;
import java.util.Map;

public interface ReturnMapper {
    int addReturn(Return ret);
    int deleteReturnByIdAndName(Map map);
    int updateReturn(Return ret);
    List<Return> QuerryAllReturn();
    List<Return> QuerryReturnByName(String brName);
    Return QuerryReturnByIdAndName(Map map);
    List<Return> QuerryReturnById(int brId);
}
