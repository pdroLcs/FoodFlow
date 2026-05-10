"use client"

import useProducts from "@/src/hooks/useProducts"

export default function ProductsSection() {

  const {data: products, isLoading: loadingProducts} = useProducts()

  if (loadingProducts) return <p>Carregando produtos...</p>

  return (
    <div>
      {products?.map(product => (
        <div key={product.id}>
          <p>{product.name}</p>
          <p>{product.category.name}</p>
          <p>{product.active ? "Ativo" : "Inativo"}</p>
        </div>
      ))}
    </div>
  )

}
