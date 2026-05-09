import { Product } from "../types/Product";

export default function ProductCard({product}: {product: Product}) {
  return (
    <div>
      <h3>{product.name}</h3>
      <p>{product.description}</p>
    </div>
  )
}
