import 'react-native-gesture-handler';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { GestureHandlerRootView } from 'react-native-gesture-handler';

import TelaInicial from './telas/TelaInicial';
import TelaLogin from './telas/TelaLogin';
import TelaCadastro from './telas/TelaCadastro';
import TelaCadastroSucesso from './telas/TelaCadastroSucesso';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <NavigationContainer>
        <Stack.Navigator screenOptions={{ headerShown: false }}>
          <Stack.Screen name="Inicial" component={TelaInicial} />
          <Stack.Screen name="Login" component={TelaLogin} />
          <Stack.Screen name="Cadastro" component={TelaCadastro} />
          <Stack.Screen name="CadastroSucesso" component={TelaCadastroSucesso} />
        </Stack.Navigator>
      </NavigationContainer>
    </GestureHandlerRootView>
  );
}