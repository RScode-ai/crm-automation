import { useEffect, useState } from "react";
import api from "../../services/api";

function AddTaskModal({ task, onClose, onTaskAdded }) {

    const [taskData, setTaskData] = useState({
        title: "",
        description: "",
        priority: "MEDIUM",
        status: "PENDING",
        dueDate: ""
    });

    useEffect(() => {

        if (task) {

            setTaskData({
                title: task.title || "",
                description: task.description || "",
                priority: task.priority || "MEDIUM",
                status: task.status || "PENDING",
                dueDate: task.dueDate
                    ? task.dueDate.substring(0, 16)
                    : ""
            });

        }

    }, [task]);

    const handleChange = (e) => {

        setTaskData({
            ...taskData,
            [e.target.name]: e.target.value
        });

    };

    const saveTask = async () => {

        try {

            if (task) {

                await api.put(
                    `/tasks/${task.id}`,
                    taskData
                );

                alert("Task Updated Successfully");

            } else {

                await api.post(
                    "/tasks",
                    taskData
                );

                alert("Task Created Successfully");

            }

            await onTaskAdded();

            onClose();

        } catch (error) {

            console.log(error);

            alert("Failed");

        }

    };

    return (

        <div className="modal">

            <div className="modal-content">

                <h2>

                    {task ? "Edit Task" : "Add Task"}

                </h2>

                <input
                    name="title"
                    placeholder="Title"
                    value={taskData.title}
                    onChange={handleChange}
                />

                <textarea
                    name="description"
                    placeholder="Description"
                    value={taskData.description}
                    onChange={handleChange}
                />

                <div className="select-row">

                    <select
                        name="priority"
                        value={taskData.priority}
                        onChange={handleChange}
                    >
                        <option value="LOW">LOW</option>
                        <option value="MEDIUM">MEDIUM</option>
                        <option value="HIGH">HIGH</option>
                    </select>

                    <select
                        name="status"
                        value={taskData.status}
                        onChange={handleChange}
                    >
                        <option value="PENDING">PENDING</option>
                        <option value="IN_PROGRESS">IN PROGRESS</option>
                        <option value="COMPLETED">COMPLETED</option>
                    </select>

                </div>

                <input
                    type="datetime-local"
                    name="dueDate"
                    value={taskData.dueDate}
                    onChange={handleChange}
                />

                <div className="modal-buttons">

                    <button
                        className="save-btn"
                        onClick={saveTask}
                    >
                        {task ? "Update Task" : "Save Task"}
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

export default AddTaskModal;