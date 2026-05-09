"use client"

import React, { useState } from "react";

export default function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e: React.SubmitEvent) => {
    e.preventDefault()

    console.log("Login:", {email, password})
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
