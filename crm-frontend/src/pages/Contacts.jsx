import { useEffect, useState } from "react";
import api from "../services/api";
import AddContactModal from "../components/contacts/AddContactModal";
import "../assets/css/leads.css";

function Contacts() {

    const [contacts, setContacts] = useState([]);

    const [loading, setLoading] = useState(true);

    const [showModal, setShowModal] = useState(false);

    const [selectedContact, setSelectedContact] = useState(null);

    const [search, setSearch] = useState("");

    const [currentPage, setCurrentPage] = useState(1);

    const contactsPerPage = 5;

    useEffect(() => {

        loadContacts();

    }, []);

    useEffect(() => {

        setCurrentPage(1);

    }, [search]);

    const loadContacts = async () => {

        try {

            const response = await api.get("/contacts");

            setContacts(response.data.data);

        } catch (error) {

            console.log(error);

        } finally {

            setLoading(false);

        }

    };

    const filteredContacts = contacts.filter(contact =>

        contact.name?.toLowerCase().includes(search.toLowerCase())

        ||

        contact.company?.toLowerCase().includes(search.toLowerCase())

        ||

        contact.phone?.toLowerCase().includes(search.toLowerCase())

    );

    const indexOfLast = currentPage * contactsPerPage;

    const indexOfFirst = indexOfLast - contactsPerPage;

    const currentContacts = filteredContacts.slice(

        indexOfFirst,

        indexOfLast

    );

    const totalPages = Math.ceil(

        filteredContacts.length / contactsPerPage

    );

    const deleteContact = async (id) => {

        const confirmDelete = window.confirm(

            "Delete this Contact?"

        );

        if (!confirmDelete) return;

        try {

            await api.delete(`/contacts/${id}`);

            alert("Contact Deleted");

            loadContacts();

        } catch (error) {

            console.log(error);

            alert("Delete Failed");

        }

    };

    const handleAddContact = () => {

        setSelectedContact(null);

        setShowModal(true);

    };

    const handleEditContact = (contact) => {

        setSelectedContact(contact);

        setShowModal(true);

    };

    if (loading) {

        return <h2>Loading Contacts...</h2>;

    }

    return (

        <div className="leads-page">

            <div className="page-header">

                <h1>Contacts</h1>

                <button

                    className="add-btn"

                    onClick={handleAddContact}

                >

                    + Add Contact

                </button>

            </div>

            <div className="toolbar">

                <input

                    className="search-box"

                    type="text"

                    placeholder="Search Contact..."

                    value={search}

                    onChange={(e)=>setSearch(e.target.value)}

                />

                <div className="pagination">

                    <button

                        disabled={currentPage===1}

                        onClick={()=>setCurrentPage(currentPage-1)}

                    >

                        Previous

                    </button>

                    <span>

                        Page {currentPage} of {totalPages || 1}

                    </span>

                    <button

                        disabled={currentPage===totalPages || totalPages===0}

                        onClick={()=>setCurrentPage(currentPage+1)}

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

                        <th>Designation</th>

                        <th>Phone</th>

                        <th>Action</th>

                    </tr>

                    </thead>

                    <tbody>

                    {

                        currentContacts.length>0 ?

                        currentContacts.map(contact=>(

                            <tr key={contact.id}>

                                <td>{contact.name}</td>

                                <td>{contact.company}</td>

                                <td>{contact.designation}</td>

                                <td>{contact.phone}</td>

                                <td>

                                    <button

                                        className="edit-btn"

                                        onClick={()=>handleEditContact(contact)}

                                    >

                                        Edit

                                    </button>

                                    <button

                                        className="delete-btn"

                                        onClick={()=>deleteContact(contact.id)}

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

                                No Contacts Found

                            </td>

                        </tr>

                    }

                    </tbody>

                </table>

            </div>

            {

                showModal &&

                <AddContactModal

                    contact={selectedContact}

                    onClose={()=>setShowModal(false)}

                    onContactAdded={loadContacts}

                />

            }

        </div>

    );

}

export default Contacts;