import LeadRow from "./LeadRow";

function LeadTable({ leads }) {

    return (

        <table>

            <thead>

                <tr>

                    <th>Name</th>

                    <th>Company</th>

                    <th>Email</th>

                    <th>Phone</th>

                    <th>Status</th>

                    <th>Actions</th>

                </tr>

            </thead>

            <tbody>

                {

                    leads.map((lead) => (

                        <LeadRow
                            key={lead.id}
                            lead={lead}
                        />

                    ))

                }

            </tbody>

        </table>

    );

}

export default LeadTable;