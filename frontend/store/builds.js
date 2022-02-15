export const state = () => ({
  minimumGenerations: 1,
  minimumSelected: 2,
  complete: false,
  currentPopulation: -1,
  populations: [],
});

export const mutations = {
  addPopulation(state, population) {
    state.populations.push(population);
    state.currentPopulation = state.currentPopulation += 1;
  },
  toggleBuildingSelection(state, {id}) {
    let selected = state.populations[state.currentPopulation].selected;

    if(!selected.includes(id)) {
      selected.push(id);
    } else {
      state.populations[state.currentPopulation].selected = selected.filter(s => s !== id)
    }
  },
  complete(state) {
    state.complete = true;
  }
};

export const actions = {
  toggleBuildingSelection({commit}, building) {
    commit('toggleBuildingSelection', building);
  },
  generate({commit}) {
    this.$axios.post('/api/builds').then(response => {
      commit('addPopulation', response.data);
    });
  },
  submitFeedback({commit, state}) {
    this.$axios.post('/api/feedback', {
      populationId: state.populations[state.currentPopulation].populationId,
      selected: state.populations[state.currentPopulation].selected,
    }).then(response => {
      commit('addPopulation', response.data);
    })
  },
  async complete({commit}, buildingId) {
    await this.$axios.post('/api/builds/done',{buildingId});
    commit('complete');
  }
};

export const getters = {
  currentPopulation: state => {
    return state.currentPopulation;
  },
  population: state => population => {
    return state.populations[population];
  },
  selected: state => {
    return state.populations[state.currentPopulation].selected || [];
  },
  isSelected: state => ({id}) => {
    return state.populations[state.currentPopulation].selected.includes(id);
  },
  minimumGenerations: state => {
    return state.minimumGenerations;
  },
  minimumSelected: state => {
    return state.minimumSelected;
  },
  complete: state => {
    return state.complete;
  }
};
