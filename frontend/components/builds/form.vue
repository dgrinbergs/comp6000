<template>
  <form @submit.prevent="generate" id="generate-form">
    <input type="submit" value="Generate" class="primary-button">
  </form>
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
<style>
#generate-form {
  @apply flex flex-col;
  @apply space-y-4;
  @apply my-4;
}

label {
  @apply capitalize;
}
</style>
