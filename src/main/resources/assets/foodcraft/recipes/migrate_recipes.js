import fs from 'fs';
import Path from 'path';

const dir = "pressure_cooking";

(async ()=>{
    await fs.promises.mkdir("C:\\Users\\nanam\\IdeaProjects\\FoodCraft-1.20\\src\\main\\resources\\data\\foodcraft\\recipes\\" + dir);

    for (const path of await fs.promises.readdir(dir)) {
        const file = JSON.parse((await fs.promises.readFile(Path.join(dir, path))).toString());
        // file.staples = file.staples.map((v) => {
        //     if (v.type === "forge:ore_dict") {
        //         return {
        //             tag: "forge:" + v.ore.toLowerCase()
        //         }
        //     } else {
        //         return v
        //     }
        // });
        if (file.fluidInput) file.fluidInput.fluid = "minecraft:" + file.fluidInput.fluid;


        file.ingredients = file.ingredients.map((v) => {
            if (v.type === "forge:ore_dict") {
                return {
                    tag: "forge:" + v.ore.toLowerCase()
                }
            } else {
                return v
            }
        });
        await fs.promises.writeFile("C:\\Users\\nanam\\IdeaProjects\\FoodCraft-1.20\\src\\main\\resources\\data\\foodcraft\\recipes\\" + dir + "\\" + path, JSON.stringify(file, null, 4));
    }
})();
