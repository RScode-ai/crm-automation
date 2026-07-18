import { useState } from "react";
import api from "../../services/api";

function AddLeadModal({ onClose, onLeadAdded }) {

    const [lead, setLead] = useState({
        name: "",
        email: "",
        phone: "",
        company: "",
        source: "",
        status: "NEW"
    });

    const handleChange = (e) => {

        setLead({
            ...lead,
            [e.target.name]: e.target.value
        });

    };

    const saveLead = async () => {

        try {

            const response = await api.post("/leads", lead);

            console.log("Lead Created :", response.data);

            await onLeadAdded();

            alert("Lead Added Successfully");

            onClose();

        } catch (error) {

            console.log(error);

            alert("Failed to Add Lead");

        }

    };

    return (

        <div className="modal">

            <div className="modal-content">

                <h2>Add New Lead</h2>

                <input
                    name="name"
                    placeholder="Name"
                    value={lead.name}
                    onChange={handleChange}
                />

                <input
                    name="email"
                    placeholder="Email"
                    value={lead.email}
                    onChange={handleChange}
                />

                <input
                    name="phone"
                    placeholder="Phone"
                    value={lead.phone}
                    onChange={handleChange}
                />

                <input
                    name="company"
                    placeholder="Company"
                    value={lead.company}
                    onChange={handleChange}
                />

                <input
                    name="source"
                    placeholder="Source"
                    value={lead.source}
                    onChange={handleChange}
                />

                <br /><br />

                <button onClick={saveLead}>
                    Save
                </button>

                <button onClick={onClose}>
                    Cancel
                </button>

            </div>

        </div>

    );

}

export default AddLeadModal;