"use client";
import { Checkbox } from "@/components/ui/checkbox";
import React, { useActionState } from "react";
import { useFormStatus } from "react-dom";
import { FcGoogle } from "react-icons/fc";
import { FaFacebook } from "react-icons/fa";
import { FaApple } from "react-icons/fa";
import Link from "next/link";
import { register } from "./action";

const RegisterForm = () => {
	const [state, loginAction] = useActionState(register, undefined);
	return (
		<div className="grid grid-cols-2">
			<div className="max-h-screen w-full">
				<img src="/login-page-photo.jpg" className="object-cover w-full h-full" />
			</div>
			<div className="flex flex-col items-center justify-center px-44 py-20 gap-10">
				<div className="text-[#D2D1D4] text-sm">
					Have an account?{" "}
					<Link href="/login" className="text-[#8A8EC7]">
						Sign In
					</Link>
				</div>
				<div className="font-bold text-5xl">Welcome Back</div>
				<form action="" className="w-full flex flex-col gap-4">
					<div className="flex flex-col gap-3">
						<div className="flex flex-col gap-1">
							<label htmlFor="firstname" className="font-bold text-[#45454d]">
								Firstname
							</label>
							<input id="firstname" name="firstname" placeholder="Enter Firstname" className="py-3 px-4 border border-gray-200 focus:outline-none focus:border-blue-500 placeholder:font-semibold placeholder:text-gray-300 w-full" />
						</div>
						{state?.errors?.firstname && <p className="text-red-500 font-semibold text-lg">{state.errors.firstname}</p>}
					</div>
					<div className="flex flex-col gap-3">
						<div className="flex flex-col gap-1">
							<label htmlFor="lastname" className="font-bold text-[#45454d]">
								Lastname
							</label>
							<input id="lastname" name="lastname" placeholder="Enter Lastname" className="py-3 px-4 border border-gray-200 focus:outline-none focus:border-blue-500 placeholder:font-semibold placeholder:text-gray-300 w-full" />
						</div>
						{state?.errors?.lastname && <p className="text-red-500 font-semibold text-lg">{state.errors.lastname}</p>}
					</div>
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
					<SubmitButton />
					<div className="flex items-center text-[#E2E1E5] w-full gap-2 mt-4">
						<div className="h-px border border-[#E2E1E5] w-full"></div>
						<div className="text-xs w-[385px]">OR LOGIN WITH EMAIL</div>
						<div className="h-px border border-[#E2E1E5] w-full"></div>
					</div>
					<div className="font-medium text-[#C7C9CF] text-sm mt-4 flex items-center justify-center gap-4">
						<div className="border border-[#E6E9F2] py-3 px-12 hover:bg-gray-100">
							<FcGoogle size={30} />
						</div>
						<div className="border border-[#E6E9F2] py-3 px-12 text-[#0F75FD] hover:bg-gray-100">
							<FaFacebook size={30} />
						</div>
						<div className="border border-[#E6E9F2] py-3 px-12 text-[#241F21] hover:bg-gray-100">
							<FaApple size={30} />
						</div>
					</div>
				</form>
			</div>
		</div>
	);
};

function SubmitButton() {
	const { pending } = useFormStatus();
	return (
		<button disabled={pending} type="submit" className="text-[#A2A5FB] bg-[#5456FE] rounded-lg py-3 w-full text-sm font-semibold">
			Create Account
		</button>
	);
}
export default RegisterForm;
