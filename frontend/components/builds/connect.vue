<template>
  <div id="connect">
    <div>
      <BuildsBuilding :clickable="false" :building="selectedBuilding" class="bg-white"/>
    </div>
  </div>
</template>
<script lang="ts">
import Vue from 'vue';
import {Building} from '~/types/Building';

export default Vue.extend({
  name: 'BuildsConnect',
  computed: {

    index(): Number {
      return this.$store.getters["builds/currentPopulation"];
    },
    buildings(): Building[] {
      return this.$store.getters["builds/population"](this.index).buildings;
    },
    selected(): String {
      return this.$store.getters["builds/population"](this.index).selected[0];
    },

    selectedBuilding(): Building {
      return this.buildings.filter(({id}: {id: String}) => id === this.selected)[0]
    }
  }
})
</script>
