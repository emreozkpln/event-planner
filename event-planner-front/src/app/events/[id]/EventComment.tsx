import React from "react";

const EventComment = ({ comments }: any) => {
	return (
		<div className="flex flex-col gap-2">
			<div className="text-xl font-semibold">Comments</div>
			<div className="ml-5 flex flex-col">
				{comments.length != 0 &&
					comments.map((item: any) => (
						<div key={item.id}>
							<div className="flex items-center gap-1">
								<div>-</div>
								<div>{item.fullname}</div>
								<div className="pl-5 font-bold text-yellow-400">{item.raiting}</div>
							</div>
							<div className="">
								Comment: <span className="text-gray-500 text-sm">{item.text}</span>
							</div>
						</div>
					))}
			</div>
		</div>
	);
};

export default EventComment;
