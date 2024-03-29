import axios from "axios";
import React, { createContext, useContext, useState, useEffect } from "react";
import { Navigate } from "react-router-dom";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const login = async (email, password) => {
    try {
      const res = await axios.post(
        `http://localhost:8888/USERS-SERVICE/api/v1/auth/login?email=${email}&password=${password}`
      );
      setUser(email);
      setLoading(false);
      localStorage.setItem("accessToken", res.data.accessToken);
      // <Navigate to={window.location.href} />;
      window.location.reload();
    } catch (error) {
      throw new Error(error.message);
    }
  };

  const logout = () => {
    localStorage.removeItem("accessToken");
    setUser(null);
    setLoading(true);
  };

  return (
    <AuthContext.Provider value={{ user, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};
