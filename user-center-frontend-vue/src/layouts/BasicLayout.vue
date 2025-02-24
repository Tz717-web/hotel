<template>
  <div id="basic-layout">
    <a-layout>
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <RouterView></RouterView>
      </a-layout-content>
    </a-layout>
  </div>
</template>
<script setup lang="ts">
// import { getCurrentUser } from "@/api/user";
import GlobalHeader from "@/components/GlobHeard.vue";
// getCurrentUser().then((res) => {
//   console.log(res);
// });
</script>
<style scoped>
.content {
  margin: 0px;
  padding: 0px;
  background-color: #fff;
  margin-block: 1px;
  background: linear-gradient(to right, #fefefe, #fff);
}
.header {
  background-color: #ffffff;
  height: 64px;
  padding-left: 40px;
  margin-bottom: 0px;
}
</style>
