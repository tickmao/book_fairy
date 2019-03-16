package com.business.hall.modules.service.impl;

import com.business.hall.modules.entity.SysDict;
import com.business.hall.modules.mapper.SysDictMapper;
import com.business.hall.modules.service.SysDictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统常用配置 服务实现类
 * </p>
 *
 * @author yz-he
 * @since 2018-07-12
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Override
    public List<SysDict> selectSysDictByConditions(SysDict sysDict) {
        return baseMapper.selectSysDictByConditions(sysDict);
    }

    @Override
    public Map<String, Integer> selectMax() {
        return baseMapper.selectMax();
    }
}
