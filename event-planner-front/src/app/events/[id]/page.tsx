import { getEventById, isRegistered } from "@/api/event";
import React from "react";
import EventCard from "./EventCard";
import EventDescription from "./EventDescription";
import EventTime from "./EventTime";
import EventContact from "./EventContact";
import { cookies } from "next/headers";
import EventComment from "./EventComment";

const EventDetailPage = async ({ params }: any) => {
	const searchId = await params;
	const data = await getEventById(searchId?.id);
	const token = (await cookies()).get("session")?.value;
	let bool;
	if (token) {
		bool = await isRegistered(searchId?.id, token);
	}

	return (
		<div className="mt-8 flex flex-col gap-8 max-w-6xl mx-auto w-full">
			<EventCard data={data} eventId={searchId?.id} token={token} bool={bool} />
			<EventDescription description={data.description} location={data.location} title={data.title} />
			<EventTime startDate={data.startDate} endDate={data.endDate} categories={data.categories} />
			<EventContact title={data.title} />
			<EventComment comments={data.comments} />
		</div>
	);
};

export default EventDetailPage;
