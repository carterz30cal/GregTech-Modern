package com.gregtechceu.gtceu.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.addon.AddonFinder;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.recipe.*;
import net.minecraftforge.fml.ModLoader;

/**
 * @author KilaBash
 * @date 2023/2/21
 * @implNote GTRecipeConditions
 */
public final class GTRecipeConditions {

    private GTRecipeConditions() {}

    public static void init() {
        GTRegistries.RECIPE_CONDITIONS.unfreeze();

        GTRegistries.RECIPE_CONDITIONS.register(BiomeCondition.INSTANCE.getType(), BiomeCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(DimensionCondition.INSTANCE.getType(), DimensionCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(PositionYCondition.INSTANCE.getType(), PositionYCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(RainingCondition.INSTANCE.getType(), RainingCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(RockBreakerCondition.INSTANCE.getType(), RockBreakerCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(ThunderCondition.INSTANCE.getType(), ThunderCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(VentCondition.INSTANCE.getType(), VentCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(CleanroomCondition.INSTANCE.getType(), CleanroomCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(ResearchCondition.INSTANCE.getType(), ResearchCondition.class);
        if (GTCEu.isCreateLoaded()) {
            GTRegistries.RECIPE_CONDITIONS.register(RPMCondition.INSTANCE.getType(), RPMCondition.class);
        }

        AddonFinder.getAddons().forEach(IGTAddon::registerRecipeConditions);
        ModLoader.get().postEvent(new GTCEuAPI.RegisterEvent<>(GTRegistries.RECIPE_CONDITIONS, (Class<Class<? extends RecipeCondition>>) RecipeCondition.class.getClass()));
        GTRegistries.RECIPE_CONDITIONS.freeze();
    }
}
