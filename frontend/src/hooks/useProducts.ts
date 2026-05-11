import { useQuery } from "@tanstack/react-query"
import { getProducts } from "../services/api"
import { Product } from "../types/Product"

const useProducts = (categoryId?: number) => {

  return useQuery<Product[]>({
    queryKey: ["products", categoryId],
    queryFn: () => getProducts(categoryId)
  })
}

export default useProducts;
