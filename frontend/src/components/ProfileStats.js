import { Text, View, StyleSheet } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function ProfileStats({ visited, likes, saved }) {
  const { colors } = useContext(ThemeContext);

  return (
    <View style={styles.stats}>

      <View>
        <Text style={[styles.number, { color: colors.text }]}>
          {likes}
        </Text>
        <Text style={[styles.label, { color: colors.text }]}>
          Curtidas
        </Text>
      </View>

      <View>
        <Text style={[styles.number, { color: colors.text }]}>
          {saved}
        </Text>
        <Text style={[styles.label, { color: colors.text }]}>
          Salvos
        </Text>
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  stats: {
    flexDirection: "row",
    gap: 30,
    marginVertical: 15,
  },

  number: {
    fontWeight: "700",
    textAlign: "center",
    fontSize: 18,
  },

  label: {
    fontSize: 13,
    textAlign: "center",
  },
});