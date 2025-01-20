import { cookies } from "next/headers"
import {JWTPayload, jwtVerify} from 'jose'

type LoginUser = {
    email:string,
    password:string
}

type RegisterUser = {
    firstname:string
    lastname:string,
    email:string,
    password:string
}

export async function decrypt(token:string | undefined = ""): Promise<JWTPayload | undefined>{
    const secret = Buffer.from('9V2nFDdmkG7EO09PvcpE8v8l3mMaijgVyS3reguh3j84','base64')
    try{
        const { payload } = await jwtVerify(token,secret,{
            algorithms: ["HS256"]
        })
        return payload
    }catch (err:any) {
        console.error("Invalid token:", err.message);
    }
}

export async function createSession({email,password}:LoginUser){
    const response = await fetch('http://localhost:8088/api/v1/auth/authenticate',{
        method: 'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body: JSON.stringify({email,password})
    })
    const {token} = await response.json()
    
    if(token){
        (await cookies()).set("session",token, {
            httpOnly:true,
            secure:true,
            expires: Date.now() + 15 * 60 * 1000
        })
    }
}

export async function deleteSession(){
    (await cookies()).delete('session')
}

export async function createUser({firstname,lastname,email,password}:RegisterUser){
    const response = await fetch('http://localhost:8088/api/v1/auth/register',{
        method: 'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body: JSON.stringify({firstname,lastname,email,password})
    })
    const data = await response.json()
    return data
}

