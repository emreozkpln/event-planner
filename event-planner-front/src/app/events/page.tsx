import { getAllEvents } from "@/api/event";
import { AppSidebar } from "@/components/sidebar/app-sidebar";
import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar";
import React from "react";
import EventListPage from "./EventListPage";
import { cookies } from "next/headers";
import { decrypt } from "@/lib/session";

const EventPage = async () => {
	const data = await getAllEvents();
	const token = (await cookies()).get("session")?.value;
	const payload = token ? await decrypt(token) : undefined;

	return (
		<SidebarProvider>
			<AppSidebar user={payload} />
			<div>
				<SidebarTrigger />
				<div>
					<EventListPage event={data.content} />
				</div>
			</div>
		</SidebarProvider>
	);
};

export default EventPage;
