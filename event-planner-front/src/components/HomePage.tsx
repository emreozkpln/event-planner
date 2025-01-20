import Link from "next/link";
import React from "react";

const HomePage = () => {
	return (
		<div className="relative h-screen w-full">
			<div
				className="absolute inset-0 bg-cover bg-center"
				style={{
					backgroundImage: "url('/homeimage.webp')",
				}}
			>
				<div className="absolute inset-0 bg-black bg-opacity-60"></div>
			</div>
			<div className="relative z-10 flex h-full items-center justify-center">
				<div className="text-center text-[#ffffffe3] flex flex-col items-center gap-5">
					<div className="text-3xl md:text-6xl font-light">EVENT PLANNER</div>
					<div className="h-px w-[100px] border border-red-700"></div>
					<div className="text-lg md:text-2xl max-w-[800px] font-thin">
						We are here to manage all your events on a single platform. We help you plan everything from location selection to guest management.
						<Link href="/events" className="font-semibold underline">
							Lets Play
						</Link>
					</div>
				</div>
			</div>
		</div>
	);
};

export default HomePage;
