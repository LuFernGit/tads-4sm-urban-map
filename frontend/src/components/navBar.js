import { View, Image, StyleSheet, TouchableOpacity } from "react-native";
import { useNavigation } from "@react-navigation/native";

export default function NavBar() {
  const navigation = useNavigation();
  return (
    <View style={styles.NavBar}>
      <TouchableOpacity onPress={() => navigation.navigate("Principal")}>
        <Image
          source={require("../assets/BotaoHome.png")}
          style={styles.icon}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate("LocaisCurtidos")}>
        {" "}
        <Image
          source={require("../assets/BotaoLike.png")}
          style={styles.icon}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate("LocaisSalvos")}>
        {" "}
        <Image
          source={require("../assets/BotaoSalvo.png")}
          style={styles.icon}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate("PerfilUsuario")}>
        <Image
          source={require("../assets/BotaoPerfil.png")}
          style={styles.icon}
        />
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  NavBar: {
    flexDirection: "row",
    justifyContent: "space-around",
    height: 55,
    paddingVertical: 10,
    paddingTop: 20,
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
