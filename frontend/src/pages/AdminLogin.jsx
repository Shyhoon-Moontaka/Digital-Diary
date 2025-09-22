import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AdminService from "../services/AdminService";

const AdminLogin = () => {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const adminService = new AdminService();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    adminService
      .login(form)
      .then((res) => {
        localStorage.setItem("adminToken", res.data.token);
        localStorage.setItem("adminId", res.data.adminId);
        navigate("/admin/dashboard");
      })
      .catch(() => {
        setError("Invalid email or password");
      });
  };

  return (
    <div className="flex items-center justify-center h-screen bg-gray-50">
      <form
        onSubmit={handleSubmit}
        className="bg-white shadow-md rounded p-6 w-96"
      >
        <h2 className="text-xl font-bold mb-4">Admin Login</h2>
        {error && <p className="text-red-500 mb-2">{error}</p>}

        <input
          type="email"
          name="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Email"
          className="border w-full p-2 mb-3"
          required
        />
        <input
          type="password"
          name="password"
          value={form.password}
          onChange={handleChange}
          placeholder="Password"
          className="border w-full p-2 mb-3"
          required
        />

        <button
          type="submit"
          className="bg-blue-500 text-white w-full py-2 rounded"
        >
          Login
        </button>
      </form>
    </div>
  );
};

export default AdminLogin;
