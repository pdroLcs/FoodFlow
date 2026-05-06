import type { Product } from "../models/Product"
import { formatPrice } from "../utils/formatPrice";

interface ProductCardProps {
  product: Product;
}

export const ProductCard = ({ product }: ProductCardProps) => {

  return (
    <div>
      <h3>{product.name}</h3>
      <p>{product.category.name}</p>
      <span>{formatPrice(product.price)}</span>
    </div>
  )
}
