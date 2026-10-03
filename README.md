# Armamentum

Mod de ejemplo de **Fabric** (Minecraft **1.21.1**, Yarn, Java 21) para aprender
**dónde va cada cosa** al añadir items custom.

Items de ejemplo: `armamentum_ingot` (simple), `armamentum_sword` /
`armamentum_pickaxe` (herramientas con material propio), `armamentum_apple`
(comida con efectos) y `armamentum_wand` (item con lógica propia).

## Dónde va cada cosa

```
src/main/                        # común: servidor + cliente
├── java/org/teamjava/armamentum/
│   ├── Armamentum.java          # entrypoint
│   └── item/
│       ├── ModItems.java        # registro de items
│       ├── ModItemGroups.java   # pestaña del creativo
│       ├── ModToolMaterials.java
│       └── custom/WandItem.java # item con comportamiento
└── resources/
    ├── fabric.mod.json          # id, entrypoints, mixins
    ├── assets/armamentum/       # lo que DIBUJA el cliente
    │   ├── lang/*.json
    │   ├── models/item/*.json
    │   └── textures/item/*.png
    └── data/armamentum/recipe/  # recetas (servidor)

src/client/                      # solo cliente
```

| Quiero... | Va en... |
|-----------|----------|
| Registrar un item | `item/ModItems.java` |
| Darle comportamiento | `item/custom/MiItem.java` (subclase) |
| Nombre | `assets/armamentum/lang/*.json` |
| Textura | `assets/armamentum/textures/item/mi_item.png` |
| Modelo | `assets/armamentum/models/item/mi_item.json` |
| Meterlo en la pestaña | `item/ModItemGroups.java` |
| Receta | `data/armamentum/recipe/mi_receta.json` |

## Añadir un item (checklist)

1. Registrar en `ModItems.java`:
   ```java
   public static final Item MI_ITEM = register("mi_item", new Item(new Item.Settings()));
   ```
2. Nombre en `lang/en_us.json` y `es_es.json`: `"item.armamentum.mi_item": "Mi Item"`.
3. Modelo `models/item/mi_item.json` (`item/generated` o `item/handheld`).
4. Textura 16x16 `textures/item/mi_item.png` (mismo nombre que el `layer0`).
5. Añadirlo en `ModItemGroups.java`: `entries.add(ModItems.MI_ITEM);`.

## Cosas clave de 1.21.1

- Decimos **receta** (no "crafteo"): una receta cubre también horno,
  cortapiedras, etc., no solo la mesa de crafteo.
- Carpeta de recetas: **`recipe/`** (singular).
- Resultado de la receta usa **`"id"`**, no `"item"`.
- Todo recurso usa el namespace **`armamentum`** (= `MOD_ID`).

## Ejecutar

```bash
./gradlew runClient     # abre el juego con el mod
./gradlew build         # genera build/libs/armamentum-1.0-SNAPSHOT.jar
```

En creativo, pestaña **Armamentum**. Las texturas actuales son placeholders.
