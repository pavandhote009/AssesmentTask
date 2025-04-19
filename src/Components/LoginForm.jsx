import React, { useState } from "react";
import axios from "../Configuration/apiService";
const LoginForm = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({
    email: "",
    password: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const validateForm = () => {
    const newErrors = {
      email: "",
      password: "",
    };
    let isValid = true;

    // Email validation
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(formData.email)) {
      newErrors.email = "Please enter a valid email address.";
      isValid = false;
    }

    // Password validation
    if (formData.password.length < 6) {
      newErrors.password = "Password must be at least 6 characters long.";
      isValid = false;
    }

    setErrors(newErrors);
    return isValid;
  };

  /**
   * Handles form submission for the login form.
   *
   * @param {Event} e The form submission event.
   *
   * Calls validateForm() to check if the form is valid.
   * If the form is valid, displays a success message and resets the form.
   * If the form is invalid, displays error messages for the invalid fields.
   */
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const response = await axios.post("/api/login", formData);
        const jwtToken = response.data.token; 
        console.log(response.data); 
        localStorage.setItem("token", jwtToken);

        alert("Login successful!");
        setFormData({ email: "", password: "" });
        setErrors({ email: "", password: "" });
      } catch (error) {
        console.error("Login failed:", error.response?.data || error.message);
        alert("Login failed. Please try again.");
      }
    }
  };
  

  return (
    <div className="flex items-center justify-center h-screen ">
      <form
        className="bg-white p-6 rounded shadow-md w-96 "
        onSubmit={handleSubmit}
      >
        <h2 className="text-lg font-semibold mb-4">Login Form</h2>
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleInputChange}
          className="w-full p-2 border rounded mb-2"
        />
        {errors.email && (
          <div className="text-red-500 text-sm mb-2">{errors.email}</div>
        )}
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleInputChange}
          className="w-full p-2 border rounded mb-2"
        />
        {errors.password && (
          <div className="text-red-500 text-sm mb-2">{errors.password}</div>
        )}
        <button
          type="submit"
          className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600"
        >
          Log In
        </button>
      </form>
    </div>
  );
};

export default LoginForm;