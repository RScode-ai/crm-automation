import { NavLink } from "react-router-dom";

function Sidebar() {

    return (

        <div className="sidebar">

            <div className="sidebar-logo">

                <h2>CRM</h2>

                <p>Automation</p>

            </div>

            <div className="sidebar-menu">

                <NavLink
                    to="/dashboard"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >
                    Dashboard
                </NavLink>

                <NavLink
                    to="/leads"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >
                    Leads
                </NavLink>

                <NavLink
                    to="/contacts"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >
                    Contacts
                </NavLink>

                <NavLink
                    to="/tasks"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >
                    Tasks
                </NavLink>

            </div>

            <div className="sidebar-footer">

                <button className="logout-btn">

                    Logout

                </button>

            </div>

        </div>

    );

}

export default Sidebar;