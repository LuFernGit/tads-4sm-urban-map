import React, { useContext } from "react";
import { View, Text, StyleSheet } from "react-native";
import { ThemeContext } from "../context/ThemeContext";

export default function DescricaoDetalhes({ descricao }) {
  const { colors } = useContext(ThemeContext);

  return (
    <View
      style={[
        styles.container,
        { backgroundColor: colors.background },
      ]}
    >
      <Text style={[styles.titulo, { color: colors.text }]}>
        Descrição:
      </Text>

      <Text style={[styles.texto, { color: colors.text }]}>
        {descricao}
      </Text>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 15,
  },

  titulo: {
    fontSize: 16,
    fontWeight: "700",
    marginBottom: 6,
  },

  texto: {
    fontSize: 14,
    lineHeight: 20,
  },
});