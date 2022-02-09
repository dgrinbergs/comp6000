<template>
  <div class="build-card" :class="{'selected': isSelected}" @click="toggleBuildingSelection">
    <div v-for="(details, feature) in building.features" :key="feature" class="feature">
      <div class="name">{{feature}}</div>
      <div class="details">
        <div class="icon">
          <img :src="details.icon" alt="feature texture icon">
        </div>
        <div>{{details.name}}</div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import Vue from 'vue'
import {Building} from "~/types/Building";

export default Vue.extend({
  name: 'BuildsCard',
  props: {
    building: {
      type: Object as () => Building,
      required: true,
    }
  },
  computed: {
    isSelected(): Boolean {
      return this.$store.getters["builds/isSelected"](this.building);
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
