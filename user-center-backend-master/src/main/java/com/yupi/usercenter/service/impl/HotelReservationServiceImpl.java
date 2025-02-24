package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.HotelReservation;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.service.HotelReservationService;
import com.yupi.usercenter.mapper.HotelReservationMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lenovo
 * @description 针对表【hotel_reservation(酒店预约表)】的数据库操作Service实现
 * @createDate 2025-02-13 22:40:50
 */
@Service
@Slf4j
public class HotelReservationServiceImpl extends ServiceImpl<HotelReservationMapper, HotelReservation>
        implements HotelReservationService{
     @Resource
     private HotelReservationMapper hotelReservationMapper;

     /**
      * @param idCard          身份证号
      * @param phoneNumber     手机号
      * @param checkInDate     入住时间
      * @param checkOutDate    离店时间
      * @param specialRequests 特殊需求
      * @return
      */
  @Override
public long roomRegister(String idCard, String phoneNumber, String checkInDate,  String checkOutDate, String specialRequests, String roomType) {
       // 1. 校验
       if (StringUtils.isAnyBlank(idCard, phoneNumber)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
       }

       // 身份证号长度不正确
       if (idCard.length() < 13) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "身份证号过短");
       }

       // 账户不能包含特殊字符
       String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
       Matcher matcher = Pattern.compile(validPattern).matcher(specialRequests);
       if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "特殊需求包含特殊字符");
       }

       // 手机号格式不正确
       if (!phoneNumber.matches("^1[34578]\\d{9}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号格式不正确");
       }

       // 身份证不能重复
       QueryWrapper<HotelReservation> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("idCard", idCard);
       long count = hotelReservationMapper.selectCount(queryWrapper);
       if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "身份证号重复，我们仅支持一证一房");
       }

//       // 手机号不能重复
//       queryWrapper = new QueryWrapper<>();
//       queryWrapper.eq("phoneNumber", phoneNumber);
//       count = hotelReservationMapper.selectCount(queryWrapper);
//       if (count > 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号重复");
//       }

       HotelReservation hotelReservation = new HotelReservation();
       hotelReservation.setIdCard(idCard);
       hotelReservation.setPhoneNumber(phoneNumber);
       hotelReservation.setCheckInDate(checkInDate);
       hotelReservation.setCheckOutDate(checkOutDate);
       hotelReservation.setSpecialRequests(specialRequests);
       hotelReservation.setRoomType(roomType);
       hotelReservation.setReservationStatus("待确认");

       // 打印对象内容以调试
       log.info("HotelReservation to be saved: {}嘿嘿嘿嘿hi嘿嘿嘿", hotelReservation);

       boolean saveResult = this.save(hotelReservation);
       if (!saveResult) {
            return -1;
       }
       return hotelReservation.getId();
  }
}
