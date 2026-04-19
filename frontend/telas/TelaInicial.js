import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";

export default function TelaInicial({ navigation }) {
  return (
    <View style={styles.container}>
      <Image
        source={require("../assets/logo.png")}
        style={styles.logo}
        resizeMode="contain"
      />

      <View style={styles.buttonsContainer}>
        <TouchableOpacity
          style={styles.loginButton}
          onPress={() => navigation.navigate("Login")}
        >
          <Text style={styles.loginText}>Login</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.registerButton}
          onPress={() => navigation.navigate("Cadastro")}
        >
          <Text style={styles.registerText}>Cadastre-se</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#dff3ff",
    justifyContent: "center",
    alignItems: "center",
  },
  logo: {
    width: 250,
    height: 150,
    marginBottom: 80,
  },
  buttonsContainer: {
    width: "80%",
  },
  loginButton: {
    backgroundColor: "#1e232c",
    padding: 15,
    borderRadius: 10,
    alignItems: "center",
    marginBottom: 15,
  },
  loginText: {
    color: "#fff",
    fontSize: 16,
  },
  registerButton: {
    backgroundColor: "#f8f8f8",
    padding: 15,
    borderRadius: 10,
    alignItems: "center",
  },
  registerText: {
    color: "#000",
    fontSize: 16,
  },
});
