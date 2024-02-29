import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import React, { useState } from 'react'
import { PlusIcon } from "../PlusIcon.jsx";
import axios from "axios";

export default function App({setAdded}) {
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [name,setName]=useState("")
  const [email,setEmail]=useState("")
  const [bank,setBankaccount]=useState("")
  // const [marque,setMarque]=useState("")

  const submitForm=()=>{
    const data={name:name?.target?.value,email:email?.target?.value,bank:bank?.target?.value}
    console.log(data);
    axios.post("http://192.168.40.183:8082/api/v1/waiters",data)
    .then(response => {
      console.log('POST request successful');
      setAdded(true)
      console.log('Response:', response.data);
    })
    .catch(error => {
      console.error('Error:', error);
    });
  }

  return (
    <>
      <Button
        onPress={onOpen}
        className="bg-foreground text-background"
        type='submit'
        endContent={<PlusIcon />}
        size="sm"
      >
        Add New
      </Button> 
      <Modal 
        isOpen={isOpen} 
        onOpenChange={onOpenChange}
        placement="center"
      >
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">Add Device Form</ModalHeader>
              <ModalBody>
              <Input type="text" variant={"bordered"} label="NAME" onChange={setName}  />
              <Input type="text" variant={"bordered"} label="EMAIL" onChange={setEmail}  />
              <Input type="text" variant={"bordered"} label="BANK ACCOUNT" onChange={setBankaccount}/>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="flat" onPress={onClose}>
                  Close
                </Button>
                <Button className='px-10 py-4 text-gray-800' type='submit'onPress={submitForm}> Submit</Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
}
