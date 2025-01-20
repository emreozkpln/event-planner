import { formatDate } from "@/utils/formatDate";
import React from "react";

const EventTime = ({ startDate, endDate, categories }: any) => {
	return (
		<div className="mx-auto max-w-5xl w-full grid grid-cols-[55%_40%] gap-8">
			<div className="flex flex-col gap-4">
				<div className="font-semibold text-2xl">Hours</div>
				<div className="flex flex-col gap-2">
					<div className="text-[#9DA2A7] font-semibold text-sm">
						Start Date: <span className="font-bold text-base text-black">{formatDate(startDate)}</span>
					</div>
					<div className="text-[#9DA2A7] font-semibold text-sm">
						End Date: <span className="font-bold text-base text-black">{formatDate(endDate)}</span>
					</div>
				</div>
			</div>
			<div>
				<div className="flex flex-col gap-4">
					<div className="font-semibold text-2xl">Tags</div>
					<div className="grid grid-cols-2 gap-3">
						{categories.map((item: any) => (
							<div key={item.id}>
								<div className="bg-[#F0EFF4] text-[#302F34] text-sm py-2 px-4 flex items-center justify-center rounded-lg font-bold">{item.categoryName}</div>
							</div>
						))}
					</div>
				</div>
			</div>
		</div>
	);
};

export default EventTime;
