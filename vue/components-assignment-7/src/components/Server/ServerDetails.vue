<template>
    <div class="col-xs-12 col-sm-6">
        <p>Server Details are currently not updated</p>
        <button @click="changeToNormal">Change to Normal</button>
    </div>

</template>

<script>
    import { eventBus } from '../../main';

    export default {
        props: {
            selectedServer: Object
        },
        methods: {
            changeToNormal() {
                eventBus.selectedServer.status = 'Normal';
                eventBus.changeServerToNormal(this.selectedServer);
            }
        }
    }
</script>

<style>

</style>
