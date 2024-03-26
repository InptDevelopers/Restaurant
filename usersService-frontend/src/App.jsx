import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import TableClient from "./components/users/client/TableClient"
import './App.css'
import TableWaiter from './components/users/waiter/TableWaiter'
import TableChef from './components/users/chef/TableChef'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className=' h-screen w-screen flex justify-center items-center'>
      <div className=' w-[700px] h-[500px]'>
       {/* <TableClient/> */}
       {/* <TableWaiter/> */}
       <TableChef/>
      </div>
    </div>
  )
}

export default App
