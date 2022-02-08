export const state = () => ({
  seasons: [],
  currentIteration: -1,
  iterations: [],
})

export const mutations = {
  setSeasons(state, seasons) {
    state.seasons = seasons;
  },
  addIteration(state, {buildings, selected, uuid}) {
    state.iterations.unshift({buildings, selected, uuid});
    state.currentIteration = state.currentIteration += 1;
  },
  toggleBuildingSelection(state, {uuid}) {
    let selected = state.iterations[state.currentIteration].selected;

    if(!selected.includes(uuid)) {
      selected.push(uuid);
    } else {
      state.iterations[state.currentIteration].selected = selected.filter(s => s !== uuid)
    }
  },
  resetBuildingSelection(state) {
    state.iterations[state.currentIteration].selected = [];
  }
}

export const actions = {
  fetchSeasons({commit}) {
    this.$axios.get('/api/seasons').then(response => commit('setSeasons', response.data));
  },
  addIteration({commit}, buildings) {
    commit('addIteration', buildings);
  },
  toggleBuildingSelection({commit}, building) {
    commit('toggleBuildingSelection', building);
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
    return state.iterations[state.currentIteration].selected.includes(building.uuid);
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
