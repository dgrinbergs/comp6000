<template>
  <div id="feedback">
    <div id="feedback-header">
      <h3 id="feedback-generation">Generation {{currentPopulation + 1}}</h3>
    </div>
    <div id="population-grid">
      <BuildsCard v-for="(building, index) in buildings" :key="index+1" :building="building"/>
    </div>
    <button @click="submitFeedback" class="primary-button">Submit Feedback</button>
  </div>
</template>
<script lang="ts">
import Vue from 'vue'
import BuildsCard from "~/components/builds/card.vue";
import {Building} from "~/types/Building";
import {Population} from "~/types/Population";

export default Vue.extend({
  name: 'BuildsFeedback',
  components: {BuildsCard},
  computed: {
    currentPopulation(): number {
      return this.$store.getters["builds/currentPopulation"];
    },
    population(): Population {
      return this.$store.getters["builds/population"](this.currentPopulation);
    },
    buildings(): Building[] {
      return this.population.buildings;
    },
  },
  methods: {
    submitFeedback() {
      this.$store.dispatch('builds/submitFeedback');
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
