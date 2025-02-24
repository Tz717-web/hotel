import { defineStore } from "pinia";
import { ref } from "vue";
import { getCurrentUser } from "@/api/user";

// 定义 User 接口
export interface User {
  username: string;
  id: string;
  avatarUrl: string;
  planetCode: string;
  userAccount: string;
  userRole: string;
  // 其他用户属性...
}

export const useLoginUserStore = defineStore("loginUser", () => {
  // 使用 User 接口或 null 作为类型
  const loginUser = ref<User>({
    username: "",
    planetCode: "",
    userAccount: "",
    id: "",
    avatarUrl: "",
    userRole: "",
  });

  async function fetchLoginUser() {
    const res = await getCurrentUser();
    if (res.data.code === 0 && res.data.data) {
      // 类型断言，确保 res.data.data 符合 User 接口
      loginUser.value = res.data.data as User;
    }
  }

  function setLoginUser(newLoginUser: User) {
    loginUser.value = newLoginUser;
  }

  return { loginUser, setLoginUser, fetchLoginUser };
});
