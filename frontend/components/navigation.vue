<template>
  <header>
    <div id="logo">
      <NuxtLink to="/">SmartGen</NuxtLink>
    </div>
    <div id="links">
      <NuxtLink to="/">Home</NuxtLink>
      <NuxtLink to="/about">About</NuxtLink>
      <NuxtLink to="#">Contact</NuxtLink>
    </div>
  </header>
</template>
<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
  name: 'Navigation'
})
</script>
<style lang="postcss">
header {
  @apply border-b border-stone-400;
  @apply flex flex-row;
  @apply justify-between;
  @apply items-center;
  @apply p-4;
}

#logo {
  @apply text-4xl;
}

#links {
  @apply flex flex-row;
  @apply space-x-4;
  @apply text-lg;
}
</style>
