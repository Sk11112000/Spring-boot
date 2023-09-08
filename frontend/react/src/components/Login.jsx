
import {
  Button,
  Checkbox,
  Flex,
  Text,
  FormControl,
  FormLabel,
  Heading,
  Input,
  Stack,
  Image,
  Alert, AlertIcon,
  
  
} from '@chakra-ui/react'

import * as Yup from 'yup'
import {Formik, Form, useField} from "formik";
import {errorNotification} from '../Services/Notification'
import { useAuth } from './authContext';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
var Userdata;
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
  
const LoginFrom=()=>{
    const navigate =useNavigate();
    const {login}=useAuth();
    return(
        <Formik 
        validateOnMount={true}
        validationSchema={Yup.object({
            username:Yup.string().email("Must be vaild email")
            .required("Email is Required"),
            password:Yup.string().max(20,"Password cannot be more than 20 Words")
            .required("Password is required")
        })}
        initialValues={{username:'',password:''}}
        onSubmit={(value, { setSubmitting })=>{
            console.log(value);
            setSubmitting(true);
           login(value).then(res=>{
            console.log("Success login",res);
            navigate("dashboard")
            localStorage.setItem("access Token")
            

           })
           .catch(err=>{
            errorNotification(err.code,
                err.response.data.message)
           }).final(()=>{
            setSubmitting(false);
           })
        }}>
             {({isValid,isSubmitting}) => (
        <Form>
          <Stack spacing={"10px"}>
          <MyTextInput
            label="username"
            name="username"
            type="email"
            placeholder="Jane@xyz.com"
          />
          <MyTextInput
            label="password"
            name="password"
            type="password"
            placeholder="Your Password"
          />
          
          <Button type="submit" colorScheme='blue' disabled={!isValid || isSubmitting}>
              Login
            </Button>
          </Stack>
            </Form>)}
        </Formik>
    )
}
const Login=()=> {
  const navigate = useNavigate();
  useEffect(()=>{
    if(localStorage.getItem("access Token"))
    navigate("/dashboard");
  })
  return (
    <Stack minH={'100vh'} direction={{ base: 'column', md: 'row' }}>
      <Flex p={8} flex={1} align={'center'} justify={'center'}>
        <Stack spacing={4} w={'full'} maxW={'md'}>
          <Heading fontSize={'2xl'}>Sign in to your account</Heading>
          <LoginFrom/>
        </Stack>
      </Flex>
      <Flex flex={1} p={10}
                flexDirection={"column"}
                alignItems={"center"}
                justifyContent={"center"}
                bgGradient={{sm: 'linear(to-r, blue.600, purple.600)'}}>
        
        <Image
          alt={'Login Image'}
          objectFit={'scale-down'}
          src={
            'https://user-images.githubusercontent.com/40702606/215539167-d7006790-b880-4929-83fb-c43fa74f429e.png'

          }
        />
      </Flex>
    </Stack>
  )
}
export default Login;
