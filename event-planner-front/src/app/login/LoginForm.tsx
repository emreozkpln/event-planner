"use client";
import { Checkbox } from "@/components/ui/checkbox";
import Link from "next/link";
import React, { useActionState } from "react";
import { useFormStatus } from "react-dom";
import { FcGoogle } from "react-icons/fc";
import { login } from "./action";
import { FaArrowLeftLong } from "react-icons/fa6";

const LoginForm = () => {
	const [state, loginAction] = useActionState(login, undefined);
	return (
		<div className="grid grid-cols-2">
			<div className="flex flex-col items-center justify-center px-44 py-20 gap-6">
				<Link href="/events" className="flex gap-1.5 items-center text-[#D2D1D4]">
					<FaArrowLeftLong />
					<div>Back</div>
				</Link>
				<div className="font-bold text-5xl">Welcome Back</div>
				<button className="w-80 py-2 border border-gray-200 shadow-md flex items-center justify-center gap-4">
					<FcGoogle />
					<div className="text-[#97979F] text-sm">Log in with Google</div>
				</button>
				<div className="flex items-center text-[#E2E1E5] w-full gap-2">
					<div className="h-px border border-[#E2E1E5] w-full"></div>
					<div className="text-xs w-[385px]">OR LOGIN WITH EMAIL</div>
					<div className="h-px border border-[#E2E1E5] w-full"></div>
				</div>
				<form action={loginAction} className="w-full flex flex-col gap-4">
					<div className="flex flex-col gap-3">
						<div className="flex flex-col gap-1">
							<label htmlFor="email" className="font-bold text-[#45454d]">
								Email Address
							</label>
							<input id="email" name="email" placeholder="Enter Email" className="py-3 px-4 border border-gray-200 focus:outline-none focus:border-blue-500 placeholder:font-semibold placeholder:text-gray-300 w-full" />
						</div>
						{state?.errors?.email && <p className="text-red-500 font-semibold text-lg">{state.errors.email}</p>}
					</div>
					<div className="flex flex-col gap-3">
						<div className="flex flex-col gap-1">
							<label htmlFor="password" className="font-bold text-[#45454d]">
								Password
							</label>
							<input id="password" name="password" placeholder="Enter Password" className="py-3 px-4 border border-gray-200 focus:outline-none focus:border-blue-500 placeholder:font-semibold placeholder:text-gray-300 w-full" />
						</div>
						{state?.errors?.password && <p className="text-red-500 font-semibold text-lg">{state.errors.password}</p>}
					</div>
					<div className="flex items-center justify-between">
						<div className="flex items-center gap-2">
							<Checkbox id="terms" />
							<label htmlFor="terms" className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
								Keep me logged in
							</label>
						</div>
						<div className="text-sm font-semibold text-[#DDBAD5]">Forgot your password?</div>
					</div>
					<SubmitButton />
					<div className="font-medium text-[#C7C9CF] text-sm mt-6 flex items-center justify-center gap-1">
						Don't have an account?{" "}
						<Link href="/register" className="text-sm font-semibold text-[#DDBAD5]">
							Sign up
						</Link>
					</div>
				</form>
			</div>
			<div className="max-h-screen w-full">
				<img src="/login-page-photo.jpg" className="object-cover w-full h-full" />
			</div>
		</div>
	);
};

function SubmitButton() {
	const { pending } = useFormStatus();
	return (
		<button disabled={pending} type="submit" className="text-[#A2A5FB] bg-[#5456FE] rounded-lg py-3 w-full text-sm font-semibold">
			Sign in
		</button>
	);
}

export default LoginForm;
