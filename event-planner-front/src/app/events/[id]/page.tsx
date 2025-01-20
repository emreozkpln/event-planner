import { getEventById } from "@/api/event";
import React from "react";
import EventCard from "./EventCard";
import EventDescription from "./EventDescription";
import EventTime from "./EventTime";
import EventContact from "./EventContact";

const EventDetailPage = async ({ params }: any) => {
	const searchId = await params;
	const data = await getEventById(searchId?.id);

	return (
		<div className="mt-8 flex flex-col gap-8 max-w-6xl mx-auto w-full">
			<EventCard data={data} />
			<EventDescription description={data.description} location={data.location} title={data.title} />
			<EventTime startDate={data.startDate} endDate={data.endDate} categories={data.categories} />
			<EventContact title={data.title} />
		</div>
	);
};

export default EventDetailPage;
