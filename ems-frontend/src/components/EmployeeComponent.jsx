import { useEffect, useState } from 'react'
import { createEmployeeAPI, getEmployeeAPI, updateEmployeeAPI } from '../services/EmployeeService';
import { useNavigate, useParams } from 'react-router-dom';
import { getAllDepartmentsAPI } from '../services/DepartmentService';

const EmployeeComponent = () => {
    const {id} = useParams();
    const [employee, setEmployee] = useState({
        firstName:'',
        lastName:'',
        email:'',
        departmentId:''
    });
    const [departments, setDepartments] = useState([]);

    const [errors, setErrors] = useState({
        firstName:'',
        lastName:'',
        email:'',
        departmentId:''
    });

    const navigator = useNavigate();

    useEffect(() => {
        getAllDepartmentsAPI().then((response) => {
            setDepartments(response.data);
        }).catch(err => console.error(err));
    }, [])

    useEffect(() => {
        if (id) {
            getEmployeeAPI(id).then((response) =>{
                setEmployee(response.data)
            }).catch(err => console.error(err));
        }
    },[id])

    const handleChange = (event) =>{
        setEmployee({...employee, [event.target.name]:event.target.value})
        
    }
    
    const saveOrUpdateEmployee = (event) => {
        event.preventDefault();
        
        if (validateForm()) {
            if (id) {
                updateEmployeeAPI(`${id}`,employee).then((response) => {
                    console.log(response.data);
                    navigator('/employees');
                }).catch(err => console.error(err));
            }
            else{
                createEmployeeAPI(employee).then((response) => {
                    console.log(response.data);
                    navigator('/employees');
                }).catch(error => console.error(error));
            }
            
        }
        
        
    }

    const validateForm = () => {
        let valid = true;
        const errorsCopy = {...errors};
        if (employee.firstName.trim()) {
            errorsCopy.firstName = '';
        }else{
            errorsCopy.firstName = "First Name is required";
            valid = false;
        }

        if (employee.lastName.trim()) {
            errorsCopy.lastName = '';
        }else{
            errorsCopy.lastName = "Last Name is required";
            valid = false;
        }

        if (employee.email.trim()) {
            errorsCopy.email = '';
        }else{
            errorsCopy.email = "Email is required";
            valid = false;
        }

        if (employee.departmentId && employee.departmentId != "Select Department") {
            errorsCopy.departmentId = '';
        }
        else{
            errorsCopy.departmentId = "Select department";
            valid=false
        }

        setErrors(errorsCopy);
        return valid;
        
    }

    const pageTitle = () =>{
        if (id) {
            return <h2 className='text-center'>Update Employee</h2>;
        }
        else{
            return <h2 className='text-center'>Add Employee</h2>
        }
    }


  return (
    <div className='container'>
        <br /><br />
        <div className='row'>
            <div className='card col-md-6 offset-md-3'>
                {
                    pageTitle()
                }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>First Name:</label>
                            <input 
                                type='text' 
                                placeholder='Enter employee first name' 
                                name='firstName' 
                                value={employee.firstName} 
                                className={`form-control ${errors.firstName ? 'is-invalid' : ''}` }
                                onChange={handleChange} />
                            {errors.firstName && <div className='invalid-feedback'> {errors.firstName} </div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name:</label>
                            <input 
                                type='text' 
                                placeholder='Enter employee last name' 
                                name='lastName' 
                                value={employee.lastName} 
                                className={`form-control ${errors.lastName ? 'is-invalid' : ''}` }
                                onChange={handleChange} />
                            {errors.lastName && <div className='invalid-feedback'> {errors.lastName} </div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Email:</label>
                            <input 
                                type='text' 
                                placeholder='Enter employee email' 
                                name='email' 
                                value={employee.email} 
                                className={`form-control ${ errors.email ? 'is-invalid' : ''}`}
                                onChange={handleChange} />
                            {errors.email && <div className='invalid-feedback'>{errors.email}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Select Department:</label>
                            <select
                                className={`form-control ${ errors.departmentId ? 'is-invalid' : ''}`}
                                name='departmentId'
                                value={employee.departmentId}
                                onChange={handleChange}>
                                    <option value="Select Department">Select Department</option>
                                    {
                                        departments.map(department => 
                                            <option key={department.id} value={department.id}> {department.departmentName} </option>)
                                    }
                            </select>
                            {errors.departmentId && <div className='invalid-feedback'>{errors.departmentId}</div>}
                        </div>
                        <button className='btn btn-success' onClick={saveOrUpdateEmployee}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent