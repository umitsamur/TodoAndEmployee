import axios from "./axios-service";

const DEPARTMENT_REST_API_BASE_URL = 'http://localhost:8080/api/departments';

export const getAllDepartmentsAPI = () => {
    return axios.get(DEPARTMENT_REST_API_BASE_URL);
}

export const createDepartmentAPI = (department) => {
    return axios.post(DEPARTMENT_REST_API_BASE_URL,department);
}

export const getDepartmentByIdAPI = (departmentId) => {
    return axios.get(DEPARTMENT_REST_API_BASE_URL+`/`+departmentId);
}

export const updateDepartmentAPI = (departmentId,department) => {
    return axios.put(DEPARTMENT_REST_API_BASE_URL+`/`+departmentId, department);
}

export const deleteDepartmentAPI = (departmentId) => {
    return axios.delete(DEPARTMENT_REST_API_BASE_URL+'/'+departmentId);
}