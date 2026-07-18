function Navbar() {

    return (

        <div className="navbar">

            <div className="navbar-left">

                <h2>Dashboard</h2>

            </div>

            <div className="navbar-center">

                <input
                    type="text"
                    placeholder="Search..."
                    className="search-box"
                />

            </div>

            <div className="navbar-right">

                <button className="notification-btn">

                    🔔

                </button>

                <div className="profile">

                    <div className="avatar">

                        R

                    </div>

                    <div>

                        <h4>Rohit Sharma</h4>
                        <p>Administrator</p>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default Navbar;