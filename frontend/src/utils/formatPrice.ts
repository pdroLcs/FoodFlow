export const formatPrice = (price: number) => {
  return price.toLocaleString("pt-Br", {
    style: "currency",
    currency: "BRL"
  })
}
