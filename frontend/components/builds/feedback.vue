<template>
  <div id="feedback">
    <div id="feedback-header">
      <h3 id="feedback-generation">Generation {{currentIteration + 1}}</h3>
    </div>
    <div id="population-grid">
      <BuildsCard v-for="(building, index) in population" :key="index+1" :id="index+1" :building="building"/>
    </div>
    <button @click="generateNext" class="primary-button">Generate next population</button>
  </div>
</template>
<script lang="ts">
import Vue from 'vue'
import BuildsCard from "~/components/builds/card.vue";

export default Vue.extend({
  name: 'BuildsFeedback',
  components: {BuildsCard},
  computed: {
    currentIteration() {
      return this.$store.getters["builds/currentIteration"];
    },
    population() {
      return this.$store.getters["builds/iteration"](this.currentIteration);
    },
  },
  methods: {
    generateNext() {
      this.$store.dispatch("builds/generateNext");
    }
  }
})
</script>
<style lang="postcss">
#feedback {
  @apply flex flex-col space-y-4;
  @apply my-4;
}

#feedback-header {
  @apply flex flex-row;
  @apply space-x-4;
  @apply items-center;
}

#feedback-generation {
  @apply rounded-lg;
  @apply text-sm py-2 px-3;
  @apply text-white;
  @apply bg-gradient-to-r from-purple-500 to-pink-500;
}

#population-grid {
  @apply grid grid-cols-4 gap-4;
}
</style>
