import { Ionicons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";
import {
  Image,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from "react-native";

export default function TelaEditarUsuario() {
  const navigation = useNavigation();

  return (
    <View style={styles.container}>
      <View style={styles.topBar}>
        <TouchableOpacity onPress={() => navigation.goBack()}>
          <Ionicons name="arrow-back" size={24} />
        </TouchableOpacity>

        <Text style={styles.title}>Editar perfil</Text>

        <TouchableOpacity onPress={() => navigation.goBack()}>
          <Text style={styles.save}>Salvar</Text>
        </TouchableOpacity>
      </View>

      <View style={styles.avatarSection}>
        <Image
          source={require("../../assets/FotoUser/julia.jpeg")}
          style={styles.avatar}
        />
        <Text style={styles.changePhoto}>Alterar foto</Text>
      </View>

      <View style={styles.form}>
        <Text>Nome completo</Text>
        <TextInput style={styles.input} value="Julia Alves Costa" />

        <Text>Usuário</Text>
        <TextInput style={styles.input} value="_JuliaCostaa" />

        <Text>Email</Text>
        <TextInput
          style={styles.inputDisabled}
          value="juliaCostaa26@gmail.com"
          editable={false}
        />

        <Text>Telefone</Text>
        <TextInput style={styles.input} value="(11) 95432-8890" />

        <Text>Data de nascimento</Text>
        <TextInput style={styles.input} placeholder="dd/mm/aaaa" />
      </View>

      <View style={styles.bottomNav}>
        <Ionicons name="home-outline" size={24} />
        <Ionicons name="heart-outline" size={24} />
        <Ionicons name="bookmark-outline" size={24} />
        <Ionicons name="person-circle" size={24} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#fff" },

  topBar: {
    marginTop: 50,
    paddingHorizontal: 16,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },

  title: { fontSize: 16 },

  save: {
    color: "#3aaefc",
    fontWeight: "500",
  },

  avatarSection: {
    alignItems: "center",
    marginTop: 20,
  },

  avatar: {
    width: 90,
    height: 90,
    borderRadius: 45,
  },

  changePhoto: {
    color: "#3aaefc",
    marginTop: 8,
  },

  form: {
    padding: 16,
  },

  input: {
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 10,
    padding: 10,
    marginBottom: 10,
  },

  inputDisabled: {
    borderWidth: 1,
    borderColor: "#ddd",
    borderRadius: 10,
    padding: 10,
    marginBottom: 10,
    backgroundColor: "#eee",
  },

  bottomNav: {
    position: "absolute",
    bottom: 0,
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-around",
    paddingVertical: 14,
    borderTopWidth: 1,
    borderTopColor: "#eee",
  },
});
