import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import React, { useState } from 'react'
import { PlusIcon } from "../PlusIcon.jsx";
import axios from "axios";

export default function App({setAdded}) {
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [nom,setNom]=useState("")
  const [description,setDescription]=useState("")
  const [address,setAddress]=useState("")
  // const [marque,setMarque]=useState("")

  const submitForm=()=>{
    const data={nom:nom?.target?.value,description:description?.target?.value,address:address?.target?.value}
    console.log(data);
    axios.post("http://192.168.40.183:8080/api/v1/restaurants",data)
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
              <Input type="text" variant={"bordered"} label="NOM" onChange={setNom}  />
              <Input type="text" variant={"bordered"} label="DESCRIPTION" onChange={setDescription}  />
              <Input type="text" variant={"bordered"} label="ADDRESS" onChange={setAddress}/>
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
