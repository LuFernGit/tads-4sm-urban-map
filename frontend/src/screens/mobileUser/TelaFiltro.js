import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

export default function TelaFiltro() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Filtro</Text>

      {[
        "Tipo de lugar",
        "Experiência",
        "Ambiente",
        "Pet friendly",
        "Preço",
        "Avaliação",
        "Estrutura",
      ].map((item) => (
        <View key={item} style={styles.item}>
          <Text>{item}</Text>
          <Text style={styles.plus}>+</Text>
        </View>
      ))}

      <View style={styles.buttons}>
        <TouchableOpacity style={styles.button}>
          <Text style={styles.buttonText}>Aplicar filtros</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.buttonSecondary}>
          <Text style={styles.buttonSecondaryText}>Limpar filtros</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },

  title: {
    fontSize: 22,
    marginBottom: 20,
  },

  item: {
    flexDirection: "row",
    justifyContent: "space-between",
    paddingVertical: 15,
    borderBottomWidth: 1,
    borderColor: "#ddd",
  },

  plus: {
    fontSize: 18,
  },

  buttons: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: "auto",
  },

  button: {
    backgroundColor: "#000",
    padding: 15,
    borderRadius: 10,
    width: "48%",
    alignItems: "center",
  },

  buttonText: {
    color: "#fff",
  },

  buttonSecondary: {
    backgroundColor: "#eee",
    padding: 15,
    borderRadius: 10,
    width: "48%",
    alignItems: "center",
  },

  buttonSecondaryText: {
    color: "#000",
  },
});
