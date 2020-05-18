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
 * @author lee
 * @since 2020-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleDevchn implements Serializable {

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
     * IOMS唯一设备通道ID
     */
    private String devchnId;

    /**
     * 同步的设备ID
     */
    private String deviceId;

    /**
     * 设备通道标记1设备，2通道
     */
    private Integer dev1chn2;

    private Long createTime;

    private Long modifyTime;


}
