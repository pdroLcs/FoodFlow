import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Menu } from "./pages/public/Menu";

const queryClient = new QueryClient();

export function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Routes>
          <Route path="/cardapio/:publicId" element={<Menu/>}/>
        </Routes>
      </BrowserRouter>
    </QueryClientProvider>
  )
}
