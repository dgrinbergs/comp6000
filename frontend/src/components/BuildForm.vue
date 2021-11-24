<template>
  <div>
    <h1>Hello</h1>
    <h1>{{building.height}}</h1>
    <h1>{{selectedSeason}}</h1>
    <form @submit.prevent="onSubmit">
      <label id="seasonLabel">Season</label>
      <select name="season" id="season" v-model="selectedSeason">
        <option :value="season" v-for="season in seasons" :key="season">{{ season }}</option>
      </select>

      <label for="buildingHeight">Building Height</label>
      <input type="text" id="buildingHeight" name="buildingHeight" v-model="building.height">

      <p style="color: red" v-if="building.height > 20">Building is too high</p>

      <input type="submit" value="Build">
    </form>
  </div>
</template>

<script>
const axios = require('axios');

export default {
  name: 'BuildForm',
  data() {
    return {
      seasons: [],
      selectedSeason: [],
      building: {
        height: 20
      },
      buildingHeightError: [],
    }
  },
  mounted() {
    axios.get('http://localhost:8080/api/seasons').then(response => this.seasons = response.data);
  },
  methods: {
    onSubmit() {
      axios.post('http://localhost:8080/api/builds', {
        'season': this.selectedSeason,
        'building': this.building
      })
    }
  }
}
</script>