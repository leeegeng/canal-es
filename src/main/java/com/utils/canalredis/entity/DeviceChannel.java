package com.utils.canalredis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源同步序号
     */
    private Long syncRoundNum;

    /**
     * 设备通道标记1设备，2通道
     */
    private Integer dev1chn2;

    /**
     * 同步的设备ID
     */
    private String deviceId;

    /**
     * 国标ID
     */
    private String gbId;

    /**
     * 科达私有ID
     */
    private String kdmno;

    /**
     * 设备厂商
     */
    private String manufacturer;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 是否启用标记
     */
    private Integer flagEnable;

    private Long liveAlarmTimestamp;

    private Long syncStatusTimestamp;

    private Long modifyTime;

    private String liveAlarm;

    /**
     * 同步的属性
     */
    private String originalAttr;

    /**
     * 自定义的属性
     */
    private String customAttr;

    private String selfData;

    private String attr;

    private String ipv4;

    private String ipv6;

    private Integer port;

    private String protocol;

    private String deviceType;

    private String deviceTypeName;

    private String protocolName;

    private Integer pointType;

    private Integer custAttrEmpty;

    private String selfDataId;

    private Integer isMajor;

    private String longitude;

    private String latitude;

    private Integer isGuoBiao;

    private Integer nmediaId;

    private Integer flagVasUse;

    private Integer flagVideo;

    private Integer flagRecord;

    private Integer flagTollgate;

    private String mediaInfo;

    private String model;

    private String installAddress;

    private Integer alarmStatus;

    private Integer platStatus;

    private Integer netStatus;

    private Integer status;

    private String extsysParentId;

    private String securityboxId;

    private String securityboxSystemParentId;

    private String extsysParentTptId;

    private String extsysParentTetId;

    private Integer sequnceIndex;

    private String domainName;

    private String civilCode;


}
