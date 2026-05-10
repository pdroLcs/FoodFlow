import { Metadata } from "next";
import MenuClient from "./MenuClient";

export const metadata: Metadata = {
  title: "Cardápio",
}

export default function Menu() {
  return (
    <MenuClient/>
  )
}
