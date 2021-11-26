<template>
  <div class="py-5 text-center">
    <h2>Generate a new build</h2>
  </div>

  <div class="row g-5">
    <form @submit.prevent="onSubmit">
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">Terrain details</h4>
        <div class="my-3">

          <template v-for="(season, index) in seasons" :key="season">
            <div class="form-check">
              <input :id="season" type="radio" :value="index" v-model="selectedSeason" class="form-check-input" required>
              <label class="form-check-label text-capitalize" :for="season">{{ season }}</label>
            </div>
          </template>
        </div>
      </div>

      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">Building details</h4>
      </div>

      <div class="col-12">
        <label for="address" class="form-label">Height (in blocks)</label>
        <input type="text" class="form-control" id="address" placeholder="20" required>
      </div>

      <hr class="my-4">

      <button class="w-100 btn btn-primary btn-lg" type="submit">Generate!</button>

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
      selectedSeason: 0,
      building: {
        height: 20
      },
    }
  },
  mounted() {
    axios.get('/api/seasons').then(response => this.seasons = response.data);
  },
  methods: {
    onSubmit() {
      axios.post('/api/builds', {
        'season': this.seasons[this.selectedSeason],
        'building': this.building
      })
    }
  }
}
</script>