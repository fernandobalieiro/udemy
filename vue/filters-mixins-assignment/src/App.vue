<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <h1>Filters & Mixins</h1>
                <!-- Exercise 1) -->
                <!-- Build a local Filter which reverses the Text it is applied on -->
                <label>Reversed Text</label>
                <p>{{ text | reverseText }}</p>
                <hr>

                <!-- Exercise 2 -->
                <!-- Build a global Filter which counts the length of a word and it appends it -->
                <!-- Like this: "Test" => Gets Filtered to => "Test (4)" -->
                <label>Count Length</label>
                <p>{{ text | countLength }}</p>
                <hr>

                <!-- Exercise 3 -->
                <!-- Do the same as in Exercises 1 & 2, now with Computed Properties -->
                <label>Computed Reversed Text</label>
                <p>{{ computedReversedText }}</p>
                <label>Computed Count Length</label>
                <p>{{ computedCountLength }}</p>

                <!-- Exercise 4 -->
                <!-- Share the Computed Property rebuilding Exercise 2 via a Mixin -->
                <app-computed-mixin></app-computed-mixin>
            </div>
        </div>
    </div>
</template>

<script>
import ComputedMixin from "./ComputedMixin.vue";

export default {
    data() {
        return {
            text: "Hello There!"
        };
    },
    components: {
        appComputedMixin: ComputedMixin
    },
    filters: {
        reverseText(value) {
            let reversedValue = "";
            for (var i = value.length - 1; i >= 0; i--) {
                reversedValue += value[i];
            }
            return reversedValue;
        }
    },
    computed: {
        computedReversedText() {
            const value = "Hello There!";
            let reversedValue = "";
            for (var i = value.length - 1; i >= 0; i--) {
                reversedValue += value[i];
            }
            return reversedValue;
        },
        computedCountLength() {
            const value = "Hello There!";
            return value + " (" + value.length + ")";
        }
    }
};
</script>

<style>

</style>
