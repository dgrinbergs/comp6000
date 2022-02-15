export const state = () => ({
  minimumGenerations: 3,
  minimumSelected: 3,
  done: false,
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
  toggleDone(state) {
    state.done = !state.done;
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
  toggleDone({commit}) {
    commit('toggleDone');
  }
};

export const getters = {
  currentPopulation: state => {
    return state.currentPopulation;
  },
  population: state => population => {
    return state.populations[population];
  },
  isSelected: state => ({id}) => {
    return state.populations[state.currentPopulation].selected.includes(id);
  },
  minimumGenerations: state => {
    return state.minimumGenerations;
  },
  selected: state => {
    return state.populations[state.currentPopulation].selected;
  },
  minimumSelected: state => {
    return state.minimumSelected;
  },
  done: state => {
    return state.done;
  }
};
