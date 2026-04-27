import { useContext, useState } from "react";
import {
  Image,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Linking,
  ScrollView,
  Dimensions,
} from "react-native";

import { SalvosContext } from "../context/SalvosContext";
import { ThemeContext } from "../context/ThemeContext";
import { CurtidosContext } from "../context/CurtidosContext";

const { width } = Dimensions.get("window");

export default function Card({ lugar, onComentarioPress, onPress }) {
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
      <Text style={[styles.locationTitle, { color: colors.text }]}>
        {lugar?.nome}
      </Text>

      {lugar?.fotosUrl?.length > 0 && (
        <ScrollView
          horizontal
          pagingEnabled
          showsHorizontalScrollIndicator={false}
          snapToInterval={width}
          decelerationRate="fast"
          onMomentumScrollEnd={(event) => {
            const slide = Math.round(
              event.nativeEvent.contentOffset.x / width
            );
            setIndexAtivo(slide);
          }}
        >
          {lugar.fotosUrl.map((img, index) => {
            const ImageComponent = index === 0 ? TouchableOpacity : View;

            return (
              <ImageComponent
                key={index}
                onPress={index === 0 ? onPress : undefined}
                activeOpacity={0.9}
                style={{ width }}
              >
                <Image source={img} style={styles.mainImage} />
              </ImageComponent>
            );
          })}
        </ScrollView>
      )}

      {lugar?.fotosUrl?.length > 1 && (
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

          <TouchableOpacity onPress={() => abrirMapa(lugar?.nome)}>
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
        {lugar?.qtdLike ?? 0} curtidas
      </Text>

      <Text style={[styles.tags, { color: colors.text }]}>
        {(lugar?.tags || [])
          .map((tag) => tag?.nome)
          .filter(Boolean)
          .map((nome) => `#${nome}`)
          .join(" ")}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    marginBottom: 20,
  },

  locationTitle: {
    fontSize: 16,
    fontWeight: "500",
    marginHorizontal: 16,
    marginBottom: 10,
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

  likes: {
    marginHorizontal: 16,
    fontSize: 13,
    marginBottom: 4,
  },

  tags: {
    marginHorizontal: 16,
    fontSize: 13,
    marginBottom: 20,
  },

  icon: {
    width: 20,
    height: 20,
    resizeMode: "contain",
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