import { cookies } from "next/headers";
import { NextRequest, NextResponse } from "next/server";
import { decrypt } from "./lib/session";

const protectedRoutes = ['/profile','/add-event']
const publicRoutes = ['/login','/register']

export default async function middleware(req: NextRequest) {
    const path = req.nextUrl.pathname
    const isProtectedRoute = protectedRoutes.includes(path);
    const isPublicRoute = publicRoutes.includes(path);

    const token = (await cookies()).get('session')?.value
    const session = await decrypt(token)

    if(isProtectedRoute && !session?.sub){
        return NextResponse.redirect(new URL("/login",req.nextUrl))
    }

    if(isPublicRoute && session?.sub){
        return NextResponse.redirect(new URL("/",req.nextUrl))
    }

    return NextResponse.next()
}