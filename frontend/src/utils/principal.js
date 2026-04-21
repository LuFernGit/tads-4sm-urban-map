import { Linking } from 'react-native';

export const abrirMapa = (local) => {
  const url = `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(local)}`;
  Linking.openURL(url);
};