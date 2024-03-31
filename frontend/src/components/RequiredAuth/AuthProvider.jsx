import axios from "axios";
import { createContext, useContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    setUser(storedUser);
  }, []);

  const login = async (email, password) => {
    try {
      const res = await axios.post(
        `http://localhost:8888/USERS-SERVICE/api/v1/auth/login?email=${email}&password=${password}`
      );

      setUser(res.data.user);
      localStorage.setItem("user", JSON.stringify(res.data.user));
      setLoading(false);
      console.log(res.data);
      localStorage.setItem("accessToken", res.data.accessToken);
      // <Navigate to={window.location.href} />;
      window.location.reload();
    } catch (error) {
      throw new Error(error.message);
    }
  };

  const register = async (user) => {
    try {
      const res = await axios.post(
        `http://localhost:8888/USERS-SERVICE/api/v1/auth/register`,
        user
      );
      setUser(res.data.user);
      setLoading(false);
      localStorage.setItem("accessToken", res.data.accessToken);
      localStorage.setItem("user", JSON.stringify(res.data.user));
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
    <AuthContext.Provider
      value={{ setUser, user, loading, login, logout, register }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};
