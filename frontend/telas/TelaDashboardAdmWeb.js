import React from "react";
import { Ionicons } from "@expo/vector-icons";
import { View, Text, StyleSheet, TouchableOpacity, Image } from "react-native";

export default function TelaDashboardAdmWeb() {
  return (
    <View style={styles.container}>
      <View style={styles.sidebar}>
        <Image
          source={require("../assets/LogoUrbanMap.png")}
          style={styles.logoImage}
        />

        <TouchableOpacity style={styles.menuItemActive}>
          <View style={[styles.iconBoxSmall, { backgroundColor: "#E3F2FD" }]}>
            <Ionicons name="grid" size={18} color="#3BA6E0" />
          </View>
          <Text style={styles.menuTextActive}>Dashboard</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.menuItem}>
          <View style={styles.iconBoxSmall}>
            <Ionicons name="home" size={18} color="#555" />
          </View>
          <Text style={styles.menuText}>Gerenciar Locais</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.menuItem}>
          <View style={styles.iconBoxSmall}>
            <Ionicons name="people" size={18} color="#555" />
          </View>
          <Text style={styles.menuText}>Gerenciar Usuários</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.menuItem}>
          <View style={styles.iconBoxSmall}>
            <Ionicons name="pricetag" size={18} color="#555" />
          </View>
          <Text style={styles.menuText}>Gerenciar Tags</Text>
        </TouchableOpacity>

        <View style={styles.logout}>
          <View style={styles.iconBoxSmall}>
            <Ionicons name="log-out" size={18} color="#555" />
          </View>
          <Text style={styles.menuText}>Sair</Text>
        </View>
      </View>

      <View style={styles.content}>
        <Text style={styles.header}>Perfil do Administrador</Text>

        <Text style={styles.title}>Dashboard</Text>
        <Text style={styles.subtitle}>
          Bem-vindo ao sistema de gerenciamento
        </Text>

        <View style={styles.cardsContainer}>
          <View style={styles.card}>
            <View style={[styles.iconBox, { backgroundColor: "#E3F2FD" }]}>
              <Ionicons name="location-outline" size={20} color="#3BA6E0" />
            </View>
            <Text style={styles.cardNumber}>24</Text>
            <Text style={styles.cardText}>Locais cadastrados</Text>
          </View>

          <View style={styles.card}>
            <View style={[styles.iconBox, { backgroundColor: "#FDECEC" }]}>
              <Ionicons name="heart-outline" size={20} color="#E74C3C" />
            </View>
            <Text style={styles.cardNumber}>98</Text>
            <Text style={styles.cardText}>Total de curtidas</Text>
          </View>

          <View style={styles.card}>
            <View style={[styles.iconBox, { backgroundColor: "#EAFBF0" }]}>
              <Ionicons name="people-outline" size={20} color="#2ECC71" />
            </View>
            <Text style={styles.cardNumber}>42</Text>
            <Text style={styles.cardText}>Total de usuários</Text>
          </View>

          <View style={styles.card}>
            <View style={[styles.iconBox, { backgroundColor: "#FFF4E5" }]}>
              <Ionicons name="bookmark-outline" size={20} color="#F39C12" />
            </View>
            <Text style={styles.cardNumber}>+12%</Text>
            <Text style={styles.cardText}>Total de salvos</Text>
          </View>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "row",
  },

  sidebar: {
    width: 220,
    backgroundColor: "#f0f0f0",
    padding: 20,
  },

  logoImage: {
    width: 160,
    height: 80,
    resizeMode: "contain",
    marginBottom: 30,
  },

  menuItem: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 20,
    gap: 10,
  },

  menuItemActive: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 20,
    gap: 10,
    backgroundColor: "#dbeafe",
    padding: 10,
    borderRadius: 8,
  },

  menuText: {
    color: "#555",
  },

  menuTextActive: {
    color: "#3BA6E0",
    fontWeight: "bold",
  },

  logout: {
    position: "absolute",
    bottom: 20,
    flexDirection: "row",
    alignItems: "center",
    gap: 10,
  },

  content: {
    flex: 1,
    padding: 30,
    paddingTop: 100,
    backgroundColor: "#fff",
    alignItems: "flex-start",
  },

  header: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    backgroundColor: "#eee",
    padding: 18,
    textAlign: "center",
    fontWeight: "bold",
    fontSize: 18,
    zIndex: 1,
  },

  title: {
    fontSize: 30,
    fontWeight: "bold",
    textAlign: "left",
  },

  subtitle: {
    color: "#666",
    marginTop: 5,
    fontSize: 16,
    marginBottom: 20,
    textAlign: "left",
  },

  cardsContainer: {
    flexDirection: "row",
    gap: 30,
    justifyContent: "flex-start",
    marginTop: 10,
  },

  card: {
    backgroundColor: "#f9f9f9",
    padding: 30,
    borderRadius: 12,
    width: 180,
    alignItems: "center",
  },

  iconBox: {
    width: 40,
    height: 40,
    borderRadius: 10,
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 10,
  },

  iconBoxSmall: {
    width: 32,
    height: 32,
    borderRadius: 8,
    backgroundColor: "#e5e5e5",
    justifyContent: "center",
    alignItems: "center",
  },

  cardNumber: {
    fontSize: 28,
    fontWeight: "bold",
    marginTop: 10,
  },

  cardText: {
    color: "#666",
    fontSize: 14,
    textAlign: "center",
    marginTop: 5,
  },
});