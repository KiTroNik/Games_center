import {RouteProps} from "./RouteProps";
import React, {useContext} from "react";
import AuthContext from "../context/AuthContext";
import {Navigate} from "react-router-dom";

const UserLoggedRoute = ({children}: RouteProps) => {
  const {isLogged} = useContext(AuthContext);
  return isLogged ? <Navigate to={"/games"} /> : <>{children}</>;
}

export default UserLoggedRoute;
