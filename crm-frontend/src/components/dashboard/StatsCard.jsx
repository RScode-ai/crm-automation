function StatsCard({ title, value }) {
    return (
        <div className="stats-card">

            <div className="stats-card-header">
                <h4>{title}</h4>
            </div>

            <div className="stats-card-body">
                <h2>{value}</h2>
            </div>

        </div>
    );
}

export default StatsCard;