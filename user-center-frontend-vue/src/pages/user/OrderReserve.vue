<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <a-layout>
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <user-outlined />
          <span>预约订单</span>
        </a-menu-item>
        <a-menu-item key="2" @click="goToMessage">
          <video-camera-outlined @click="goToMessage" />
          <span @click="goToMessage"> 个人信息</span>
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
          placeholder="请输入预约手机号进行搜索"
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
                  <a-button danger @click="DeleteHotel(record.id)"
                    >取消预约</a-button
                  >
                </span>
              </template>
            </template>
          </a-table>
        </div>
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
import { hotelSearch, deleteHotel } from "@/api/user";
import { message } from "ant-design-vue";
import dayjs from "dayjs";
import { useLoginUserStore } from "@/store/useLoginUserStore";

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
//编辑用户、
// const updateLocale = async (userAccount: string) => {
//   const res = await updateLocale(userAccount);
//   if (res.data.code === 0) {
//     message.success("编辑成功");
//     fatchData();
//   } else {
//     message.error("编辑失败");
//   }
// };

// 搜索框
const searchvalue = ref("");

// 搜索
const onSearch = async () => {
  HotelSearch(searchvalue.value);
};

// 数据
const data = ref([]);
const loginUserStore = useLoginUserStore();
const phonenumber = loginUserStore.loginUser.userAccount;
// 获取数据
const HotelSearch = async (phoneNumber = phonenumber) => {
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
function goToMessage() {
  router.push("/user/orderMessage");
}
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
