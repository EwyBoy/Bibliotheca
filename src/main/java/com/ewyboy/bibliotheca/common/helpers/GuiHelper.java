package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/**
 * Created by EwyBoy
 */
public class GuiHelper {

    public static final ResourceLocation slotTexture = new ResourceLocation("bibliotheca", "textures/gui/slot.png");
    public static final ResourceLocation guiTexture = new ResourceLocation("bibliotheca", "textures/gui/gui.png");
    public static final ResourceLocation entryTexture = new ResourceLocation("bibliotheca", "textures/gui/entry.png");
    public static final ResourceLocation screenTexture = new ResourceLocation("bibliotheca", "textures/gui/screen.png");
    public static final ResourceLocation maskTexture = new ResourceLocation("bibliotheca", "textures/gui/mask.png");
    public static final ResourceLocation buttonTexture = new ResourceLocation("bibliotheca", "textures/gui/button.png");

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void renderSlots(int x, int y) {
        MINECRAFT.textureManager.bindTexture(slotTexture);

        int realSize = 18;
        double u = 1;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();

        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x, y + realSize, 0).tex(0, u).endVertex();
        worldRenderer.pos(x + realSize, y + realSize, 0).tex(u, u).endVertex();
        worldRenderer.pos(x + realSize, y, 0).tex(u, 0).endVertex();
        worldRenderer.pos(x, y, 0).tex(0, 0).endVertex();
        tessellator.draw();
    }

    public static void renderGuiBackground(int x, int y, int width, int height, ResourceLocation texture) {
        renderBackground(x, y, width - 14, height - 15, texture);
    }

    public static void renderBackground(int x, int y, int width, int height, ResourceLocation texture) {
        MINECRAFT.textureManager.bindTexture(texture);

        int realWidth = (width) + 14;
        int realHeight = 15 + (height);
        int by = y + (realHeight - 7);

        renderPartBackground(x, y, 0, 0, 7, 7, 7, 7);
        renderPartBackground(x + 7, y, 8, 0, 8, 7, (width), 7);
        renderPartBackground(x + 7 + (width), y, 9, 0, 15, 7, 7, 7);

        renderPartBackground(x, by, 0, 8, 7, 15, 7, 7);
        renderPartBackground(x + 7, by, 8, 8, 7, 15, (width), 7);
        renderPartBackground(x + 7 + (width), by, 9, 8, 15, 15, 7, 7);

        renderPartBackground(x, y + 7, 0, 7, 7, 7, 7, (realHeight - 14));
        renderPartBackground(x + realWidth - 8, y + 7, 8, 7, 15, 7, 8, (realHeight - 14));

        renderPartBackground(x + 7, y + 7, 8, 8, 8, 8, (width), realHeight - 14);
    }

    private static void renderPartBackground(int x, int y, int startX, int startY, int endX, int endY, int width, int height) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);

        double chestTextureSize = 15d;
        worldRenderer.pos((double) x, (double) y + height, 0).tex(getEnd(chestTextureSize, startX), getEnd(chestTextureSize, endY)).endVertex();
        worldRenderer.pos((double) x + width, (double) y + height, 0).tex(getEnd(chestTextureSize, endX), getEnd(chestTextureSize, endY)).endVertex();
        worldRenderer.pos((double) x + width, (double) y + 0, 0).tex(getEnd(chestTextureSize, endX), getEnd(chestTextureSize, startY)).endVertex();
        worldRenderer.pos((double) x, (double) y, 0).tex(getEnd(chestTextureSize, startX), getEnd(chestTextureSize, startY)).endVertex();

        tessellator.draw();
    }

    private static double getEnd(double width, double other) {
        return (1D / width) * other;
    }

}
