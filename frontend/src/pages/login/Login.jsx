import React from "react";
import {Checkbox, Input, Link,Button} from "@nextui-org/react";
import {MailIcon} from './MailIcon.jsx';
import {LockIcon} from './LockIcon.jsx';

export default function Login() {

  return (
      <div className="flex justify-center  h-full">
        <form action="submit" className="w-[500px] mt-20 h-fit flex-col gap-6 flex border border-blue-400 p-10 rounded-2xl">
            <h2 className="font-medium text-2xl">Login</h2>
            <Input
                autoFocus
                endContent={
                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                }
                label="Email"
                placeholder="Enter your email"
                variant="bordered"
            />
            <Input
                endContent={
                <LockIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                }
                label="Password"
                placeholder="Enter your password"
                type="password"
                variant="bordered"
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
            <Button color="primary">Login</Button>
        </form>
      </div>
  );
}
