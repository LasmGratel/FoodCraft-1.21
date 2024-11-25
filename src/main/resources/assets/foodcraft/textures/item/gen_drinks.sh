#!/bin/bash

# 定义颜色和文件名的数组
colors=(
  "#6f2da8" "#e5db3b" "#f6edd0" "#f6f3c9" "#ffafaf" "#f6ae24"
  "#ffd986" "#fcf393" "#ece382" "#eb8c30" "#f18a25" "#ce7031"
  "#ea7b0e" "#f46c30" "#b57c63" "#fd6d0d" "#fcf4d6" "#fc5a8d" "#f7eb6a")
colors3=("#c300ff" "#06ad1a" "#e2ffe6" "#027714" "#ff2121" "#eeeedd"
  "#ffffdd" "#ffff99" "#ffa265")
colors2=("#ffffff" "#956844" "#ae927c" "#f4872f" "#fa2059" "#e0da42"
  "#e7e480" "#fafaf2" "#512008" "#ed7f1e" "#ededed" "#e848b9"
  "#98a285" "#c29833" "#50462d" "#ffffff"
)

names=(
  "grape" "pear" "litchi" "longan" "peach" "orange"
  "mango" "lemon" "grapefruit" "persimmon" "papaya" "loquat"
  "hawthorn" "pomegranate" "date" "cherry" "coconut" "strawberry" "banana")
names3=("eggplant" "cucumber" "cabbage" "spinach" "tomato" "rice"
  "sticky_rice" "corn" "sweet_potato")
names2=("soymilk" "hot_chocolate" "chocolate_milk" "carrot_juice" "apple_juice" "golden_grape_juice"
  "golden_apple" "soybean_milk" "cola" "fanta" "sprite" "melon"
  "tea" "milk_tea" "coffee" "sugar_with_water"
)


# 检查数组长度是否一致
if [ ${#colors[@]} -ne ${#names[@]} ]; then
  echo "Error: colors and names arrays must have the same length!"
  exit 1
fi

# 遍历数组并执行命令
for i in "${!colors[@]}"; do
  color="${colors[i]}"
  name="${names[i]}"

  echo "Processing $name with color $color..."

  magick jam_overlay.png \
    \( -clone 0 -fill "$color" -colorize 100 \) \
    -compose multiply -composite jam.png \
    -compose overlay -composite "${name}_jam.png"

  if [ $? -ne 0 ]; then
    echo "Error processing $name with color $color."
    exit 1
  fi
done



echo "All files processed successfully!"
