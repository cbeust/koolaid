import VueResource from 'vue-resource'
Vue.use(VueResource)

// Home.vue
export default {
    computed: {
        message () {
            // We will see what `params` is shortly
            return "Computed";
        }
    }
}