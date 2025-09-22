import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function Register() {
  const authService = new AuthService();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    role: "User",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await authService.register(formData);
      navigate("/login");
      alert("We've created your account for you.");
    } catch (error) {
      alert(error);
    }
  };

  return (
    <div className="flex flex-col md:flex-row min-h-screen">
      {/* Left Side (No SVG) */}
      <div className="flex-1 flex items-center justify-center p-10">
        <div className="space-y-6 max-w-xl">
          <h1 className="text-5xl lg:text-6xl font-bold relative inline-block">
            <span className="relative z-10">Digital Diary</span>
            <span className="absolute left-0 bottom-1 w-full h-2 bg-blue-500 z-0 rounded"></span>
          </h1>
          <h2 className="text-gray-500 text-2xl font-semibold">
            A Blog Portal For JUST
          </h2>
        </div>
      </div>

      {/* Right Side */}
      <div className="flex-1 flex items-center justify-center bg-gray-50 p-10">
        <form
          onSubmit={handleSubmit}
          className="bg-white p-8 rounded-xl shadow-2xl w-full max-w-md space-y-5"
        >
          <h2 className="text-3xl font-semibold text-center">Register</h2>

          <div>
            <label className="block mb-1 font-medium">First Name</label>
            <input
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-pink-400"
              required
            />
          </div>

          <div>
            <label className="block mb-1 font-medium">Last Name</label>
            <input
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-pink-400"
              required
            />
          </div>

          <div>
            <label className="block mb-1 font-medium">Email</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-pink-400"
              required
            />
          </div>

          <div>
            <label className="block mb-1 font-medium">Password</label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-pink-400"
              required
            />
          </div>

          <button
            type="submit"
            className="w-full bg-green-500 hover:bg-pink-600 text-white py-2 px-4 rounded-md transition"
          >
            Register
          </button>

          <Link
            to="/login"
            className="w-full text-center block text-pink-600 hover:underline"
          >
            Already have an account? Login
          </Link>
        </form>
      </div>
    </div>
  );
}

export default Register;
