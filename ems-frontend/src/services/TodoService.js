import axios from "./axios-service";
//import { getToken } from "./AuthService";

const TODO_BASE_API_REST_URL = "http://localhost:8080/api/todos";
/*
// Add a request interceptor
axios.interceptors.request.use(function (config) {
    config.headers['Authorization'] = getToken();
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    if(response.status === 401) {
      alert("You are not authorized");
    }
    return response;
  }, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
  });
*/

export const getAllTodosAPI = () => {
    return axios.get(TODO_BASE_API_REST_URL);
}

export const saveTodoAPI = (todo) => {
    return axios.post(TODO_BASE_API_REST_URL,todo);
}

export const getTodoByIdAPI = (todoId) => {
    return axios.get(TODO_BASE_API_REST_URL+'/'+todoId);
}

export const updateTodoByIdAPI = (todoId, updatedTodo) => {
    return axios.put(TODO_BASE_API_REST_URL+'/'+todoId, updatedTodo);
}

export const deleteTodoByIdAPI = (todoId) => {
    return axios.delete(TODO_BASE_API_REST_URL+'/'+todoId);
}

export const completeTodoByIdAPI = (todoId) => {
    return axios.patch(TODO_BASE_API_REST_URL+'/'+todoId+'/complete')
}

export const inCompleteTodoByIdAPI = (todoId) => {
    return axios.patch(TODO_BASE_API_REST_URL+'/'+todoId+'/in-complete')
}