import React, { useEffect } from "react";
import { View, Text, StyleSheet, Image } from "react-native";

export default function TelaCadastroSucesso({ navigation }) {
  useEffect(() => {
    const timer = setTimeout(() => {
      navigation.reset({
        index: 0,
        routes: [{ name: "Login" }],
      });
    }, 3000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <View style={styles.container}>
      <Image
        source={require("../../assets/sucesso.png")}
        style={styles.imagemSucesso}
        resizeMode="contain"
      />

      <Text style={styles.title}>Cadastro realizado</Text>

      <Text style={styles.subtitle}>
        Seu cadastro foi realizado com sucesso
      </Text>

      <Text style={styles.info}>
        Você será redirecionado automaticamente...
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    justifyContent: "center",
    alignItems: "center",
    padding: 25,
  },

  imagemSucesso: {
    width: 180,
    height: 180,
    marginBottom: 20,
  },

  title: {
    fontSize: 26,
    fontWeight: "bold",
    color: "#1e232c",
    marginBottom: 10,
  },

  subtitle: {
    fontSize: 15,
    color: "#444",
    textAlign: "center",
    marginBottom: 10,
  },

  info: {
    fontSize: 13,
    color: "#666",
    marginTop: 10,
  },
});