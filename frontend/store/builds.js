export const state = () => ({
  minimumGenerations: 3,
  currentPopulation: -1,
  populations: [],
});

export const mutations = {
  addPopulation(state, population) {
    state.populations.push(population);
    state.currentPopulation = state.currentPopulation += 1;
  },
  toggleBuildingSelection(state, {buildingId}) {
    let selected = state.populations[state.currentPopulation].selected;

    if(!selected.includes(buildingId)) {
      selected.push(buildingId);
    } else {
      state.populations[state.currentPopulation].selected = selected.filter(s => s !== buildingId)
    }
  },
};

export const actions = {
  toggleBuildingSelection({commit}, building) {
    commit('toggleBuildingSelection', building);
  },
  generate({commit}) {
    this.$axios.post('/api/builds').then(response => {
      commit('addPopulation', response.data.initialPopulation);
    });
  },
  submitFeedback({commit, state}) {
    this.$axios.post('/api/feedback', {
      populationId: state.populations[state.currentPopulation].populationId,
      selected: state.populations[state.currentPopulation].selected,
    }).then(response => {
      commit('addPopulation', response.data);
    })
  }
};

export const getters = {
  currentPopulation: state => {
    return state.currentPopulation;
  },
  population: state => population => {
    return state.populations[population];
  },
  isSelected: state => building => {
    return state.populations[state.currentPopulation].selected.includes(building.buildingId);
  },
  minimumGenerations: state => {
    return state.minimumGenerations;
  }
};
