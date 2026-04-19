import {
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from "react-native";

export default function TelaCadastro({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.back} onPress={() => navigation.goBack()}>
        ←
      </Text>

      <Text style={styles.title}>
        Junte-se a nós.{"\n"}Compartilhe momentos
      </Text>

      <TextInput placeholder="Nome Completo" style={styles.input} />
      <TextInput placeholder="Email" style={styles.input} />
      <TextInput placeholder="Telefone" style={styles.input} />
      <TextInput placeholder="Data de nascimento" style={styles.input} />
      <TextInput placeholder="Usuário" style={styles.input} />
      <TextInput placeholder="Senha" style={styles.input} secureTextEntry />
      <TextInput
        placeholder="Confirme sua senha"
        style={styles.input}
        secureTextEntry
      />

      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate("CadastroSucesso")}
      >
        <Text style={styles.buttonText}>Cadastre-se</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#dff3ff",
    padding: 25,
    paddingTop: 60,
  },
  back: {
    fontSize: 28,
    marginBottom: 20,
    color: "#1e232c",
  },
  title: {
    fontSize: 30,
    fontWeight: "bold",
    color: "#1e232c",
    marginBottom: 25,
  },
  input: {
    backgroundColor: "#f8f8f8",
    padding: 15,
    borderRadius: 10,
    marginBottom: 15,
    borderWidth: 1,
    borderColor: "#e8ecf4",
  },
  button: {
    backgroundColor: "#1e232c",
    padding: 16,
    borderRadius: 10,
    alignItems: "center",
    marginTop: 10,
  },
  buttonText: {
    color: "#fff",
    fontWeight: "bold",
    fontSize: 16,
  },
});
