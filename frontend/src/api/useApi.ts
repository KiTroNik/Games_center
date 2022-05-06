import axios, { AxiosRequestConfig } from "axios";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";
import dayjs from "dayjs";

const baseURL = "http://localhost:8090";

interface IUser {
  sub: string;
  roles: string[];
  iss: string;
  exp: number;
}

const UseApi = () => {
  const navigate = useNavigate();
  const { authTokens, setIsLogged, setAuthTokens, isAdmin, setIsAdmin } =
    useContext(AuthContext);
  const axiosInstance = axios.create({
    baseURL,
    headers: {
      "Content-Type": "application/json",
      Authorization: authTokens ? `Bearer ${authTokens.access}` : "",
    },
  });

  if (authTokens) {
    axiosInstance.interceptors.request.use(async (req: AxiosRequestConfig) => {
      const user: IUser = jwt_decode(authTokens?.access!)
      const isExpired = dayjs.unix(user.exp).diff(dayjs()) < 1;

      if (user.roles.includes("ROLE_ADMIN")) {
        setIsAdmin(true);
      }

      if (!isExpired) return req;

      try {
        const response = await axios.post(`${baseURL}/api/token/refresh/`, {
          refresh: authTokens?.refresh,
        });

        localStorage.setItem("authTokens", JSON.stringify(response.data));
        setAuthTokens(response.data);
        setIsLogged(true);
        req.headers!.Authorization = `Bearer ${response.data.access}`;
        return req;
      } catch (e) {
        setIsLogged(false);
        setAuthTokens(null);
        setIsAdmin(false);
        localStorage.removeItem("authTokens");
        navigate("/");
      }
    });
  }

  return axiosInstance;
};

export default UseApi;
