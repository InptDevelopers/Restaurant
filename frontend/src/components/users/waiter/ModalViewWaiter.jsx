import React, { useEffect, useState } from 'react'
import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link, DropdownItem, Tooltip} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import {PlusIcon} from "./PlusIcon.jsx";
import {EyeIcon} from "./EyeIcon.jsx";

import axios from 'axios';

const ModalViewWaiter = ({id}) => {
    const {isOpen, onOpen, onOpenChange} = useDisclosure();
    useEffect(()=>{
      const getWaiter=async()=>{
        const res=await axios.get(`http://localhost:8082/api/v1/waiters/${id}`)
        const {name,email,bankAccount,reservations}=res.data;
        setName(name)
        setEmail(email)
        setBankAccount(bankAccount)
        setReservations(reservations)
      }
      getWaiter()
    },[])
    const [email,setEmail]=useState("");
    const [name,setName]=useState("");
    const [bankAccount,setBankAccount]=useState("")
    const [reservations,setReservations]=useState("")


  return (
    <>
            <span onClick={onOpen} className="text-lg text-default-400 cursor-pointer active:opacity-50">
              <EyeIcon />
            </span>            
            <Modal 
              isOpen={isOpen} 
              onOpenChange={onOpenChange}
              placement="top-center"
            >
              <ModalContent>
                {(onClose) => (
                  <>
                    <form>

                        <ModalHeader className="flex flex-col gap-1">Add Waiter</ModalHeader>
                        <ModalBody>
                            <p className='text-black font-bold'>ID:{id}</p>
                            <Input
                                required
                                autoFocus
                                endContent={
                                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Name"
                                value={name}
                      
                                placeholder="Enter Waiter name"
                                type='text'
                                variant="bordered"
                                disabled
                            />
                            <Input
                                required
                                endContent={
                                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Email"
                                value={email}
                                type='email'
                                placeholder="Enter Waiter email"
                                variant="bordered"
                                disabled
                            />
                            <Input
                                required
                                endContent={
                                <LockIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Bank Account"
                                value={bankAccount}
                                placeholder="Enter Waiter bank account"
                                type="text"
                                variant="bordered"
                                disabled
                            />
                            <div className='flex flex-col gap-3'>
                              <p>Waiters Reservations</p>
                              {
                                reservations?.map((reservation)=>(
                                  <div className='flex justify-between'>
                                    <p className='font-bold text-black underline '>{reservation}</p>
                                  </div>
                                ))
                              }
                            </div>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="danger" variant="flat" onPress={onClose}>
                                Close
                            </Button>
                            {/* <Button color="primary" type='submit' onPress={()=>{handleSubmit}}>
                                Submit
                            </Button> */}
                        </ModalFooter>
                    </form>
                  </>
                )}
              </ModalContent>
            </Modal>
    </>
  )
}

export default ModalViewWaiter