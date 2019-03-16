package com.book.fairy.modules.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.fairy.modules.entity.SysDict;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统常用配置 Mapper 接口
 * </p>
 *
 * @author yz-he
 * @since 2018-07-12
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> selectSysDictByConditions(SysDict sysDict);
    Map<String,Integer> selectMax();

}
