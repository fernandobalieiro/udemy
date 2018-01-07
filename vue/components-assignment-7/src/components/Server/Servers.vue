<template>
    <div class="col-xs-12 col-sm-6">
        <ul class="list-group">
            <li class="list-group-item"
                v-for="(value, key, index) in servers">
                <server-item
                    :serverItem="value"
                    :serverIndex="key"></server-item>
            </li>
        </ul>
    </div>
</template>

<script>
    import ServerItem from './ServerItem.vue'
    import { eventBus } from '../../main';

    export default {
        data: function() {
            return {
              servers: [
                  {id: 1, status: 'Normal'},
                  {id: 2, status: 'Critical'},
                  {id: 3, status: 'Unknown'},
                  {id: 4, status: 'Normal'}
              ]
            };
        },
        components: {
            ServerItem
        }
    }
</script>

<style>

</style>
