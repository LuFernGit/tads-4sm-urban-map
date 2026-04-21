import { useState } from "react";
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

const { width } = Dimensions.get("window");

export default function Card({ lugar }) {
  const [curtido, setCurtido] = useState(false);
  const [indexAtivo, setIndexAtivo] = useState(0);

  const abrirMapa = (nome) => {
    const url = `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(
      nome,
    )}`;
    Linking.openURL(url);
  };

  return (
    <View style={styles.card}>
      <Text style={styles.locationTitle}>{lugar.nome}</Text>

      {lugar.fotosUrl?.length > 1 ? (
        <View>
          <ScrollView
            horizontal
            pagingEnabled
            showsHorizontalScrollIndicator={false}
            onScroll={(event) => {
              const slide = Math.round(
                event.nativeEvent.contentOffset.x / width,
              );
              setIndexAtivo(slide);
            }}
            scrollEventThrottle={16}
          >
            {lugar.fotosUrl.map((img, index) => (
              <Image
                key={index}
                source={img} 
                style={styles.mainImage}
              />
            ))}
          </ScrollView>

          <View style={styles.dotsContainer}>
            {lugar.fotosUrl.map((_, i) => (
              <View
                key={i}
                style={[styles.dot, indexAtivo === i && styles.dotActive]}
              />
            ))}
          </View>
        </View>
      ) : (
        <Image source={lugar.fotosUrl[0]} style={styles.mainImage} />
      )}

      <View style={styles.actionsRow}>
        <View style={styles.leftActions}>
          <TouchableOpacity onPress={() => setCurtido(!curtido)}>
            <Image
              source={
                curtido
                  ? require("../assets/BotaoLikeFilled.png")
                  : require("../assets/BotaoLike.png")
              }
              style={styles.icon}
            />
          </TouchableOpacity>

          <Image
            source={require("../assets/BotaoComentario.png")}
            style={styles.icon}
          />

          <TouchableOpacity onPress={() => abrirMapa(lugar.nome)}>
            <Image
              source={require("../assets/BotaoGoogleMaps.png")}
              style={styles.icon}
            />
          </TouchableOpacity>
        </View>

        <Image
          source={require("../assets/BotaoSalvo.png")}
          style={styles.icon}
        />
      </View>

      <Text style={styles.likes}>{lugar.qtdLike} curtidas</Text>

      <Text style={styles.tags}>
        {lugar.tags?.map((tag) => `#${tag.nome}`).join(" ")}
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
    height: 380,
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
    color: "#222",
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
    marginBottom: 10,
  },

  dot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    backgroundColor: "#ccc",
    marginHorizontal: 3,
  },

  dotActive: {
    backgroundColor: "#333",
    width: 8,
    height: 8,
  },
});
