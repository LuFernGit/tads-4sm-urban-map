import { useNavigation } from "@react-navigation/native";
import { useContext, useEffect, useRef, useState } from "react";
import {
  ScrollView,
  StyleSheet,
  View,
  TouchableOpacity,
  Animated,
} from "react-native";

import Card from "../../components/Card";
import Header from "../../components/Header";
import NavBar from "../../components/NavBar";
import ScrollToTopButton from "../../components/ScrollToTopButton";
import SearchBar from "../../components/SearchBar";
import TelaFiltro from "../mobileUser/TelaFiltro";

import BottomSheetComentarios from "../../components/BottomSheetComentarios";

import { ThemeContext } from "../../context/ThemeContext";
import { comentariosMock } from "../../mock/ComentariosMock";
import { locaisMock } from "../../mock/LocaisMock";

export default function TelaPrincipal() {
  const [busca, setBusca] = useState("");
  const [lugares, setLugares] = useState([]);
  const [comentarios, setComentarios] = useState([]);
  const [localSelecionado, setLocalSelecionado] = useState(null);
  const [showButton, setShowButton] = useState(false);
  const [filtroVisivel, setFiltroVisivel] = useState(false);

  const { colors } = useContext(ThemeContext);
  const navigation = useNavigation();

  const scrollRef = useRef(null);
  const bottomSheetRef = useRef(null);
  const slideAnim = useRef(new Animated.Value(320)).current;

  useEffect(() => {
    setLugares(locaisMock);
    setComentarios(comentariosMock);
  }, []);

  const handleScroll = (event) => {
    const y = event.nativeEvent.contentOffset.y;
    setShowButton(y > 200);
  };

  const scrollToTop = () => {
    scrollRef.current?.scrollTo({ y: 0, animated: true });
  };

  const abrirComentarios = (lugar) => {
    setLocalSelecionado(lugar);
    bottomSheetRef.current?.expand();
  };

  const adicionarComentario = (novo) => {
    setComentarios((prev) => [
      { ...novo, localId: localSelecionado?.id },
      ...prev,
    ]);
  };

  const comentariosFiltrados = comentarios.filter(
    (c) => c.localId === localSelecionado?.id,
  );

  const filtrados = lugares.filter((l) =>
    l.nome.toLowerCase().includes(busca.toLowerCase()),
  );

  const abrirFiltro = () => {
    setFiltroVisivel(true);

    Animated.timing(slideAnim, {
      toValue: 0,
      duration: 250,
      useNativeDriver: true,
    }).start();
  };

  const fecharFiltro = () => {
    Animated.timing(slideAnim, {
      toValue: 320,
      duration: 200,
      useNativeDriver: true,
    }).start(() => {
      setFiltroVisivel(false);
    });
  };

  return (
    <View style={[styles.container, { backgroundColor: colors.background }]}>
      <ScrollView
        ref={scrollRef}
        onScroll={handleScroll}
        scrollEventThrottle={16}
        contentContainerStyle={styles.scroll}
      >
        <Header />

        <SearchBar
          value={busca}
          onChangeText={setBusca}
          onFilterPress={abrirFiltro}
        />

        {filtrados.map((item) => (
          <Card
            key={item.id}
            lugar={item}
            onComentarioPress={() => abrirComentarios(item)}
            onPress={() =>
              navigation.navigate("DetalhesLocal", { lugar: item })
            }
          />
        ))}
      </ScrollView>

      <ScrollToTopButton visible={showButton} onPress={scrollToTop} />
      <NavBar />

      <BottomSheetComentarios
        bottomSheetRef={bottomSheetRef}
        comentarios={comentariosFiltrados}
        onEnviarComentario={adicionarComentario}
      />

      {filtroVisivel && (
        <View style={styles.overlay}>
          {/* fundo escuro */}
          <TouchableOpacity
            style={styles.backdrop}
            activeOpacity={1}
            onPress={() => setFiltroVisivel(false)}
          />

          {/* painel lateral */}
          <View style={styles.panel}>
            <TelaFiltro
              onAplicar={(filtros) => {
                console.log(filtros);
                setFiltroVisivel(false);
              }}
              onFechar={() => setFiltroVisivel(false)}
            />
          </View>
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1 },

  scroll: {
    paddingBottom: 100,
  },

  overlay: {
    ...StyleSheet.absoluteFillObject,
    flexDirection: "row",
    zIndex: 999,
  },

  backdrop: {
    flex: 1,
    backgroundColor: "rgba(0,0,0,0.5)",
  },

  panel: {
    width: 320,
    height: "100%",
    backgroundColor: "#fff",
  },
});
