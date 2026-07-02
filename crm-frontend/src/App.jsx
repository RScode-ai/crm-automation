import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Leads from "./pages/Leads";
import Contacts from "./pages/Contacts";
import Tasks from "./pages/Tasks";

function App() {

    return (

        <Routes>

            <Route
                path="/"
                element={<Login />}
            />

            <Route
                path="/dashboard"
                element={<Dashboard />}
            />

            <Route
                path="/leads"
                element={<Leads />}
            />

            <Route
                path="/contacts"
                element={<Contacts />}
            />

            <Route
                path="/tasks"
                element={<Tasks />}
            />

        </Routes>

    );

}

export default App;