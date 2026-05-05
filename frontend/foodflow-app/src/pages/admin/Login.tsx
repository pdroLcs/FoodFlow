import { useState } from "react"
import { api } from "../../services/api"

export const Login = () => {

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  const handleLogin = async () => {
    const response = await fetch(api.login(), {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({email, password})
    })

    if (response.ok) {
      const data = await response.json();
      localStorage.setItem("token", data.token);
      window.location.href = "/admin";
    }
  }

  return (
    <div>
      <h2>Login</h2>

      <input type="email" id="email" placeholder="seuemail@exemplo.com" onChange={(e) => setEmail(e.target.value)}/>

      <input type="password" id="password" placeholder="sua senha" onChange={(e) => setPassword(e.target.value)}/>

      <button onClick={handleLogin}>Entrar</button>
    </div>
  )
}
