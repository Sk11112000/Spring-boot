import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useAuth } from "../components/authContext";
const ProtectedRoute=({children})=>{
    const {isCustomerAuthenticated}=useAuth();
    console.log(isCustomerAuthenticated());
    const  navigate=useNavigate();
    useEffect (() => {
        if (!isCustomerAuthenticated()) {
        navigate("/")
        }
        }
        )
        return isCustomerAuthenticated()?children:"";


}
export default ProtectedRoute;