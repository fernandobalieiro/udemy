export const wordMixin = {
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
