import { Feather, Ionicons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";
import { useState } from "react";
import {
  Image,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from "react-native";

export default function TelaPrincipal() {
  const [curtido, setCurtido] = useState(false);
  const navigation = useNavigation();

  return (
    <View style={styles.container}>
      <ScrollView showsVerticalScrollIndicator={false}>
        <View style={styles.header}>
          <Image
            source={require("../assets/logo.png")}
            style={styles.logo}
            resizeMode="contain"
          />
        </View>

        <View style={styles.searchRow}>
          <View style={styles.searchBox}>
            <Ionicons name="search" size={18} color="#888" />
            <TextInput
              placeholder="Buscar"
              placeholderTextColor="#888"
              style={styles.input}
            />
          </View>

          <TouchableOpacity onPress={() => navigation.navigate("Filtro")}>
            <Feather name="sliders" size={20} color="#333" />
          </TouchableOpacity>
        </View>

        <Text style={styles.locationTitle}>Parque Ibirapuera</Text>

        <Image
          source={require("../assets/ParqueIbirapuera.png")}
          style={styles.mainImage}
        />

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

            <Image
              source={require("../assets/BotaoGoogleMaps.png")}
              style={styles.icon}
            />
          </View>

          <Image
            source={require("../assets/BotaoSalvo.png")}
            style={styles.icon}
          />
        </View>

        <Text style={styles.likes}>
          curtido por JessicaMaia_ e outras 12 pessoas
        </Text>

        <Text style={styles.tags}>
          #aberto #petfriendly #espacoaberto #familiar ...
        </Text>
      </ScrollView>

      <View style={styles.bottomNav}>
        <Ionicons name="home-outline" size={24} />
        <Ionicons name="heart-outline" size={24} />
        <Ionicons name="bookmark-outline" size={24} />
        <Ionicons name="person-circle-outline" size={24} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#fff" },
  header: { paddingTop: 50, paddingHorizontal: 20, paddingBottom: 10 },
  logo: { width: 110, height: 50 },
  searchRow: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    paddingHorizontal: 16,
    marginBottom: 20,
  },
  searchBox: {
    flex: 1,
    flexDirection: "row",
    alignItems: "center",
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 20,
    paddingHorizontal: 12,
    marginRight: 12,
    height: 42,
  },
  input: { flex: 1, marginLeft: 8 },
  locationTitle: {
    fontSize: 16,
    fontWeight: "500",
    marginHorizontal: 16,
    marginBottom: 10,
  },
  mainImage: { width: "100%", height: 380 },
  actionsRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    padding: 16,
  },
  leftActions: { flexDirection: "row", gap: 16 },
  likes: { marginHorizontal: 16, fontSize: 13 },
  tags: { marginHorizontal: 16, fontSize: 13, marginBottom: 20 },
  bottomNav: {
    flexDirection: "row",
    justifyContent: "space-around",
    padding: 14,
    borderTopWidth: 1,
    borderColor: "#eee",
  },
  icon: { width: 24, height: 24 },
});
