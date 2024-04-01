import React, { useState } from 'react'
import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';
import {DeleteIcon} from "./DeleteIcon";
import {PlusIcon} from "./PlusIcon.jsx";
import axios from 'axios';
import instance from "../../../../axios.js";


const ModalDeleteClient = ({name,id,mutate}) => {
    const {isOpen, onOpen, onOpenChange} = useDisclosure();
    
  return (
    <>
            <span onClick={onOpen} className="text-lg text-danger cursor-pointer active:opacity-50">
              <DeleteIcon />
            </span>
            
            <Modal 
              isOpen={isOpen} 
              onOpenChange={onOpenChange}
              placement="top-center"
            >
              <ModalContent>
                {(onClose) => (
                  <>
                    <form >

                        <ModalHeader className="flex flex-col gap-1">Add Client</ModalHeader>
                        <ModalBody>
                            <p>Are You sure you want to Delete client : {name}</p>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="danger" variant="flat" onPress={onClose}>
                                Cancel
                            </Button>
                            <Button color="primary" type='submit' onPress={async()=>{
                                await instance.delete(`/USERS-SERVICE/api/v1/clients/${id}`)
                                mutate()
                                onClose()
                            }}>
                                Confirm
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

export default ModalDeleteClient