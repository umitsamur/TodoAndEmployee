import  {useEffect, useState} from "react";
import { deleteEmployeeAPI, listEmployeesAPI } from "../services/EmployeeService";
import { useNavigate } from "react-router-dom";


const ListEmployee = () => {

    const [employees, setEmployees] = useState([])
    const navigator = useNavigate()

    useEffect(() => {
        getAllEmployees();
    },[])

    const getAllEmployees = () => {
        listEmployeesAPI().then((response) => {
          setEmployees(response.data);
        }).catch((error) => console.error(error))
    }

    const addEmployee = () => {
        navigator('/add-employee')
    }

    const updateEmployee = (id) =>{
        navigator(`/edit-employee/${id}`)
    }

    const deleteEmployee = (id) => {
        deleteEmployeeAPI(id).then(() => {
          getAllEmployees();
        }).catch(error => console.error(error));
    }



  return (
    <>
      <div className="container">
        <h2 className="text-center">List of Employees</h2>
        <button className="btn btn-primary mb-2" onClick={addEmployee}>Add Employee</button>
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th className="text-center">Employee Id</th>
              <th className="text-center">Employee First Name</th>
              <th className="text-center">Employee Last Name</th>
              <th className="text-center">Employee Email</th>
              <th className="text-center">Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => (
              <tr key={employee.id}>
                <td className="text-center">{employee.id}</td>
                <td className="text-center">{employee.firstName}</td>
                <td className="text-center">{employee.lastName}</td>
                <td className="text-center">{employee.email}</td>
                <td className="text-center">
                    <button className="btn btn-info" style={{margin:'0 5px'}} onClick={() => updateEmployee(employee.id)}>Update</button>
                    <button className="btn btn-danger" style={{margin:'0 5px', width:'77.75px'}} onClick={() => deleteEmployee(employee.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default ListEmployee;
