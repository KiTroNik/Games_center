import React, { useContext, useEffect } from "react";
import AuthContext from "../context/AuthContext";
import { toast } from "react-toastify";
import { Navigate } from "react-router-dom";
import { RouteProps } from "./RouteProps";

const AdminRoute = ({ children }: RouteProps) => {
  const { isLogged, isAdmin } = useContext(AuthContext);

  useEffect(() => {
    if (!isLogged) {
      toast.warning("Please log in to access this page.", {
        position: toast.POSITION.TOP_CENTER,
      });
    }
  });

  return isAdmin ? <>{children}</> : <Navigate to={"/games"} />;
};

export default AdminRoute;
