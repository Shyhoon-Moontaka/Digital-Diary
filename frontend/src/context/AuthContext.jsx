import { createContext, useState } from "react";
import { useNavigate } from "react-router-dom";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [adminToken, setAdminToken] = useState(
    localStorage.getItem("adminToken")
  );

  const login = async (token) => {
    await localStorage.setItem("token", token);
    window.location = window.location.origin + "/home";
  };

  const logout = async () => {
    await localStorage.removeItem("token");
    window.location = window.location.origin + "/login";
  };

  const value = { login, logout, token, adminToken };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export default AuthContext;
