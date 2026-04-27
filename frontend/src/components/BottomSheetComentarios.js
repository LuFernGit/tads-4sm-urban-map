import { useMemo, useContext } from "react";
import { View } from "react-native";
import BottomSheet from "@gorhom/bottom-sheet";
import { ThemeContext } from "../context/ThemeContext";

export default function BottomSheetComentarios({ bottomSheetRef }) {
  const snapPoints = useMemo(() => ["90%"], []);

  const { colors } = useContext(ThemeContext);

  return (
    <BottomSheet
      ref={bottomSheetRef}
      index={0}
      snapPoints={snapPoints}
      enablePanDownToClose
      backgroundStyle={{
        backgroundColor: colors.card,
      }}
      handleIndicatorStyle={{
        backgroundColor: colors.text,
      }}
      style={{
        position: "absolute",
        top: 0,
        left: 0,
        right: 0,
        elevation: 999,
        zIndex: 999,
      }}
    >
      <View
        style={{
          flex: 1,
          backgroundColor: colors.background,
        }}
      />
    </BottomSheet>
  );
}