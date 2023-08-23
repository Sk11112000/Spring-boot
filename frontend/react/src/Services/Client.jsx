import axios from "axios";
export const getCustomers=async()=>{
    // eslint-disable-next-line no-useless-catch
    try{
        return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/customers`)
    }
    catch(e)
    {
        throw e;
    }
}
export const createNewCustomer= async(customer)=>{
     // eslint-disable-next-line no-useless-catch
    try{
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/add/customer`,customer)
    }
    catch(e)
    {
        throw e;
    }

}
export const deleteCustomer= async(Id)=>{
    // eslint-disable-next-line no-useless-catch
   try{
       return await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/api/v1/delete/${Id}`)
   }
   catch(e)
   {
       throw e;
   }

}
export const updateCustomer= async(Id,customer)=>{
    // eslint-disable-next-line no-useless-catch
   try{
        console.log(Id,customer)
       return await axios.put(`${import.meta.env.VITE_API_BASE_URL}/api/v1/update/${Id}`,customer)
   }
   catch(e)
   {
       throw e;
   }

}