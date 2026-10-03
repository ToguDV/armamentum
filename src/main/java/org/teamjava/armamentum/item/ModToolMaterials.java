package org.teamjava.armamentum.item;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.recipe.Ingredient;

/**
 * Materiales de herramienta propios.
 *
 * En 1.21.1 un ToolMaterial NO se registra: es una simple interfaz que implementas
 * (aquí con un enum, igual que hace el propio Minecraft con ToolMaterials).
 * La usan SwordItem / PickaxeItem / AxeItem... y de aquí sacan durabilidad,
 * velocidad, daño, encantabilidad y con qué se repara.
 */
public enum ModToolMaterials implements ToolMaterial {

    // (durabilidad, velocidadMina, dañoAtaque, encantabilidad, tagBloquesIncorrectos)
    //
    // Durabilidad de referencia vanilla: hierro 250, diamante 1561, netherite 2031.
    // Velocidad: hierro 6.0, diamante 8.0, netherite 9.0.
    // Daño: hierro 2.0, diamante 3.0, netherite 4.0.
    ARMAMENTUM(1800, 9.5F, 4.5F, 18, BlockTags.INCORRECT_FOR_NETHERITE_TOOL);

    private final int durability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int enchantability;
    private final TagKey<Block> inverseTag;

    ModToolMaterials(int durability, float miningSpeedMultiplier, float attackDamage,
                     int enchantability, TagKey<Block> inverseTag) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.inverseTag = inverseTag;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return inverseTag;
    }

    /**
     * Con qué se repara en el yunque / al gastar en crafteo.
     * Se calcula en el momento (no en un campo) para no depender del orden de
     * inicialización entre esta clase y ModItems.
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.ARMAMENTUM_INGOT);
    }
}
