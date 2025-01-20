export function formatDate(dateArray: [number, number, number, number, number]): string {
    const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  
    const year = dateArray[0];
    const month = months[dateArray[1] - 1];
    const day = dateArray[2];
  
    return `${day} ${month} ${year}`;
  }