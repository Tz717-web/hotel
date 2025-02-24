package com.yupi.usercenter.service;
import com.yupi.usercenter.model.domain.HotelReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.Date;

//@SpringBootTest
//public class HotelReservationServiceTests {
//    @Resource
//    private HotelReservationService hotelReservationService;
//
//
//    @Test
//    void roomRegister() {
//        String idCard = "123456789012345678";
//        String phoneNumber = "13800138000";
//        Date checkInDate = Date.from(LocalDate.parse("2022-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay().toInstant(java.time.ZoneOffset.of("+8")));
//        Date checkOutDate = Date.from(LocalDate.parse("2022-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay().toInstant(java.time.ZoneOffset.of("+8")));
//        String specialRequests = "请空调";
//        long id = hotelReservationService.roomRegister(idCard, phoneNumber, checkInDate, checkOutDate, specialRequests);
//        Assertions.assertEquals(-1, id);
//
//    }
//}
