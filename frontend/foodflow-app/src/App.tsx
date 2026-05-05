import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Menu } from "./pages/public/Menu";
import { Login } from "./pages/admin/Login";
import { PrivateRout } from "./components/PrivateRoute";
import { Admin } from "./pages/admin/Admin";

const queryClient = new QueryClient();

export function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login/>}/>

          <Route path="/cardapio/:publicId" element={<Menu/>}/>

          <Route path="/admin" element={
            <PrivateRout>
              <Admin/>
            </PrivateRout>
          }/>
        </Routes>
      </BrowserRouter>
    </QueryClientProvider>
  )
}
