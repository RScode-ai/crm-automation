function RecentTasks() {

    const tasks = [

        "Call New Client",

        "Follow up Payment",

        "Send Proposal"

    ];

    return (

        <div className="widget">

            <h3>Today's Tasks</h3>

            <ul className="task-list">

                {

                    tasks.map((task,index)=>(

                        <li key={index}>

                            {task}

                        </li>

                    ))

                }

            </ul>

        </div>

    );

}

export default RecentTasks;