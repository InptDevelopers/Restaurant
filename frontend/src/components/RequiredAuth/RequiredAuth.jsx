import React, { useContext, useEffect, useState } from "react";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import instance from "../../../axios";
import Login from "../../pages/login/Login";

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
        if (location.pathname.startsWith("/admin")) {
          path = "/verifyAdmin";
        } else {
          path = "/verifyClient";
        }
        const res = await instance.get(`/USERS-SERVICE/api/v1/auth${path}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (location.pathname === "/login") {
          navigate("/home");
        }
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
      <Login />
    ) : (
      <div>403</div>
    )
  ) : (
    loading && <Outlet />
  );
}

export default RequiredAuth;
