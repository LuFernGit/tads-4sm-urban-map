import { View, Text, StyleSheet, Pressable } from "react-native";
import Slider from "@react-native-community/slider";
import { AntDesign, Ionicons } from "@expo/vector-icons";
import { useState, useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

export default function TextSizeControl({ value, setValue }) {
  const [open, setOpen] = useState(false);
  const { colors,  } = useContext(ThemeContext);

  const getLabel = () => {
    if (value === 0) return "Pequeno";
    if (value === 1) return "Médio";
    return "Grande";
  };

  const accent = "#38B6FF"; 

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
      <Pressable style={styles.header} onPress={() => setOpen(!open)}>
        <AntDesign name="font-size" size={20} color={colors.text} />

        <Text style={[styles.title, { color: colors.text }]}>
          Tamanho do Texto
        </Text>

        <Text style={[styles.current, { color: accent }]}>
          {getLabel()}
        </Text>

        <Ionicons
          name={open ? "chevron-up-outline" : "chevron-down-outline"}
          size={18}
          color={colors.text}
        />
      </Pressable>

      {open && (
        <View style={styles.content}>
          <Slider
            style={{ width: "100%", height: 40 }}
            minimumValue={0}
            maximumValue={2}
            step={1}
            value={value}
            onValueChange={setValue}
            minimumTrackTintColor={accent}
            maximumTrackTintColor={colors.text + "30"}
            thumbTintColor={accent}
          />

          <View style={styles.sliderLabels}>
            <Text style={[styles.label, { color: colors.text }]}>
              Pequeno
            </Text>
            <Text style={[styles.label, { color: colors.text }]}>
              Médio
            </Text>
            <Text style={[styles.label, { color: colors.text }]}>
              Grande
            </Text>
          </View>
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    padding: 15,
    borderRadius: 10,
    borderWidth: 1,
  },

  header: {
    flexDirection: "row",
    alignItems: "center",
  },

  title: {
    fontSize: 16,
    fontWeight: "600",
    flex: 1,
    marginLeft: 8,
  },

  current: {
    fontSize: 14,
    fontWeight: "500",
    marginRight: 10,
  },

  content: {
    marginTop: 10,
  },

  sliderLabels: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 5,
  },

  label: {
    fontSize: 12,
  },
});