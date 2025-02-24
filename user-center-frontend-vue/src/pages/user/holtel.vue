<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <a-layout>
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <user-outlined />
          <span>预订订单</span>
        </a-menu-item>
        <a-menu-item key="2">
          <video-camera-outlined />
          <span @click="goTo">入住订单</span>
        </a-menu-item>
        <a-menu-item key="3">
          <upload-outlined />
          <span>我的收藏</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <menu-unfold-outlined
          v-if="collapsed"
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined
          v-else
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
        <a-input-search
          style="
            width: 320px;
            height: 60px;
            padding-top: 10px;
            margin-top: 10px;
            margin-left: 12px;
          "
          v-model:value="searchvalue"
          placeholder="请输入用户名或账号进行搜索"
          enter-button
          @search="onSearch"
        />
      </a-layout-header>
      <a-layout-content
        :style="{
          margin: '10px',
          padding: '10px',
          background: '#fff',
          minHeight: '500px',
          display: 'flex',
        }"
      >
        <div id="userManagePage">
          <a-table
            :columns="columns"
            :data-source="data"
            style="margin-left: 5px"
          >
            <template #bodyCell="{ column, record }">
              <template v-if="column.dataIndex === 'createdAt'">
                {{ dayjs(record.createdAt).format("YYYY-MM-DD HH:mm:ss") }}
              </template>
              <template v-else-if="column.key === 'action'">
                <span>
                  <a-button
                    style="
                      color: green;
                      border: 1px solid green;
                      font-weight: 500;
                      margin-right: 5px;
                    "
                    @click="EditHotel(record.phoneNumber)"
                    >编辑</a-button
                  >
                  <a-button danger @click="DeleteHotel(record.id)"
                    >删除</a-button
                  >
                </span>
              </template>
            </template>
          </a-table>
        </div>
        <!-- 编辑表单 -->
        <a-form
          style="
            position: absolute;
            width: 800px;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            padding-right: 100px;
            padding-top: 30px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          "
          v-bind="layout"
          v-show="isFormVisible"
          class="reservation-form"
          name="nest-messages"
          autocomplete="off"
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
            酒店信息修改表单
          </div>
          <a-form-item label="id" name="id" style="display: none">
            <a-input v-model:value="Hotel.id" placeholder="请输入您的手机号" />
          </a-form-item>
          <a-form-item
            label="身份证号"
            name="idCard"
            value="请输入您的身份证号"
            :rules="[
              { required: true, message: '请输入身份证号!' },
              { min: 13, message: '手机号长度至少为13位!' },
            ]"
          >
            <a-input
              v-model:value="Hotel.idCard"
              placeholder="请输入您的身份证号"
            />
          </a-form-item>
          <a-form-item label="手机号" name="phone">
            <a-input
              v-model:value="Hotel.phoneNumber"
              placeholder="请输入您的手机号"
            />
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

          <a-form-item label="房间类型" name="roomType">
            <a-input
              v-model:value="Hotel.roomType"
              placeholder="请输入您的房间类型"
            />
          </a-form-item>
          <a-form-item label="房间号" name="numberOfRooms">
            <a-input
              v-model:value="Hotel.numberOfRooms"
              placeholder="请输入您的房间号"
            />
          </a-form-item>
          <a-form-item label="预约状态" name="reservationStatus">
            <a-input
              v-model:value="Hotel.reservationStatus"
              placeholder="预约状态"
            />
          </a-form-item>
          <a-form-item name="specialRequests" label="特殊要求">
            <a-textarea v-model:value="Hotel.specialRequests" />
          </a-form-item>
          <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 11 }">
            <a-button
              type="primary"
              html-type="submit"
              style="margin-right: 10px"
              @click="submitForm"
              >修改</a-button
            >
            <a-button @click="isFormVisible = false">取消</a-button>
          </a-form-item>
        </a-form>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
//侧边栏
import { ref } from "vue";
import {
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
} from "@ant-design/icons-vue";
const selectedKeys = ref<string[]>(["1"]);
const collapsed = ref<boolean>(false);
//表格
import { hotelSearch, deleteHotel, hotelUpdate } from "@/api/user";
import { message } from "ant-design-vue";
import dayjs from "dayjs";
import { reactive } from "vue";
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
const Hotel = reactive<hotel>({
  id: "",
  idCard: "",
  phoneNumber: "",
  checkInDate: "",
  checkOutDate: "",
  specialRequests: "",
  roomType: "",
  reservationStatus: "",
  numberOfRooms: "",
});
const columns = [
  {
    title: "身份证号",
    dataIndex: "idCard",
  },
  {
    title: "手机号",
    dataIndex: "phoneNumber",
  },
  {
    title: "入住时间",
    dataIndex: "checkInDate",
  },
  {
    title: "离店时间",
    dataIndex: "checkOutDate",
  },
  { title: "创建时间", dataIndex: "createdAt" },
  {
    title: "房间类型",
    dataIndex: "roomType",
  },
  {
    title: "房间号",
    dataIndex: "numberOfRooms",
  },
  {
    title: "预订状态",
    dataIndex: "reservationStatus",
  },
  {
    title: "操作",
    key: "action",
  },
];

// 删除用户
const DeleteHotel = async (id: string) => {
  const res = await deleteHotel(id);
  if (res.data.code === 0) {
    message.success("删除成功");
    HotelSearch();
  } else {
    message.error("删除失败");
  }
};

// 搜索框
const searchvalue = ref("");

// 搜索
const onSearch = async () => {
  HotelSearch(searchvalue.value);
};

// 数据
const data = ref([]);

// 获取数据
const HotelSearch = async (phoneNumber = "") => {
  const res = await hotelSearch(phoneNumber);
  if (res.data.data) {
    data.value = res.data.data;
  } else {
    message.error("获取用户列表失败");
  }
};

HotelSearch();
//切换路由
import { useRouter } from "vue-router";
const router = useRouter();
function goTo() {
  router.push("/");
}
//编辑表单
const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

const submitForm = async () => {
  //todo修改接口
  console.log("修改用户信息", Hotel);
  const res = await hotelUpdate(Hotel);
  if (res.data.code === 0) {
    message.success("修改成功");
    HotelSearch();
    isFormVisible.value = false; // 提交后隐藏表单
  } else {
    message.error("修改失败");
  }
};

const isFormVisible = ref(false);
//数据回显
const EditHotel = async (phoneNumber: string) => {
  isFormVisible.value = true;
  const res = await hotelSearch(phoneNumber);
  if (res.data.data) {
    Hotel.id = res.data.data[0].id;
    Hotel.idCard = res.data.data[0].idCard;
    Hotel.phoneNumber = res.data.data[0].phoneNumber;
    Hotel.checkInDate = res.data.data[0].checkInDate;
    Hotel.checkOutDate = res.data.data[0].checkOutDate;
    Hotel.specialRequests = res.data.data[0].specialRequests;
    Hotel.roomType = res.data.data[0].roomType;
    Hotel.reservationStatus = res.data.data[0].reservationStatus;
    Hotel.numberOfRooms = res.data.data[0].numberOfRooms;
  } else {
    message.error("获取用户列表失败");
  }
};
</script>

<style scoped>
.table {
  width: 100%;
}
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.3);
  margin: 16px;
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
