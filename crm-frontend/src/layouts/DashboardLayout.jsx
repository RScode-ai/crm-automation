import Sidebar from "../components/layout/Sidebar";
import Navbar from "../components/layout/Navbar";
import { Outlet } from "react-router-dom";

function DashboardLayout() {
    return (
        <div className="dashboard-layout">

            <Sidebar />

            <div className="main-content">

                <Navbar />

                <div className="page-content">
                    <Outlet />
                </div>

            </div>

        </div>
    );
}

export default DashboardLayout;