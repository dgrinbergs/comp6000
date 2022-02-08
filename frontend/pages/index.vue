<template>
  <div id="content">
    <div class="mb-4">
      <h1>Generate a new build</h1>
      <h3>{{ userInstructions }}</h3>
    </div>

    <BuildsForm v-if="currentIteration < 0"/>
    <BuildsFeedback v-else/>

  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import BuildsFeedback from "~/components/builds/feedback.vue";
import BuildsForm from "~/components/builds/form.vue";

export default Vue.extend({
  name: 'IndexPage',
  components: {BuildsFeedback, BuildsForm},
  data() {
    return {
      status: 'pending',
    }
  },
  computed: {
    userInstructions() {
      if (this.currentIteration < 0) {
        return "First, fill out some details below:";
      } else {
        return "Pick your favourite builds and generate the next generation"
      }

    },
    currentIteration() {
      return this.$store.getters["builds/currentIteration"];
    }
  },
})
</script>
<style lang="postcss">
h1 {
  @apply text-2xl;
}

#content {
  @apply p-4;
}
</style>
