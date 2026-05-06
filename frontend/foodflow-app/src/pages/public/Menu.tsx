import { ProductCard } from "../../components/ProductCard";
import { useParams } from "react-router-dom";
import { useProducts } from "../../hooks/useProducts";
import { useCategories } from "../../hooks/useCategories";

export const Menu = () => {

  const { data: products, isLoading: loadingProducts } = useProducts()
  const { data: categories, isLoading: loadingCategories } = useCategories()

  const { publicId } = useParams();

  if (loadingCategories || loadingProducts) {
    return <p>Carregando...</p>;
  }

  return (
    <div>
      <h1>Menu</h1>

      {products?.map(product => (
        <ProductCard key={product.id} product={product}/>
      ))}
    </div>
  );
};
