import { Image, StyleSheet } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function Logo() {
  const { modoEscuro } = useContext(ThemeContext);

  return (
    <Image
      source={
        modoEscuro
          ? require("../assets/logo-dark.png")
          : require("../assets/logo.png")
      }
      style={styles.logo}
      resizeMode="contain"
    />
  );
}

const styles = StyleSheet.create({
  logo: {
    width: 200,
    height: 110,
    marginBottom: 80,
  },
});