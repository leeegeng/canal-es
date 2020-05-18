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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录帐号
     */
    private String loginName;

    private String password;

    /**
     * 密码修改时间
     */
    private Long passwordModifyTime;

    /**
     * 私钥
     */
    private String salt;

    /**
     * 是否启用
     */
    private Integer flagEnable;

    /**
     * 是否多点登录
     */
    private Integer flagMultiLogin;

    /**
     * 联系方式
     */
    private String contact;

    private String source;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 单位id
     */
    private String deptId;

    private Long createTime;

    private Long modifyTime;

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

    private String attr;


}
