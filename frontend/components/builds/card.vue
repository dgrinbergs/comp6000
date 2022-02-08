<template>
  <div class="build-card" :class="{'selected': isSelected}" @click="toggleBuildingSelection">
    <div class="h-36 w-36 bg-stone-200"></div>
    <table>
      <tr>
        <th>Floor</th>
        <td>{{building.floor.displayName || building.floor.name}}</td>
      </tr>
      <tr>
        <th>Walls</th>
        <td>{{building.perimeter.north.displayName || building.perimeter.north.name}}</td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import Vue from 'vue'

type Building  = {
  uuid: String,
  floor: String,
  perimeter: String
}

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
    toggleBuildingSelection(){
      this.$store.dispatch('builds/toggleBuildingSelection', this.building)
    }
  }
})
</script>
<style lang="postcss">
.build-card {
  @apply cursor-pointer;
  @apply flex flex-col;
  @apply items-center;
  @apply p-2;
  @apply border border-black rounded-md;
}

.selected {
  @apply bg-green-100;
}
</style>
