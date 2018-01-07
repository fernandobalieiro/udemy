<template>
    <div class="col-xs-12 col-sm-6">
        <p v-if="!selectedServer">Server Details are currently not updated</p>
        <p v-else>Selected Server: {{ selectedServer }}</p>
        <button @click="changeToNormal">Change to Normal</button>
    </div>

</template>

<script>
    import { eventBus } from '../../main';

    export default {
        data: function(){
            return {
              selectedServer: null
            };
        },
        methods: {
            changeToNormal() {
                this.selectedServer.status = 'Normal';
            }
        },
        created() {
            eventBus.$on('selectedServer', (server) => {
                console.log('bla');
                this.selectedServer = server;
            });
        }
    }
</script>

<style>

</style>
