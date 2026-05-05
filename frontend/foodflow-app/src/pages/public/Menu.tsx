import { useQuery } from "@tanstack/react-query";
import { ProductCard } from "../../components/ProductCard";
import { api } from "../../services/api";
import type { Product } from "../../models/Product";
import { useParams } from "react-router-dom";

export const Menu = () => {
  const { data: products, isLoading: loadingProducts } = useQuery<Product[]>({
    queryKey: ["products"],
    queryFn: async () => {
      const res = await fetch(api.getProducts());
      return res.json();
    },
  });

  const { data: categories, isLoading: loadingCategories } = useQuery({
    queryKey: ["categories"],
    queryFn: async () => {
      const res = await fetch(api.getCategories());
      return res.json();
    },
  });

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
