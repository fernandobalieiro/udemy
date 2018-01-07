import Vue from 'vue'
import App from './App.vue'

export const eventBus = new Vue({
    data: {
        selectedServer: {}
    },
    methods: {
        changeServerToNormal(selectedServer) {
            this.$emit('serverWasUpdated', selectedServer);
        }
    }
});

new Vue({
  el: '#app',
  render: h => h(App)
})
