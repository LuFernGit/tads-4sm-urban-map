import { useState, useContext } from "react";
import {
  Image,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Linking,
  Dimensions,
  ScrollView,
} from "react-native";

import { SalvosContext } from "../context/SalvosContext";
import { ThemeContext } from "../context/ThemeContext";
import { CurtidosContext } from "../context/CurtidosContext";

const { width } = Dimensions.get("window");

export default function CardDetalhes({ lugar, onComentarioPress }) {
  const [indexAtivo, setIndexAtivo] = useState(0);

  const { salvos, toggleSalvo } = useContext(SalvosContext);
  const { colors, modoEscuro } = useContext(ThemeContext);

  const { toggleCurtido, isCurtido } = useContext(CurtidosContext);

  const curtido = isCurtido(lugar.id);
  const salvo = salvos.some((item) => item.id === lugar.id);

  const abrirMapa = (nome) => {
    const url = `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(
      nome
    )}`;
    Linking.openURL(url);
  };

  return (
    <View style={[styles.card, { backgroundColor: colors.background }]}>
      <ScrollView
        horizontal
        pagingEnabled
        snapToInterval={width}
        decelerationRate="fast"
        showsHorizontalScrollIndicator={false}
        onMomentumScrollEnd={(event) => {
          const slide = Math.round(
            event.nativeEvent.contentOffset.x / width
          );
          setIndexAtivo(slide);
        }}
      >
        {lugar.fotosUrl?.map((img, index) => (
          <View key={index} style={{ width }}>
            <Image source={img} style={styles.mainImage} />
          </View>
        ))}
      </ScrollView>

      {lugar.fotosUrl?.length > 1 && (
        <View style={styles.dotsContainer}>
          {lugar.fotosUrl.map((_, i) => (
            <View
              key={i}
              style={[
                styles.dot,
                {
                  backgroundColor:
                    indexAtivo === i ? colors.text : "#999",
                },
              ]}
            />
          ))}
        </View>
      )}

      <View style={styles.actionsRow}>
        <View style={styles.leftActions}>
          <TouchableOpacity onPress={() => toggleCurtido(lugar)}>
            <Image
              source={
                curtido
                  ? modoEscuro
                    ? require("../assets/BotaoLikeFilled-dark.png")
                    : require("../assets/BotaoLikeFilled.png")
                  : modoEscuro
                  ? require("../assets/BotaoLike-dark.png")
                  : require("../assets/BotaoLike.png")
              }
              style={styles.icon}
            />
          </TouchableOpacity>

          <TouchableOpacity onPress={() => onComentarioPress?.(lugar)}>
            <Image
              source={
                modoEscuro
                  ? require("../assets/BotaoComentario-dark.png")
                  : require("../assets/BotaoComentario.png")
              }
              style={styles.icon}
            />
          </TouchableOpacity>

          <TouchableOpacity onPress={() => abrirMapa(lugar.nome)}>
            <Image
              source={
                modoEscuro
                  ? require("../assets/BotaoGoogleMaps-dark.png")
                  : require("../assets/BotaoGoogleMaps.png")
              }
              style={styles.icon}
            />
          </TouchableOpacity>
        </View>

        <TouchableOpacity onPress={() => toggleSalvo(lugar)}>
          <Image
            source={
              salvo
                ? modoEscuro
                  ? require("../assets/BotaoSalvoFilled-dark.png")
                  : require("../assets/BotaoSalvoFilled.png")
                : modoEscuro
                ? require("../assets/BotaoSalvo-dark.png")
                : require("../assets/BotaoSalvo.png")
            }
            style={styles.icon}
          />
        </TouchableOpacity>
      </View>

      <Text style={[styles.likes, { color: colors.text }]}>
        {lugar.qtdLike} curtidas
      </Text>

      <Text style={[styles.tags, { color: colors.text }]}>
        {(lugar.tags || [])
          .map((tag) => tag?.nome)
          .filter(Boolean)
          .map((nome) => `#${nome}`)
          .join(" ")}
      </Text>

      <Text style={[styles.tituloDescricao, { color: colors.text }]}>
        Descrição:
      </Text>

      <Text style={[styles.descricao, { color: colors.text }]}>
        {lugar.descricao}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    marginBottom: 20,
  },

  mainImage: {
    width: width,
    height: 450,
    resizeMode: "cover",
  },

  actionsRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    paddingHorizontal: 16,
    paddingVertical: 12,
  },

  leftActions: {
    flexDirection: "row",
    gap: 16,
  },

  icon: {
    width: 20,
    height: 20,
    resizeMode: "contain",
  },

  likes: {
    marginHorizontal: 16,
    fontSize: 13,
    marginBottom: 4,
  },

  tags: {
    marginHorizontal: 16,
    fontSize: 13,
    marginBottom: 10,
  },

  descricao: {
    marginHorizontal: 16,
    fontSize: 14,
    marginBottom: 20,
  },

  tituloDescricao: {
    marginHorizontal: 16,
    fontSize: 15,
    fontWeight: "600",
    marginBottom: 4,
  },

  dotsContainer: {
    flexDirection: "row",
    justifyContent: "center",
    marginTop: 8,
  },

  dot: {
    width: 6,
    height: 6,
    borderRadius: 10,
    marginHorizontal: 3,
  },
});
