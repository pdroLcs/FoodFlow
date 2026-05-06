import { useCategories } from "../hooks/useCategories"

interface CategoryDropdownProps {
  onSelectCategory: (id: number | undefined) => void
}

export const CategoryDropdown = ({onSelectCategory}: CategoryDropdownProps) => {

  const { data: categories, isLoading: loadingCategories } = useCategories()

  if (loadingCategories) return <p>Carregando...</p>

  return (
    <select onChange={(e) => {
      const value = e.target.value;
      onSelectCategory(value ? Number(value) : undefined);
    }}>
      <option value="">Todas</option>

      {categories?.map(category => (
        <option key={category.id} value={category.id}>
          {category.name}
        </option>
      ))}
    </select>
  )
}
