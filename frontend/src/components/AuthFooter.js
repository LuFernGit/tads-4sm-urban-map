import { TouchableOpacity, Text, StyleSheet } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function AuthFooter({ onPress }) {
  const { colors } = useContext(ThemeContext);

  return (
    <TouchableOpacity onPress={onPress} style={styles.footer}>
      <Text style={{ color: colors.text }}>
        Não tem uma conta?{" "}
        <Text style={[styles.link, { color: "#38B6FF" }]}>
          Cadastre-se agora.
        </Text>
      </Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  footer: {
    marginTop: 200,
  },
  link: {
    fontStyle: "italic",
    fontWeight: "900",
  },
});