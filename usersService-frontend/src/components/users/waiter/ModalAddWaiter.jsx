import React, { useState } from 'react'
import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import {PlusIcon} from "./PlusIcon.jsx";
import axios from 'axios';

const ModalWaiter = ({mutate}) => {
    const {isOpen, onOpen, onOpenChange} = useDisclosure();
    const [email,setEmail]=useState("");
    const [name,setName]=useState("");
    const [bankAccount,setBankAccount]=useState("")
    const handleSubmit=async()=>{
        const data={
            name:name,email:email,bankAccount:bankAccount
        }
        console.log(data);
        if(email&&name&&bankAccount){
            const res=await axios.post("http://localhost:8082/api/v1/waiters",data)
            console.log(res);
            if(res.status==201){
                setEmail("")
                setName("")
                setBankAccount("")
                mutate()
            }else{
                alert(res.data)
            }
        }
    }
  return (
    <>
     <Button onPress={onOpen} color="primary" endContent={<PlusIcon />}>
              Add New
            </Button>
            <Modal 
              isOpen={isOpen} 
              onOpenChange={onOpenChange}
              placement="top-center"
            >
              <ModalContent>
                {(onClose) => (
                  <>
                    <form onSubmit={handleSubmit}>

                        <ModalHeader className="flex flex-col gap-1">Add Waiter</ModalHeader>
                        <ModalBody>
                            <Input
                                required
                                autoFocus
                                endContent={
                                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Name"
                                placeholder="Enter Waiter name"
                                value={name}
                                type='text'
                                variant="bordered"
                                onValueChange={setName}
                            />
                            <Input
                                required
                                endContent={
                                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Email"
                                type='email'
                                placeholder="Enter Waiter email"
                                variant="bordered"
                                value={email}
                                onValueChange={setEmail}
                            />
                            <Input
                                required
                                endContent={
                                <LockIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Bank Account"
                                placeholder="Enter Waiter bank account"
                                type="text"
                                value={bankAccount}
                                variant="bordered"
                                onValueChange={setBankAccount}
                            />
                        </ModalBody>
                        <ModalFooter>
                            <Button color="danger" variant="flat" onPress={onClose}>
                                Close
                            </Button>
                            <Button color="primary" type='submit' onPress={handleSubmit}>
                                Submit
                            </Button>
                        </ModalFooter>
                    </form>
                  </>
                )}
              </ModalContent>
            </Modal>
    </>
  )
}

export default ModalWaiter