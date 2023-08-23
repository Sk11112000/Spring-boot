import { Alert, AlertIcon, Box, Button, FormLabel, Input, Stack } from '@chakra-ui/react';
import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import { createNewCustomer } from '../Services/Client';
import { useState } from 'react';
import { useToast } from '@chakra-ui/react';
const MyTextInput = ({ label, ...props }) => {
  
  const [field, meta] = useField(props);
  return (
    <>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status={"error"} mt={"2px"}>
            <AlertIcon></AlertIcon>
            {meta.error}</Alert>
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
        <Alert className="error" status={"error"} mt={"2px"}>
        <AlertIcon></AlertIcon>
        {meta.error}</Alert>
      ) : null}
    </Box>
  );
};

// And now we can use these
const CreateNewCustomerFrom = ({fetchCustomers}) => {
  const toast = useToast();
 
  var newCustomererror='';
  return (
    <>
     
      <Formik
        initialValues={{
            name: '',
            email: '',
            age: 0,
            gender: '',
          }}
        validationSchema={Yup.object({
          name: Yup.string()
            .max(15, 'Must be 15 characters or less')
            .required('Required'),
          email: Yup.string()
            .email('Invalid email address')
            .required('Required'),
            age:Yup.number()
            .min(16,'Age should be atleast 16')
            .max(100,'Age should be More than 100')
            .required('Required'),
            gender: Yup.string()
            .oneOf(['MALE','FEMALE'],
            'Invalid gender').required('Required')
        })}
        onSubmit={(customer, { setSubmitting }) => {
          
          createNewCustomer(customer)
            .then(res => {
              toast({
                title: 'Customer created.',
                description: "We've created Customer for you.",
                status: 'success',
                duration: 9000,
                isClosable: true,
              })
            })
            .catch(err => {
              toast({
                
                title: 'Customer was not Created .',
                description: err.message,
                status: 'error',
                duration: 9000,
                isClosable: true,
              })
            })
            .finally(() => {
              fetchCustomers();
              setSubmitting(false);
            });
        }}
      >

       {({isValid,isSubmitting}) => (
        <Form>
          <Stack spacing={"10px"}>
          <MyTextInput
            label="Name"
            name="name"
            type="text"
            placeholder="Jane"
          />
        <MyTextInput
            label="Email Address"
            name="email"
            type="email"
            placeholder="jane@formik.com"
          />
          <MyTextInput
            label="AGE"
           name="age"
            type="number"
            placeholder="18"
          />
          
        
          <MySelect label="Gender Type" name="gender">
            <option value="">Select a Gender </option>
            <option value="MALE">MALE</option>
            <option value="FEMALE">FEMALE</option>
          </MySelect>
          <Button type="submit" disabled={!isValid || isSubmitting}>
              Submit
            </Button>


          
        </Stack>
        </Form>)}
      </Formik>
    </>
  );
};
export default CreateNewCustomerFrom;