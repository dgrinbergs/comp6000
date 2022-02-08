<template>
  <main>
    <Navigation/>
    <Nuxt/>
  </main>
</template>
<script lang="ts">
import Vue from 'vue'
import Navigation from "~/components/navigation.vue";

export default Vue.extend({
  name: 'default',
  components: {Navigation},
})
</script>
<style lang="postcss">
main {
  @apply mx-64;
  @apply min-h-screen;
  @apply flex flex-col;
}
</style>
