import { message } from "ant-design-vue";
import router from "@/router/index";
import { getCurrentUser } from "@/api/user";
/**
 * 全局权限校验
 */

router.beforeEach(async (to, from, next) => {
  const res = await getCurrentUser();
  const user = res.data.data;
  console.log(user);

  const toUrl = to.fullPath;
  if (toUrl.startsWith("/user/Order")) {
    if (!user) {
      message.error("请先登录");
      next(`/user/login?redirect=${to.fullPath}`);
    }
  }
  if (toUrl.startsWith("/admin") || toUrl.startsWith("/user/holtel")) {
    if (!user || user.userRole !== 1) {
      message.error("没有权限");
      next(`/user/login?redirect=${to.fullPath}`);
      return;
    }
  }
  next();
});
