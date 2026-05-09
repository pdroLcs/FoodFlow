import { Product } from "../types/Product";

const API_URL_BASE = process.env.NEXT_PUBLIC_API_URL;

export const getProducts = async (): Promise<Product[]> => {
  console.log("chamou");
  const res = await fetch(`${API_URL_BASE}/produtos`);
  return res.json();
};
