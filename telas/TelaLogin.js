import React from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Image } from 'react-native';

export default function TelaLogin({ navigation }) {
  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/logo.png')}
        style={styles.logo}
        resizeMode="contain"
      />
      
      <Text style={styles.title}>Bem-vindo de volta!</Text>

      <TextInput
        placeholder="Digite seu e-mail"
        style={styles.input}
        keyboardType="email-address"
        autoCapitalize="none"
      />
      
      <TextInput
        placeholder="Digite sua senha"
        style={styles.input}
        secureTextEntry
      />

      <TouchableOpacity style={styles.button} onPress={() => alert('Fazendo login...')}>
        <Text style={styles.buttonText}>Entrar</Text>
      </TouchableOpacity>

      <TouchableOpacity
  onPress={() => navigation.navigate('Cadastro')}
  style={styles.footer}
>
        <Text>
          Não tem uma conta? <Text style={{ color: 'blue', fontWeight: 'bold' }}>Cadastre-se agora.</Text>
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#dff3ff',
    alignItems: 'center',
    paddingTop: 80,
  },
  logo: {
    width: 200,
    height: 120,
    marginBottom: 20,
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 30,
    color: '#1e232c',
  },
  input: {
    width: '80%',
    backgroundColor: '#f8f8f8',
    padding: 15,
    borderRadius: 10,
    marginBottom: 15,
    borderWidth: 1,
    borderColor: '#e8ecf4',
  },
  button: {
    width: '80%',
    backgroundColor: '#1e232c',
    padding: 15,
    borderRadius: 10,
    alignItems: 'center',
    marginTop: 10,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  footer: {
    marginTop: 40,
  },
});