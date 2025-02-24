<template>
  <div id="userManagePage">
    <a-input-search
      style="width: 320px; height: 60px; padding-top: 10px; margin-left: 10px"
      v-model:value="searchvalue"
      placeholder="请输入用户名或账号进行搜索"
      enter-button
      @search="onSearch"
    />
    <a-table :columns="columns" :data-source="data" style="margin-left: 5px">
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
                margin-right: 5px;
              "
              >编辑</a-button
            >
            <a-button danger @click="deleteuser(record.id)">删除</a-button>
          </span>
        </template>
      </template>
    </a-table>
  </div>
</template>

<script lang="ts" setup>
import { searchUsers, deleteUser } from "@/api/user";
import { message } from "ant-design-vue";
import dayjs from "dayjs";

import { ref } from "vue";

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

// 删除用户
const deleteuser = async (id: string) => {
  const res = await deleteUser(id);
  if (res.data.code === 0) {
    message.success("删除成功");
    fatchData();
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
  fatchData(searchvalue.value);
};

// 数据
const data = ref([]);

// 获取数据
const fatchData = async (userAccount = "") => {
  const res = await searchUsers(userAccount);
  if (res.data.data) {
    data.value = res.data.data;
  } else {
    message.error("获取用户列表失败");
  }
};

fatchData();
</script>

<style></style>
