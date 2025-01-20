"use server";

import { createSession, deleteSession } from "@/lib/session";
import { redirect } from "next/navigation";
import { z } from "zod";

const loginSchema = z.object({
	email: z.string().email({ message: "Invalid email address." }).trim(),
	password: z.string().min(6, { message: "Password must be at least 6 characters long." }).max(100, { message: "Password must be less than 100 characters." }).trim(),
});

export async function login(prevState: any, formData: FormData) {
	const result = loginSchema.safeParse(Object.fromEntries(formData));
	if (!result.success) {
		return {
			errors: result.error.flatten().fieldErrors,
		};
	}

	const { email, password } = result.data;
	await createSession({email, password});
	redirect("/events");
}

export async function logout() {
	await deleteSession();
	redirect("/login");
}
