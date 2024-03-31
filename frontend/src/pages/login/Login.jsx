import React, { useState } from "react";
import { Checkbox, Input, Link, Button } from "@nextui-org/react";
import { MailIcon } from "./MailIcon.jsx";
import { LockIcon } from "./LockIcon.jsx";
import axios from "axios";
import { Link as Li } from "react-router-dom";
import { useAuth } from "../../components/RequiredAuth/AuthProvider.jsx";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useAuth();

  const login1 = async (e) => {
    e.preventDefault();
    login(email, password);
  };
  return (
    <div className="flex justify-center  h-full">
      <form
        action="submit"
        className="w-[500px] mt-20 h-fit flex-col gap-6 flex border border-blue-400 p-10 rounded-2xl"
        onSubmit={login1}
      >
        <h2 className="font-medium text-2xl">Login</h2>
        <Input
          autoFocus
          endContent={
            <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
          }
          label="Email"
          placeholder="Enter your email"
          type="email"
          variant="bordered"
          value={email}
          onValueChange={setEmail}
        />
        <Input
          endContent={
            <LockIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
          }
          label="Password"
          placeholder="Enter your password"
          type="password"
          variant="bordered"
          value={password}
          onValueChange={setPassword}
        />
        <div className="flex py-2 px-1 justify-between">
          <Checkbox
            classNames={{
              label: "text-small",
            }}
          >
            Remember me
          </Checkbox>
          <Link color="primary" href="#" size="sm">
            Forgot password?
          </Link>
        </div>
        <Button type="submit" color="primary">
          Login
        </Button>
        <Link color="primary" size="sm" className="self-end">
          <Li to="/register">You don&apos;t have an account?</Li>
        </Link>
      </form>
    </div>
  );
}
