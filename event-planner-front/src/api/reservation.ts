export async function createReservation(eventId:number,token:string){
    const response = await fetch(`http://localhost:8088/api/v1/reservation/create/${eventId}`,{
        method: 'POST',
        headers: {
            'Content-Type':'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
    return await response.json()
}