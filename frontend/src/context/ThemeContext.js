import { createContext, useState } from "react";

export const ThemeContext = createContext();

export function ThemeProvider({ children }) {
  const [modoEscuro, setModoEscuro] = useState(false);
  const [tamanhoTexto, setTamanhoTexto] = useState(1);

  const colors = {
    background: modoEscuro ? "#121212" : "#fff",
    text: modoEscuro ? "#fff" : "#000",
    card: modoEscuro ? "#1e1e1e" : "#ffffff",
    border: modoEscuro ? "#333" : "#e6e6e6",
    placeholder: modoEscuro ? "#aaa" : "#888",
    input: modoEscuro ? "#1a1a1a" : "#f0f1f2",
    accent: modoEscuro ? "#4DA3FF" : "#38B6FF",
    primary: modoEscuro ? "#444" : "#000",
    secondary: modoEscuro ? "#666" : "#AFAFB0",
    buttonText: modoEscuro ? "#fff" : "#fff",
    navbar: modoEscuro ? "#000000" : "#fff"
  };

  return (
    <ThemeContext.Provider
      value={{
        modoEscuro,
        setModoEscuro,
        tamanhoTexto,
        setTamanhoTexto,
        colors,
      }}
    >
      {children}
    </ThemeContext.Provider>
  );
}
