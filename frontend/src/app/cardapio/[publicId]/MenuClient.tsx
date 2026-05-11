"use client"

import CategoryFilter from "@/src/components/CategoryFilter"
import ProductCard from "@/src/components/ProductCard"
import useProducts from "@/src/hooks/useProducts"
import { useState } from "react"

export default function MenuClient() {

  const [selectedCategoryId, setSelectedCategoryId] = useState<number>()

  const {data: products, isLoading: loadingProducts} = useProducts(selectedCategoryId)

  const handleCategoryFilter = (categoryId: number) => setSelectedCategoryId(categoryId)

  if (loadingProducts) return <p>Carregando produtos...</p>

  return (
    <div>
        <h2>Cardápio</h2>

        <h2>Categorias</h2>

        <CategoryFilter onSelect={handleCategoryFilter}/>

        {products?.length === 0 ? <p>Nenhum produto disponível.</p>
          : products?.map(product => (
            <ProductCard key={product.id} product={product}/>
        ))}
    </div>
  )
}
