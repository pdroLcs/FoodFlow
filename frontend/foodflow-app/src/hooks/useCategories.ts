import { useQuery } from "@tanstack/react-query";
import type { Category } from "../models/Category";
import { api } from "../services/api";

export const useCategories = () => {
  return useQuery<Category[]>({
    queryKey: ["categorias"],
    queryFn: async () => {
      const res = await fetch(api.getCategories());
      return res.json();
    },
  });
};
