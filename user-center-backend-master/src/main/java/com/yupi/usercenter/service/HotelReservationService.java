package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.HotelReservation;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

/**
* @author Lenovo
* @description 针对表【hotel_reservation(酒店预约表)】的数据库操作Service
* @createDate 2025-02-13 22:40:50
*/
public interface HotelReservationService extends IService<HotelReservation> {
    /**
     * 用户注册
     * @param idCard 身份证号
     * @param phoneNumber 手机号
     * @param checkInDate 入住时间
     * @param checkOutDate 离店时间
     * @param specialRequests 特殊需求

     * @param roomType    房间类型
     * @return 成功返回1，失败返回0
     */
     long roomRegister(String idCard, String phoneNumber, String checkInDate,   String checkOutDate, String specialRequests, String roomType);

    }
