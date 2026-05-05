import type { Product } from "../models/Product"

interface ProductCardProps {
  product: Product;
}

export const ProductCard = ({ product }: ProductCardProps) => {

  return (
    <div>
      <h3>{product.name}</h3>
      <p>{product.category.name}</p>
    </div>
  )
}
