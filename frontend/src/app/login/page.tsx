"use client"

import { login } from "@/src/services/api";
import { useRouter } from "next/navigation";
import React, { useState } from "react";

export default function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const router = useRouter()

  const handleLogin = async (e: React.SubmitEvent) => {
    e.preventDefault()

    try {
      const data = await login(email, password)
      document.cookie = `token=${data.token}; path=/`
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
