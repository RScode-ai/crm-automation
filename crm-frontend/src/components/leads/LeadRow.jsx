function LeadRow({ lead }) {

    return (

        <tr>

            <td>{lead.name}</td>

            <td>{lead.company}</td>

            <td>{lead.email}</td>

            <td>{lead.phone}</td>

            <td>{lead.status}</td>

            <td>

                <button>Edit</button>

                <button>Delete</button>

            </td>

        </tr>

    );

}

export default LeadRow;