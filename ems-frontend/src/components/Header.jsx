import { NavLink, useNavigate } from "react-router-dom";
import { isUserLoggedIn, logout } from "../services/AuthService";

const Header = () => {
  const isAuth = isUserLoggedIn();

  const navigator = useNavigate();

  const handleLogout= () => {
    logout();
    navigator("login");
  }

  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark" style={{ justifyContent: "center" }}>
          <a className="navbar-brand" href="http://localhost:3000"> Employee Management System </a>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              {
                isAuth &&
                <>
                  <li className="nav-item">
                    <NavLink className="nav-link" to={"/employees"}>
                      Employees
                    </NavLink>
                  </li>
                  <li className="nav-item">
                    <NavLink className="nav-link" to={"/departments"}>
                      Departments
                    </NavLink>
                  </li>
                  <li className="nav-item">
                    <NavLink className="nav-link" to={"/todos"}>
                      Todos
                    </NavLink>
                  </li>
                </>
              }

            </ul>
          </div>

          <ul className="navbar-nav">

            {
              !isAuth &&
              <>
                <li className="nav-item">
                  <NavLink className="nav-link" to={"/register"}>
                    Register
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink className="nav-link" to={"/login"}>
                    Login
                  </NavLink>
                </li>
              </>
            }

            {
              isAuth && 
              <>
                <li className="nav-item">
                  <NavLink className='nav-link' to={"/login"} onClick={handleLogout} >Logout</NavLink>
                </li>
              </>
            }


          </ul>
        </nav>
      </header>
    </div>
  );
};

export default Header;
