import { useEffect, useState } from "react";
import StatsCard from "../components/dashboard/StatsCard";
import { getDashboardStats } from "../services/dashboardService";

function Dashboard() {

    const [stats, setStats] = useState(null);

    useEffect(() => {
        loadDashboard();
    }, []);

    const loadDashboard = async () => {
        try {
            const data = await getDashboardStats();
            setStats(data);
        } catch (error) {
            console.log(error);
            alert("Unable to load dashboard.");
        }
    };

    if (!stats) {
        return <h2>Loading...</h2>;
    }

    return (
        <>
            <h1>Dashboard</h1>

            <StatsCard
                title="Total Leads"
                value={stats.totalLeads}
            />

            <StatsCard
                title="Total Contacts"
                value={stats.totalContacts}
            />

            <StatsCard
                title="Total Tasks"
                value={stats.totalTasks}
            />

            <StatsCard
                title="Pending Tasks"
                value={stats.pendingTasks}
            />

            <StatsCard
                title="Completed Tasks"
                value={stats.completedTasks}
            />
        </>
    );
}

export default Dashboard;