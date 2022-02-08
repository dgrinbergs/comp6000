export const state = () => ({
  seasons: [],
  currentIteration: -1,
  iterations: [],
  selectedBuildings: []
})

export const mutations = {
  setSeasons(state, seasons) {
    state.seasons = seasons;
  },
  addIteration(state, buildings) {
    state.iterations.unshift(buildings);
    state.currentIteration = state.currentIteration += 1;
  },
  makeBuildingSelection(state, building) {
    if (!state.selectedBuildings.includes(building)) {
      state.selectedBuildings.push(building);
    }
  },
  resetBuildingSelection(state) {
    state.selectedBuildings = [];
  }
}

export const actions = {
  fetchSeasons({commit}) {
    this.$axios.get('/api/seasons').then(response => commit('setSeasons', response.data));
  },
  addIteration({commit}, buildings) {
    commit('addIteration', buildings);
  },
  makeBuildingSelection({commit}, building) {
    commit('makeBuildingSelection', building);
  },
  generateNext({commit, state}) {
    const newPopulation = shuffle(state.iterations.at(-1));
    commit('addIteration', newPopulation);
    commit('resetBuildingSelection');
  }
}

export const getters = {
  seasons: state => {
    return state.seasons;
  },
  currentIteration: state => {
    return state.currentIteration;
  },
  iteration: state => iteration => {
    return state.iterations[iteration];
  },
  isSelected: state => building => {
    return state.selectedBuildings.includes(building);
  }
}

function shuffle(array) {
  let currentIndex = array.length,  randomIndex;
  while (currentIndex !== 0) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;
    [array[currentIndex], array[randomIndex]] = [array[randomIndex], array[currentIndex]];
  }
  return array;
}
