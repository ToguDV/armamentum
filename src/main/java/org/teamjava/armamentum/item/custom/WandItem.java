package org.teamjava.armamentum.item.custom;

import java.util.List;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Ejemplo de item con comportamiento propio: una varita que, al usarla
 * (clic derecho), da efectos al jugador y entra en cooldown.
 *
 * Este es el patrón para cualquier item "especial":
 *   - Extiende {@link Item} (o ToolItem, ArmorItem, etc.).
 *   - Sobrescribe el método que necesites (use, useOnBlock, appendTooltip...).
 *   - El item se registra igual que los demás, desde ModItems.
 */
public class WandItem extends Item {

    /** Duración de los efectos y del cooldown, en ticks (20 ticks = 1 segundo). */
    private static final int EFFECT_DURATION_TICKS = 20 * 10;
    private static final int COOLDOWN_TICKS = 20 * 15;

    public WandItem(Settings settings) {
        super(settings);
    }

    /**
     * Se llama cuando el jugador hace clic derecho con el item.
     *
     * IMPORTANTE: el mundo existe en cliente y servidor. Cualquier cosa que
     * cambie el estado del juego (daño, efectos, spawnear entidades) debe ir
     * SIEMPRE en el lado servidor ({@code !world.isClient()}).
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, EFFECT_DURATION_TICKS, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, EFFECT_DURATION_TICKS, 1));
            // Gasta 1 de durabilidad en la mano que la usa.
            stack.damage(1, user, hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }

        // Cooldown visual (la barra gris sobre el item).
        user.getItemCooldownManager().set(this, COOLDOWN_TICKS);

        return TypedActionResult.success(stack);
    }

    /**
     * Añade una línea extra al tooltip del item.
     * El texto en sí vive en el archivo de idioma (lang), nunca hardcodeado.
     */
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.armamentum.armamentum_wand.tooltip").formatted(Formatting.GRAY));
    }
}
