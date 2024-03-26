import React, { useEffect, useState } from 'react'
import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import {PlusIcon} from "./PlusIcon.jsx";
import {EditIcon} from './EditIcon.jsx';


import axios from 'axios';

const ModalEditWaiter = ({id,mutate}) => {
  const {isOpen, onOpen, onOpenChange} = useDisclosure();
  const [changed,setChanged]=useState(false)
    useEffect(()=>{
      const getWaiter=async()=>{
        const res=await axios.get(`http://localhost:8082/api/v1/waiters/${id}`)
        const {name,email,bankAccount,reservations}=res.data;
        console.log(res.data);
        setName(name)
        setEmail(email)
        setBankAccount(bankAccount)
        setReservations(reservations)
      }
      getWaiter()
      setChanged(false)
    },[changed])
    const [email,setEmail]=useState("");
    const [name,setName]=useState("");
    const [bankAccount,setBankAccount]=useState("")
    const [reservations,setReservations]=useState("")


    const handleUpdate=async()=>{
        const data={
          name:name,email:email,bankAccount:bankAccount,reservations:reservations
        }
        console.log(data);
        if(email&&name&&bankAccount){
            const res=await axios.put(`http://localhost:8082/api/v1/waiters/${id}`,data)
            console.log(res);
            if(res.status==200){
                mutate()
            }else{
                alert(res.data)
            }
        }
    }
    const handleDeleteReservation=async(reservation)=>{
      console.log(reservation);
      const res=await axios.delete(`http://localhost:8082/api/v1/waiters/${id}/reservations/${reservation}`)
      console.log(res);
      if(res.status==204){
          mutate()
          setChanged(true)
      }else{
          alert(res.data)
      }
    }
  return (
    <>
            <span onClick={onOpen} className="text-lg text-default-400 cursor-pointer active:opacity-50">
              <EditIcon />
            </span>
            <Modal 
              isOpen={isOpen} 
              onOpenChange={onOpenChange}
              placement="top-center"
            >
              <ModalContent>
                {(onClose) => (
                  <>
                    <form onSubmit={handleUpdate}>

                        <ModalHeader className="flex flex-col gap-1">Edit Waiter</ModalHeader>
                        <ModalBody>
                            <p className='text-black font-bold'>ID:{id}</p>
                            <Input
                                required
                                autoFocus
                                endContent={
                                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                }
                                label="Name"
                                placeholder="Enter Waiter name"
                                type='text'
                                variant="bordered"
                                value={name}
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
                                onValueChange={setBankAccount}
                                variant="bordered"
                                
                            />
                            <div className='flex flex-col gap-3'>
                              <p>Waiters Reservations</p>
                              {
                                reservations?.map((reservation)=>(
                                  <div className='flex justify-between'>
                                    <p className='font-bold text-black underline '>{reservation}</p>
                                    <Button color='danger' onPress={()=>handleDeleteReservation(reservation)}>Delete</Button>
                                  </div>
                                ))
                              }
                            </div>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="danger" variant="flat" onPress={onClose}>
                                Close
                            </Button>
                            <Button color="primary" type='submit' onPress={handleUpdate}>
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

export default ModalEditWaiter