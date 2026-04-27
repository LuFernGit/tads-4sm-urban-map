import { useState, useContext } from "react";
import {
  TextInput,
  View,
  StyleSheet,
  TouchableOpacity,
  Text,
} from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { ThemeContext } from "../context/ThemeContext";

export default function InputField({
  label,
  value,
  editable = true,
  placeholder,
  secureTextEntry,
  keyboardType,
  onChangeText,
  error,
}) {
  const [hidePassword, setHidePassword] = useState(secureTextEntry);

  const { colors } = useContext(ThemeContext);

  return (
    <View style={{ width: "100%", alignItems: "center" }}>

      {label && (
        <Text style={[styles.label, { color: colors.text }]}>
          {label}
        </Text>
      )}

      <View
        style={[
          styles.container,
          {
            backgroundColor: colors.card,
            borderColor: error ? "red" : colors.text + "30",
          },
          !editable && styles.containerDisabled,
        ]}
      >
        <TextInput
          style={[
            styles.input,
            { color: colors.text },
            !editable && styles.inputDisabled,
          ]}
          value={value}
          editable={editable}
          placeholder={placeholder}
          placeholderTextColor={colors.text + "70"}
          secureTextEntry={hidePassword}
          keyboardType={keyboardType}
          onChangeText={onChangeText}
        />

        {secureTextEntry && (
          <TouchableOpacity
            onPress={() => setHidePassword(!hidePassword)}
            style={styles.icon}
          >
            <Ionicons
              name={hidePassword ? "eye-off" : "eye"}
              size={20}
              color={colors.text}
            />
          </TouchableOpacity>
        )}
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    width: "80%",
    flexDirection: "row",
    alignItems: "center",
    borderRadius: 10,
    borderWidth: 1,
    paddingHorizontal: 10,
    marginBottom: 15,
  },

  input: {
    flex: 1,
    padding: 15,
  },

  inputDisabled: {
    color: "#999",
  },

  icon: {
    padding: 5,
  },

  label: {
    width: "80%",
    marginBottom: 5,
    fontSize: 14,
  },

  containerDisabled: {
    opacity: 0.6,
  },
});