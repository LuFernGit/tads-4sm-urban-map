import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Image } from 'react-native';

export default function TelaCadastroSucesso({ navigation }) {
  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/sucesso.png')}
        style={styles.imagemSucesso}
        resizeMode="contain"
      />

      <Text style={styles.title}>Cadastro realizado</Text>
      <Text style={styles.subtitle}>
        Seu cadastro foi realizado com sucesso
      </Text>

      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Login')}
      >
        <Text style={styles.buttonText}>Login</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#dff3ff',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 25,
  },
  imagemSucesso: {
    width: 200,
    height: 200,
    marginBottom: 20,
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#1e232c',
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 16,
    color: '#444',
    textAlign: 'center',
    marginBottom: 30,
  },
  button: {
    width: '100%',
    backgroundColor: '#1e232c',
    padding: 16,
    borderRadius: 10,
    alignItems: 'center',
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 16,
  },
});