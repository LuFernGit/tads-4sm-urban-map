import { Ionicons } from "@expo/vector-icons";
import { Text, View, StyleSheet, TouchableOpacity } from "react-native";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function ProfileHeader({
  username,
  onSettingsPress,
  onBackPress,
}) {
  const { colors } = useContext(ThemeContext);

  return (
    <View
      style={[
        styles.container,
        {
          borderBottomWidth: 1,
          borderBottomColor: colors.border,
          backgroundColor: colors.background,
        },
      ]}
    >
      <TouchableOpacity onPress={onBackPress} style={styles.side}>
        <Ionicons name="chevron-back" size={28} color={colors.text} />
      </TouchableOpacity>

      <View style={styles.center}>
        <Text
          numberOfLines={1}
          style={[styles.title, { color: colors.text }]}
        >
          {username}
        </Text>
      </View>

      <TouchableOpacity onPress={onSettingsPress} style={styles.side}>
        <Ionicons name="settings-outline" size={24} color={colors.text} />
      </TouchableOpacity>
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