package com.yupi.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 酒店预约表
 * @TableName hotel_reservation
 */
@TableName(value ="hotel_reservation")
@Data
public class HotelReservation implements Serializable {
    /**
     * 预约ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */


    /**
     * 身份证号
     */
    @JsonProperty
    private String idCard;

    /**
     * 手机号
     */
    @JsonProperty
    private String phoneNumber;

    /**
     * 入住日期
     */
    @JsonProperty
    private String checkInDate;

    /**
     * 离店日期
     */
    @JsonProperty
    private   String checkOutDate;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 房间号
     */
    private Integer numberOfRooms;

    /**
     * 特殊要求
     */
    @JsonProperty
    private String specialRequests;

    /**
     * 预约状态
     */
    private String reservationStatus;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}