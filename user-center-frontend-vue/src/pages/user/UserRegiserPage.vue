<template>
  <div id="user-regiser-page">
    <h1 class="title">用户注册中心</h1>
    <a-form
      style="max-width: 500px; margin: 30px auto"
      :model="formState"
      name="basic"
      label-align="left"
      :label-col="{ span: 4 }"
      :wrapper-col="{ span: 20 }"
      autocomplete="off"
      @finish="handleSubmit"
    >
      <a-form-item
        label="账号"
        name="userAccount"
        value="请输入您的账号!"
        :rules="[
          { required: true, message: '请输入您的手机号!' },
          { min: 11, message: '手机号长度至少为11位!' },
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
          { min: 6, message: '密码长度至少为6位!' },
        ]"
      >
        <a-input-password
          v-model:value="formState.userPassword"
          placeholder="请输入您的密码"
        />
      </a-form-item>
      <a-form-item
        label="确认密码"
        name="checkPassword"
        value="请在次输入您的密码!"
        :rules="[
          { required: true, message: '请输入您的密码!' },
          { min: 6, message: '确认密码长度至少为6位!' },
        ]"
      >
        <a-input-password
          v-model:value="formState.checkPassword"
          placeholder="请再次输入您的密码"
        />
      </a-form-item>
      <a-form-item
        label="星球号"
        name="planetCode"
        value="请输入您的星球号!"
        :rules="[{ required: true, message: '请输入您的星球号!' }]"
      >
        <a-input
          v-model:value="formState.planetCode"
          placeholder="请输入您的星球号（前1000可领取优惠券）"
        />
      </a-form-item>

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
import { userRegister } from "@/api/user";
// import { useLoginUserStore } from "@/store/useLoginUserStore";
import { message } from "ant-design-vue";
import { reactive } from "vue";
import { useRouter } from "vue-router";
// const loginUserStore = useLoginUserStore();
const router = useRouter();
interface FormState {
  userAccount: string; // 用户账号
  userPassword: string; // 用户密码
  checkPassword: string; // 确认密码
  remember: boolean; // 是否同意条约
  roomNumber: string; // 房间号
  planetCode: string; //编号
}
//提交表单
const handleSubmit = async (values: FormState) => {
  const res = await userRegister(values);
  if (
    formState.userAccount === "" ||
    formState.userPassword === "" ||
    formState.checkPassword === "" ||
    formState.planetCode === ""
  ) {
    message.error("账号或密码不能为空");
    return;
  }
  if (values.userPassword !== values.checkPassword) {
    message.error("两次密码输入不一致");
    return;
  }
  if (res.data.code === 0 && res.data.data) {
    //注册成功
    message.success("注册成功");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    console.log(res.data);
    message.error("注册失败" + res.data.description);
  }
};
const formState = reactive<FormState>({
  userAccount: "",
  userPassword: "",
  remember: true,
  roomNumber: "",
  planetCode: "",
  checkPassword: "",
});
</script>
<style>
#user-regiser-page {
  text-align: center;
}
.title {
  font-weight: 500;
  margin-bottom: 20px;
  margin-top: 20px;
}
</style>
