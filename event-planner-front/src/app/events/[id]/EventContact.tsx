import React from "react";
import { Facebook } from "lucide-react";
import { Twitter } from "lucide-react";
import { Linkedin } from "lucide-react";

const EventContact = ({ title }: any) => {
	return (
		<div className="w-full grid grid-cols-[55%_40%] gap-8 mb-4">
			<div className="flex flex-col gap-5">
				<div className="font-semibold text-2xl">How can I contact the organizer with any question</div>
				<div className="text-sm text-[#9C9FA4] font-semibold">
					Please visit <span className="text-[#7958CB] font-bold leading-7">www.{title}.net</span> and refet to the FAQ section for all questions and contact information.
				</div>
			</div>
			<div className="flex flex-col gap-5">
				<div className="font-semibold text-2xl">Share With Friends</div>
				<div className="flex gap-4">
					<div className="text-white flex items-center justify-center w-12 h-12 rounded-full bg-[#3B5997]">
						<Facebook size={20} />
					</div>
					<div className="text-white flex items-center justify-center w-12 h-12 rounded-full bg-[#55ACEE]">
						<Twitter size={20} />
					</div>
					<div className="text-white flex items-center justify-center w-12 h-12 rounded-full bg-[#2381B1]">
						<Linkedin size={20} />
					</div>
				</div>
			</div>
		</div>
	);
};

export default EventContact;
