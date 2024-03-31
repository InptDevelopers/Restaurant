import { useContext, useEffect, useState } from "react";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import instance from "../../../axios";
import Login from "../../pages/login/Login";
import Navbar from "../restaurantcomponents/navbar";
import Error403Page from "../../pages/Error403Page";
import { useAuth } from "./AuthProvider";
import SignUp from "../../pages/SignUp/SignUp";

function RequiredAuth() {
  const navigate = useNavigate();
  const location = useLocation();
  const [isSignPage, setIsSignPage] = useState(false);
  const [loading, setLoading] = useState(false);
  const testUserPermission = async () => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      try {
        let path;
        const user = JSON.parse(localStorage.getItem("user"));
        if (user.role === "ADMIN") {
          path = "/verifyAdmin";
        } else {
          path = "/verifyClient";
        }

        await instance.get(`/USERS-SERVICE/api/v1/auth${path}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (
          location.pathname === "/login" ||
          location.pathname === "/register" ||
          location.pathname === "/"
        ) {
          if (user.role === "ADMIN") navigate("/admin/restaurant");
          if (user.role === "CLIENT") navigate("/restaurants");
        }
        console.log(location.pathname);

        setIsSignPage(false);
      } catch (err) {
        setIsSignPage(true);
        // navigate("/login");
      }
    } else {
      setIsSignPage(true);
    }
    setLoading(true);
  };

  useEffect(() => {
    testUserPermission();
  }, []);

  return isSignPage ? (
    localStorage.getItem("accessToken") == null ? (
      location.pathname === "/register" ? (
        <SignUp />
      ) : (
        <Login />
      )
    ) : (
      <Error403Page />
    )
  ) : (
    loading && (
      <>
        <Navbar />
        <Outlet />
      </>
    )
  );
}

export default RequiredAuth;
