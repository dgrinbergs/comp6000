<template>
  <div id="feedback">
    <div id="feedback-header">
      <h3 id="feedback-generation">Generation {{currentPopulation + 1}}</h3>
      <p>{{instructions}}</p>
    </div>
    <div id="population-grid">
      <BuildsCard v-for="(building, index) in buildings" :key="index+1" :building="building"/>
    </div>
    <div id="actions">
      <button :disabled="!submittable" @click="submitFeedback" class="primary-button" :class="{disabled : !submittable}">Submit Feedback</button>
      <button v-if="completable" @click="done" class="secondary-button">Done</button>
    </div>
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
    instructions(): string {
      const difference = this.$store.getters["builds/minimumSelected"] - this.$store.getters['builds/selected'].length;
      if (difference > 0) {
        return `Select ${difference} more ${difference > 1 ? 'buildings' : 'building'} that you like`;
      } else {
        return "You can submit your feedback";
      }
    },
    currentPopulation(): number {
      return this.$store.getters["builds/currentPopulation"];
    },
    population(): Population {
      return this.$store.getters["builds/population"](this.currentPopulation);
    },
    buildings(): Building[] {
      return this.population.buildings;
    },
    completable(): boolean {
      return this.currentPopulation >= this.$store.getters["builds/minimumGenerations"];
    },
    submittable(): boolean {
      return this.$store.getters['builds/selected'].length >= this.$store.getters["builds/minimumSelected"];
    }
  },
  methods: {
    submitFeedback() {
      this.$store.dispatch('builds/submitFeedback');
    },
    done() {

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

#feedback > #actions {
  @apply flex flex-row;
  @apply justify-between;
}

#population-grid {
  @apply grid grid-cols-4 gap-4;
}
</style>
