package com.baomidou.mybatisplus.samples.generator.canalredis.entity;

import com.baomidou.mybatisplus.samples.generator.common.BaseEntity;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DeviceChannel extends BaseEntity {

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

    private Integer netStatus;

    private Integer platStatus;

    private Integer status;

    private String liveAlarm;

    private Integer alarmStatus;

    private Integer pointType;

    private Integer isMajor;

    private Long liveAlarmTimeStamp;

    private String selfDataId;

    private Long syncStatusTimestamp;

    private Integer custAttrEmpty;


}
