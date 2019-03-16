package com.book.fairy.modules.service;

import com.book.fairy.modules.entity.SysDict;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统常用配置 服务类
 * </p>
 *
 * @author yz-he
 * @since 2018-07-12
 */
public interface SysDictService extends IService<SysDict> {
    List<SysDict> selectSysDictByConditions(SysDict sysDict);

    Map<String,Integer> selectMax();
}
