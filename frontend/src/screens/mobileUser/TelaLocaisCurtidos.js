import { useNavigation } from "@react-navigation/native";
import { useContext } from "react";
import { ScrollView, StyleSheet, View, Text } from "react-native";

import HeaderDetails from "../../components/HeaderDetails";
import NavBar from "../../components/NavBar";
import Card from "../../components/Card";

import { CurtidosContext } from "../../context/CurtidosContext";
import { ThemeContext } from "../../context/ThemeContext";

export default function TelaLocaisCurtidos() {
  const navigation = useNavigation();
  const { colors } = useContext(ThemeContext);
  const { curtidos } = useContext(CurtidosContext);

  const styles = createStyles(colors);

  return (
    <View style={styles.container}>
      <HeaderDetails
        title="Locais Curtidos"
        onBack={() => {
          if (navigation.canGoBack()) {
            navigation.goBack();
          } else {
            navigation.navigate("Principal");
          }
        }}
      />

      <ScrollView showsVerticalScrollIndicator={false}>
        {curtidos.length === 0 ? (
          <Text style={[styles.emptyText, { color: colors.text }]}>
            Você ainda não curtiu nenhum local 💔
          </Text>
        ) : (
          curtidos.map((item) => (
            <Card key={item.id} lugar={item} />
          ))
        )}
      </ScrollView>

      <NavBar />
    </View>
  );
}

const createStyles = (colors) =>
  StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: colors.background,
    },
    emptyText: {
      textAlign: "center",
      marginTop: 50,
      fontSize: 16,
    },
  });