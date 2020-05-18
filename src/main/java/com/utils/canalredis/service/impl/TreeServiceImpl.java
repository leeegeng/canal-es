package com.utils.canalredis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utils.canalredis.entity.Group;
import com.utils.canalredis.entity.Tree;
import com.utils.canalredis.mapper.TreeMapper;
import com.utils.canalredis.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
@Service
public class TreeServiceImpl extends ServiceImpl<TreeMapper, Tree> implements ITreeService {
    @Autowired
    private GroupServiceImpl groupService;

    @Override
    public List<Group> getChildren(String treeId, String groupId) {
        QueryWrapper<Group> wrapper = new QueryWrapper<>();
        wrapper.eq("tree_id",treeId)
                .eq("parent_id", groupId)
                .ne("id", groupId)
                .orderByAsc("`left`");
        List<Group> records = groupService.list(wrapper);

        return records;
    }

    @Override
    public List<Group> getDescendantsAndMe(String treeId, String groupId){
        Group group = groupService.getOne(new QueryWrapper<Group>().eq("tree_id", treeId).eq("id", groupId));
        QueryWrapper<Group> wrapper = new QueryWrapper<Group>();
        wrapper.eq("tree_id", treeId)
                .ge("`left`", group.getLeft())
                .le("`right`", group.getRight())
                .orderByAsc("`left`");
        List<Group> groupList = groupService.list(wrapper);

        return groupList;
    }
}
