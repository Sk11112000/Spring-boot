'use client'
import { useState } from 'react';
import { useToast } from '@chakra-ui/react';
import {
    Tag,
    Heading,
    Avatar,
    Box,
    Center,
    Image,
    Flex,
    Text,
    Stack,
    Button,
    useColorModeValue,
    Alert,  
} from '@chakra-ui/react'
import { deleteCustomer } from '../Services/Client';
import UpdateCustomerWindow from './UpdateCustomerFrom';
import { useDisclosure } from '@chakra-ui/react';
export default function CardWithImage({id,name,email,age,gender,fetchCustomers}) {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const toast = useToast();
  const DeleteCustomer=(id)=>{
    

    deleteCustomer(id).then(res=>{
      toast({
        title: 'Customer Information Delete.',
        description: "We've Delete the customer's information.",
        status: 'success',
        duration: 9000,
        isClosable: true,
      })
     
      fetchCustomers();
    }).catch(err=>{
      toast({
        title: 'Customer Information Was Not Deleted.',
        description: err.message,
        status: 'error',
        duration: 9000,
        isClosable: true,
      })
    })
  }
    if(gender==="male"||gender==="MALE"||gender==="Men")
    {
        gender="men"
    }
    else{
        gender="women"
    }
    const genderUpperCase = gender.toUpperCase()==="MEN"?"MALE":"FEMALE";
    
  return (
    <Center py={6}>
      <Box
        maxW={'270px'}
        w={'full'}
        bg={useColorModeValue('white', 'gray.800')}
        boxShadow={'2xl'}
        rounded={'md'}
        overflow={'hidden'}>
        <Image
          h={'120px'}
          w={'full'}
          src={
            'https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'
          }
          objectFit="cover"
          alt="#"
        />
        <Flex justify={'center'} mt={-12}>
          <Avatar
            size={'xl'}
            src={
              `https://randomuser.me/api/portraits/${gender}/${age}.jpg`
            }
            css={{
              border: '2px solid white',
            }}
          />
        </Flex>

        <Box p={6}>
          <Stack spacing={0} align={'center'} mb={5}>
            <Tag borderRadius={"full"}>{id}</Tag>
            <Heading fontSize={'2xl'} fontWeight={500} fontFamily={'body'}>
              {name}
            </Heading>
            <Text color={'gray.500'}>{email}</Text>
            <Text color={'gray.500'}>Age: {age} | Gender:{genderUpperCase}</Text>
          </Stack>
          <Flex>
          <Button
           onClick={()=>DeleteCustomer(id)}
            w={'full'}
            mt={8}
            mr={2}
            colorScheme='red'
            color={'white'}
            rounded={'md'}
            _hover={{
              transform: 'translateY(-2px)',
              boxShadow: 'lg',
            }}>
            Delete
          </Button>
          <Button
      onClick={onOpen}
      w={'full'}
      mt={8}
      bg={useColorModeValue('#151f21', 'gray.900')}
      color={'white'}
      rounded={'md'}
      _hover={{
        transform: 'translateY(-2px)',
        boxShadow: 'lg',
      }}
    >
      Update
    </Button>
          </Flex>

         
        </Box>
        <UpdateCustomerWindow id={id} email={email} age={age} gender={gender} name={name} isOpen={isOpen} onClose={onClose} fetchCustomers={fetchCustomers} />
      </Box>
    </Center>
  )
}