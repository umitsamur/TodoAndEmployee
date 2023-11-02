import axios from 'axios';
import { getToken } from './AuthService';

axios.interceptors.request.use(function (config) {
  config.headers['Authorization'] = getToken();
  return config;
}, function (error) {
  return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
  if (response.status === 401) {
    console.log("You are not authorized!")
    alert("You are not authorized");
  }
  return response;
}, function (error) {
  return Promise.reject(error);
});

export default axios;