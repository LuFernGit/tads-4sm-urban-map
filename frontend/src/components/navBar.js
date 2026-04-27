import { View, Image, StyleSheet, TouchableOpacity } from "react-native";
import { useNavigation, useRoute } from "@react-navigation/native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function NavBar() {
  const navigation = useNavigation();
  const route = useRoute();

  const { modoEscuro, colors } = useContext(ThemeContext);

  const isActive = (rota) => route.name === rota;

  return (
    <View
      style={[
        styles.NavBar,
        {
          backgroundColor: colors.background,
          borderTopColor: modoEscuro ? "#333" : "#eee",
        },
      ]}
    >

      <TouchableOpacity onPress={() => navigation.navigate("Principal")}>
        <Image
          source={
            isActive("Principal")
              ? modoEscuro
                ? require("../assets/BotaoHomeFilled-dark.png")
                : require("../assets/BotaoHomeFilled.png")
              : modoEscuro
                ? require("../assets/BotaoHome-dark.png")
                : require("../assets/BotaoHome.png")
          }
          style={styles.icon}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate("LocaisCurtidos")}>
        <Image
          source={
            isActive("LocaisCurtidos")
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

      <TouchableOpacity onPress={() => navigation.navigate("LocaisSalvos")}>
        <Image
          source={
            isActive("LocaisSalvos")
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

      <TouchableOpacity onPress={() => navigation.navigate("PerfilUsuario")}>
        <Image
          source={
            isActive("PerfilUsuario")
              ? modoEscuro
                ? require("../assets/BotaoPerfilFilled-dark.png")
                : require("../assets/BotaoPerfilFilled.png")
              : modoEscuro
                ? require("../assets/BotaoPerfil-dark.png")
                : require("../assets/BotaoPerfil.png")
          }
          style={styles.icon}
        />
      </TouchableOpacity>

    </View>
  );
}

const styles = StyleSheet.create({
  NavBar: {
    position: "absolute",
    bottom: 0,
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-around",
    height: 65,
    paddingTop: 15,
    borderTopWidth: 1,
  },

  icon: {
    width: 24,
    height: 24,
    resizeMode: "contain",
  },
});