<template>
  <button @click="generate" class="primary-button">Generate</button>
</template>
<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
  name: 'BuildsForm',
  methods: {
    generate() {
      this.$store.dispatch('builds/generate');
    }
  }
})
</script>
