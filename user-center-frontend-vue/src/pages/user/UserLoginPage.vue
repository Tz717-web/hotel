<template>
  <div id="user-login-page">
    <h1 class="title">用户登录中心</h1>
    <a-form
      style="max-width: 500px; margin: 30px auto"
      :model="formState"
      name="basic"
      label-align="left"
      :label-col="{ span: 3 }"
      :wrapper-col="{ span: 20 }"
      autocomplete="off"
      @finish="handleSubmit"
    >
      <a-form-item
        label="账号"
        name="userAccount"
        value="请输入您的账号!"
        :rules="[
          { required: true, message: '请输入您的账号!' },
          { min: 4, message: '手机号长度至少为4位!' },
        ]"
      >
        <a-input
          v-model:value="formState.userAccount"
          placeholder="请输入您的手机号"
        />
      </a-form-item>

      <a-form-item
        label="密码"
        name="userPassword"
        value="请输入您的密码!"
        :rules="[
          { required: true, message: '请输入您的密码!' },
          { min: 8, message: '密码长度至少为8位!' },
        ]"
      >
        <a-input-password
          v-model:value="formState.userPassword"
          placeholder="请输入您的密码"
        />
      </a-form-item>
      <!-- <a-form-item
        label="房间号"
        name="房间号"
        value="请输入您的房间号（选填）!"
      >
        <a-input
          v-model:value="formState.roomNumber"
          placeholder="请输入您的房间号（选填）"
        />
      </a-form-item> -->

      <a-form-item name="remember" :wrapper-col="{ offset: 3, span: 20 }">
        <a-checkbox v-model:checked="formState.remember"
          >同意《用户协议》</a-checkbox
        >
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 3, span: 20 }">
        <a-button type="primary" html-type="submit">登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { userLogin } from "@/api/user";
import { useLoginUserStore } from "@/store/useLoginUserStore";
import { message } from "ant-design-vue";
import { reactive } from "vue";
import { useRouter } from "vue-router";
const loginUserStore = useLoginUserStore();
const router = useRouter();
interface FormState {
  userAccount: string; // 用户账号
  userPassword: string; // 用户密码
  checkPassword: string; // 确认密码
  remember: boolean; // 是否同意条约
  roomNumber: string; // 房间号（已废弃）
  planetCode: string; //房间号
}
//提交表单
const handleSubmit = async (values: FormState) => {
  const res = await userLogin(values);
  if (res.data.code === 0 && res.data.data) {
    await loginUserStore.fetchLoginUser();
    message.success("登录成功");
    router.push({
      path: "/",
      replace: true,
    });
  } else {
    message.error("登录失败重新请输入" + res.data.description);
  }
  console.log("用户信息:", values.userAccount);
};
const formState = reactive<FormState>({
  userAccount: "",
  userPassword: "",
  remember: true,
  roomNumber: "", // 房间号（已废弃）
  planetCode: "", //房间号
  checkPassword: "",
});
</script>
<style>
#user-login-page {
  text-align: center;
}
.title {
  font-weight: 500;
  margin-bottom: 20px;
  margin-top: 20px;
}
</style>
