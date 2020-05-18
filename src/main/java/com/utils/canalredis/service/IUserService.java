package com.utils.canalredis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.canalredis.entity.User;
import com.utils.canalredis.entity.UserRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
public interface IUserService extends IService<User> {
    // 获取用户所有角色列表
    List<UserRole> getUserRoles(String userID);
    // 获取用户拥有设备的角色列表
    List<String> getUserDevRoles(String userID);
    List<String> getUserDevRolesSql(String userID);
    //  获取用户有权限分组列表
    List<String> getUserGroups(String userID);
    // 获取用户有权限分组列表（包括子分组）
    List<String> getUserGroupsDescendant(String userID);
}
