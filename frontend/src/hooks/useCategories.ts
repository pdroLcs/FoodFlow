import { useQuery } from "@tanstack/react-query";
import { getCategories } from "../services/api";

const useCategories = () => {
  return useQuery({
    queryKey: ["categories"],
    queryFn: getCategories,
  });
};

export default useCategories;
