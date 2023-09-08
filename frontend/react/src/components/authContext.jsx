import { createContext,useContext,useEffect,useState } from "react";
import {login as performLogin} from '../Services/Client'
import jwtDecode from "jwt-decode";
const AuthContext=createContext({});

const AuthProvide=({children})=>{
    const [customer,setCustomer]=useState(null);
    
    const login = async (usernameAndPassword) => {
        return new Promise((resolve, reject) => {
            performLogin(usernameAndPassword).then(res => {
                const jwtToken = res.headers["authorization"];
                console.log(jwtToken);
                localStorage.setItem("access Token",jwtToken);                     
               setCustomer({
                ...res.data.customerDTO
               })
               resolve(res);
            }).catch(err => {
                reject(err);
            })
        }
        )
       
    }
    
    const logout=()=>{
       
        localStorage.removeItem("access Token");
        setCustomer(null)
       
        
    }
    const isCustomerAuthenticated=()=>{
       const token=localStorage.getItem("access Token");
       if(!token)
       return false;
       const { exp: expiration } = jwtDecode (token) ;
       console.log(jwtDecode(token))
    if(Date.now()>expiration *1000)
    {
        logout();
        return false;
    }
    return true
        
    }

    return (
        <AuthContext.Provider value={{
            customer,
            login,
            logout,
            isCustomerAuthenticated
            
        }}>
            {children}
        </AuthContext.Provider>
    )
}
export  const useAuth=()=>useContext(AuthContext)
export default AuthProvide;