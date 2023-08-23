import React from 'react';
import { useDisclosure } from '@chakra-ui/react';
import { Button, Drawer, DrawerBody, DrawerContent, DrawerCloseButton, DrawerOverlay, DrawerHeader, DrawerFooter, Input } from '@chakra-ui/react';
import CreateNewCustomerFrom from './CreateCustomerFrom';

const AddIcon = () => "+";

const DrawerForm = ({fetchCustomers}) => {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <Button variant='outline' colorScheme='blue' leftIcon={<AddIcon />} onClick={onOpen}>
        Create Customer
      </Button>
      <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          <DrawerHeader>Create New Customer</DrawerHeader>
  
          <DrawerBody>
            <CreateNewCustomerFrom fetchCustomers={fetchCustomers}/>
          </DrawerBody>
  
          <DrawerFooter>
            
          </DrawerFooter>
        </DrawerContent>
      </Drawer>
    </>
  );
};

export default DrawerForm;
