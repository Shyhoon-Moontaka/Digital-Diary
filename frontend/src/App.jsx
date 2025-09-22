import "./App.css";
import Register from "./pages/Register";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import ProfileView from "./pages/ProfileView";
import Network from "./pages/Network";
import AddPost from "./pages/AddPost";
import PrivateRoute from "./pages/PrivateRoute";
import SharedPost from "./pages/SharedPost";
import ProfileEdit from "./pages/ProfileEdit";
import AdminLogin from "./pages/AdminLogin";
import AdminRegister from "./pages/AdminRegister";
import AdminDashboard from "./pages/AdminDashboard";
import AdminPrivateRoute from "./pages/AdminPrivateRoute";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route
          path="/home"
          element={
            <PrivateRoute>
              <Home />
            </PrivateRoute>
          }
        />
        <Route
          path="/network"
          element={
            <PrivateRoute>
              <Network />
            </PrivateRoute>
          }
        />
        <Route
          path="/addPost"
          element={
            <PrivateRoute>
              <AddPost />
            </PrivateRoute>
          }
        />
        <Route
          path="/profile/:userId"
          element={
            <PrivateRoute>
              <ProfileView />
            </PrivateRoute>
          }
        />
        <Route
          path="/profile/edit"
          element={
            <PrivateRoute>
              <ProfileEdit />
            </PrivateRoute>
          }
        />
        <Route
          path="/post/:postId"
          element={
            <PrivateRoute>
              <SharedPost />
            </PrivateRoute>
          }
        />
        <Route path="/admin/login" element={<AdminLogin />} />
        <Route path="/admin/register" element={<AdminRegister />} />
        <Route
          path="/admin/dashboard"
          element={
            <AdminPrivateRoute>
              <AdminDashboard />
            </AdminPrivateRoute>
          }
        />
        <Route path="*" element={<Register />} />
      </Routes>
    </>
  );
}

export default App;
