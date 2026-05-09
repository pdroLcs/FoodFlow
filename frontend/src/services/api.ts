import { Category } from "../types/Category";
import { Product } from "../types/Product";

const API_URL_BASE = process.env.NEXT_PUBLIC_API_URL;

export const getProducts = async (): Promise<Product[]> => {
  const res = await fetch(`${API_URL_BASE}/produtos`);
  return res.json();
};

export const getCategories = async (): Promise<Category[]> => {
  const res = await fetch(`${API_URL_BASE}/categorias`)
  return res.json()
}

export const login = async (email: string, password: string) => {
  const res = await fetch(`${API_URL_BASE}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({email, password})
  })

  if (!res.ok) throw new Error("Login inválido")

  return res.json()
}
