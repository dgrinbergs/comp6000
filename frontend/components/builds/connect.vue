<template>
  <div id="connect">
    <button @click="notDone" class="primary-button">Go back</button>
  </div>
</template>
<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
  name: 'BuildsConnect',
  methods: {
    notDone() {
      this.$store.dispatch("builds/toggleDone");
    }
  }
})
</script>
