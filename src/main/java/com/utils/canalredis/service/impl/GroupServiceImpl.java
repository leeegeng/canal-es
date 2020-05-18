package com.utils.canalredis.service.impl;

import com.utils.canalredis.entity.Group;
import com.utils.canalredis.mapper.GroupMapper;
import com.utils.canalredis.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {
    @Autowired
    private GroupMapper groupMapper;
}
