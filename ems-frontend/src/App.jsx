import './App.css'
import DepartmentComponent from './components/DepartmentComponent'
import EmployeeComponent from './components/EmployeeComponent'
import Footer from './components/Footer'
import Header from './components/Header'
import ListDepartment from './components/ListDepartment'
import ListEmployee from './components/ListEmployee'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import ListTodo from './components/ListTodo'
import TodoComponent from './components/TodoComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { getLoggedInUser, isUserLoggedIn } from './services/AuthService'
import ErrorPageComponent from './components/ErrorPageComponent'
import PropTypes, { node } from 'prop-types'

function App() {

  const AuthenticatedRequest = ({ children }) => {
      const isAuth = isUserLoggedIn();

      if (isAuth) {
        return children;
      }

      return <Navigate to="/" />
  }

  AuthenticatedRequest.propTypes = {
    children: PropTypes.node.isRequired,
  }


  window.addEventListener("storage", () => {

    if (getLoggedInUser() === null && !isUserLoggedIn()) {

      window.location.reload(false);
      window.location.assign('http://' + window.location.hostname + ':' + 3000 + '/login');
    }

  });

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          {/*http://localhost:300*/}
          <Route path='/' element={<LoginComponent />}></Route>

          {/*http://localhost:300/employees*/}
          <Route path='/employees' element={
          <AuthenticatedRequest>
          <ListEmployee />
          </AuthenticatedRequest>
          }></Route>

          {/*http://localhost:300/add-employee*/}
          <Route path='/add-employee' element={
          <AuthenticatedRequest>
          <EmployeeComponent />
          </AuthenticatedRequest>
          }></Route>


          {/*http://localhost:300/edit-employee/1*/}
          <Route path='/edit-employee/:id' element={
          <AuthenticatedRequest>
          <EmployeeComponent />
          </AuthenticatedRequest>
          }></Route>

          {/*http://localhost:300/departments*/}
          <Route path='/departments' element={
          <AuthenticatedRequest>
          <ListDepartment />
          </AuthenticatedRequest>
          }></Route>
          
          {/*http://localhost:300/add-department*/}
          <Route path='/add-department' element={
          <AuthenticatedRequest>
          <DepartmentComponent />
          </AuthenticatedRequest>
          }></Route>


          {/*http://localhost:300/edit-department/1*/}
          <Route path='/edit-department/:id' element={
          <AuthenticatedRequest>
            <DepartmentComponent />
          </AuthenticatedRequest>
          }></Route>

          {/*http://localhost:300/todos*/}
          <Route path='todos' element={
          <AuthenticatedRequest>
          <ListTodo />
          </AuthenticatedRequest>
          }></Route>

          {/*http://localhost:300/add-todo*/}
          <Route path='add-todo' element={
          <AuthenticatedRequest>
          <TodoComponent />
          </AuthenticatedRequest>
          }></Route>

          {/*http://localhost:300/edit-todo/1*/}
          <Route path='edit-todo/:id' element={
          <AuthenticatedRequest>
          <TodoComponent />
          </AuthenticatedRequest>
          }></Route>



          {/* http://localhost:3000/register */}
          <Route path='register' element={<RegisterComponent />}></Route>
          {/* http://localhost:3000/login */}
          <Route path='login' element={<LoginComponent />}></Route>

          {/* 
            <Route path='404' element={<ErrorPageComponent />}/>
            <Route path='*' element={<Navigate to='404' />} />
            */}
        </Routes>
        {/*<Footer />*/}
      </BrowserRouter>
    </>
  )
}

export default App
