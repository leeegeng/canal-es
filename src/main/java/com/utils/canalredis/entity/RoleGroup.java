package com.utils.canalredis.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源同步序号
     */
    private Long syncRoundNum;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 同步的树ID
     */
    private String treeId;

    /**
     * 同步的分组ID
     */
    private String groupId;

    private Long createTime;

    private Long modifyTime;


}
