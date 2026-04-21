import { Ionicons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";
import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";

export default function TelaPerfilUsuario() {
  const navigation = useNavigation();

  return (
    <View style={styles.container}>
      <View style={styles.topBar}>
        <Ionicons name="arrow-back" size={24} />
        <Text style={styles.username}>_JuliaCostaa</Text>
        <Ionicons name="settings-outline" size={22} />
      </View>

      <View style={styles.profileSection}>
        <Image
          source={require("../../assets/FotoUser/julia.jpeg")}
          style={styles.avatar}
        />

        <Text style={styles.name}>Julia Alves Costa</Text>
        <Text style={styles.usernameLight}>_JuliaCostaa</Text>

        <View style={styles.stats}>
          <View>
            <Text style={styles.statNumber}>24</Text>
            <Text style={styles.statLabel}>Visitados</Text>
          </View>
          <View>
            <Text style={styles.statNumber}>120</Text>
            <Text style={styles.statLabel}>Curtidas</Text>
          </View>
          <View>
            <Text style={styles.statNumber}>18</Text>
            <Text style={styles.statLabel}>Salvos</Text>
          </View>
        </View>

        <TouchableOpacity
          style={styles.button}
          onPress={() => navigation.navigate("EditarUsuario")}
        >
          <Text style={styles.buttonText}>Editar perfil</Text>
        </TouchableOpacity>
      </View>

      <TouchableOpacity style={styles.logout}>
        <Text>Sair</Text>
      </TouchableOpacity>

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
  },

  username: { fontWeight: "500" },

  profileSection: {
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
    marginTop: 10,
  },

  usernameLight: {
    color: "#777",
    marginBottom: 10,
  },

  stats: {
    flexDirection: "row",
    gap: 30,
    marginVertical: 15,
  },

  statNumber: {
    fontWeight: "bold",
    textAlign: "center",
  },

  statLabel: {
    fontSize: 12,
    textAlign: "center",
  },

  button: {
    backgroundColor: "#000",
    paddingVertical: 10,
    paddingHorizontal: 25,
    borderRadius: 8,
  },

  buttonText: {
    color: "#fff",
  },

  logout: {
    marginTop: 20,
    marginLeft: 20,
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
