"use client"

import { useQuery } from "@tanstack/react-query"
import { getRestaurantTables } from "../services/api"

const useRestaurantTables = () => {
  return useQuery({
    queryKey: ["restaurantTables"],
    queryFn: getRestaurantTables
  })
}

export default useRestaurantTables
