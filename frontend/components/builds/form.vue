<template>
  <form @submit.prevent="onSubmit" id="generate-form">
    <div class="input-group">
      <h2>Terrain Details</h2>
      <template v-for="(season, index) in seasons">
        <div class="radio-option">
          <input type="radio" :id="season" name="season" :value="index" v-model="build.season">
          <label :for="season">{{ season }}</label>
        </div>
      </template>
    </div>
    <div class="input-group">
      <h2>Building Details</h2>
      <label for="height">Height (in blocks)</label>
      <input type="text" id="height" v-model="build.building.height">
    </div>
    <input type="submit" value="Generate" class="primary-button">
  </form>
</template>
<script lang="ts">
import Vue from 'vue'
import {Population} from "~/types/Population";

export default Vue.extend({
  name: 'BuildsForm',
  mounted() {
    this.$store.dispatch('builds/fetchSeasons');
  },
  computed: {
    seasons() {
      return this.$store.getters["builds/seasons"];
    }
  },
  data() {
    return {
      build: {
        season: 0,
        building: {
          height: 20,
        },
        iterations: []
      },
    }
  },
  methods: {
    onSubmit() {
      type R = {
        data: {
          initialPopulation: Population
        }
      };

      this.$axios.post('/api/builds', {
        'season': this.seasons[this.build.season],
        'building': this.build.building
      }).then((response: R) => this.$store.dispatch('builds/addPopulation', response.data.initialPopulation));
    }
  }
})
</script>
<style>
#generate-form {
  @apply flex flex-col;
  @apply space-y-4;
  @apply my-4;
}

#generate-form h2 {
  @apply text-xl;
}

.input-group {
  @apply flex flex-col;
  @apply space-y-1;
}

.radio-option {
  @apply flex flex-row;
  @apply space-x-2;
  @apply items-center;
}

label {
  @apply capitalize;
}
</style>
