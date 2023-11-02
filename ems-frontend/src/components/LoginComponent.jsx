import React, { useState } from 'react'
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const LoginComponent = () => {
    
    const [loginForm, setLoginForm] = useState({
        'usernameOrEmail':'',
        'password':''
    });

    const navigator = useNavigate();

    async function handleLoginForm(e) {
        e.preventDefault();

        await loginAPICall(loginForm).then(response => {
            
            //const token = 'Basic ' + window.btoa(loginForm.usernameOrEmail+':'+loginForm.password);

            const token = 'Bearer ' + response.data.accessToken;
            storeToken(token);
            saveLoggedInUser(loginForm.usernameOrEmail);
            navigator("/todos");

            window.location.reload(false);
        }).catch((error) => {
            if (error.response) {
                console.log(error.response.data);
            }
            else{
                console.error(error);
            }
        });
    }

    return (
        <div className='container'>
            <br /><br />
            <div className='row'>
                <div className='col-md-6 offset-md-3'>
                    <div className='card'>
                        <div className='card-header'>
                            <h2 className='text-center'>Login Form</h2>
                        </div>

                        <div className='card-body'>
                            <div className='row mb-3'>
                                <label className='col-md-2 form-label label-centering'>Username:</label>
                                <div className='col-md-10'>
                                    <input
                                        type='text'
                                        name='usernameOrEmail'
                                        placeholder='Enter username or email'
                                        className='form-control'
                                        value={loginForm.usernameOrEmail}
                                        onChange={(e) => setLoginForm({...loginForm, usernameOrEmail:e.target.value})}
                                        />
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-2 form-label label-centering'>Password:</label>
                                <div className='col-md-10'>
                                    <input
                                        type='password'
                                        name='password'
                                        placeholder='Enter password'
                                        className='form-control'
                                        value={loginForm.password}
                                        onChange={(e) => setLoginForm({...loginForm, password:e.target.value})}
                                        />
                                </div>
                            </div>

                            <button className='btn btn-primary' onClick={handleLoginForm}>Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default LoginComponent