import { useQuery } from "@tanstack/react-query"
import { getProductsAdmin } from "../services/api"

const useProductsAdmin = () => {
  return useQuery({
    queryKey: ["products"],
    queryFn: getProductsAdmin
  })
}

export default useProductsAdmin
