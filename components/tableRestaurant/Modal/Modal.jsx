import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import React, { useState } from 'react'
import { PlusIcon } from "../PlusIcon.jsx";

export default function App({setAdded}) {
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [name,setName]=useState("")
  const [type,setType]=useState("")
  const [serie,setSerie]=useState("")
  const [marque,setMarque]=useState("")

  const submitForm=()=>{
    const data={name:name?.target?.value,type:type?.target?.value,serie:serie?.target?.value,marque:marque?.target?.value}
    console.log(data);
    axios.post('http://localhost:3000/nft',data)
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
              <Input type="text" variant={"bordered"} label="Name" onChange={setName}  />
              <Input type="text" variant={"bordered"} label="Type" onChange={setType}  />
              <Input type="text" variant={"bordered"} label="Serie" onChange={setSerie}/>
              <Input type="text" variant={"bordered"} label="Marque" onChange={setMarque} />
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
