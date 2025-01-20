import React from "react";

const EventDescription = ({ description, location, title }: any) => {
	return (
		<div className="grid grid-cols-[55%_40%] gap-8">
			<div className="flex flex-col gap-5">
				<div className="font-semibold text-2xl">Description</div>
				<div className="text-sm text-[#9C9FA4] font-semibold">{description}</div>
			</div>
			<div className="flex flex-col gap-3">
				<div className="font-semibold text-2xl">Event Location</div>
				<div>
					<iframe className="rounded-3xl w-full h-[220px]" src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=Camikebir,%20Kemal%20Ar%C4%B1kan%20Sk.%20No:2,%20Can%20%20Taksi+(Can%20Taxi)&amp;t=&amp;z=15&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"></iframe>
				</div>
				<h1 className="text-2xl font-semibold">{title}</h1>
				<div className="text-[#9C9FA4] font-semibold">{location}</div>
			</div>
		</div>
	);
};

export default EventDescription;
