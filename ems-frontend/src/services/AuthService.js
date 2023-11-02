import axios from "./axios-service";

const AUTH_REST_API_BASE_URL = "http://localhost:8080/api/auth";

export const registerAPICall = (registerForm) => {
    return axios.post(AUTH_REST_API_BASE_URL + '/register', registerForm);
}

export const loginAPICall = (loginForm) => {
    return axios.post(AUTH_REST_API_BASE_URL + '/login' , loginForm);
}

export const storeToken = (token) => {
    return localStorage.setItem("token",token);
}

export const getToken = () => {
    return localStorage.getItem("token");
}

export const saveLoggedInUser = (username) => {
    return localStorage.setItem("authenticatedUser", username);
}

export const isUserLoggedIn = () => {
    const username = localStorage.getItem("authenticatedUser");
    if (username == null) {
        return false;
    }
    else{
        return true;
    }
}

export const getLoggedInUser = () => {
    const username = localStorage.getItem("authenticatedUser");
    return username;
}

export const logout = () => {
    localStorage.clear();
}