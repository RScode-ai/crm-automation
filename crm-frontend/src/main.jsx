import React from "react";
import ReactDOM from "react-dom/client";
import AppRoutes from "./routes/AppRoutes";
import "./assets/css/dashboard.css";
import "./assets/css/global.css";
import "./assets/css/layout.css";
import "./assets/css/sidebar.css";
import "./assets/css/navbar.css";


ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <AppRoutes />
    </React.StrictMode>
);