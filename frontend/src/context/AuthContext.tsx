import { createContext, useEffect, useState } from "react";
import React from "react";

interface AppContextInterface {
  authTokens: Itokens | null;
  setAuthTokens: React.Dispatch<React.SetStateAction<Itokens | null>>;
  isLogged: boolean;
  setIsLogged: React.Dispatch<React.SetStateAction<boolean>>;
  isAdmin: boolean;
  setIsAdmin: React.Dispatch<React.SetStateAction<boolean>>;
}

const AuthContext = createContext<AppContextInterface>(
  {} as AppContextInterface
);

export default AuthContext;

interface authProviderProps {
  children: React.ReactNode;
}

interface Itokens {
  access: string;
  refresh: string;
}

export const AuthProvider = ({ children }: authProviderProps) => {
  const [authTokens, setAuthTokens] = useState<Itokens | null>(() => {
    return localStorage.getItem("authTokens")
      ? JSON.parse(localStorage.getItem("authTokens")!)
      : null;
  });
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLogged, setIsLogged] = useState(() => {
    return !!localStorage.getItem("authTokens");
  });

  const contextData = {
    authTokens: authTokens,
    setAuthTokens: setAuthTokens,
    isLogged: isLogged,
    setIsLogged: setIsLogged,
    isAdmin: isAdmin,
    setIsAdmin: setIsAdmin,
  };

  useEffect(() => {
    if (authTokens) {
      setIsLogged(true);
    }
  }, [authTokens]);

  return (
    <AuthContext.Provider value={contextData}>{children}</AuthContext.Provider>
  );
};
