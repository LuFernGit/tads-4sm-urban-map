import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import 'react-native-gesture-handler';
import { GestureHandlerRootView } from 'react-native-gesture-handler';

import TelaCadastro from './telas/TelaCadastro';
import TelaCadastroSucesso from './telas/TelaCadastroSucesso';
import TelaInicial from './telas/TelaInicial';
import TelaLogin from './telas/TelaLogin';
import TelaPerfilAdmin from './telas/TelaPerfilAdmin';
import TelaPerfilAdminWeb from './telas/TelaPerfilAdminWeb';
import TelaPesquisa from './telas/TelaPesquisa';
import TelaPrincipal from './telas/TelaPrincipal';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <NavigationContainer>
        <Stack.Navigator screenOptions={{ headerShown: false }}>
          <Stack.Screen name="Inicial" component={TelaInicial} />
          <Stack.Screen name="Login" component={TelaLogin} />
          <Stack.Screen name="Cadastro" component={TelaCadastro} />
          <Stack.Screen
            name="CadastroSucesso"
            component={TelaCadastroSucesso}
          />

          {/* telas futuras */}
          <Stack.Screen name="PerfilAdmin" component={TelaPerfilAdmin} />
          <Stack.Screen
            name="PerfilAdminWeb"
            component={TelaPerfilAdminWeb}
          />
          <Stack.Screen name="Principal" component={TelaPrincipal} />
          <Stack.Screen name="Pesquisa" component={TelaPesquisa} />
        </Stack.Navigator>
      </NavigationContainer>
    </GestureHandlerRootView>
  );
}