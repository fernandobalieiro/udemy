<template>
  <div @click="setSelectedServer(serverItem)">
      <p>Server #{{ serverIndex }}<p>
      <p>Id: {{ serverItem.id }}
      <p>Status: {{ serverItem.status }}</p>
  </div>
</template>

<script>
    import { eventBus } from '../../main';

    export default {
        props: {
            serverIndex: Number,
            serverItem: Object
        },
        methods: {
            setSelectedServer(serverItem) {
                eventBus.selectedServer = serverItem;
                console.log(eventBus.selectedServer.id);
            }
        }
    }
</script>

<style lang="css">
</style>
