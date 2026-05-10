"use client"

import { login } from "@/src/services/api";
import { useRouter } from "next/router";
import { useState } from "react";

export default function LoginClient() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const router = useRouter()

  const handleLogin = async (e: React.SubmitEvent) => {
    e.preventDefault()

    try {
      await login(email, password)
      router.push("/admin")
    } catch (err) {
      console.log("Erro:", err);
    }
  }

  return (
    <div>
      <form onSubmit={handleLogin}>
        <h2>Login</h2>

        <input id="email" type="email" placeholder="email@exemplo.com" value={email} onChange={(e) => setEmail(e.target.value)}/>
        <input id="password" type="password" placeholder="suasenha" value={password} onChange={(e) => setPassword(e.target.value)}/>

        <button type="submit">Entrar</button>
      </form>
    </div>
  )

}
