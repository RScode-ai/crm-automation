import { useEffect, useState } from "react";
import api from "../../services/api";

function AddContactModal({ contact, onClose, onContactAdded }) {

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        phone: "",
        company: "",
        designation: "",
        address: ""
    });

    useEffect(() => {

        if (contact) {

            setFormData({
                name: contact.name || "",
                email: contact.email || "",
                phone: contact.phone || "",
                company: contact.company || "",
                designation: contact.designation || "",
                address: contact.address || ""
            });

        }

    }, [contact]);

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const saveContact = async () => {

        try {

            if (contact) {

                await api.put(
                    `/contacts/${contact.id}`,
                    formData
                );

                alert("Contact Updated Successfully");

            } else {

                await api.post(
                    "/contacts",
                    formData
                );

                alert("Contact Added Successfully");

            }

            await onContactAdded();

            onClose();

        } catch (error) {

            console.log(error);

            alert("Operation Failed");

        }

    };

    return (

        <div className="modal">

            <div className="modal-content">

                <h2>

                    {
                        contact
                            ? "Edit Contact"
                            : "Add Contact"
                    }

                </h2>

                <input
                    name="name"
                    placeholder="Name"
                    value={formData.name}
                    onChange={handleChange}
                />

                <input
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                />

                <input
                    name="phone"
                    placeholder="Phone"
                    value={formData.phone}
                    onChange={handleChange}
                />

                <input
                    name="company"
                    placeholder="Company"
                    value={formData.company}
                    onChange={handleChange}
                />

                <input
                    name="designation"
                    placeholder="Designation"
                    value={formData.designation}
                    onChange={handleChange}
                />

                <textarea
                    name="address"
                    placeholder="Address"
                    rows="3"
                    value={formData.address}
                    onChange={handleChange}
                />

                <br />

                <div className="modal-buttons">

                    <button
                        className="save-btn"
                        onClick={saveContact}
                    >
                        {contact ? "Update Contact" : "Save Contact"}
                    </button>

                    <button
                        className="cancel-btn"
                        onClick={onClose}
                    >
                        Cancel
                    </button>

                </div>

            </div>

        </div>

    );

}

export default AddContactModal;