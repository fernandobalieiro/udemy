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
        <div class="row">
            <my-servers-management></my-servers-management>
        </div>
    </div>
</template>

<script>
    import Header from '../Shared/Header.vue';
    import Footer from '../Shared/Footer.vue';
    import Servers from './Servers.vue';
    import ServerDetails from './ServerDetails.vue';

    export default {
        components: {
            'my-header': Header,
            'my-servers': Servers,
            'my-server-details': ServerDetails,
            'my-servers-management': Footer
        }
    }
</script>

<style>
</style>
