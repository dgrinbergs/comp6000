<template>
  <div id="content">
    <template v-if="backendAvailable">
      <div class="mb-4">
        <h1>Generate a new build</h1>
        <h3>{{ userInstructions }}</h3>
      </div>

      <BuildsForm v-if="currentPopulation < 0"/>
      <BuildsConnect v-else-if="done"/>
      <BuildsFeedback v-else/>
    </template>
    <div v-else class="banner banner-error">
      <p>The backend server is not running. It must be running before you can use this site. Refresh this page once it's running</p>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import BuildsFeedback from "~/components/builds/feedback.vue";
import BuildsForm from "~/components/builds/form.vue";
import BuildsConnect from "~/components/builds/connect.vue";

export default Vue.extend({
  name: 'IndexPage',
  components: {BuildsConnect, BuildsFeedback, BuildsForm},
  data() {
    return {
      status: 'pending',
      backendAvailable: false
    }
  },
  created() {
    this.$axios.get('/')
      .then(() => this.backendAvailable = true)
      .catch(() => console.error("Backend unavailable"));
  },
  computed: {
    userInstructions(): string {
      if (this.currentPopulation < 0) {
        return "Press the Generate button to get started!";
      } else if (this.done) {
        return "";
      } else if (this.currentPopulation >= this.$store.getters["builds/minimumGenerations"]) {
        return "Pick your favourite builds and generate the next generation, or press done if you're finished.";
      } else {
        return "Pick your favourite builds and generate the next generation";
      }
    },
    currentPopulation(): number {
      return this.$store.getters["builds/currentPopulation"];
    },
    done(): boolean {
      return this.$store.getters["builds/complete"];
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
