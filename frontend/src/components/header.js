import { Image, StyleSheet, View } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function Header() {
  const { modoEscuro } = useContext(ThemeContext);

  return (
    <View style={styles.header}>
      <Image
        source={
          modoEscuro
            ? require("../assets/logo-dark.png")
            : require("../assets/logo.png")
        }
        style={styles.logo}
        resizeMode="contain"
      />
    </View>
  );
}

const styles = StyleSheet.create({
  header: {
    paddingTop: 10,
    paddingHorizontal: 20,
    paddingBottom: 10,
  },
  logo: {
    width: 110,
    height: 50,
  },
});