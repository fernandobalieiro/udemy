<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <br>
                <button class="btn btn-primary" @click="selectedComponent = 'appBlue'">Load Blue Template</button>
                <button class="btn btn-success" @click="selectedComponent = 'appGreen'">Load Green Template</button>
                <button class="btn btn-danger" @click="selectedComponent = 'appRed'">Load Red Template</button>
                <hr>
                <component :is="selectedComponent">
                    <p>Default Content</p>
                </component>
                <!-- <app-blue>
                    <h1 slot="blue">I'm Blue</h1>
                </app-blue>
                <app-green>
                    <h2 slot="green">I'm Green</h2>
                </app-green>
                <app-red>
                    <h3 slot="red">I'm Red</h3>
                </app-red> -->
            </div>
        </div>
    </div>
</template>

<script>
import Blue from "./components/Blue.vue";
import Green from "./components/Green.vue";
import Red from "./components/Red.vue";

export default {
    data: () => {
        return {
            selectedComponent: "appBlue"
        };
    },
    components: {
        appBlue: Blue,
        appGreen: Green,
        appRed: Red
    }
};
</script>

<style>

</style>
