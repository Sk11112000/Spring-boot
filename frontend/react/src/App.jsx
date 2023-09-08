import React, { useEffect, useState } from 'react'
import SidebarWithHeader from './components/shared/SideBar'
import { getCustomers } from './Services/Client'
import { Wrap,WrapItem, Spinner,Text } from '@chakra-ui/react'
import CardWithImage from './components/card'
import DrawerForm from './components/DrawerForm'
import { useNavigate } from 'react-router-dom';

const App = () => {
  const [customers,setCustomers]=useState([]);
  
  const [loading,setLoading]=useState(false);
  const fetchCustomers=()=>{
    setLoading(true);
    setTimeout(()=>{
    getCustomers().then( res=>{
    
   setCustomers(res.data);
  }).catch(err=>{
    console.log(err);
  }).finally(()=>{
    setLoading(false);
  })
   },10)

  }
  useEffect(()=>{
    
     fetchCustomers();
  },[])
  if(loading){
    return(
    <SidebarWithHeader>
    <Spinner
  thickness='4px'
  speed='0.65s'
  emptyColor='gray.200'
  color='blue.500'
  size='xl'
/>
    </SidebarWithHeader>
    )
  }
  if(customers.length<=0)
  {return(
    <SidebarWithHeader>
      <DrawerForm
      fetchCustomers={fetchCustomers}
      />
   <Text>No Customer is Present</Text>
   
    </SidebarWithHeader>
  )
  }
  return (
    
    <SidebarWithHeader>
      <DrawerForm
      fetchCustomers={fetchCustomers}
      />
      <Wrap justifyContent={"center"} spacing={"30"}>
      {customers.map((customer,index)=>(
        <WrapItem>
        <CardWithImage
  id={customer.id}
  name={customer.name}
  email={customer.email}
  age={customer.age}
  gender={customer.gender}
  fetchCustomers={fetchCustomers}
/>
        </WrapItem>
      ))}
       </Wrap>
    </SidebarWithHeader>
  )
}

export default App