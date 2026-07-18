import { useEffect, useState } from "react";
import api from "../services/api";
import AddLeadModal from "../components/leads/AddLeadModal";

function Leads() {

    const [showModal, setShowModal] = useState(false);
    const [leads, setLeads] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadLeads();
    }, []);

const loadLeads = async () => {
    try {
        const response = await api.get("/leads");

        console.log("Complete Response =", response.data);
        console.log("Leads Array =", response.data.data);

        setLeads(response.data.data);
    } catch (error) {
        console.log(error);
    } finally {
        setLoading(false);
    }
};

    if (loading) {
        return <h2>Loading Leads...</h2>;
    }

    return (

        <div>

            <div className="page-header">

                <h1>Leads</h1>

                <button onClick={() => setShowModal(true)}>
                    + Add Lead
                </button>

            </div>

            <br />

            <input
                type="text"
                placeholder="Search Lead..."
            />

            <br /><br />

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
                    leads.length > 0 ? (

                        leads.map((lead) => (

                            <tr key={lead.id}>

                                <td>{lead.name}</td>
                                <td>{lead.company}</td>
                                <td>{lead.status}</td>
                                <td>{lead.phone}</td>

                                <td>

                                    <button>Edit</button>

                                    <button>Delete</button>

                                </td>

                            </tr>

                        ))

                    ) : (

                        <tr>

                            <td colSpan="5">
                                No Leads Found
                            </td>

                        </tr>

                    )
                }

                </tbody>

            </table>

            {
                showModal && (

                    <AddLeadModal
                        onClose={() => setShowModal(false)}
                        onLeadAdded={loadLeads}
                    />

                )
            }

        </div>

    );

}

export default Leads;