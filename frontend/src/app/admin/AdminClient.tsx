import ProductsSection from "@/src/components/admin/ProductsSection";
import RestaurantTableSection from "@/src/components/admin/RestaurantTableSection";

export default function AdminClient() {
  return (
    <div>
      <h2>Página Admin</h2>

      <main>
        <h2>Produtos</h2>
        <ProductsSection/>

        <h2>Mesas</h2>
        <RestaurantTableSection/>
      </main>
    </div>
  )
}
