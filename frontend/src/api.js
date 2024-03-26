import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api/v1/reservations"
})

export const addReservation = async (reservation) => {
    const res = await api.post("", reservation);
    return res.data;
}