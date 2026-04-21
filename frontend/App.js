import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import "react-native-gesture-handler";
import { GestureHandlerRootView } from "react-native-gesture-handler";

import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";
import { StatusBar } from "expo-status-bar";

import TelaCadastro from './src/screens/mobileUser/TelaCadastro'
import TelaCadastroSucesso from './src/screens/mobileUser/TelaCadastroSucesso';
import TelaInicial from './src/screens/mobileUser/TelaInicial';
import TelaLogin from './src/screens/mobileUser/TelaLogin';
import TelaPrincipal from "./src/screens/mobileUser/TelaPrincipal";

import TelaEditarUsuario from "./src/screens/mobileUser/TelaEditarUsuario";
import TelaFiltro from "./src/screens/mobileUser/TelaFiltro";
import TelaLocaisCurtidos from "./src/screens/mobileUser/TelaLocaisCurtidos";
import TelaLocaisSalvos from "./src/screens/mobileUser/TelaLocaisSalvos";
import TelaPerfilUsuario from "./src/screens/mobileUser/TelaPerfilUsuario";

import TelaPerfilAdmin from "./src/screens/mobileAdm/TelaPerfilAdmin";

import TelaPerfilAdminWeb from "./src/screens/webAdm/TelaPerfilAdminWeb";

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <SafeAreaProvider>

      <GestureHandlerRootView style={{ flex: 1 }}>

<SafeAreaView style={{ flex: 1, backgroundColor: '#fff' }}>
          <StatusBar style="dark" />

          <NavigationContainer>
            <Stack.Navigator
              initialRouteName="Principal"
              screenOptions={{ headerShown: false }}
            >
              <Stack.Screen name="Principal" component={TelaPrincipal} />
              <Stack.Screen name="Inicial" component={TelaInicial} />
              <Stack.Screen name="Login" component={TelaLogin} />
              <Stack.Screen name="Cadastro" component={TelaCadastro} />
              <Stack.Screen name="CadastroSucesso" component={TelaCadastroSucesso} />
              <Stack.Screen name="Filtro" component={TelaFiltro} />
              <Stack.Screen name="PerfilUsuario" component={TelaPerfilUsuario} />
              <Stack.Screen name="EditarUsuario" component={TelaEditarUsuario} />
              <Stack.Screen name="LocaisCurtidos" component={TelaLocaisCurtidos} />              
              <Stack.Screen name="LocaisSalvos" component={TelaLocaisSalvos} />
              <Stack.Screen name="PerfilAdmin" component={TelaPerfilAdmin} />
              <Stack.Screen name="PerfilAdminWeb" component={TelaPerfilAdminWeb} />
            </Stack.Navigator>
          </NavigationContainer>

        </SafeAreaView>

      </GestureHandlerRootView>

    </SafeAreaProvider>
  );
}