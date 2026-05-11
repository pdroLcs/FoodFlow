import useCategories from "../hooks/useCategories"

interface Props {
  selectedCategoryId?: number
  onSelect: (categoryId: number) => void
}

export default function CategoryFilter({ selectedCategoryId, onSelect }: Props) {

  const {data: categories, isLoading: loadingCategories} = useCategories()

  if (loadingCategories) return <p>Carregando categorias...</p>

  return (
    <select id="category" value={selectedCategoryId} onChange={(e) => onSelect(Number(e.target.value))}>
      <option value="">
        Todas as categorias
      </option>

      {categories?.map(category => (
        <option key={category.id} value={category.id}>
          {category.name}
        </option>
      ))}
    </select>
  )
}
