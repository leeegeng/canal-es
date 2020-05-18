package com.utils.canalredis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("`group`")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源同步序号
     */
    private Long syncRoundNum;

    @TableField("`id`")
    private String id;

    /**
     * 修改后版本号
     */
    private Long optmVersion;

    /**
     * 同步的树ID
     */
    private String treeId;

    /**
     * ioms32位父分组ID
     */
    private String parentId;

    /**
     * 同步的分组ID
     */
    private String idOrginal;

    /**
     * 同步的父分组ID
     */
    private String parentIdOrginal;

    /**
     * 树结构左值
     */
    @TableField("`left`")
    private Long left;

    /**
     * 树结构右值
     */
    @TableField("`right`")
    private Long right;

    /**
     * 分组的层级，0是第一级
     */
    private Long level;

    /**
     * 分组国标ID
     */
    private String gbId;

    /**
     * 分组原始国标ID
     */
    private String gbIdOrginal;

    /**
     * 分组域ID
     */
    private String domainId;

    /**
     * 分组名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 同步的属性
     */
    private String originalAttr;

    /**
     * 自定义的属性
     */
    private String customAttr;

    private String attr;

    private Integer isVirtual;

    private Integer custAttrEmpty;


}
