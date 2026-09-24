import { useEffect, useState } from "react";
import api from "../services/api";
import AddLeadModal from "../components/leads/AddLeadModal";
import "../assets/css/leads.css";

function Leads() {

    const [showModal, setShowModal] = useState(false);

    const [selectedLead, setSelectedLead] = useState(null);

    const [search, setSearch] = useState("");

    const [currentPage, setCurrentPage] = useState(1);

    const leadsPerPage = 5;

    const [leads, setLeads] = useState([]);

    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadLeads();
    }, []);

    useEffect(() => {
        setCurrentPage(1);
    }, [search]);

    const loadLeads = async () => {

        try {

            const response = await api.get("/leads");

            setLeads(response.data.data);

        } catch (error) {

            console.log(error);

        } finally {

            setLoading(false);

        }

    };

    const filteredLeads = leads.filter((lead) => {

        return (

            lead.name?.toLowerCase().includes(search.toLowerCase())

            ||

            lead.company?.toLowerCase().includes(search.toLowerCase())

            ||

            lead.phone?.toLowerCase().includes(search.toLowerCase())

        );

    });



    const indexOfLastLead = currentPage * leadsPerPage;

    const indexOfFirstLead = indexOfLastLead - leadsPerPage;

    const currentLeads =
        filteredLeads.slice(
            indexOfFirstLead,
            indexOfLastLead
        );

    const totalPages =
        Math.ceil(filteredLeads.length / leadsPerPage);



    const deleteLead = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this Lead?"
        );

        if (!confirmDelete) {
            return;
        }

        try {

            await api.delete(`/leads/${id}`);

            alert("Lead Deleted Successfully");

            loadLeads();

        } catch (error) {

            console.log(error);

            alert("Failed to Delete Lead");

        }

    };

    const handleAddLead = () => {

        setSelectedLead(null);

        setShowModal(true);

    };

    const handleEditLead = (lead) => {

        setSelectedLead(lead);

        setShowModal(true);

    };

    if (loading) {
        return <h2>Loading Leads...</h2>;
    }

    return (

        <div className="leads-page">

            <div className="page-header">

                <h1>Leads</h1>

                <button
                    className="add-btn"
                    onClick={handleAddLead}
                >
                    + Add Lead
                </button>

            </div>

            <div className="toolbar">

                <input
                    className="search-box"
                    type="text"
                    placeholder="Search by Name, Company or Phone..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />

                <div className="pagination">

                    <button
                        disabled={currentPage === 1}
                        onClick={() => setCurrentPage(currentPage - 1)}
                    >
                        Previous
                    </button>

                    <span>
                        Page {currentPage} of {totalPages || 1}
                    </span>

                    <button
                        disabled={
                            currentPage === totalPages ||
                            totalPages === 0
                        }
                        onClick={() => setCurrentPage(currentPage + 1)}
                    >
                        Next
                    </button>

                </div>



            </div>

            <div className="table-container">

                <table>

                    <thead>

                        <tr>

                            <th>Name</th>
                            <th>Company</th>
                            <th>Status</th>
                            <th>Phone</th>
                            <th>Action</th>

                        </tr>

                    </thead>

                    <tbody>

                        {

                            filteredLeads.length > 0 ?

                                currentLeads.map((lead) => (

                                    <tr key={lead.id}>

                                        <td>{lead.name}</td>

                                        <td>{lead.company}</td>

                                        <td>
                                            <span className={`status ${lead.status}`}>
                                                {lead.status}
                                            </span>
                                        </td>

                                        <td>{lead.phone}</td>

                                        <td>

                                            <button
                                                className="edit-btn"
                                                onClick={() => handleEditLead(lead)}
                                            >
                                                Edit
                                            </button>

                                            <button
                                                className="delete-btn"
                                                onClick={() => deleteLead(lead.id)}
                                            >
                                                Delete
                                            </button>

                                        </td>

                                    </tr>

                                ))

                                :

                                <tr>

                                    <td
                                        colSpan="5"
                                        className="no-data"
                                    >
                                        No Leads Found
                                    </td>

                                </tr>

                        }

                    </tbody>

                </table>

            </div>

            {

                showModal &&

                <AddLeadModal

                    lead={selectedLead}

                    onClose={() => setShowModal(false)}

                    onLeadAdded={loadLeads}

                />

            }

        </div>




    );

}

export default Leads;