"use client"

import ProductCard from "@/src/components/ProductCard"
import useCategories from "@/src/hooks/useCategories"
import useProducts from "@/src/hooks/useProducts"

export default function MenuClient() {

  const {data: products, isLoading: loadingProducts} = useProducts()
  const {data: categories, isLoading: loadingCategories} = useCategories()

  if (loadingProducts || loadingCategories) return <p>Carregando...</p>

  if (products?.length === 0) return <p>Nenhum produto disponível.</p>

  return (
    <div>
        <h2>Cardápio</h2>

        {products?.map(product => (
          <ProductCard key={product.id} product={product}/>
        ))}

        <h2>Categorias</h2>

        {categories?.map(category => (
          <p key={category.id}>{category.name}</p>
        ))}
    </div>
  )
}
