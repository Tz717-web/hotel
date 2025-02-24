import { createApp } from "vue";
import App from "./App.vue";

import Antd from "ant-design-vue";
import { createPinia } from "pinia";
import "ant-design-vue/dist/reset.css";
import "@/access";
import router from "./router";
const pinia = createPinia();
const app = createApp(App).use(pinia).use(Antd).use(router);
app.config.globalProperties.$moment = moment; // 使用 app.config.globalProperties 来注册全局属性
app.mount("#app");
import moment from "moment";
import "moment/locale/zh-cn";
moment.locale("zh-cn"); //设置语言 或 moment.lang('zh-cn');
