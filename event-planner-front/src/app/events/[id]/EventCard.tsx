import React from "react";
import { formatDate } from "@/utils/formatDate";
import { MapPin } from "lucide-react";
import Link from "next/link";

const EventCard = ({ data }: any) => {
	return (
		<div className="min-h-[400px] bg-gray-900 flex items-center justify-center py-4 px-8 rounded-lg">
			<div className="flex justify-between gap-9 md:flex-row w-full bg-gray-900 text-white rounded-lg p-4 md:p-8 space-y-4 md:space-y-0">
				<div className="flex flex-col gap-6 items-start">
					<Link href="/events" className="text-[#E8F5FB] font-semibold text-lg">
						&larr; Back
					</Link>
					<h1 className="text-4xl font-semibold">{data.title}</h1>
					<p className="text-[#C0CCE4]">By {data.fullname}</p>
					<p className="">{data.location}</p>
					<button className=" text-[#D2E4F6] text-lg flex gap-2 items-center justify-center">
						<MapPin /> View Map
					</button>
				</div>
				<div className="w-full md:w-1/3 bg-white text-gray-800 rounded-lg p-6 shadow-lg">
					<h2 className="text-lg font-bold">Date & Time</h2>
					<p className="text-sm text-[#A4ABB3] font-semibold mt-2">{formatDate(data.startDate)}</p>
					<button className="text-sm text-[#815EE1] mt-4 font-bold tracking-wider">+ Add to Calendar</button>
					<div className="mt-6">
						<button className="w-full bg-purple-600 text-white py-4 rounded-lg font-semibold hover:bg-purple-700">Apply Now (Free)</button>
					</div>
					<p className="text-sm text-gray-500 text-center font-semibold mt-4">No Refunds</p>
				</div>
			</div>
		</div>
	);
};

export default EventCard;
