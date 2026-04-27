import { View, Text, StyleSheet } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function AcessibilityCard() {
  const { colors, tamanhoTexto } = useContext(ThemeContext);

  return (
    <View
      style={[
        styles.card,
        {
          backgroundColor: colors.card,
          borderColor: colors.border,
        },
      ]}
    >
            <View style={styles.cardHeader}>
        <Ionicons name="accessibility-outline" size={25} color="#38B6FF" />
        <Text style={[styles.cardTitle, { color: colors.text }]}>
          Acessibilidade
        </Text>
      </View>
      <Text
        style={[
          styles.cardSubtitle,
          { fontSize: 14 + tamanhoTexto, color: colors.text },
        ]}
      >
        Recursos de acessibilidade para visão
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    padding: 20,
    borderRadius: 15,
    borderWidth: 1,
  },
  cardHeader: {
    flexDirection: "row",
    alignItems: "center",
    gap: 15,
    marginBottom: 10,
  },
  cardTitle: {
    fontSize: 20,
    fontWeight: "bold",
  },
  cardSubtitle: {
    marginTop: 5,
  },
});
