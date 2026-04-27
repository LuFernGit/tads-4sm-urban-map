import { TouchableOpacity, Text, StyleSheet } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function PrimaryButton({ title, onPress, variant = "dark" }) {
  const { colors } = useContext(ThemeContext);

  return (
    <TouchableOpacity
      style={[
        styles.button,
        {
          backgroundColor:
            variant === "light"
              ? colors.secondary || "#AFAFB0"
              : colors.primary || "#000",
        },
      ]}
      onPress={onPress}
    >
      <Text
        style={[
          styles.text,
          {
            color:
              variant === "light"
                ? colors.text || "#000"
                : colors.buttonText || "#fff",
          },
        ]}
      >
        {title}
      </Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  button: {
    width: "80%",
    padding: 15,
    borderRadius: 10,
    alignItems: "center",
    marginBottom: 20,
  },

  text: {
    fontSize: 16,
    fontWeight: "bold",
  },
});