import { createContext, useState } from "react";

export const CurtidosContext = createContext();

export function CurtidosProvider({ children }) {
  const [curtidos, setCurtidos] = useState([]);

  const toggleCurtido = (lugar) => {
    const existe = curtidos.some((item) => item.id === lugar.id);

    if (existe) {
      setCurtidos(curtidos.filter((item) => item.id !== lugar.id));
    } else {
      setCurtidos([...curtidos, lugar]);
    }
  };

  const isCurtido = (id) => {
    return curtidos.some((item) => item.id === id);
  };

  return (
    <CurtidosContext.Provider value={{ curtidos, toggleCurtido, isCurtido }}>
      {children}
    </CurtidosContext.Provider>
  );
}