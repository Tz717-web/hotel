<template>
  <div id="globheard">
    <a-row :wrap="false">
      <a-col flex="200px">
        <div class="title-bar">
          <img
            class="logo"
            src="@\img\微信图片_20241210140437.jpg"
            alt="logo"
          />
          <div class="title">小猪酒店预订系统</div>
        </div></a-col
      >
      <a-col flex="auto"
        ><a-menu
          v-model:selectedKeys="current"
          mode="horizontal"
          :items="items"
          @click="handleClick"
      /></a-col>
      <a-col flex="50px">
        <div class="user-login-help">
          <div class="userName" v-if="loginUserStore?.loginUser?.id">
            <a-button type="primary" herf="/" class="userName">{{
              loginUserStore.loginUser.username ?? "用户中心"
            }}</a-button>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script lang="ts" setup>
import { h, ref } from "vue";
import * as iconsVue from "@ant-design/icons-vue";
import { MenuProps } from "ant-design-vue";
import { useRouter } from "vue-router";
import { useLoginUserStore } from "@/store/useLoginUserStore";
const loginUserStore = useLoginUserStore();
const router = useRouter();
//路由跳转事件
const handleClick = ({ key }: { key: string }) => {
  router.push({
    path: key,
  });
};
const current = ref<string[]>(["mail"]);
// 监听路由变化，更新当前选中菜单
router.afterEach((to) => {
  current.value = [to.path];
});
const items = ref<MenuProps["items"]>([
  {
    key: "/",
    icon: () => h(iconsVue.AimOutlined),
    label: "首页",
    title: "首页",
  },
  {
    key: "/user/login",
    icon: () => h(iconsVue.UserOutlined),
    label: "用户中心",
    title: "用户中心",
  },
  {
    key: "/user/register",
    icon: () => h(iconsVue.UserOutlined),
    label: "用户注册",
    title: "用户注册",
  },

  {
    key: "/admin/userManage",
    icon: () => h(iconsVue.SettingOutlined),
    label: "管理员入口",
    title: "管理员入口",
    children: [
      {
        label: "用户管理",
        key: "/admin/userManage",
      },
      {
        label: "酒店信息管理",
        key: "/user/holtel",
      },
    ],
  },
  {
    key: "/user/Order",
    icon: () => h(iconsVue.UserOutlined),
    label: " 个人中心",
    title: "个人中心",
  },
]);
</script>
<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}
.logo {
  width: 32px;
  height: 32px;
}
.title {
  font-size: 15px;
  color: black;
  margin-left: 16px;
  font-weight: bold;
  line-height: 32px;
}
.userName {
  font-size: 14px;
  font-weight: 550;
}
</style>
