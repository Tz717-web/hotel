# 数据库初始化
# @author <a href="https://github.com/liyupi">程序员鱼皮</a>
# @from <a href="https://yupi.icu">编程导航知识星球</a>

-- 创建库
create database if not exists yupi;

-- 切换库
use yupi;

# 用户表
create table user
(
    username     varchar(256)                       null comment '用户昵称',
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号'
)
    comment '用户';

# 导入示例用户
INSERT INTO yupi.user (username, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('小智', 'xiaozhi', 'https://himg.bdimg.com/sys/portraitn/item/public.1.e137c1ac.yS1WqOXfSWEasOYJ2-0pvQ', null, 'b0dd3697a192885d7c055db46155b26a', null, null, 0, '2023-08-06 14:14:22', '2023-08-06 14:39:37', 0, 1, '1');
#酒店预订表
CREATE TABLE IF NOT EXISTS hotel_reservation (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT '预约ID',
 idCard VARCHAR(18) NOT NULL COMMENT '身份证号',
   phoneNumber VARCHAR(20) NOT NULL COMMENT '手机号',
   checkInDate varchar(256)  NOT NULL COMMENT '入住日期',
 checkOutDate varchar(256)  NOT NULL COMMENT '离店日期',
 roomType VARCHAR(100) NOT NULL COMMENT '房间类型',
 numberOfRooms INT NOT NULL DEFAULT 1 COMMENT '房间数量',
  specialRequests TEXT COMMENT '特殊要求',
  reservationStatus VARCHAR(50) NOT NULL DEFAULT 'Pending' COMMENT '预约状态',
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   UNIQUE (idCard, phoneNumber)
)
    COMMENT='酒店预约表';
#导入示例数据
INSERT INTO yupi.hotel_reservation ( idCard, phoneNumber, checkInDate, checkOutDate, roomType, numberOfRooms, specialRequests, reservationStatus, createdAt, updatedAt) VALUES ( '420102199001011234', '13800138000', '2023-08-06', '2023-08-08', '单人间', 1, '无', 'Pending', '2023-08-06 14:14:22', '2023-08-06 14:39:37');
