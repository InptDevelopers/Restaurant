import { useState } from "react";
import { Input, Link, Button, RadioGroup, Radio } from "@nextui-org/react";
import { useAuth } from "../../components/RequiredAuth/AuthProvider.jsx";
import { Link as Li } from "react-router-dom";

export default function SignUp() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const { register } = useAuth();

  const signUp = async (e) => {
    e.preventDefault();
    const user = {
      name,
      email,
      password,
      role,
    };
    register(user);
  };
  return (
    <div className="flex justify-center  h-full">
      <form
        action="submit"
        className="w-[500px] mt-20 h-fit flex-col gap-3 flex border border-blue-400 p-10 rounded-2xl"
        onSubmit={signUp}
      >
        <h2 className="font-medium text-2xl">Register</h2>
        <Input
          autoFocus
          label="Name"
          placeholder="Enter your name"
          variant="bordered"
          value={name}
          onValueChange={setName}
        />
        <Input
          autoFocus
          label="Email"
          placeholder="Enter your email"
          type="email"
          variant="bordered"
          value={email}
          onValueChange={setEmail}
        />
        <Input
          label="Password"
          placeholder="Enter your password"
          type="password"
          variant="bordered"
          value={password}
          onValueChange={setPassword}
        />
        <Input
          label="ConfirmPassword"
          placeholder="Enter your password"
          type="password"
          variant="bordered"
          value={confirmPassword}
          onValueChange={setConfirmPassword}
        />
        <RadioGroup
          label="Are you ?"
          orientation="horizontal"
          value={role}
          onValueChange={setRole}
        >
          <Radio value="CLIENT">Client</Radio>
          <Radio value="ADMIN">Admin</Radio>
        </RadioGroup>
        <Button type="submit" color="primary">
          Register
        </Button>
        <Link color="primary" size="sm" className="self-end">
          <Li to="/login">You have an account?</Li>
        </Link>
      </form>
    </div>
  );
}
