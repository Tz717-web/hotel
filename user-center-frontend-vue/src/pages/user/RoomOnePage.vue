<template>
  <div id="room-one">
    <div class="room-detail">
      <!-- 房间图片 -->
      <img :src="roomImage" alt="房间图片" class="room-image" />
      <!-- 详情信息 -->
      <div class="detail-info">
        <!-- 基本信息 -->
        <div class="basic-info">
          <p><strong>类型:</strong> {{ roomType }}</p>
          <p><strong>间数:</strong> {{ roomCount }}</p>
          <p style="margin-bottom: 0"><strong>酒店:</strong> {{ hotelName }}</p>
        </div>
        <!-- 洗浴用品 -->
        <div class="bath-supplies">
          <h3 style="padding: 0">洗浴用品</h3>
          <ul>
            <li v-for="(item, index) in bathItems" :key="index">
              {{ "✅" + item }}
            </li>
          </ul>
        </div>
        <!-- 卫浴设施 -->
        <div class="bath-facilities">
          <h3>卫浴设施</h3>
          <ul>
            <li v-for="(item, index) in bathFacilities" :key="index">
              {{ "✅" + item }}
            </li>
          </ul>
        </div>
        <!-- 客房设施 -->
        <div class="room-facilities">
          <h3>客房设施</h3>
          <ul>
            <li v-for="(item, index) in roomFacilities" :key="index">
              {{ "✅" + item }}
            </li>
          </ul>
        </div>
        <!-- 媒体科技 -->
        <div class="media-tech" style="margin-bottom: 5px">
          <h3>媒体科技</h3>
          <ul>
            <li v-for="(item, index) in mediaTech" :key="index">
              {{ "✅" + item }}
            </li>
          </ul>
        </div>
        <!-- 价格和操作按钮 -->
        <div class="price-actions">
          <p
            style="
              font-size: 18px;
              color: #ff2828;
              font-weight: 550;
              margin-top: 0;
              margin-left: 10px;
            "
          >
            ¥{{ price }}
          </p>
          <button class="add-to-favorites">加入收藏</button>
          <button class="book-now" @click="ReserveRoom">立即预订</button>
        </div>
      </div>
    </div>
  </div>
  <a-form
    style="
      max-width: 800px;
      margin: 0 auto;
      padding-right: 100px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    "
    v-bind="layout"
    v-show="isFormVisible"
    class="reservation-form"
    :model="Hotel"
    name="nest-messages"
    autocomplete="off"
    @finish="handleSubmit"
  >
    <div
      style="
        margin-bottom: 20px;
        margin-left: 100px;
        text-align: center;
        font-size: 24px;
        font-weight: 600;
        color: #2ac2d2;
      "
    >
      酒店预约表单
    </div>
    <a-form-item
      label="身份证号"
      name="idCard"
      value="请输入您的身份证号"
      :rules="[
        { required: true, message: '请输入身份证号!' },
        { min: 13, message: '手机号长度至少为13位!' },
      ]"
    >
      <a-input v-model:value="Hotel.idCard" placeholder="请输入您的身份证号" />
    </a-form-item>

    <a-form-item
      label="入住日期"
      :rules="[{ required: true, message: '请输入入住日期!' }]"
    >
      <a-input
        v-model:value="Hotel.checkInDate"
        placeholder="(格式:YYYY-MM-DD)"
      />
    </a-form-item>

    <a-form-item
      label="入住日期"
      :rules="[{ required: true, message: '请输入离开日期!' }]"
    >
      <a-input
        v-model:value="Hotel.checkOutDate"
        placeholder="(格式:YYYY-MM-DD)"
      />
    </a-form-item>

    <a-form-item name="specialRequests" label="特殊要求">
      <a-textarea v-model:value="Hotel.specialRequests" />
    </a-form-item>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 11 }">
      <a-button type="primary" html-type="submit" style="margin-right: 10px"
        >提交</a-button
      >
      <a-button @click="isFormVisible = false">取消</a-button>
    </a-form-item>
  </a-form>
</template>

<script lang="ts" setup>
import { reactive } from "vue";
import { message } from "ant-design-vue";
import { useRouter } from "vue-router";
import { hotelRegister } from "@/api/user";
import { ref } from "vue";
interface hotel {
  id: string;
  idCard: string;
  phoneNumber: string;
  checkInDate: string;
  checkOutDate: string;
  specialRequests: string;
  roomType: string;
  reservationStatus: string;
  numberOfRooms: string;
}

const roomImage = new URL("@/img/总统套房.jpg", import.meta.url).href;
const roomType = "豪华总统房";
const roomCount = 50;
const hotelName = "小猪酒店";
const bathItems = [
  "牙刷",
  "牙膏",
  "沐浴露",
  "洗发水",
  "护发素",
  "香皂",
  "梳子",
  "剃须刀",
];
const bathFacilities = [
  "私人浴室",
  "私人卫生间",
  "吹风机",
  "毛巾",
  "浴巾",
  "拖鞋",
];
const roomFacilities = ["空调", "手动窗帘", "鸭绒被", "毛毯"];
const mediaTech = ["电视机", "有线频道", "智能门锁", "电影服务", "智能客控"];
const price = 1000;

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

const router = useRouter();
const handleSubmit = async () => {
  // ... 提交逻辑 ...

  isFormVisible.value = false; // 提交后隐藏表单
  const res = await hotelRegister(Hotel);
  console.log(Hotel);
  if (Hotel.idCard === "") {
    message.error("身份证号不能为空");
    return;
  }
  if (res.data.data) {
    //注册成功
    message.success("注册成功");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    console.log(res.data.data);
    message.error("注册失败" + res.data.description);
  }
};
const Hotel = reactive<hotel>({
  id: "",
  idCard: "",
  phoneNumber: "",
  checkInDate: "",
  checkOutDate: "",
  specialRequests: "",
  roomType: "豪华总统房",
  reservationStatus: "",
  numberOfRooms: "1",
});
import { useLoginUserStore } from "@/store/useLoginUserStore";
const userlogin = useLoginUserStore();
if (userlogin?.loginUser?.id) {
  Hotel.phoneNumber = userlogin.loginUser.userAccount;
}
const isFormVisible = ref(false);

function ReserveRoom() {
  isFormVisible.value = true;
}
</script>

<style scoped>
.reservation-form {
  display: block;
  transition: all 0.3s ease; /* 添加过渡效果 */
  position: absolute; /* 绝对定位 */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 8px; /* 添加圆角 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  width: 700px;
  height: 600px;
}
#room-one {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  border-radius: 8px; /* 添加圆角 */
}

.room-detail {
  display: flex;
  align-items: flex-start;
}

.room-image {
  height: 400px;
  border-radius: 8px; /* 添加圆角 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  margin-right: 20px; /* 添加右侧边距 */
}

.detail-info {
  width: 70%; /* 调整文字区域宽度以适应剩余空间 */
  height: 100%; /* 确保保护区域高度与图片高度一致 */
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.basic-info,
.bath-supplies,
.bath-facilities,
.room-facilities,
.media-tech,
.price-actions {
  margin-bottom: 10px; /* 添加段落间距 */
}

.basic-info p {
  margin-bottom: 5px; /* 减小行间距 */
  font-size: 14px; /* 减小字体大小 */
}

h3 {
  font-weight: 600;
  margin-top: 5px;
  margin-bottom: 5px;
  font-size: 16px; /* 减小标题字体大小 */
}

ul {
  display: inline;
  list-style-type: none;
  padding: 0;
  margin-bottom: 5px; /* 添加列表间距 */
}

li {
  display: inline;
  margin-bottom: 5px; /* 减小行间距 */
  font-size: 14px; /* 减小列表字体大小 */
  margin: 0 5px; /* 为列表项之间添加间距 */
}

.price-actions {
  margin-top: 10px;
}

.price-actions p {
  margin-bottom: 5px; /* 减小行间距 */
  font-size: 14px; /* 减小价格字体大小 */
}

.add-to-favorites,
.book-now {
  padding: 8px 16px; /* 减小按钮内边距 */
  margin-right: 10px;
  cursor: pointer;
  border-radius: 6px; /* 添加按钮圆角 */
  border: none;
  font-size: 14px; /* 减小按钮字体大小 */
  font-weight: 545; /* 加粗按钮文字 */
}

.add-to-favorites {
  background-color: #fe8510;
  color: white;
}

.book-now {
  background-color: #1677ff;
  color: white;
}

strong {
  margin-right: 5px; /* 为标签和内容之间添加间距 */
}
/*酒店预约表单*/
</style>
