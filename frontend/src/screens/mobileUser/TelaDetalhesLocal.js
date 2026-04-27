import { useNavigation, useRoute } from "@react-navigation/native";
import { useContext, useEffect, useRef, useState } from "react";
import { ScrollView, StyleSheet, Text, View } from "react-native";

import BottomSheetComentarios from "../../components/BottomSheetComentarios";
import CardDetalhes from "../../components/CardDetalhes";
import HeaderDetalhes from "../../components/HeaderDetails";
import NavBar from "../../components/NavBar";

import { ThemeContext } from "../../context/ThemeContext";
import { comentariosMock } from "../../mock/ComentariosMock";

export default function TelaDetalhesLocal() {
  const [comentarios, setComentarios] = useState([]);
  const [localSelecionado, setLocalSelecionado] = useState(null);

  const { colors } = useContext(ThemeContext);

  const navigation = useNavigation();
  const route = useRoute();

  const bottomSheetRef = useRef(null);

  useEffect(() => {
    if (route.params?.lugar) {
      setLocalSelecionado(route.params.lugar);
    }
    setComentarios(comentariosMock);
  }, [route.params]);

  const abrirComentarios = (lugar) => {
    setLocalSelecionado(lugar);
    bottomSheetRef.current?.expand();
  };

  const adicionarComentario = (novo) => {
    setComentarios((prev) => [
      {
        ...novo,
        localId: localSelecionado?.id,
      },
      ...prev,
    ]);
  };

  const comentariosFiltrados = comentarios.filter(
    (c) => c.localId === localSelecionado?.id,
  );

  if (!localSelecionado) {
    return (
      <View style={[styles.container, styles.center]}>
        <Text>Carregando...</Text>
      </View>
    );
  }

  return (
    <View style={[styles.container, { backgroundColor: colors.background }]}>
      <View style={styles.header}>
        <HeaderDetalhes
          title={localSelecionado.nome}
          onBack={() => navigation.goBack()}
        />
      </View>

      <ScrollView contentContainerStyle={styles.scroll}>
        <CardDetalhes
          lugar={localSelecionado}
          onComentarioPress={() => abrirComentarios(localSelecionado)}
        />
      </ScrollView>

      <NavBar />

      <BottomSheetComentarios
        bottomSheetRef={bottomSheetRef}
        comentarios={comentariosFiltrados}
        onEnviarComentario={adicionarComentario}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },

  center: {
    justifyContent: "center",
    alignItems: "center",
  },

  header: {
    backgroundColor: "#fff",
  },

  scroll: {
    paddingBottom: 100,
  },
});
