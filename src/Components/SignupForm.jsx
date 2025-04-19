import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../Configuration/apiService";

const SignupForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({
    name: "",
    email: "",
    password: "",
  });

  // navigation
  const Navigate=useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const validateForm = () => {
    const newErrors = {
      name: "",
      email: "",
      password: "",
    };
    let isValid = true;

    // name validation
    if (formData.name.length < 3) {
      newErrors.name = "name must be at least 3 characters long.";
      isValid = false;
    }

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

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const response = await axios.post("/api/register", formData); 
        console.log(response.data); // For debugging
        alert("Signup successful!");
        Navigate("/login");
        setFormData({ name: "", email: "", password: "" });
        setErrors({ name: "", email: "", password: "" });
      } catch (error) {
        console.error("Signup failed:", error.response?.data || error.message);
        alert("Signup failed. Please try again.");
      }
    }
  };

  return (
    <div className="flex  items-center justify-center h-screen ">
      <form
        className="bg-white p-6 rounded shadow-md w-80"
        onSubmit={handleSubmit}
      >
        <h2 className="text-lg font-semibold mb-4">Signup Form</h2>
        <input
          type="text"
          name="name"
          placeholder="name"
          value={formData.name}
          onChange={handleInputChange}
          className="w-full p-2 border rounded mb-2"
        />
        {errors.name && (
          <div className="text-red-500 text-sm mb-2">{errors.name}</div>
        )}
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
          Sign Up
        </button>
      </form>
    </div>
  );
};

export default SignupForm;
