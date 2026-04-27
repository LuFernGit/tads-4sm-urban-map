import { useState } from "react";
import { useNavigation } from "@react-navigation/native";
import { View, StyleSheet, Image, Text, TouchableOpacity } from "react-native";
import * as ImagePicker from "expo-image-picker";

import { usuariosMock } from "../../mock/UsuariosMock";

import ProfileHeader from "../../components/ProfileHeader";
import InputField from "../../components/InputField";
import NavBar from "../../components/NavBar";

export default function TelaEditarUsuario() {
  const navigation = useNavigation();

  const userData = usuariosMock[1];

  const [nome, setNome] = useState(userData.nome);
  const [user, setUser] = useState(userData.usuario);
  const [email] = useState(userData.email);
  const [telefone, setTelefone] = useState(userData.telefone);
  const [data, setData] = useState(userData.nascimento);

  // 📸 foto do usuário
  const [foto, setFoto] = useState(userData.fotoPerfil);

  // 📸 escolher imagem
  const escolherImagem = async () => {
    const permissao = await ImagePicker.requestMediaLibraryPermissionsAsync();

    if (!permissao.granted) {
      alert("Permissão necessária para acessar a galeria!");
      return;
    }

    const resultado = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 1,
    });

    if (!resultado.canceled) {
      setFoto({ uri: resultado.assets[0].uri });
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.content}>
        
        <ProfileHeader
          title="Editar perfil"
          onBack={() => navigation.goBack()}
          rightText="Salvar"
          onRightPress={() => navigation.goBack()}
        />

        {/* FOTO */}
        <View style={styles.avatarSection}>
          <TouchableOpacity onPress={escolherImagem}>
            <Image
              source={foto}
              style={styles.avatar}
            />
          </TouchableOpacity>

          <TouchableOpacity onPress={escolherImagem}>
            <Text style={styles.changePhoto}>Alterar foto</Text>
          </TouchableOpacity>
        </View>

        {/* FORM */}
        <View style={styles.form}>
          <InputField
            label="Nome completo"
            value={nome}
            onChangeText={setNome}
          />

          <InputField
            label="Usuário"
            value={user}
            onChangeText={setUser}
          />

          <InputField
            label="Email"
            value={email}
            editable={false}
          />

          <InputField
            label="Telefone"
            value={telefone}
            onChangeText={setTelefone}
            keyboardType="phone-pad"
          />

          <InputField
            label="Data de nascimento"
            value={data}
            onChangeText={setData}
            placeholder="dd/mm/aaaa"
            keyboardType="numeric"
          />
        </View>

      </View>

      <NavBar />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },

  content: {
    flex: 1,
  },

  avatarSection: {
    alignItems: "center",
    marginTop: 20,
  },

  avatar: {
    width: 100,
    height: 100,
    borderRadius: 50,
  },

  changePhoto: {
    color: "#3aaefc",
    marginTop: 8,
  },

  form: {
    alignItems: "center",
    marginTop: 20,
    gap: 10,
  },
});