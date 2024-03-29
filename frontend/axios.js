import axios from "axios";

const accessToken = localStorage.getItem("accessToken");

const instance = axios.create({
  baseURL: "http://localhost:8888",
  headers: {
    Authorization: `Bearer ${accessToken}`,
    "Content-Type": "application/json",
  },
});

export default instance;
