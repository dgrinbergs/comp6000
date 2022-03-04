<template>
  <div class="p-4 flex flex-col space-y-4">
    <div>
      <h1>About Us</h1>
      <h3>Overview</h3>
      <div id="overview">
        <p>We are final year Computer Science students at the University of Kent.</p>
        <p>Our projects aim was to create an application which creates houses and
          buildings in Minecraft based on feedback provided by the user of a frontend webpage.</p>
        <p>The feedback provided powers a AI system which uses a Genetic Algorithm to create a
          number of buildings of varying materials and block types.</p>
        <p>SmartGen allows users to create buildings in Minecraft without ever setting foot in the game itself.</p>
      </div>
    </div>
    <div>
      <h3>Who we are</h3>
      <div id="team">
        <div class="team-member">
          <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg"
               alt="Minecraft Steve Head">
          <p>Samuel Wheeler</p>
          <p>Project Manager</p>
        </div>
        <div class="team-member">
          <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg"
               alt="Minecraft Steve Head">
          <p>Harry Devane</p>
          <p>Developer</p>
        </div>
        <div class="team-member">
          <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg"
               alt="Minecraft Steve Head">
          <p>Dainis Grinbergs</p>
          <p>Developer</p>
        </div>
        <div class="team-member">
          <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg"
               alt="Minecraft Steve Head">
          <p>Corinne Batho-Newton</p>
          <p>Researcher, Algorithm Logician and Administration</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
  name: 'AboutPage',
})
</script>
<style>

#team {
  @apply flex flex-row items-center justify-around;
}

.team-member {
  @apply flex flex-col justify-center items-center;
}

.team-member > img {
  @apply h-12;
}
</style>
