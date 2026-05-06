import { ProductCard } from "../../components/ProductCard";
import { useParams } from "react-router-dom";
import { useProducts } from "../../hooks/useProducts";
import { useState } from "react";
import { CategoryDropdown } from "../../components/CategoryDropdown";

export const Menu = () => {

  const [selectedCategory, setSelectedCategory] = useState<number | undefined>()

  const { data: products, isLoading: loadingProducts } = useProducts(false, selectedCategory)

  const { publicId } = useParams();

  const handleSelectCategory = (categoryId: number | undefined) => {
    setSelectedCategory(categoryId)
  }

  if (loadingProducts) {
    return <p>Carregando...</p>;
  }

  return (
    <div>
      <h1>Menu</h1>

      <CategoryDropdown onSelectCategory={handleSelectCategory}/>

      {products?.map(product => (
        <ProductCard key={product.id} product={product}/>
      ))}
    </div>
  );
};
