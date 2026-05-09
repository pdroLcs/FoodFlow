"use client"

import ProductCard from "@/src/components/ProductCard";
import useProducts from "@/src/hooks/useProducts"

export default function Cardapio() {

  const {data: products, isLoading: loadingProducts} = useProducts()

  if (loadingProducts) return <p>Carregando...</p>

  return (
    <div>
        <h2>Cardápio</h2>

        {products?.map(product => (
          <ProductCard key={product.id} product={product}/>
        ))}
    </div>
  )
}
