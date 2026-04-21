import { MaterialIcons, Ionicons } from "@expo/vector-icons";
import { StyleSheet, TextInput, TouchableOpacity, View } from "react-native";


export default function SearchBar({
  value,
  onChangeText,
  onClear,
  onFilterPress,
}) {
  return (
    <View style={styles.searchRow}>
      <View style={styles.searchBox}>
        <MaterialIcons name="search" size={ICON_SIZE} color="#888" />

        <TextInput
          value={value}
          onChangeText={onChangeText}
          placeholder="Buscar"
          placeholderTextColor="#888"
          style={styles.input}
        />

        {value.length > 0 && (
          <TouchableOpacity
            onPress={onClear}
            hitSlop={10}
          >
            <Ionicons name="close-circle" size={ICON_SIZE} color="#999" />
          </TouchableOpacity>
        )}
      </View>

      <TouchableOpacity onPress={onFilterPress} hitSlop={10}>
        <Ionicons name="filter" size={ICON_SIZE} color="#333" />
      </TouchableOpacity>
    </View>
  );
}

const ICON_SIZE = 20;

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
    borderColor: "#ddd",
    borderRadius: 25, 
    paddingHorizontal: 12,
    marginRight: 12,
    height: 44, 
    backgroundColor: "#fff", 
  },

  input: {
    flex: 1,
    marginLeft: 8,
    paddingVertical: 0, 
  },
});