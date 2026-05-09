import { Category } from "./Category"

export type Product = {
  id: number,
  name: string,
  description: string,
  price: number,
  imageUrl: string,
  active: boolean,
  category: Category
}
