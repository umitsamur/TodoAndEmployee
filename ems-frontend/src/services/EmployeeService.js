import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/employees";

export const listEmployeesAPI = () => {
    return axios.get(REST_API_BASE_URL);
}

export const createEmployeeAPI = (employee) => {
    return axios.post(REST_API_BASE_URL, employee);
}

export const getEmployeeAPI = (employeeId) => {
    return axios.get(REST_API_BASE_URL+'/'+employeeId);
}

export const updateEmployeeAPI = (employeeId, employee) => {
    return axios.put(REST_API_BASE_URL+'/'+employeeId, employee);
}

export const deleteEmployeeAPI = (employeeId) => {
    return axios.delete(REST_API_BASE_URL+'/'+employeeId);
}