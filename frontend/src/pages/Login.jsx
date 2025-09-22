import React, { useContext, useState } from "react";
import AuthContext from "../context/AuthContext";
import AuthService from "../services/AuthService";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const { login } = useContext(AuthContext);

  const authService = new AuthService();

  const [formValues, setFormValues] = useState({
    email: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormValues((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
      role: "User",
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const result = await authService.login(formValues);
      login(result.data.token);
      alert("Login Successfully");
    } catch (e) {
      alert(e.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex min-h-screen">
      {/* Left side */}
      <div className="hidden md:flex flex-col justify-center items-center w-1/2 p-10 bg-white">
        <div className="text-3xl font-bold relative mb-8">
          <span className="relative z-10">Digital Diary</span>
          <span
            className="absolute bottom-1 left-0 w-full h-1/3 bg-gray-500 -z-10"
            style={{ opacity: 0.5 }}
          />
          <br />
          <span className="text-violet-500">A Blog Portal For JUST</span>
        </div>
        {/* SVG removed */}
      </div>

      {/* Right side */}
      <div className="flex justify-center items-center w-full md:w-1/2 p-10 bg-gray-50">
        <form
          onSubmit={handleSubmit}
          className="bg-white p-10 rounded-xl shadow-2xl w-full max-w-lg space-y-6"
        >
          <h2 className="text-3xl font-semibold">Login</h2>

          <div>
            <label htmlFor="email" className="block mb-1 font-medium">
              Email address
            </label>
            <input
              id="email"
              name="email"
              type="email"
              value={formValues.email}
              onChange={handleChange}
              required
              className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-pink-500"
            />
          </div>

          <div>
            <label htmlFor="password" className="block mb-1 font-medium">
              Password
            </label>
            <input
              id="password"
              name="password"
              type="password"
              value={formValues.password}
              onChange={handleChange}
              required
              className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-pink-500"
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-green-500 text-white py-3 rounded-md hover:bg-violet-600 transition-colors disabled:opacity-50"
          >
            {loading ? "Logging in..." : "Submit"}
          </button>
          <Link
            to="/register"
            className="w-full text-center block text-pink-600 hover:underline"
          >
            Don't have an account? Register Here:
          </Link>
        </form>
      </div>
    </div>
  );
}

export default Login;
