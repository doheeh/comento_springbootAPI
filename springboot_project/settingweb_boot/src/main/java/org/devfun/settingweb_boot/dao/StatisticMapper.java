package org.devfun.settingweb_boot.dao;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.devfun.settingweb_boot.dto.StatisticDto;


public interface  StatisticMapper {
    public HashMap<String, Object> selectYearLogin(String year);

}
