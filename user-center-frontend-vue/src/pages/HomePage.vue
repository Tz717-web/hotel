<template>
  <div class="HomePage">
    <main class="main-content">
      <section class="welcome">
        <div style="position: absolute" class="welcome-text">
          旅行订酒店,就上小猪酒店预订网.
        </div>
        <img
          src="@/img/微信图片_20250208151928.jpg"
          alt="风景"
          style="
            width: 1450px;
            height: 250px;
            border-bottom-left-radius: 40%;
            border-bottom-right-radius: 40%;
          "
        />
      </section>

      <section class="cards-container">
        <div class="card">
          <h2>酒店信息管理</h2>
          <p>管理和查看酒店的基本信息</p>
          <a class="btn" @click="goToRoute('')">进入</a>
        </div>

        <div class="card">
          <h2>用户登录</h2>
          <p>用于用户进行登录酒店系统</p>
          <a class="btn" @click="goToRoute('/user/login')">进入</a>
        </div>

        <div class="card">
          <h2>用户注册</h2>
          <p>用于用户注册个人信息</p>
          <a class="btn" @click="goToRoute('/user/register')">进入</a>
        </div>

        <div class="card">
          <h2>用户管理</h2>
          <p>查看和管理所有用户信息。</p>
          <a class="btn" @click="goToRoute('/admin/userManage')">进入</a>
        </div>
      </section>
      <div style="margin-top: 20px; font-size: 25px; font-weight: 600">
        平台优质酒店
      </div>
      <section class="room-container">
        <div class="room">
          <img class="room-img" src="@/img/总统套房.jpg" alt="总统套房" />
          <h2>豪华总统套房</h2>
          <p>豪华总统套房，享受一流的服务。</p>
          <h4>¥1000起</h4>
          <a class="btn" @click="goToRoute('/user/RoomOnePage')">预订</a>
        </div>
        <div class="room">
          <img class="room-img" src="@/img/普通商务房.jpg" alt="总统套房" />
          <h2>实用商务套房</h2>
          <p>实用商务套房，提供舒适的环境。</p>
          <h4>¥800起</h4>
          <a class="btn" @click="goToRoute('/user/RoomtwoPage')">预订</a>
        </div>
        <div class="room">
          <img class="room-img" src="@/img/双人房间.jpg" alt="总统套房" />
          <h2>惬意双人套房</h2>
          <p>惬意双人套房，旅途温馨相伴。</p>
          <h4>¥600起</h4>
          <a class="btn" @click="goToRoute('/user/RoomThreePage')">预订</a>
        </div>
        <div class="room">
          <img class="room-img" src="@/img/亲子房.jpg" alt="总统套房" />
          <h2>温馨亲子套房</h2>
          <p>温馨亲子套房，提供舒适的环境。</p>
          <h4>¥1200起</h4>
          <a class="btn" href="/user/RoomFourPage">预订</a>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  name: "HomePage",
  setup() {
    const router = useRouter(); // 在 setup 中调用 useRouter

    const goToRoute = (route) => {
      router.push({ path: route }); // 安全地使用 router
    };

    return {
      goToRoute,
    };
  },
};
</script>
<style scoped>
.HomePage {
  font-family: "Arial", sans-serif;
  text-align: center;
  position: relative; /* 需要为该元素设置相对定位 */
  min-height: 90vh; /* 保证页面最小高度 */
}

.welcome-text {
  position: absolute;
  top: 16%;
  left: 10%;
  font-size: 30px;
  color: #fff;
  font-style: italic;
  font-weight: 600;
}
.HomePage::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.2); /* 半透明白色 */
  z-index: -1; /* 确保这个伪元素在图片和内容下层 */
}
.main-content {
  width: 100%;
  max-width: 100%;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.cards-container {
  display: flex;
  justify-content: center;
  margin-top: 40px;
  gap: 30px;
  flex-wrap: wrap;
}

.card {
  background-color: #ffffff;
  border-radius: 16px;
  padding: 20px;
  width: 250px;
  text-align: center;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.room {
  background-color: #ffffff;
  border-radius: 16px;
  padding: 0px;
  width: 250px;
  text-align: center;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.room-img {
  width: 100%;
  height: 150px;
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;
}
.room-container {
  display: flex;
  justify-content: center;
  margin-top: 40px;
  gap: 30px;
  flex-wrap: wrap;
  padding: 0;
  margin-bottom: 50px;
}
.card:hover {
  transform: translateY(-10px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
}
.room:hover {
  transform: translateY(-8px);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
}
.card h2 {
  font-size: 24px;
  color: #2c3e50;
  font-weight: 600;
  margin-bottom: 20px;
}
.room h2 {
  font-size: 18px;
  color: #158dd8;
  font-weight: 600;
  margin-bottom: 10px;
  margin-top: 10px;
}
.card p {
  color: #7f8c8d;
  margin-bottom: 20px;
}
.room p {
  color: #7f8c8d;
  margin-bottom: 10px;
  font-size: 12px;
}
.room h4 {
  font-size: 14px;
  color: rgb(16, 150, 16);
  font-weight: 550;
  /* text-align: left;
  margin-left: 35px; */
}
.card .btn {
  display: inline-block;
  padding: 12px 20px;
  background-color: #3498db;
  color: white;
  text-decoration: none;
  border-radius: 8px;
  font-weight: 500;
  transition: background-color 0.3s ease, transform 0.3s ease;
}
.room .btn {
  display: inline-block;
  padding: 12px 20px;
  margin-bottom: 10px;
  background-color: #3498db;
  color: white;
  text-decoration: none;
  border-radius: 8px;
  font-weight: 550;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.card .btn:hover {
  background-color: #2980b9;
  transform: scale(1.1);
}
.room .btn:hover {
  background-color: #2980b9;
  transform: scale(1.1);
}

.card .btn:active {
  transform: scale(1);
}
.room .btn:active {
  transform: scale(1);
}
</style>
