import { useState, useContext } from "react";
import {
  Image,
  Text,
  View,
  StyleSheet,
  TouchableOpacity,
  Modal,
} from "react-native";

import { ThemeContext } from "../context/ThemeContext";

export default function ProfileInfo({ fotoPerfil, name }) {
  const { colors } = useContext(ThemeContext);

  const imagemPadrao = require("../assets/FotoUser/julia.jpeg");

  const imagemFinal = fotoPerfil ? fotoPerfil : imagemPadrao;

  const [visible, setVisible] = useState(false);

  return (
    <View style={styles.container}>

      <TouchableOpacity onPress={() => setVisible(true)}>
        <Image source={imagemFinal} style={styles.avatar} />
      </TouchableOpacity>

      <Text style={[styles.name, { color: colors.text }]}>
        {name}
      </Text>

      <Modal visible={visible} transparent={true}>
        <TouchableOpacity
          style={styles.modalBackground}
          onPress={() => setVisible(false)}
        >
          <Image source={imagemFinal} style={styles.fullImage} />
        </TouchableOpacity>
      </Modal>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    alignItems: "center",
    marginTop: 20,
  },

  avatar: {
    width: 90,
    height: 90,
    borderRadius: 45,
  },

  name: {
    fontSize: 18,
    marginTop: 25,
    fontWeight: "600",
  },

  modalBackground: {
    flex: 1,
    backgroundColor: "rgba(0, 0, 0, 0.9)",
    justifyContent: "center",
    alignItems: "center",
  },

  fullImage: {
    width: 300,
    height: 300,
    borderRadius: 10,
    resizeMode: "contain",
  },
});