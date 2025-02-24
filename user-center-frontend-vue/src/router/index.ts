import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomePage from "@/pages/HomePage.vue";
import UserRegiserPage from "@/pages/user/UserRegiserPage.vue";
import UserManagePage from "@/pages/admin/UserManagePage.vue";
import UserLoginPage from "@/pages/user/UserLoginPage.vue";
import RoomOnePage from "@/pages/user/RoomOnePage.vue";
import RoomtwoPage from "@/pages/user/RoomtwoPage.vue";
import RoomthreePage from "@/pages/user/RoomthreePage.vue";
import RoomfourPage from "@/pages/user/RoomfourPage.vue";
import Order from "@/pages/user/OrderReserve.vue";
import orderMessage from "@/pages/user/orderMessage.vue";
import holtel from "@/pages/user/holtel.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: HomePage,
  },
  {
    path: "/user/login",
    name: "userLogin",
    component: UserLoginPage,
  },
  {
    path: "/user/register",
    name: "userRegister",
    component: UserRegiserPage,
  },
  {
    path: "/admin/userManage",
    name: "userManage",
    component: UserManagePage,
  },
  {
    path: "/user/RoomOnePage",
    name: "RoomOne",
    component: RoomOnePage,
  },
  {
    path: "/user/RoomtwoPage",
    name: "RoomTwo",
    component: RoomtwoPage,
  },
  {
    path: "/user/RoomThreePage",
    name: "RoomThree",
    component: RoomthreePage,
  },
  {
    path: "/user/RoomFourPage",
    name: "RoomFour",
    component: RoomfourPage,
  },
  //注意命名规范小写接大写最好
  {
    path: "/user/Order",
    name: "OrderReserve",
    component: Order,
  },
  {
    path: "/user/orderMessage",
    name: "orderMessage",
    component: orderMessage,
  },
  {
    path: "/user/holtel",
    name: "holtel",
    component: holtel,
  },
  // 路由故障解决方案
  {
    path: "/:pathMatch(.*)*",
    meta: {
      title: "找不到此页面",
    },
    component: (to) => {
      // 获取路径的最后一个字符串
      const segments = to.path.split("/");
      const lastSegment = segments[segments.length - 1];
      return import(`@/pages/user/${lastSegment}.vue`).catch(() => {
        // 如果找不到组件，可以返回一个默认的组件或处理错误
        return import("@/pages/HomePage.vue");
      });
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
});

export default router;
