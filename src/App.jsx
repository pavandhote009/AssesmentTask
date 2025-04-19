import React from 'react'
import SignupForm from './Components/SignupForm'
import LoginForm from './Components/LoginForm'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

function App() {
    const router = createBrowserRouter([
      {
        path: "/",
        element: <SignupForm/>
      },
      {
        path: "/login",
        element: <LoginForm/>
      }
    ])
  return (
    <div className='bg-black'>
   <RouterProvider router={router}/>
    </div>  
  )
}

export default App