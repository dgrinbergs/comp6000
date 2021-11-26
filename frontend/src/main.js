import {createApp} from 'vue'
import App from './App.vue'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import axios from "axios";

axios.defaults.baseURL=process.env.VUE_APP_API_URL;

createApp(App).mount('main')
