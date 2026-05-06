import type React from "react";
import { Helmet } from "react-helmet-async";

interface PageTitleProps {
  children: React.ReactNode;
}

export const PageTitle = ({children}: PageTitleProps) => {
  return (
    <Helmet>
      <title>{children}</title>
    </Helmet>
  )
}
