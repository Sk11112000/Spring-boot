import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { ChakraProvider, createStandaloneToast } from '@chakra-ui/react';
import Login from './components/Login.jsx'
import AuthProvide from './components/authContext.jsx';
import ProtectedRoute from './Services/ProtectedRoute.jsx';
const { ToastContainer } = createStandaloneToast();
const router = createBrowserRouter([
  {
    path: '/',
    element: <Login/>, // Changed variable name
  },
  {
    path: 'dashboard',
    element:<ProtectedRoute><App/></ProtectedRoute> ,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ChakraProvider>
      <AuthProvide>
        <RouterProvider router={router} />
        </AuthProvide>        
    </ChakraProvider>
  </React.StrictMode>
);
  