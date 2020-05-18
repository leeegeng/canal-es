package com.utils.canalredis.mapper;

import com.utils.canalredis.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT role_id FROM user_role WHERE user_id=#{userID} \n"+
            " AND  role_id IN (SELECT role_id FROM role_devchn GROUP BY role_id)")
    List<String> getUserDevRoles(String UserID);

    @Select("SELECT\n" +
            "DISTINCT totalTreeGroup.id,\n" +
            "totalTreeGroup.tree_id,\n" +
            "totalTreeGroup.`left`,\n" +
            "totalTreeGroup.`right` \n" +
            "FROM\n" +
            "`group` totalTreeGroup \n" +
            "JOIN\n" +
            "(\n" +
            "SELECT\n" +
            "g.tree_id,\n" +
            "id,\n" +
            "`left`,\n" +
            "`right` \n" +
            "FROM\n" +
            "`group` g \n" +
            "JOIN\n" +
            "round_union ru \n" +
            "ON g.sync_round_num = ru.sync_round_num \n" +
            "JOIN\n" +
            "(\n" +
            "SELECT\n" +
            "r.sync_round_num,\n" +
            "r.tree_id,\n" +
            "r.group_id \n" +
            "FROM\n" +
            "user_role ur \n" +
            "JOIN\n" +
            "role_group r \n" +
            "ON r.`role_id` = ur.role_id \n" +
            "AND ur.user_id = #{UserID} \n" +
            "JOIN\n" +
            "round_union ru \n" +
            "ON r.sync_round_num = ru.sync_round_num \n" +
            "AND r.tree_id = 'defaulttreeid0000000000000000000' \n" +
            ") rg \n" +
            "ON g.tree_id = rg.tree_id \n" +
            "AND g.id = rg.group_id \n" +
            ") targetGrp \n" +
            "ON totalTreeGroup.tree_id = targetGrp.tree_id \n" +
            "AND totalTreeGroup.left >= targetGrp.left \n" +
            "AND totalTreeGroup.right <= targetGrp.right \n")
    List<String> getUserRoleGroups(String UserID);
}
