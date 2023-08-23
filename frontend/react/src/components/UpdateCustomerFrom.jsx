import { Button, Stack } from "@chakra-ui/react";
import React from "react";
import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import { Input,Alert,AlertIcon } from "@chakra-ui/react";
import { updateCustomer } from '../Services/Client';

import {
  useToast,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalCloseButton,
  ModalBody,
  ModalFooter,
  FormLabel,
  Box,
} from "@chakra-ui/react";

const MyTextInput = ({ label, defaultValue, ...props }) => {
  const [field, meta] = useField(props);

  return (
    <>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Input className="text-input" {...field} {...props} defaultValue={defaultValue} />
      {meta.touched && meta.error ? (
        <Alert className="error" status="error" mt="2px">
          <AlertIcon />
          {meta.error}
        </Alert>
      ) : null}
    </>
  );
};

const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <select {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status="error" mt="2px">
          <AlertIcon />
          {meta.error}
        </Alert>
      ) : null}
    </Box>
  );
};

function UpdateCustomerWindow({ id, name, age, gender, email, isOpen, onClose,fetchCustomers }) {
  const toast = useToast();

  return (
    <>
      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Update Customer</ModalHeader>
          <ModalCloseButton />
          <Formik
            initialValues={{
              name: name,
              email: email,
              age: age,
              gender: gender,
            }}
            validationSchema={Yup.object({
              name: Yup.string()
                .max(15, 'Must be 15 characters or less')
                .required('Required'),
              email: Yup.string()
                .email('Invalid email address')
                .required('Required'),
              age: Yup.number()
                .min(16, 'Age should be at least 16')
                .max(100, 'Age should be no more than 100')
                .required('Required'),
              gender: Yup.string()
                .oneOf(['MALE', 'FEMALE'], 'Invalid gender')
                .required('Required'),
            })}
            onSubmit={(customer, { setSubmitting }) => {
              
              // Uncomment the following updateCustomer() logic when you want to enable it
             
              updateCustomer(id, customer)
                .then(res => {
                    
                  toast({
                    title: 'Customer updated.',
                    description: "We've updated the customer's information.",
                    status: 'success',
                    duration: 9000,
                    isClosable: true,
                  });
                  fetchCustomers();
                })
                .catch(err => {
                  toast({
                    title: 'Customer was not updated.',
                    description: err.message,
                    status: 'error',
                    duration: 9000,
                    isClosable: true,
                  });
                })
                .finally(() => {
                  setSubmitting(false);
                });
              
            }}
          >
            {({ isValid, isSubmitting }) => (
              <Form>
                <Stack spacing={"10px"} p={"1em"}>
                  <MyTextInput
                    label="Name"
                    name="name"
                    type="text"
                    placeholder="Jane"
                    defaultValue={name}
                  />
                  <MyTextInput
                    label="Email Address"
                    name="email"
                    type="email"
                    placeholder="jane@formik.com"
                    defaultValue={email}
                  />
                  <MyTextInput
                    label="Age"
                    name="age"
                    type="number"
                    placeholder="18"
                    defaultValue={age}
                  />
                  <MySelect label="Gender Type" name="gender">
                    <option value="">Select a Gender</option>
                    <option value="MALE">MALE</option>
                    <option value="FEMALE">FEMALE</option>
                  </MySelect>
                  <Button
                    type="submit"
                    disabled={!isValid || isSubmitting}
                    onClick={onClose}
                  >
                    Submit
                  </Button>
                  <Button onClick={onClose}>Cancel</Button>
                </Stack>
              </Form>
            )}
          </Formik>
        </ModalContent>
      </Modal>
    </>
  );
}

export default UpdateCustomerWindow;
