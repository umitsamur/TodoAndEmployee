import { useEffect, useState } from "react"
import { deleteDepartmentAPI, getAllDepartmentsAPI } from "../services/DepartmentService";
import { Link, useNavigate } from "react-router-dom";

const ListDepartment = () => {
    
    const [departments, setDepartments] = useState([]);
    const navigator = useNavigate();

    useEffect(()=>{
        getAllDepartments();
    },[])

    const getAllDepartments = () => {
        getAllDepartmentsAPI().then(response => {
            setDepartments(response.data);
        }).catch(err => console.error(err));
    }

    const updateDepartment = (departmentId) => {
        navigator(`/edit-department/${departmentId}`);
    }

    const deleteDepartment = (departmentId) => {
        deleteDepartmentAPI(departmentId).then(() => {
            getAllDepartments()
        }).catch(err => console.error(err));
    }
    return (
        <div className="container">
            <h2 className="text-center">List of Departments</h2>
            <Link to='/add-department' className="btn btn-primary mb-2">Add Department</Link>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th className="text-center">Department Id</th>
                        <th className="text-center">Department Name</th>
                        <th className="text-center">Department Description</th>
                        <th className="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {departments.map(department => 
                        <tr key={department.id}>
                            <td className="text-center">{department.id}</td>
                            <td className="text-center">{department.departmentName}</td>
                            <td className="text-center">{department.departmentDescription}</td>
                            <td className="text-center">
                                <button className="btn btn-info" style={{margin:'0 5px'}} onClick={() => updateDepartment(department.id)}>Update</button>
                                <button className="btn btn-danger" style={{margin:'0 5px', width:'77.75px'}} onClick={() => deleteDepartment(department.id)}>Delete</button>
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

export default ListDepartment