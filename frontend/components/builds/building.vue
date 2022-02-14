<template>
  <div class="build-card" :class="{'selected': isSelected}" @click="toggleBuildingSelection">
    <div v-for="({icon, name}, feature, index) in features" :key="index" class="feature">
      <div class="name">{{ feature }}</div>
      <div class="details">
        <div class="icon">
          <img :src="icon" alt="feature texture icon">
        </div>
        <div>{{ name }}</div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import Vue from 'vue'
import {Building} from "~/types/Building";

export default Vue.extend({
  name: 'Building',
  components: {},

  props: {
    building: {
      type: Object as () => Building,
      required: true,
    }
  },
  computed: {
    isSelected(): Boolean {
      return this.$store.getters["builds/isSelected"](this.building);
    },
    features(): Map<String, any> {
      return this.building.features;
    }
  },
  methods: {
    toggleBuildingSelection() {
      this.$store.dispatch('builds/toggleBuildingSelection', this.building)
    }
  }
})
</script>
<style lang="postcss">
.selected {
  @apply bg-green-100;
}

.build-card {
  @apply cursor-pointer;
  @apply flex flex-col;
  @apply items-center;
  @apply p-4;
  @apply border border-black rounded-md;
}

.feature {
  @apply w-full;
  @apply flex flex-row items-center justify-between;
  @apply space-x-4 space-y-2;
}

.feature > .name {
  @apply w-1/3 text-right;
}

.feature .details {
  @apply grow;
  @apply flex flex-col;
  @apply items-center;
}

.feature .icon {
  @apply w-12;
}
</style>
