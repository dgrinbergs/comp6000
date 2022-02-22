<template>
  <div id="feedback">
    <div id="feedback-header">
      <div class="flex flex-row items-center space-x-2">
        <h3 id="feedback-generation">Generation {{ currentPopulation + 1 }}</h3>
        <p>{{ instructions }}</p>
      </div>
      <div class="flex flex-row items-center space-x-2">
        <button :disabled="!submittable" @click="submitFeedback" class="primary-button"
                :class="{disabled : !submittable}"
                v-if="!completable">
          Submit Feedback
        </button>
        <div v-if="completable" class="flex flex-row space-x-4 items-center">
          <p>The build you have selected will be generated in game</p>
          <button :disabled="!completable" @click="complete" class="secondary-button">Done</button>
        </div>
      </div>
    </div>
    <div id="population-grid">
      <BuildsCard v-for="(building, index) in buildings" :key="index+1" :building="building"/>
    </div>
  </div>
</template>
<script lang="ts">
import Vue from 'vue'
import BuildsCard from "~/components/builds/building.vue";
import {Building} from "~/types/Building";
import {Population} from "~/types/Population";

export default Vue.extend({
  name: 'BuildsFeedback',
  components: {BuildsCard},
  computed: {
    instructions(): string {
      const difference = this.$store.getters["builds/minimumSelected"] - this.selected.length;
      if (difference > 0) {
        return `Select at least ${difference} more ${difference > 1 ? 'buildings' : 'building'} that you like`;
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
      return (this.currentPopulation >= this.$store.getters["builds/minimumGenerations"]) &&
        this.selected.length === 1;
    },
    submittable(): boolean {
      return this.selected.length >= this.$store.getters["builds/minimumSelected"];
    },
    selected(): String[] {
      return this.$store.getters['builds/selected'];
    },
  },
  methods: {
    submitFeedback() {
      this.$store.dispatch('builds/submitFeedback');
    },
    complete() {
      this.$store.dispatch('builds/complete', this.selected[0]);
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
  @apply justify-between;
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
  @apply grid grid-cols-3 gap-4;
}
</style>
