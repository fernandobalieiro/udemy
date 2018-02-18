<template>
    <div>
        <h3>Edit the User</h3>
        <p>Locale: {{ $route.query.locale }}</p>
        <p>Locale: {{ $route.query.q }}</p>
        <hr>
        <button class="btn btn-primary" @click="test">Confirm</button>
        <div style="height: 700px"></div>
        <p id="data">Some extra data</p>
    </div>
</template>

<script>
export default {
    data() {
        return {
            confirmed: false
        };
    },
    beforeRouteLeave: (to, from, next) => {
        // FIXME Not working properly. this.conforme has no value defined.
        if (this.confirmed) {
            next();
        } else {
            if (confirm("Are you sure?")) {
                next();
            } else {
                next(false);
            }
        }
    },
    methods: {
        test() {
            this.confirmed = true;
            console.log("method", this.confirmed);
        }
    }
};
</script>
