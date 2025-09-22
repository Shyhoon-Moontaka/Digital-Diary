import { Navigate } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";

const AdminPrivateRoute = ({ children }) => {
  const { adminToken } = useContext(AuthContext);

  if (!adminToken) {
    return <Navigate to="/admin/login" />;
  }

  return children;
};

export default AdminPrivateRoute;
