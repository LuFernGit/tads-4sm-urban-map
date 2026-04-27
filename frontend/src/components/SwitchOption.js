import { View, Text, StyleSheet, Switch } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function SwitchOption({ value, setValue }) {
  const { colors, modoEscuro } = useContext(ThemeContext);

  return (
   <View
      style={[
        styles.option,
        {
          backgroundColor: colors.card,
          borderColor: colors.border,
        },
      ]}
    >
      <Ionicons name="moon" size={20} color={colors.text} />

      <Text style={[styles.text, { color: colors.text }]}>
        Modo Escuro
      </Text>

      <Switch
        value={value}
        onValueChange={setValue}
        trackColor={{
          false: modoEscuro ? "#444" : "#ccc",
          true: "#38B6FF",
        }}
        thumbColor="#fff"
        ios_backgroundColor={modoEscuro ? "#444" : "#ccc"}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  option: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    padding: 15,
    borderRadius: 12,
    borderWidth: 1,
  },

  text: {
    fontSize: 16,
    fontWeight: "600",
    flex: 1,
    marginLeft: 10,
  },
});