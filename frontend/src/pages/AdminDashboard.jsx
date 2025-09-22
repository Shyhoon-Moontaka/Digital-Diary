import React, { useContext, useEffect, useState } from "react";
import AdminService from "../services/AdminService";
import AuthContext from "../context/AuthContext";

const AdminDashboard = () => {
  const { adminToken } = useContext(AuthContext);
  const [users, setUsers] = useState([]);
  const [stats, setStats] = useState({});
  const [id, setId] = useState(null);
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    role: "User",
  });
  const [isEditing, setIsEditing] = useState(false);

  const adminService = new AdminService();

  useEffect(() => {
    if (adminToken) {
      loadUsers();
      loadStatistics();
    }
  }, [adminToken]);

  const loadUsers = () => {
    adminService.getAllUsers(adminToken).then((res) => setUsers(res.data));
  };

  const loadStatistics = () => {
    adminService.getStatistics(adminToken).then((res) => setStats(res.data));
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setForm({
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      role: "User",
    });
    setIsEditing(false);
    setId(null);
  };

  const handleCreateUser = (e) => {
    e.preventDefault();
    if (isEditing) {
      adminService.updateUser(id, form, adminToken).then(() => {
        loadUsers();
        resetForm();
      });
    } else {
      adminService.createUser(form, adminToken).then(() => {
        loadUsers();
        resetForm();
      });
    }
  };

  const handleDelete = (id) => {
    adminService.deleteUser(id, adminToken).then(() => loadUsers());
  };

  const handleEdit = (user) => {
    setForm({
      id: user.id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: "",
      role: user.role,
    });
    setIsEditing(true);
    setId(user.id);
  };

  return (
    <div className="p-6">
      <h1 className="text-xl font-bold mb-4">Admin Dashboard</h1>

      <div className="grid grid-cols-5 gap-4 mb-6">
        {Object.entries(stats).map(([key, value]) => (
          <div key={key} className="bg-gray-100 p-4 rounded shadow text-center">
            <p className="font-semibold">{key}</p>
            <p>{value}</p>
          </div>
        ))}
      </div>

      <h2 className="text-lg font-semibold mb-2">
        {isEditing ? "Update User" : "Create User"}
      </h2>
      <form onSubmit={handleCreateUser} className="flex gap-2 mb-4">
        <input
          type="text"
          name="firstName"
          value={form.firstName}
          onChange={handleChange}
          placeholder="First Name"
          className="border p-2"
          required
        />
        <input
          type="text"
          name="lastName"
          value={form.lastName}
          onChange={handleChange}
          placeholder="Last Name"
          className="border p-2"
          required
        />
        <input
          type="email"
          name="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Email"
          className="border p-2"
          required
        />
        <input
          type="password"
          name="password"
          value={form.password}
          onChange={handleChange}
          placeholder={isEditing ? "New Password (optional)" : "Password"}
          className="border p-2"
        />
        <select
          name="role"
          value={form.role}
          onChange={handleChange}
          className="border p-2"
        >
          <option value="User">User</option>
          <option value="Admin">Admin</option>
        </select>
        <button type="submit" className="bg-blue-500 text-white px-4 py-2">
          {isEditing ? "Update" : "Add"}
        </button>
        {isEditing && (
          <button
            type="button"
            onClick={resetForm}
            className="bg-gray-400 text-white px-4 py-2"
          >
            Cancel
          </button>
        )}
      </form>

      <table className="w-full border">
        <thead>
          <tr className="bg-gray-200">
            <th className="border px-2 py-1">ID</th>
            <th className="border px-2 py-1">Name</th>
            <th className="border px-2 py-1">Email</th>
            <th className="border px-2 py-1">Role</th>
            <th className="border px-2 py-1">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((u) => (
            <tr key={u.id}>
              <td className="border px-2 py-1">{u.id}</td>
              <td className="border px-2 py-1">
                {u.firstName} {u.lastName}
              </td>
              <td className="border px-2 py-1">{u.email}</td>
              <td className="border px-2 py-1">{u.role}</td>
              <td className="flex justify-around border px-2 py-1 flex gap-2">
                <button
                  onClick={() => handleEdit(u)}
                  className="bg-yellow-500 text-white px-2 py-1 rounded"
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(u.id)}
                  className="bg-red-500 text-white px-2 py-1 rounded"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminDashboard;
