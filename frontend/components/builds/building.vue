<template>
  <div class="build-card" :class="className" @click="toggleBuildingSelection">
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
    },
    clickable: {
      type: Boolean,
      required: false,
      default: true
    }
  },
  computed: {
    isSelected(): Boolean {
      return this.$store.getters["builds/isSelected"](this.building);
    },
    features(): Map<String, any> {
      return this.building.features;
    },
    className(): String | undefined {
      let className = "";

      if (this.isSelected &&
        this.$store.getters["builds/selected"].length === 1 &&
        (this.$store.getters["builds/currentPopulation"] >= this.$store.getters["builds/minimumGenerations"])) {
        className += 'bg-green-200';
      } else if (this.isSelected) {
        className += 'bg-blue-200';
      }

      if (this.clickable) {
        className += ` cursor-pointer`;
      }

      return className;
    }
  },
  methods: {
    toggleBuildingSelection() {
      if (this.clickable) {
        this.$store.dispatch('builds/toggleBuildingSelection', this.building)
      }
    }
  }
})
</script>
<style lang="postcss">
.build-card {
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
