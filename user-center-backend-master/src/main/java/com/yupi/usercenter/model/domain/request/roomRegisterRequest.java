package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class roomRegisterRequest implements Serializable {
   private  String idCard;
   private  String phoneNumber;
   private String checkInDate;
   private  String checkOutDate;
   private  String specialRequests;
   private  String  roomType;

}
