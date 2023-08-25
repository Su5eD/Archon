package safro.archon.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import safro.archon.Archon;
import safro.archon.util.ArchonUtil;

public class ManaHudRenderer {
    private static final Identifier MANA_TEXTURE = new Identifier(Archon.MODID, "textures/gui/mana_icon.png");

    public static void init() {
        HudRenderCallback.EVENT.register(ManaHudRenderer::render);
    }

    private static void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.player.isAlive() && client.getCameraEntity() instanceof PlayerEntity player) {
            if (Archon.CONFIG.displayManaWithItem) {
                if (ArchonUtil.isValidManaItem(player.getStackInHand(Hand.MAIN_HAND)) || ArchonUtil.isValidManaItem(player.getStackInHand(Hand.OFF_HAND))) {
                    renderManaHud(client, context, player);
                }
            } else {
                renderManaHud(client, context, player);
            }
        }
    }

    private static void renderManaHud(MinecraftClient client, DrawContext context, PlayerEntity player) {
        int xoffset = Archon.CONFIG.mana_xoffset;
        int yoffset = Archon.CONFIG.mana_yoffset;

        int a = context.getScaledWindowWidth() / 2;
        int k = context.getScaledWindowHeight() - (yoffset + 5);
        context.drawTexture(MANA_TEXTURE, a - (xoffset + 20), k, 0, 0, 16, 16, 16, 16);
        client.getProfiler().pop();

        if (ArchonUtil.get(player).getMana() <= ArchonUtil.get(player).getMaxMana()) {
            String string = ArchonUtil.get(player).getMana() + "/" + ArchonUtil.get(player).getMaxMana();
            int n = context.getScaledWindowHeight() - yoffset;
            context.drawText(client.textRenderer, string, a - xoffset, n, 16777215, true);
            client.getProfiler().pop();
        }
    }
}
