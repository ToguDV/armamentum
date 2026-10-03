package org.teamjava.armamentum.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.teamjava.armamentum.Armamentum;
import org.teamjava.armamentum.item.custom.WandItem;

/**
 * Aquí se declaran y registran TODOS los items del mod.
 *
 * Patrón de cada item:
 *   1. Un campo {@code public static final Item NOMBRE = register("id", new Item(...));}
 *   2. El id es el nombre que verás en las texturas, modelos, lang, recetas...
 *
 * Convención de nombres:
 *   - Java: MAYÚSCULAS (ARMAMENTUM_INGOT)
 *   - id / recursos: snake_case (armamentum_ingot)
 */
public final class ModItems {

    // No se puede instanciar esta clase: solo agrupa constantes y métodos estáticos.
    private ModItems() {
    }

    // ---------------------------------------------------------------------
    // 1) ITEM SIMPLE
    // Un item que solo existe (material de crafteo). No tiene comportamiento.
    // Recursos necesarios:
    //   assets/armamentum/models/item/armamentum_ingot.json
    //   assets/armamentum/textures/item/armamentum_ingot.png
    //   lang: item.armamentum.armamentum_ingot
    // ---------------------------------------------------------------------
    public static final Item ARMAMENTUM_INGOT = register("armamentum_ingot",
            new Item(new Item.Settings()));

    // ---------------------------------------------------------------------
    // 2) ESPADA (usa un material de herramienta propio)
    // ToolItem ya aplica automáticamente la durabilidad del material.
    // ---------------------------------------------------------------------
    public static final Item ARMAMENTUM_SWORD = register("armamentum_sword",
            new SwordItem(ModToolMaterials.ARMAMENTUM, new Item.Settings()));

    // ---------------------------------------------------------------------
    // 3) PICOTA (mismo material, otra herramienta)
    // ---------------------------------------------------------------------
    public static final Item ARMAMENTUM_PICKAXE = register("armamentum_pickaxe",
            new PickaxeItem(ModToolMaterials.ARMAMENTUM, new Item.Settings()));

    // ---------------------------------------------------------------------
    // 4) COMIDA
    // FoodComponent define nutrición, saturación y efectos al comer.
    // ---------------------------------------------------------------------
    public static final Item ARMAMENTUM_APPLE = register("armamentum_apple",
            new Item(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .nutrition(6)              // medios muslos de comida
                            .saturationModifier(1.2F)  // saturación
                            // efecto, probabilidad (1.0F = siempre)
                            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
                            .build())
                    .rarity(Rarity.RARE)));

    // ---------------------------------------------------------------------
    // 5) ITEM CON LÓGICA PROPIA (subclase de Item)
    // El comportamiento vive en item/custom/WandItem.java.
    // ---------------------------------------------------------------------
    public static final Item ARMAMENTUM_WAND = register("armamentum_wand",
            new WandItem(new Item.Settings()
                    .maxDamage(250)
                    .rarity(Rarity.EPIC)));

    /**
     * Registra un item en el registro de Minecraft.
     *
     * @param name id sin namespace (p. ej. "armamentum_ingot")
     * @param item instancia ya construida
     * @return el mismo item, para poder asignarlo al campo
     */
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Armamentum.MOD_ID, name), item);
    }

    /**
     * Fuerza la carga de esta clase (y por tanto la inicialización de los campos).
     * Se llama desde {@link Armamentum#onInitialize()}.
     */
    public static void register() {
        Armamentum.LOGGER.info("[Armamentum] Registrando items...");
    }
}
