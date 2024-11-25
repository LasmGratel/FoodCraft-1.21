package dev.lasm.foodcraft.item;

import dev.lasm.foodcraft.api.Colorable;
import net.minecraft.world.item.Item;

public class ColorableItem extends Item implements Colorable {
    private final int color;

    public ColorableItem(Properties properties, int color) {
        super(properties);
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }
}
