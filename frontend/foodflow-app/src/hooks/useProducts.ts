import { useQuery } from "@tanstack/react-query";
import type { Product } from "../models/Product";
import { fetchWithAuth } from "../services/fetchWithAuth";
import { api } from "../services/api";

export const useProducts = (isAdmin?: boolean, categoryId?: number) => {
  return useQuery<Product[]>({
    queryKey: ["products", isAdmin, categoryId],
    queryFn: async () => {
      const res = isAdmin ? await fetchWithAuth(api.getProductsAdmin(categoryId)) : await fetch(api.getProducts(categoryId))
      return res.json();
    },
  });
};
