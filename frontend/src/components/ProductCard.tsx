import React from "react";
import { Product } from "../types/Product";
import { formatPrice } from "../utils/formatPrice";

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
      <p>{formatPrice(product.price)}</p>
      {children}
    </div>
  )
}
