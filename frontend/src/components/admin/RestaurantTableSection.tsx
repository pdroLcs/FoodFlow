"use client"

import useRestaurantTables from "@/src/hooks/useRestaurantTables";
import { openQrCode } from "@/src/services/api";

export default function RestaurantTableSection() {

  const {data: restaurantTables, isLoading: loadingRestaurantTables} = useRestaurantTables()

  if (loadingRestaurantTables) return <p>Carregando mesas...</p>

  if (restaurantTables?.length === 0) return <p>Nenhuma mesa no momento.</p>

  return (
    <div>
      <h2>Mesas</h2>

      {restaurantTables?.map(restaurantTable => (
        <div key={restaurantTable.id}>
          <h3>Mesa #{restaurantTable.number}</h3>
          <p>{restaurantTable.occupied ? "Ocupado" : "Livre"}</p>
          <button onClick={() => openQrCode(restaurantTable.publicId)}>QR Code</button>
        </div>
      ))}
    </div>
  )
}
