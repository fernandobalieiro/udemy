<template>
    <div>
        <hr>
        <hr>
        <!-- Exercise 4 -->
        <!-- Share the Computed Property rebuilding Exercise 2 via a Mixin -->
        <label>Computed Reversed Text</label>
        <p>{{ computedReversedText }}</p>
        <label>Computed Count Length</label>
        <p>{{ computedCountLength }}</p>
    </div>
</template>

<script>
import { wordMixin } from "./wordMixin";

export default {
    mixins: [wordMixin]
};
</script>

<style>

</style>
