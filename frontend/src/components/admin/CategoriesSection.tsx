"use client"

import useCreateCategory from "@/src/hooks/useCreateCategory"
import React, { useState } from "react"

export default function CategoriesSection() {

  const [name, setName] = useState("")

  const {mutate: createCategory, isPending: pendingCreateCategory} = useCreateCategory()

  const handleSubmit = (e: React.SubmitEvent) => {
    e.preventDefault()

    if (!name.trim()) return

    createCategory(name)
    setName("")
  }

  return (
    <div>
      <h2>Categorias</h2>

      <form onSubmit={handleSubmit}>

        <input id="name" type="text" placeholder="Nome da categoria" value={name} onChange={(e) => setName(e.target.value)}/>

        <button type="submit" disabled={pendingCreateCategory}>
          {pendingCreateCategory ? "Adicionando..." : "Adicionar"}
        </button>

      </form>
    </div>
  )
}
