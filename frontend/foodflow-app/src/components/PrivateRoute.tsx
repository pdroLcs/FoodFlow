import type { JSX } from "react";
import { Navigate } from "react-router-dom";
import { isTokenValid } from "../auth/auth";

export const PrivateRout = ({children}: {children: JSX.Element}) => {
  return isTokenValid() ? children : <Navigate to="/login"/>
}
