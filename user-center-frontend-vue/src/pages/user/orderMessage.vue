<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <a-layout>
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <user-outlined />
          <span> 个人信息</span>
        </a-menu-item>
        <a-menu-item key="2" @click="goToRserve">
          <video-camera-outlined @click="goToRserve" />
          <span @click="goToRserve"> 预约订单</span>
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
      </a-layout-header>
      <a-layout-content
        :style="{
          margin: '10px',
          padding: '10px',
          background: '#fff',
          minHeight: '500px',
        }"
      >
        <a-table
          :columns="columns"
          :data-source="data"
          style="margin-left: 5px"
        >
          <template #bodyCell="{ column, record }">
            <template v-if="column.dataIndex === 'avatarUrl'">
              <a-image
                v-if="record.avatarUrl"
                :src="record.avatarUrl"
                :width="50"
              />
              <img v-else src="@/img/6zSMDOe1CqbznDn.jpg" width="50" />
            </template>
            <template v-else-if="column.dataIndex === 'username'">
              <span v-if="record.username">{{ record.username }}</span>
              <span v-else>房主</span>
            </template>
            <template v-else-if="column.dataIndex === 'gender'">
              <div v-if="record.gender === 1">
                <a-tag color="blue">男</a-tag>
              </div>
              <div v-else-if="record.gender === 2">
                <a-tag color="pink">女</a-tag>
              </div>
            </template>
            <template v-else-if="column.dataIndex === 'userRole'">
              <div v-if="record.userRole === 1">
                <a-tag color="green">管理员</a-tag>
              </div>
              <div v-else-if="record.userRole === 0">
                <a-tag color="blue">普通用户</a-tag>
              </div>
            </template>
            <template v-else-if="column.dataIndex === 'createTime'">
              {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
            </template>
            <template v-else-if="column.key === 'action'">
              <span>
                <a-button
                  style="
                    color: green;
                    border: 1px solid green;
                    font-weight: 500;
                  "
                  @click="EditUsers(record.userAccount)"
                  >编辑</a-button
                >
              </span>
            </template>
          </template>
        </a-table>
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
          <a-form-item label="用户名" name="username">
            <a-input
              v-model:value="Users.username"
              placeholder="请输入您的用户名"
            />
          </a-form-item>
          <a-form-item label="手机号" name="userAccount">
            <a-input
              v-model:value="Users.userAccount"
              placeholder="请输入您的手机号"
            />
          </a-form-item>
          <a-form-item label="密码" name="password">
            <a-input
              v-model:value="Users.userPassword"
              placeholder="请输入您的密码"
            />
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
import { searchUsers, updateUser } from "@/api/user";
import { message } from "ant-design-vue";
import dayjs from "dayjs";
import { useLoginUserStore } from "@/store/useLoginUserStore";
import { reactive } from "vue";
interface users {
  id: string;
  username: string;
  userAccount: string;
  userPassword: string;
}
const Users = reactive<users>({
  id: "",
  username: "",
  userAccount: "",
  userPassword: "",
});
const columns = [
  {
    title: "用户名",
    dataIndex: "username",
  },
  {
    title: "账号",
    dataIndex: "userAccount",
  },
  {
    title: "头像",
    dataIndex: "avatarUrl",
  },
  {
    title: "房号",
    dataIndex: "planetCode",
  },
  { title: "创建时间", dataIndex: "createTime" },
  {
    title: "用户角色",
    dataIndex: "userRole",
  },
  {
    title: "操作",
    key: "action",
  },
];

// 数据
const data = ref([]);

// 获取数据
const loginUserStore = useLoginUserStore();
const useraccount = loginUserStore.loginUser?.userAccount;
const fatchData = async (userAccount = useraccount) => {
  const res = await searchUsers(userAccount);
  if (res.data.data) {
    data.value = res.data.data;
  } else {
    message.error("获取用户列表失败");
  }
};

fatchData();
const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};
//修改表单提交
const submitForm = async () => {
  const res = await updateUser(Users);
  if (res.data.code === 0) {
    message.success("修改成功");
    isFormVisible.value = false;
    fatchData();
  } else {
    message.error("修改失败");
  }
};

const isFormVisible = ref(false);
//数据回显
const EditUsers = async (userAccount: string) => {
  isFormVisible.value = true;
  const res = await searchUsers(userAccount);
  console.log(res.data.data[0]);

  if (res.data.data) {
    Users.id = res.data.data[0].id;
    Users.userAccount = res.data.data[0].userAccount;
    Users.username = res.data.data[0].username;
    Users.userPassword = res.data.data[0].userPassword;
  } else {
    message.error("获取用户列表失败");
  }
};
//切换路由
import { useRouter } from "vue-router";
const router = useRouter();
function goToRserve() {
  router.push("/user/Order");
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
/*图片上传 */
.upload-list-inline :deep(.ant-upload-list-item) {
  float: left;
  width: 200px;
  margin-right: 8px;
}
.upload-list-inline [class*="-upload-list-rtl"] :deep(.ant-upload-list-item) {
  float: right;
}
</style>
