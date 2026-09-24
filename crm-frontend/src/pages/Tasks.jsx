import { useEffect, useState } from "react";
import api from "../services/api";
import AddTaskModal from "../components/tasks/AddTaskModal";
import "../assets/css/task.css";

function Tasks() {

    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(true);

    const [showModal, setShowModal] = useState(false);
    const [selectedTask, setSelectedTask] = useState(null);

    const [search, setSearch] = useState("");

    const [currentPage, setCurrentPage] = useState(1);
    const tasksPerPage = 5;

    useEffect(() => {
        loadTasks();
    }, []);

    useEffect(() => {
        setCurrentPage(1);
    }, [search]);

    const loadTasks = async () => {

        try {

            const response = await api.get("/tasks");

            setTasks(response.data.data);

        } catch (error) {

            console.log(error);

        } finally {

            setLoading(false);

        }

    };

    const filteredTasks = tasks.filter((task) =>

        task.title?.toLowerCase().includes(search.toLowerCase()) ||

        task.description?.toLowerCase().includes(search.toLowerCase()) ||

        task.priority?.toLowerCase().includes(search.toLowerCase()) ||

        task.status?.toLowerCase().includes(search.toLowerCase())

    );

    const indexOfLastTask = currentPage * tasksPerPage;
    const indexOfFirstTask = indexOfLastTask - tasksPerPage;

    const currentTasks =
        filteredTasks.slice(
            indexOfFirstTask,
            indexOfLastTask
        );

    const totalPages =
        Math.ceil(filteredTasks.length / tasksPerPage);

    const handleAddTask = () => {

        setSelectedTask(null);

        setShowModal(true);

    };

    const handleEditTask = (task) => {

        setSelectedTask(task);

        setShowModal(true);

    };

    const deleteTask = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this Task?"
        );

        if (!confirmDelete) return;

        try {

            await api.delete(`/tasks/${id}`);

            alert("Task Deleted Successfully");

            loadTasks();

        } catch (error) {

            console.log(error);

            alert("Failed to Delete Task");

        }

    };

    if (loading) {

        return <h2>Loading Tasks...</h2>;

    }

    return (

        <div className="tasks-page">

            <div className="page-header">

                <h1>Tasks</h1>

                <button
                    className="add-btn"
                    onClick={handleAddTask}
                >
                    + Add Task
                </button>

            </div>

            <div className="toolbar">

                <input
                    className="search-box"
                    type="text"
                    placeholder="Search Task..."
                    value={search}
                    onChange={(e) =>
                        setSearch(e.target.value)
                    }
                />

                <div className="pagination">

                    <button
                        disabled={currentPage === 1}
                        onClick={() =>
                            setCurrentPage(currentPage - 1)
                        }
                    >
                        Previous
                    </button>

                    <span>

                        Page {currentPage} of {totalPages || 1}

                    </span>

                    <button
                        disabled={
                            currentPage === totalPages ||
                            totalPages === 0
                        }
                        onClick={() =>
                            setCurrentPage(currentPage + 1)
                        }
                    >
                        Next
                    </button>

                </div>

            </div>

            <div className="table-container">

                <table>

                    <thead>

                        <tr>

                            <th>Title</th>
                            <th>Priority</th>
                            <th>Status</th>
                            <th>Due Date</th>
                            <th>Action</th>

                        </tr>

                    </thead>

                    <tbody>

                        {

                            currentTasks.length > 0 ?

                                currentTasks.map((task) => (

                                    <tr key={task.id}>

                                        <td>{task.title}</td>

                                        <td>

                                            <span
                                                className={`priority ${task.priority}`}
                                            >
                                                {task.priority}
                                            </span>

                                        </td>

                                        <td>

                                            <span
                                                className={`status ${task.status}`}
                                            >
                                                {task.status}
                                            </span>

                                        </td>

                                        <td>

                                            {
                                                task.dueDate ?

                                                    new Date(task.dueDate)
                                                        .toLocaleDateString()

                                                    :

                                                    "-"
                                            }

                                        </td>

                                        <td>

                                            <button
                                                className="edit-btn"
                                                onClick={() =>
                                                    handleEditTask(task)
                                                }
                                            >
                                                Edit
                                            </button>

                                            <button
                                                className="delete-btn"
                                                onClick={() =>
                                                    deleteTask(task.id)
                                                }
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
                                        No Tasks Found
                                    </td>

                                </tr>

                        }

                    </tbody>

                </table>

            </div>

            {

                showModal &&

                <AddTaskModal

                    task={selectedTask}

                    onClose={() =>
                        setShowModal(false)
                    }

                    onTaskAdded={loadTasks}

                />

            }

        </div>

    );

}

export default Tasks;