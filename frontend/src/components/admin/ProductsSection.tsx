"use client"

import ProductCard from "../ProductCard"
import useProductsAdmin from "@/src/hooks/useProductsAdmin"

export default function ProductsSection() {

  const {data: products, isLoading: loadingProducts} = useProductsAdmin()

  if (loadingProducts) return <p>Carregando produtos...</p>

  return (
    <div>
      <h2>Produtos</h2>

      {products?.map(product => (
        <div key={product.id}>
          <ProductCard product={product}>
            <p>{product.active ? "Ativo" : "Inativo"}</p>
          </ProductCard>
        </div>
      ))}
    </div>
  )

}
