import Vue from 'vue'
import App from './App.vue'
import Home from './components/Server/Home.vue'

Vue.component('my-home', Home);

new Vue({
  el: '#app',
  render: h => h(App)
})
