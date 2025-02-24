package com.yupi.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.HotelReservation;
import com.yupi.usercenter.model.domain.request.roomRegisterRequest;
import com.yupi.usercenter.service.HotelReservationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 酒店接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081"}, allowCredentials = "true")
public class HotelController {

    @Resource
    private HotelReservationService hotelReservationService;

    /**
     * 酒店注册
     *
     * @param
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> hotelRegister(@RequestBody roomRegisterRequest roomRegisterRequest) {
        String idCard = roomRegisterRequest.getIdCard();
        String phoneNumber = roomRegisterRequest.getPhoneNumber();
        String checkInDate = roomRegisterRequest.getCheckInDate();
        String checkOutDate = roomRegisterRequest.getCheckOutDate();
        String specialRequests = roomRegisterRequest.getSpecialRequests();
        String roomType = roomRegisterRequest.getRoomType();
        if (StringUtils.isAnyBlank(idCard, specialRequests)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "身份证号、手机号、特殊要求不能为空");
        }
        if (StringUtils.isAnyBlank( phoneNumber)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请先登录才能预订哦！·");
        }

        if (checkInDate == null || checkOutDate == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "入住时间或离店时间为空");
        }
        long result = hotelReservationService.roomRegister( idCard, phoneNumber, checkInDate, checkOutDate, specialRequests,roomType);
        return ResultUtils.success(result);
    }


    // 酒店查询
    @GetMapping("/all")
    public BaseResponse<List<HotelReservation>> getAllHotels() {
        // 创建QueryWrapper对象，但不设置任何条件
        QueryWrapper<HotelReservation> queryWrapper = new QueryWrapper<>();
        // 调用hotelReservationService的list方法获取所有HotelReservation对象
        List<HotelReservation> reservations = hotelReservationService.list(queryWrapper);
        // 检查查询结果是否为空
        if (reservations == null || reservations.isEmpty()) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        // 返回成功响应，并将查询到的预订信息列表作为响应体返回
        return ResultUtils.success(reservations);
    }

    //用户查询自己的预订信息
    @GetMapping("/search")
    public BaseResponse<List<HotelReservation>> hotelSearch(String phoneNumber) {
        QueryWrapper<HotelReservation> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(phoneNumber)) {
            queryWrapper.like("phoneNumber", phoneNumber);
        }
        // 使用实例方法调用
        List<HotelReservation> reservations = hotelReservationService.list(queryWrapper);
        return ResultUtils.success(reservations);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteHotel(@RequestBody long id) {
        boolean result = hotelReservationService.removeById(id);
        return ResultUtils.success(result);
    }
    //修改接口
    //修改接口
    @PutMapping("/update")
public BaseResponse<Boolean> updateHotel(@RequestBody HotelReservation hotelReservation) {
    // 获取原始预订信息
    HotelReservation originalReservation = hotelReservationService.getById(hotelReservation.getId());
    if (originalReservation == null) {
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "预订信息不存在");
    }

    // 更新字段，如果传入的值为空则保持原值
    if (StringUtils.isNotBlank(hotelReservation.getIdCard())) {
        originalReservation.setIdCard(hotelReservation.getIdCard());
    }
    if (StringUtils.isNotBlank(hotelReservation.getPhoneNumber())) {
        originalReservation.setPhoneNumber(hotelReservation.getPhoneNumber());
    }
    if (StringUtils.isNotBlank(hotelReservation.getCheckInDate())) {
        originalReservation.setCheckInDate(hotelReservation.getCheckInDate());
    }
    if (StringUtils.isNotBlank(hotelReservation.getCheckOutDate())) {
        originalReservation.setCheckOutDate(hotelReservation.getCheckOutDate());
    }
    if (StringUtils.isNotBlank(hotelReservation.getSpecialRequests())) {
        originalReservation.setSpecialRequests(hotelReservation.getSpecialRequests());
    }
        if (StringUtils.isNotBlank(hotelReservation.getRoomType())) {
            originalReservation.setRoomType(hotelReservation.getRoomType());
        }
        if (hotelReservation.getNumberOfRooms() != null) {
            originalReservation.setNumberOfRooms(hotelReservation.getNumberOfRooms());
        }
        if (StringUtils.isNotBlank(hotelReservation.getReservationStatus())) {
            originalReservation.setReservationStatus(hotelReservation.getReservationStatus());
        }


    // 设置更新时间
    originalReservation.setUpdatedAt(new Date());

    // 调用更新方法
    boolean result = hotelReservationService.updateById(originalReservation);
    return ResultUtils.success(result);
}
}





