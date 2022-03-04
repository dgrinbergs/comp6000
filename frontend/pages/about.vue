<template>
  <style>
    body {
      font-family: 'IBM Plex Sans', sans-serif;
    }
    #overview {
      margin: auto;
      width: 80%;
      float: center;
    }
    #team {
      float: center;
      margin: auto;
    }
    #teammembers {
      float: left;
      padding: 40px;
      text-align: center;
      margin: auto;
    }
    h1 {
      text-align: center;
    }
    h3 {
      text-align: center;
    }
    img {
      height: 30px;
      width: 30px;
    }
  </style>
  <h1>About Us</h1><br>
  <h3>Overview</h3>
  <div id="overview">
    We are Final Year Computer Science Students at the University of Kent.<br>
    Our project's aim was to create an application which creates houses and
    buildings in Minecraft based on feedback provided by the user of a frontend webpage.<br>
    The feedback provided powers a AI system which uses a Genetic Algorithm to create a
    number of buildings of varying materials and block types.<br>
    SmartGen allows users to create buildings in Minecraft without ever setting foot in the game itself.
  </div>
  <h3>Who we are?</h3>
  <div style="margin: auto; width: 80%; float: center;">
    <div id="teammembers">
      <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg" alt="Minecraft Steve Head"><br>Samuel Wheeler<br>Project Manager
    </div>
    <div id="teammembers">
      <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg" alt="Minecraft Steve Head"><br>Harry Devane<br>Developer
    </div>
    <div id="teammembers">
      <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg" alt="Minecraft Steve Head"><br>Dainis Grinbergs<br>Developer
    </div>
    <div id="teammembers">
      <img src="https://pm1.narvii.com/7616/bc3b22a89815985dc74b830a01ad331c0d3e9f86r1-768-768v2_hq.jpg" alt="Minecraft Steve Head"><br>Corinne Batho-Newton<br>Researcher, Algorithm Logician and Administration
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
  name: 'AboutPage',
})
</script>
