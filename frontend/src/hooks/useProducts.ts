import { useQuery } from "@tanstack/react-query"
import { getProducts } from "../services/api"
import { Product } from "../types/Product"

const useProducts = () => {

  return useQuery<Product[]>({
    queryKey: ["products"],
    queryFn: getProducts
  })
}

export default useProducts;
