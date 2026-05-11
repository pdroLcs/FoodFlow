"use client"

import ProductCard from "@/src/components/ProductCard"
import useProducts from "@/src/hooks/useProducts"

export default function MenuClient() {

  const {data: products, isLoading: loadingProducts} = useProducts()

  if (loadingProducts) return <p>Carregando produtos...</p>

  if (products?.length === 0) return <p>Nenhum produto disponível.</p>

  return (
    <div>
        <h2>Cardápio</h2>

        {products?.map(product => (
          <ProductCard key={product.id} product={product}/>
        ))}
    </div>
  )
}
