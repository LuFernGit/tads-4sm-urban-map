export function getAdjustedFontSize(baseSize, level = "medium") {
  const adjustment = {
    small: -4,
    medium: 0,
    large: 4,
  };

  return baseSize + (adjustment[level] ?? 0);
}

