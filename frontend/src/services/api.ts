import { Category } from "../types/Category";
import { Product } from "../types/Product";
import { RestaurantTable } from "../types/Table";

const API_URL_BASE = process.env.NEXT_PUBLIC_API_URL;

export const getProducts = async (): Promise<Product[]> => {
  const res = await fetch(`${API_URL_BASE}/produtos`, {
    credentials: "include"
  });

  return res.json();
};

export const getProductsAdmin = async (): Promise<Product[]> => {
  const res = await fetch(`${API_URL_BASE}/produtos/admin`, {
    credentials: "include"
  });

  return res.json();
}

export const getCategories = async (): Promise<Category[]> => {
  const res = await fetch(`${API_URL_BASE}/categorias`, {
    credentials: "include"
  })
  return res.json()
}

export const getRestaurantTables = async (): Promise<RestaurantTable[]> => {
  const res = await fetch(`${API_URL_BASE}/mesas`, {
    credentials: "include"
  })

  if (!res.ok) throw new Error("Não autorizado")

  return res.json()
}

export const login = async (email: string, password: string) => {
  const res = await fetch(`${API_URL_BASE}/auth/login`, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({email, password})
  })

  if (!res.ok) throw new Error("Login inválido")
}

export const openQrCode = (publicId: string) => {
  window.open(`${API_URL_BASE}/mesas/public/${publicId}/qrcode`, "_blank")
}
