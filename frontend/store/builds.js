export const state = () => ({
  seasons: [],
  currentPopulation: -1,
  populations: [],
})

export const mutations = {
  setSeasons(state, seasons) {
    state.seasons = seasons;
  },
  addPopulation(state, population) {
    state.populations.unshift(population);
    state.currentPopulation = state.currentPopulation += 1;
  },
  toggleBuildingSelection(state, {uuid}) {
    let selected = state.populations[state.currentPopulation].selected;

    if(!selected.includes(uuid)) {
      selected.push(uuid);
    } else {
      state.populations[state.currentPopulation].selected = selected.filter(s => s !== uuid)
    }
  },
}

export const actions = {
  fetchSeasons({commit}) {
    this.$axios.get('/api/seasons').then(response => commit('setSeasons', response.data));
  },
  addPopulation({commit}, population) {
    commit('addPopulation', population);
  },
  toggleBuildingSelection({commit}, building) {
    commit('toggleBuildingSelection', building);
  },
  generate({commit}) {

  },
  submitFeedback({commit, state}) {

    console.log(state.populations[state.currentPopulation]);

    this.$axios.post('/api/feedback', {
      populationId: state.populations[state.currentPopulation].populationId,
      selected: state.populations[state.currentPopulation].selected,
    }).then(response => {
      commit('addPopulation', response.data);
    })
  }
}

export const getters = {
  seasons: state => {
    return state.seasons;
  },
  currentPopulation: state => {
    return state.currentPopulation;
  },
  population: state => currentPopulation => {
    return state.populations[currentPopulation];
  },
  isSelected: state => building => {
    return state.populations[state.currentPopulation].selected.includes(building.uuid);
  }
}

const shuffle = array => {
  let currentIndex = array.length,  randomIndex;
  while (currentIndex !== 0) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;
    [array[currentIndex], array[randomIndex]] = [array[randomIndex], array[currentIndex]];
  }
  return array;
}
