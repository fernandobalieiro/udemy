<template>
    <div class="form-group">
        <label>Full Name</label>
        <input
                type="text"
                id="firstName"
                class="form-control"
                :value="firstName"
                @input="firstNameChanged($event)">
        <input
                type="text"
                id="lastName"
                class="form-control"
                :value="lastName"
                @input="lastNameChanged($event)">
    </div>
</template>

<script>
export default {
    props: ["value"],
    computed: {
        firstName() {
            return this.value.split(" ")[0];
        },
        lastName() {
            return this.value.split(" ")[1];
        }
    },
    methods: {
        firstNameChanged(event) {
            this.value = event.target.value + " " + this.lastName;
            this.$emit("input", this.value);
        },
        lastNameChanged(event) {
            this.value = this.firstName + " " + event.target.value;
            this.$emit("input", this.value);
        }
    }
};
</script>

<style>

</style>
