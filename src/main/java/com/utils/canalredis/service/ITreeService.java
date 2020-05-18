package com.utils.canalredis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.canalredis.entity.Group;
import com.utils.canalredis.entity.Tree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
public interface ITreeService extends IService<Tree> {
    List<Group> getChildren(String treeId, String groupId);
    List<Group> getDescendantsAndMe(String treeId, String groupId);
}
