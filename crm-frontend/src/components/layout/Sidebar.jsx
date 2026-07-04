import { Link } from "react-router-dom";

function Sidebar() {
    return (
        <div className="sidebar">

            <h2>CRM</h2>

            <Link to="/dashboard">Dashboard</Link>
            <Link to="/leads">Leads</Link>
            <Link to="/contacts">Contacts</Link>
            <Link to="/tasks">Tasks</Link>

        </div>
    );
}

export default Sidebar;