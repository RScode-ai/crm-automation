import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Dashboard from "../pages/Dashboard";
import Leads from "../pages/Leads";
import Contacts from "../pages/Contacts";
import Tasks from "../pages/Tasks";
import Login from "../pages/Login";

import DashboardLayout from "../layouts/DashboardLayout";

// simple auth check (temporary)
const isAuthenticated = true;

function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>

                {/* LOGIN ROUTE */}
                <Route path="/login" element={<Login />} />

                {/* PROTECTED ROUTES */}
                <Route
                    path="/"
                    element={
                        isAuthenticated ? (
                            <DashboardLayout />
                        ) : (
                            <Navigate to="/login" />
                        )
                    }
                >

                    <Route index element={<Dashboard />} />
                    <Route path="dashboard" element={<Dashboard />} />
                    <Route path="leads" element={<Leads />} />
                    <Route path="contacts" element={<Contacts />} />
                    <Route path="tasks" element={<Tasks />} />

                </Route>

            </Routes>
        </BrowserRouter>
    );
}

export default AppRoutes;