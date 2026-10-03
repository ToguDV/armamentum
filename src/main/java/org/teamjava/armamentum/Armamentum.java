package org.teamjava.armamentum;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teamjava.armamentum.item.ModItemGroups;
import org.teamjava.armamentum.item.ModItems;

/**
 * Punto de entrada del mod (lado común: servidor + cliente).
 *
 * Aquí SOLO se llama a las clases que registran cosas (items, bloques, grupos...).
 * La lógica concreta de cada cosa vive en su propia clase, dentro del paquete que
 * le corresponda. Así el entrypoint no se convierte en un archivo gigante.
 */
public class Armamentum implements ModInitializer {

    /** Identificador del mod. Debe coincidir con el "id" de fabric.mod.json. */
    public static final String MOD_ID = "armamentum";

    /** Logger compartido: úsalo con Armamentum.LOGGER.info("..."). */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Registro de contenido. El orden importa poco, pero es buena costumbre
        // registrar primero los items y después los grupos que los agrupan.
        ModItems.register();
        ModItemGroups.register();

        LOGGER.info("[Armamentum] Mod inicializado. Items y grupos registrados.");
    }
}
