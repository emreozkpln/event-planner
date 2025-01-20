"use server"
import { createUser } from '@/lib/session';
import { redirect } from 'next/navigation';
import {z} from 'zod'

const registerSchema = z.object({
	firstname: z.string().min(2, { message: "Firstname must be at least 2 characters long." }).max(50, { message: "Firstname must be less than 50 characters." }).trim(),
	lastname: z.string().min(2, { message: "Lastname must be at least 2 characters long." }).max(50, { message: "Lastname must be less than 50 characters." }).trim(),
	email: z.string().email({ message: "Invalid email address." }).trim(),
	password: z.string().min(6, { message: "Password must be at least 6 characters long." }).max(100, { message: "Password must be less than 100 characters." }).trim(),
});

export async function register(prevState:any,formData:FormData){
    const result = registerSchema.safeParse(Object.fromEntries(formData))
    if(!result.success){
        return {
            errors: result.error.flatten().fieldErrors
        }
    }
    const {firstname,lastname,email,password} = result.data
    await createUser({firstname,lastname,email,password})
    redirect("/login")
}