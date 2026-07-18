import api from "./api";

export const getAllLeads = async () => {
    const response = await api.get("/leads");
    return response.data.data;
};

export const deleteLead = async (id) => {
    return await api.delete(`/leads/${id}`);
};