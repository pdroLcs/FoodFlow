import CategoriesSection from "@/src/components/admin/CategoriesSection";
import ProductsSection from "@/src/components/admin/ProductsSection";
import RestaurantTableSection from "@/src/components/admin/RestaurantTableSection";

export default function AdminClient() {
  return (
    <div>
      <h2>Página Admin</h2>

      <main>
        <CategoriesSection/>

        <ProductsSection/>
        
        <RestaurantTableSection/>
      </main>
    </div>
  )
}
