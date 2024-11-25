const fs = require('fs');
const itemModel = '../../textures/item/';

fs.readdirSync(itemModel).filter(s => s.endsWith("_jam.png")).forEach(s => {
    let name = s.substring(0, s.lastIndexOf(".png"));
    fs.writeFileSync(name + ".json", JSON.stringify({"parent":"item/generated","textures":{"layer0":"foodcraft:item/" + name}}))
})
