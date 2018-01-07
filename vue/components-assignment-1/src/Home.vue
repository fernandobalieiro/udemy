<template>
    <div class="container">
        <div class="row">
            <my-header></my-header>
        </div>
        <hr>
        <div class="row">
            <my-servers></my-servers>
            <my-server-details></my-server-details>
        </div>
        <hr>
        <my-servers-management></my-servers-management>
    </div>
</template>

<script>
    import Header from './Header.vue';
    import Servers from './Servers.vue';
    import ServerDetails from './ServerDetails.vue';
    import ServersManagement from './ServersManagement.vue';

    export default {
        components: {
            'my-header': Header,
            'my-servers': Servers,
            'my-server-details': ServerDetails,
            'my-servers-management': ServersManagement
        }
    }
</script>

<style>
</style>
