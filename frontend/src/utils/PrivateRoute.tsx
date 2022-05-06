import React, { useContext, useEffect } from "react";
import AuthContext from "../context/AuthContext";
import { toast } from "react-toastify";
import { Navigate } from "react-router-dom";
import { RouteProps } from "./RouteProps";

const PrivateRoute = ({ children }: RouteProps) => {
  const { isLogged, isAdmin } = useContext(AuthContext);

  useEffect(() => {
    if (!isLogged) {
      toast.warning("Please log in to access this page.", {
        position: toast.POSITION.TOP_CENTER,
      });
    }
  });

  if (isAdmin) {
    return <Navigate to={"/admin"} />;
  }

  return isLogged ? <>{children}</> : <Navigate to={"/login"} />;
};

export default PrivateRoute;
