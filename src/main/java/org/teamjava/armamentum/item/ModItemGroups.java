package org.teamjava.armamentum.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.teamjava.armamentum.Armamentum;

/**
 * Grupo (pestaña) propio en el inventario creativo.
 *
 * Sin esto, los items solo aparecen en la pestaña de búsqueda. Con esto tienen
 * su propia pestaña con el icono que elijas.
 *
 * El nombre visible se define en el lang:
 *   itemGroup.armamentum.armamentum
 */
public final class ModItemGroups {

    private ModItemGroups() {
    }

    /** Clave con la que se registra el grupo en el registro ITEM_GROUP. */
    public static final RegistryKey<ItemGroup> ARMAMENTUM_GROUP_KEY =
            RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Armamentum.MOD_ID, "armamentum"));

    /** El grupo en sí. Se construye y registra en la inicialización de la clase. */
    public static final ItemGroup ARMAMENTUM_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            ARMAMENTUM_GROUP_KEY,
            FabricItemGroup.builder()
                    // Nombre de la pestaña (clave de traducción).
                    .displayName(Text.translatable("itemGroup.armamentum.armamentum"))
                    // Icono de la pestaña.
                    .icon(() -> new ItemStack(ModItems.ARMAMENTUM_SWORD))
                    // Qué items aparecen y en qué orden.
                    .entries((context, entries) -> {
                        entries.add(ModItems.ARMAMENTUM_INGOT);
                        entries.add(ModItems.ARMAMENTUM_SWORD);
                        entries.add(ModItems.ARMAMENTUM_PICKAXE);
                        entries.add(ModItems.ARMAMENTUM_APPLE);
                        entries.add(ModItems.ARMAMENTUM_WAND);
                    })
                    .build());

    /** Fuerza la carga de la clase (y con ella el registro de arriba). */
    public static void register() {
        Armamentum.LOGGER.info("[Armamentum] Registrando grupo creativo...");
    }
}
