const Vue = require("vue").default;
const TopVue = require("./components/Top.vue").default;

$(function () {
    new Vue({
        el: '#top',
        components: { "top": TopVue }
    });
});
