import React, { useState } from 'react'
import { registerAPICall } from '../services/AuthService';

const RegisterComponent = () => {
    
    const [registerForm, setRegisterForm] = useState({
        'name':'',
        'username':'',
        'email':'',
        'password':''
      });

    const handleRegistrationForm = (event) => {
        event.preventDefault();

        registerAPICall(registerForm).then(response => {
            console.log(response.data);
        }).catch(err => console.error(err));
    }

  return (
    <div className='container'>
        <br /><br />
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'>User Registration Form</h2>
                    </div>
                    <div className='card-body'>
                        <form>
                            {/* Name input */}
                            <div className='row mb-3'>
                                <label className='col-md-2 control-label label-centering'>Name:</label>
                                <div className='col-md-10'>
                                    <input
                                        type='text'
                                        name='name'
                                        className='form-control'
                                        placeholder='Enter Name'
                                        value={registerForm.name}
                                        onChange={(e) => setRegisterForm({...registerForm, name:e.target.value})}
                                        />
                                </div>
                            </div>

                            { /* Username input */}
                            <div className='row mb-3'>
                                <label className='col-md-2 control-label label-centering'>Username:</label>
                                <div className='col-md-10'>
                                    <input
                                        type='text'
                                        name='username'
                                        className='form-control'
                                        placeholder='Enter Username'
                                        value={registerForm.username}
                                        onChange={(e) => setRegisterForm({...registerForm, username:e.target.value})}
                                        />
                                </div>
                            </div>

                            { /* Email input */}
                            <div className='row mb-3'>
                                <label className='col-md-2 control-label label-centering'>Email:</label>
                                <div className='col-md-10'>
                                    <input
                                        type='text'
                                        name='email'
                                        className='form-control'
                                        placeholder='Enter Email'
                                        value={registerForm.email}
                                        onChange={(e) => setRegisterForm({...registerForm, email:e.target.value})}
                                        />
                                </div>
                            </div>

                            { /* Password input */}
                            <div className='row mb-3'>
                                <label className='col-md-2 control-label label-centering'>Password:</label>
                                <div className='col-md-10'>
                                    <input
                                        type='password'
                                        name='password'
                                        className='form-control'
                                        placeholder='Enter Password'
                                        value={registerForm.password}
                                        onChange={(e) => setRegisterForm({...registerForm, password:e.target.value})}
                                        />
                                </div>
                            </div>

                            <div className='form-group mb-3'>
                                <button className='btn btn-primary' onClick={handleRegistrationForm}>Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default RegisterComponent