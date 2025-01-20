import { decrypt } from "@/lib/session";
import { cookies } from "next/headers";
import Link from "next/link";
import React from "react";

const Header = async () => {
	const token = (await cookies()).get("session")?.value;
	const payload = token ? await decrypt(token) : undefined;
	return (
		<header className="absolute top-0 left-0 w-full z-20 bg-gradient-to-b from-black to-transparent">
			<div className="container mx-auto flex items-center justify-between max-w-6xl px-6 py-4">
				<Link href="/" className="text-2xl font-bold text-white">
					EVENT<span className="text-red-500">PLANNER</span>
				</Link>
				{token && token ? (
					<Link href="/profile" className="text-white text-lg hover:text-red-500 transition-colors">
						{(payload?.fullname as string) || undefined}
					</Link>
				) : (
					<nav className="flex items-center gap-8 text-white text-lg">
						<Link href="/login" className="hover:text-red-500 transition-colors">
							Sign in
						</Link>
						<Link href="/register" className="hover:text-red-500 transition-colors">
							Sign up
						</Link>
					</nav>
				)}
			</div>
		</header>
	);
};

export default Header;
