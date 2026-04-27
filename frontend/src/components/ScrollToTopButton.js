import React, { useContext } from "react";
import { TouchableOpacity, StyleSheet } from "react-native";
import { Feather } from "@expo/vector-icons";
import { ThemeContext } from "../context/ThemeContext";

export default function ScrollToTopButton({ visible, onPress }) {
  const { colors, modoEscuro } = useContext(ThemeContext);

  if (!visible) return null;

  return (
    <TouchableOpacity
      style={[
        styles.button,
        {
          backgroundColor: modoEscuro ? "#2a2a2af0" : "#dbdbdbf0",
        },
      ]}
      onPress={onPress}
    >
      <Feather name="arrow-up" size={24} color={colors.text} />
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  button: {
    position: "absolute",
    bottom: 70,
    right: 15,
    padding: 10,
    borderRadius: 50,
    elevation: 5,
  },
});