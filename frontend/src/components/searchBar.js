import { MaterialIcons, Ionicons } from "@expo/vector-icons";
import { StyleSheet, TextInput, TouchableOpacity, View } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

const ICON_SIZE = 20;

export default function SearchBar({
  value,
  onChangeText,
  onClear,
  onFilterPress,
}) {
  const { colors, modoEscuro } = useContext(ThemeContext);

  return (
    <View style={styles.searchRow}>

      <View
        style={[
          styles.searchBox,
          {
            backgroundColor: colors.card,
            borderColor: modoEscuro ? "#333" : "#ddd",
          },
        ]}
      >
        <MaterialIcons name="search" size={ICON_SIZE} color={colors.text} />

        <TextInput
          value={value}
          onChangeText={onChangeText}
          placeholder="Buscar"
          placeholderTextColor={modoEscuro ? "#aaa" : "#888"}
          style={[styles.input, { color: colors.text }]}
        />

        {value.length > 0 && (
          <TouchableOpacity onPress={onClear} hitSlop={10}>
            <Ionicons
              name="close-circle"
              size={ICON_SIZE}
              color={colors.text}
            />
          </TouchableOpacity>
        )}
      </View>

      <TouchableOpacity onPress={onFilterPress} hitSlop={10}>
        <Ionicons
          name="filter"
          size={ICON_SIZE}
          color={colors.text}
        />
      </TouchableOpacity>

    </View>
  );
}

const styles = StyleSheet.create({
  searchRow: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    paddingHorizontal: 16,
    marginBottom: 12,
    marginTop: 5,
  },

  searchBox: {
    flex: 1,
    flexDirection: "row",
    alignItems: "center",
    borderWidth: 1,
    borderRadius: 25,
    paddingHorizontal: 12,
    marginRight: 12,
    height: 44,
  },

  input: {
    flex: 1,
    marginLeft: 8,
    paddingVertical: 0,
  },
});