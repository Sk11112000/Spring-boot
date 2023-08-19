import React, { useEffect, useState } from 'react'
import SidebarWithHeader from './components/shared/SideBar'
import { getCustomers } from './Services/Client'
import { Wrap,WrapItem, Spinner,Text } from '@chakra-ui/react'
import CardWithImage from './components/card'

const App = () => {
  const [customers,setCustomers]=useState([]);
  const [loading,setLoading]=useState(false);
  useEffect(()=>{
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
   <Text>No Customer is Present</Text>
    </SidebarWithHeader>
  )
  }
  return (
    
    <SidebarWithHeader>
      <Wrap justifyContent={"center"} spacing={"30"}>
      {customers.map((customer,index)=>(
        <WrapItem>
        <CardWithImage {...customer}/>
        </WrapItem>
      ))}
       </Wrap>
    </SidebarWithHeader>
  )
}

export default App