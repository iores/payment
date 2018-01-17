<template>
  <div id="app">
    <h1>hello  app</h1>
    <p>
      <router-link to="/foo">转到 Foo</router-link>
    </p>
    <p>
      <router-link to="/bar">转到 Bar</router-link>
    </p>
    <p>
      <router-link v-bind:to="{name:'user',params:{userId: '887'}}" replace>转到 user887</router-link>
    </p>
    <p>
      <router-link to="/user/888" replace>转到 user888</router-link>
    </p>

    <p>
      <router-link to="/all" replace>多个组件</router-link>
    </p>

    <p>
      <router-link to="/user/887/profile">转到 user/profile</router-link>
    </p>

    <p>
      <router-link to="/loadData">加载动画</router-link>
    </p>
    <transition name="el-zoom-in-center">
      <router-view />
    </transition>

    <router-view name="a" />
    <router-view name="b" />
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
