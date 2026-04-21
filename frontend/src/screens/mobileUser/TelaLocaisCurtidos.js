import { Ionicons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";
import { useState } from "react";
import {
  Image,
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from "react-native";

export default function TelaLocaisCurtidos() {
  const navigation = useNavigation();
  const [curtido, setCurtido] = useState(true);

  return (
    <View style={styles.container}>
      <ScrollView showsVerticalScrollIndicator={false}>
        <View style={styles.topBar}>
          <TouchableOpacity onPress={() => navigation.goBack()}>
            <Ionicons name="arrow-back" size={24} color="#000" />
          </TouchableOpacity>

          <Text style={styles.title}>Locais Curtidos</Text>

          <View style={{ width: 24 }} />
        </View>

        <Text style={styles.locationTitle}>Museu do Ipiranga</Text>

        <Image
          source={require("../../assets/Locais/museuIpiranga1.jpg")}
          style={styles.mainImage}
        />

        <View style={styles.actionsRow}>
          <View style={styles.leftActions}>
            <TouchableOpacity onPress={() => setCurtido(!curtido)}>
              <Image
                source={
                  curtido
                    ? require("../../assets/BotaoLikeFilled.png")
                    : require("../../assets/BotaoLike.png")
                }
                style={styles.icon}
              />
            </TouchableOpacity>

            <Image
              source={require("../../assets/BotaoComentario.png")}
              style={styles.icon}
            />

            <Image
              source={require("../../assets/BotaoGoogleMaps.png")}
              style={styles.icon}
            />
          </View>

          <Image
            source={require("../../assets/BotaoSalvo.png")}
            style={styles.icon}
          />
        </View>

        <Text style={styles.likes}>
          Curtido por JoãoGabriel e outras 19 pessoas
        </Text>

        <Text style={styles.tags}>
          #Museu #História #Cultura #PasseioEmFamilia #Imperdivel #BemAvaliado
        </Text>

        <Text style={styles.date}>29 abril de 2025</Text>

        <Text style={styles.locationTitle}>Cristo Redentor</Text>
      </ScrollView>

      <View style={styles.bottomNav}>
        <TouchableOpacity onPress={() => navigation.navigate("Principal")}>
          <Image
            source={require("../../assets/BotaoHome.png")}
            style={styles.icon}
          />
        </TouchableOpacity>

        <TouchableOpacity onPress={() => navigation.navigate("LocaisCurtidos")}>
          <Image
            source={require("../../assets/BotaoLikeFilled.png")}
            style={styles.icon}
          />
        </TouchableOpacity>

        <Image
          source={require("../../assets/BotaoSalvo.png")}
          style={styles.icon}
        />

        <TouchableOpacity onPress={() => navigation.navigate("PerfilUsuario")}>
          <Image
            source={require("../../assets/BotaoPerfil.png")}
            style={styles.icon}
          />
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },

  topBar: {
    marginTop: 50,
    paddingHorizontal: 16,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },

  title: {
    fontSize: 16,
    fontWeight: "500",
  },

  locationTitle: {
    fontSize: 16,
    fontWeight: "500",
    marginHorizontal: 16,
    marginVertical: 10,
  },

  mainImage: {
    width: "100%",
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
    marginBottom: 4,
  },

  date: {
    marginHorizontal: 16,
    fontSize: 11,
    color: "#888",
    marginBottom: 20,
  },

  bottomNav: {
    position: "absolute",
    bottom: 0,
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-around",
    paddingVertical: 14,
    borderTopWidth: 1,
    borderTopColor: "#eee",
    backgroundColor: "#fff",
  },

  icon: {
    width: 24,
    height: 24,
    resizeMode: "contain",
  },
});
