import React from "react";
import { Product } from "../types/Product";

interface ProductCardProps {
  product: Product,
  children?: React.ReactNode
}

export default function ProductCard({product, children}: ProductCardProps) {
  return (
    <div>
      <h3>{product.name}</h3>
      <p>{product.description}</p>
      <img src={product.imageUrl} alt={product.name} width={200}/>
      {children}
    </div>
  )
}
