import { formatDate } from "@/utils/formatDate";
import React from "react";
import { MapPin } from "lucide-react";
import { Star } from "lucide-react";
import Link from "next/link";

const EventListPage = ({ event }: any) => {
	return (
		<div className="grid grid-cols-2 xl:grid-cols-3 gap-7 p-10">
			{event &&
				event.map((item: any) => (
					<Link href={`/events/${item.id}`} key={item.id} className="relative">
						<div>
							<img src="https://assets.unileversolutions.com/v1/985812.jpg" alt="" className="h-[500px] w-96 rounded-xl leading-3" />
						</div>
						<div className="absolute top-5 left-5 grid grid-cols-2 gap-3">
							{item.categories.map((category: any) => (
								<div key={category.id} className="flex items-center text-white bg-[#F6542D] py-1 px-3 rounded-xl justify-center">
									<div className="font-semibold">{category.categoryName}</div>
								</div>
							))}
						</div>
						<div className="absolute top-5 right-5 text-white bg-[#019AFD] rounded-full py-1 px-1 cursor-pointer">
							<Star size={19} />
						</div>
						<div className="absolute bottom-5 left-5 right-5 flex flex-col gap-2 bg-white rounded-lg p-4 shadow-lg">
							<h3 className="text-lg font-semibold text-gray-800">{item.title}</h3>
							<p className="text-sm text-gray-500">By {item.fullname}</p>
							<p className="text-sm font-medium text-gray-600">
								{formatDate(item.startDate)} - {formatDate(item.endDate)}
							</p>

							<div className="mt-2 flex items-center text-sm text-gray-500 truncate ...">
								<MapPin />
								{item.location}
							</div>
						</div>
					</Link>
				))}
		</div>
	);
};

export default EventListPage;
