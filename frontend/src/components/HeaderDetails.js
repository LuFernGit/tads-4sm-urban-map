import React, { useContext } from "react";
import { View, Text, StyleSheet, TouchableOpacity } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { ThemeContext } from "../context/ThemeContext";

export default function HeaderDetalhes({ title, onBack }) {
  const { colors } = useContext(ThemeContext);

  return (
    <View
  style={[
    styles.container,
    {
      borderBottomWidth: 1,
      borderBottomColor: colors.border,
    },
  ]}
>

      <TouchableOpacity onPress={onBack} style={styles.side}>
        <Ionicons name="chevron-back" size={28} color={colors.text} />
      </TouchableOpacity>

      <View style={styles.center}>
        <Text
          numberOfLines={1}
          style={[styles.title, { color: colors.text }]}
        >
          {title}
        </Text>
      </View>

      <View style={styles.side} />

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    alignItems: "center",
    paddingTop: 10,
    height: 70,
    paddingHorizontal: 10,
  },

  side: {
    width: 40,
    justifyContent: "center",
    alignItems: "flex-start",
  },

  center: {
    flex: 1,
    alignItems: "center",
  },

  title: {
    fontSize: 16,
    fontWeight: "bold",
  },
});