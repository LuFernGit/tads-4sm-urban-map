import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import "react-native-gesture-handler";
import { GestureHandlerRootView } from "react-native-gesture-handler";

import TelaEditarUsuario from "./telas/TelaEditarUsuario";
import TelaFiltro from "./telas/TelaFiltro";
import TelaLocaisCurtidos from "./telas/TelaLocaisCurtidos";
import TelaPerfilUsuario from "./telas/TelaPerfilUsuario";
import TelaPesquisa from "./telas/TelaPesquisa";
import TelaPrincipal from "./telas/TelaPrincipal";

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <NavigationContainer>
        <Stack.Navigator
          initialRouteName="LocaisCurtidos"
          screenOptions={{ headerShown: false }}
        >
          <Stack.Screen name="Principal" component={TelaPrincipal} />
          <Stack.Screen name="Pesquisa" component={TelaPesquisa} />
          <Stack.Screen name="Filtro" component={TelaFiltro} />
          <Stack.Screen name="PerfilUsuario" component={TelaPerfilUsuario} />
          <Stack.Screen name="EditarUsuario" component={TelaEditarUsuario} />
          <Stack.Screen name="LocaisCurtidos" component={TelaLocaisCurtidos} />
        </Stack.Navigator>
      </NavigationContainer>
    </GestureHandlerRootView>
  );
}
