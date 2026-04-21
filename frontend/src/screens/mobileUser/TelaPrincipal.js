import { useEffect, useState } from "react";
import { ScrollView, StyleSheet, View } from "react-native";

import Header from "../../components/header";
import SearchBar from "../../components/searchBar";
import Card from "../../components/card";
import NavBar from "../../components/navBar";

import { locaisMock } from "../../mock/LocaisMock";
// para integracao com o backend
// import { buscarLugares } from "../../services/api";

export default function TelaPrincipal() {
  const [busca, setBusca] = useState("");
  const [lugares, setLugares] = useState([]);

  useEffect(() => {
    carregar();
  }, []);

  const carregar = async () => {
    setLugares(locaisMock);

    /*
    try {
      const dados = await buscarLugares();
      setLugares(dados);
    } catch (error) {
      console.log("Erro ao buscar lugares:", error);
    }
    */
  };

  const filtrados = lugares.filter((l) =>
    l.nome.toLowerCase().includes(busca.toLowerCase())
  );

  const limparBusca = () => {
    setBusca("");
  };

  return (
    <View style={styles.container}>
      
      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.scrollContent}
      >
        <Header />

        <SearchBar
          value={busca}
          onChangeText={setBusca}
          onClear={limparBusca}
        />

        {filtrados.map((item) => (
          <Card key={item.id} lugar={item} />
        ))}
      </ScrollView>

      <NavBar />

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },

  scrollContent: {
    paddingBottom: 100, 
  },
});