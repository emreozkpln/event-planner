export async function getAllEvents(){
    const res = await fetch("http://localhost:8088/api/v1/event")
    return await res.json() 
}

export async function getEventById(id:number){
    const res = await fetch(`http://localhost:8088/api/v1/event/${id}`)
    return await res.json()
}

export async function isRegistered(id:number,token:string){
    const res = await fetch(`http://localhost:8088/api/v1/reservation/isRegistered/${id}`,{
        headers:{
            'Authorization': 'Bearer ' + token
        }
    })
    return res.json()
}