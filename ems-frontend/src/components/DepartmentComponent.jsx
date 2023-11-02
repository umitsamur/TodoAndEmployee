import { useEffect, useState } from "react"
import { createDepartmentAPI, getDepartmentByIdAPI, updateDepartmentAPI } from "../services/DepartmentService";
import { useNavigate, useParams } from "react-router-dom";

const DepartmentComponent = () => {
    const [department,setDepartment] = useState({
        'departmentName':'',
        'departmentDescription':''
    });

    const [errors,setErrors] = useState({
        'departmentName':'',
        'departmentDescription':''
    });

    const {id} = useParams();

    const navigator = useNavigate();

    useEffect(() => {
        if (id) {
            getDepartmentByIdAPI(id).then(response => {
                setDepartment(response.data);
            }).catch(err => console.error(err));
        }
    }, [id])

    const handleChange = (event) => {
        setDepartment({...department, [event.target.name]:event.target.value});
    }

    const pageTitle = () => {
        if(id){
            return <h2 className="text-center">Update Department</h2>
        }
        else{
            return <h2 className="text-center">Add Department</h2>
        }
    }

    const validateForm = () => {
        let valid = true;
        const errorsCopy = {...errors};
        if (department.departmentName.trim()) {
            errorsCopy.departmentName = '';
        }
        else{
            errorsCopy.departmentName = `Department name is required`;
            valid = false;
        }

        if (department.departmentDescription.trim()) {
            errorsCopy.departmentDescription = '';
        }
        else{
            errorsCopy.departmentDescription = `Department description is required`;
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    const saveOrUpdateDepartment = (event) => {
        event.preventDefault();
        if (validateForm()) {
            if (id) {
                updateDepartmentAPI(id,department).then(() => {
                    navigator('/departments');
                }).catch(err => console.error(err));
            }else{
                createDepartmentAPI(department).then(() => {
                    navigator('/departments');
                }).catch(err => console.error(err))
            }
            
        }
    }

    return (
        <div className="container"><br /> <br />
            <div className="row">
                <div className="card col-md-6 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-2">
                                <label className="form-label">Department Name: </label>
                                <input
                                    type="text"
                                    name="departmentName"
                                    placeholder="Enter department name"
                                    value={department.departmentName}
                                    className={`form-control ${errors.departmentName ? 'is-invalid' : '' }`}
                                    onChange={handleChange}
                                    />
                            {
                                errors.departmentName && <div className="invalid-feedback">{errors.departmentName}</div>
                            }
                            </div>
                            

                            <div className="form-group mb-2">
                                <label className="form-label">Department Description: </label>
                                <input
                                    type="text"
                                    name="departmentDescription"
                                    placeholder="Enter department description"
                                    value={department.departmentDescription}
                                    className={`form-control ${errors.departmentDescription ? 'is-invalid' : '' }`}
                                    onChange={handleChange}
                                    />
                            {
                                errors.departmentDescription && <div className="invalid-feedback">{errors.departmentDescription}</div>
                            }
                            <button className="btn btn-success" onClick={saveOrUpdateDepartment}>Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DepartmentComponent