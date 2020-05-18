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
public class Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源同步序号
     */
    private Long syncRoundNum;

    private String name;

    /**
     * 同步的根分组树ID
     */
    private String rootGroupId;

    /**
     * 签名
     */
    private String fingerprint;

    /**
     * 来源
     */
    private Integer sourceType;

    /**
     * 业务分类(1:ioms默认树,2:同步源默认树,3:行政区域,4:建设单位,5:维修单位,999普通树)
     */
    private Integer businessType;

    /**
     * 设备/通道(1:设备,2:通道,3:设备通道)
     */
    private Integer devchnType;

    /**
     * 描述
     */
    private String descp;

    /**
     * 同步的属性
     */
    private String originalAttr;

    /**
     * 用户自定义属性
     */
    private String customAttr;

    private Long createTime;

    private Long modifyTime;

    private String attr;

    private Integer custAttrEmpty;


}
