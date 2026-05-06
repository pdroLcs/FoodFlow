import { useCategories } from "../../hooks/useCategories"
import { useProducts } from "../../hooks/useProducts"
import { PageTitle } from "../../components/PageTitle"

export const Admin = () => {

  const { data: products, isLoading: loadingProducts } = useProducts(true)
  const { data: categories, isLoading: loadingCategories } = useCategories()

  if (loadingCategories || loadingProducts) {
    return <p>Carregando...</p>;
  }

  return (
    <div>
      <PageTitle>Página Admin</PageTitle>

      <h2>Painel Admin</h2>

      <h3>Produtos</h3>
      {products?.map(product => (
        product.name
      ))}

      <h3>Categorias</h3>
      {categories?.map(category => (
        category.name
      ))}
    </div>
  )
}
