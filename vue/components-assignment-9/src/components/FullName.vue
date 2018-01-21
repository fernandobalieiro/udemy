<template>
    <div class="form-group">
        <label>Full Name</label>
        <input
                type="text"
                id="firstName"
                class="form-control"
                @blur="firstNameChanged"
                v-model="fName">
        <input
                type="text"
                id="lastName"
                class="form-control"
                @blur="lastNameChanged"
                v-model="lName">
    </div>
</template>

<script>
export default {
    props: ["firstName", "lastName"],
    data() {
        return {
            fName: this.firstName,
            lName: this.lastName
        };
    },
    methods: {
        firstNameChanged(fName) {
            this.$emit("firstNameChanged", fName.target.value);
        },
        lastNameChanged(lName) {
            this.$emit("lastNameChanged", lName.target.value);
        }
    }
};
</script>

<style>

</style>
