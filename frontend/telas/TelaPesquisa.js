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

export default function TelaPesquisa() {
  const [curtido, setCurtido] = useState(false);
  const navigation = useNavigation();

  return (
    <View style={styles.container}>
      <ScrollView>
        <View style={styles.header}>
          <Image source={require("../assets/logo.png")} style={styles.logo} />
        </View>

        <View style={styles.searchRow}>
          <View style={styles.searchBox}>
            <Ionicons name="search" size={18} color="#888" />
            <TextInput value="Japan House" style={styles.input} />
          </View>

          <TouchableOpacity onPress={() => navigation.navigate("Filtro")}>
            <Feather name="sliders" size={20} />
          </TouchableOpacity>
        </View>

        <Text style={styles.locationTitle}>Japan House</Text>

        <Image
          source={require("../assets/JapanHouse.png")}
          style={styles.mainImage}
        />

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
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#fff" },
  header: { paddingTop: 50, paddingHorizontal: 20 },
  logo: { width: 110, height: 50 },
  searchRow: { flexDirection: "row", padding: 16 },
  searchBox: { flex: 1, flexDirection: "row" },
  input: { flex: 1 },
  locationTitle: { margin: 16 },
  mainImage: { width: "100%", height: 300 },
  icon: { width: 24, height: 24 },
});
