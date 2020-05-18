package com.utils.canalredis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utils.canalredis.entity.RoleDevchn;
import com.utils.canalredis.entity.RoleGroup;
import com.utils.canalredis.entity.User;
import com.utils.canalredis.entity.UserRole;
import com.utils.canalredis.mapper.UserMapper;
import com.utils.canalredis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private RoleGroupServiceImpl roleGroupService;

    @Autowired
    private RoleDevchnServiceImpl roleDevchnService;

    @Override
    public List<UserRole> getUserRoles( String userID){
        List<UserRole> roleList = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", userID));

        return roleList;
    }

    @Override
    public List<String > getUserDevRoles(String userID){
        List<String> devroleList = roleDevchnService.list(new QueryWrapper<RoleDevchn>().groupBy("role_id"))
                                .stream().map(RoleDevchn::getRoleId).collect(Collectors.toList());
        List<String> roleList = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", userID).in("role_id", devroleList))
                                        .stream().map(UserRole::getRoleId).collect(Collectors.toList());

        return roleList;

    }

    @Override
    public List<String> getUserDevRolesSql(String userID) {
        List<String> roleList = userMapper.getUserDevRoles(userID);
        return roleList;
    }

    @Override
    public List<String> getUserGroups(String userID){
        List<UserRole> roles = getUserRoles(userID);

        List<String> groupList = roleGroupService.list(new QueryWrapper<RoleGroup>().
                in("role_id", roles.stream().map(r -> r.getRoleId()).distinct().collect(Collectors.toList())))
                .stream().map(gs -> gs.getGroupId()).collect(Collectors.toList());

        return groupList;
    }

    @Override
    public List<String> getUserGroupsDescendant(String userID){
        List<String> groupList = userMapper.getUserRoleGroups(userID);

        return groupList;
    }
}
