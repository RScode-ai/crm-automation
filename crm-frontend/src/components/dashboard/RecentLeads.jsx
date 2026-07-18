function RecentLeads() {

    const leads = [

        {
            name: "ABC Technologies",
            status: "New"
        },

        {
            name: "XYZ Pvt Ltd",
            status: "Contacted"
        },

        {
            name: "Tech Solutions",
            status: "Qualified"
        }

    ];

    return (

        <div className="widget">

            <h3>Recent Leads</h3>

            <table className="crm-table">

                <thead>

                <tr>

                    <th>Company</th>

                    <th>Status</th>

                </tr>

                </thead>

                <tbody>

                {

                    leads.map((lead,index)=>(

                        <tr key={index}>

                            <td>{lead.name}</td>

                            <td>{lead.status}</td>

                        </tr>

                    ))

                }

                </tbody>

            </table>

        </div>

    );

}

export default RecentLeads;