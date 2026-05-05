const BASE_API_URL = import.meta.env.VITE_BASE_API_URL;

export const api = {
  getCategories: () => `${BASE_API_URL}/categorias`,
  getProducts: (categoryId?: number) =>
    categoryId
      ? `${BASE_API_URL}/produtos?categoryId=${categoryId}`
      : `${BASE_API_URL}/produtos`,
  login: () => `${BASE_API_URL}/auth/login`,
}
