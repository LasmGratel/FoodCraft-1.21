const data = "data/foodcraft";

import * as fs from 'fs';
import * as path from 'path';

const fruits = ["CORN", "CUCUMBER", "EGGPLANT", "FACING_HEAVEN_PEPPER", "GREEN_PEPPER", "PEANUT", "RICE", "STICKY_RICE", "SWEET_POTATO", "TOMATO", "WHITE_RADISH", "CABBAGE", "STRAWBERRY", "MUNG_BEAN", "SOYBEAN", "ADZUKI_BEAN"];

for (let fruit of fruits) {
    fruit = fruit.toLowerCase();
}

function replaceOreDict(value) {
    if (value.ore) {
        value.type = undefined;
        value.tag = value.ore;
        value.ore = undefined;
    } else if (value.item) {
        value.item = value.item.replace('foodcraftreloaded', 'foodcraft');
    }
    return value;
}

for (const file of fs.readdirSync(path.join(data, 'recipe'))) {
    if (fs.lstatSync(path.join(data, 'recipe', file)).isDirectory()) continue;
    let recipe = JSON.parse(fs.readFileSync(path.join(data, 'recipe', file)));
    if (recipe.type == "foodcraft:blasting") {
        recipe.type = "minecraft:smelting";
        recipe.result = {
            id: recipe.result.replace('foodcraftreloaded', 'foodcraft')
        };
    }
    if (recipe.ingredients) {
        recipe.ingredients = recipe.ingredients.map((value, i, arr) => 
            replaceOreDict(value)
        );
    } else if (recipe.key) {
        for (const key of Object.keys(recipe.key)) {
            replaceOreDict(recipe.key[key]);
        }
    }
    fs.writeFileSync(path.join(data, 'recipe', file), JSON.stringify(recipe, null, 2));
}
