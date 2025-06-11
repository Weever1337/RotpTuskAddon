package com.doggys_tilt.rotp_t.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.texture.TextureManager;

public class SelectorActWidget extends Widget {
    final ActType icon;
    private boolean isSelected;
    public SelectorActWidget(ActType type, int x, int y) {
        super(x, y, 25, 25, type.getName());
        this.icon = type;
    }
    public void renderButton(MatrixStack matrixStack, int x, int y, float p_230431_4_) {
        Minecraft minecraft = Minecraft.getInstance();
        this.drawSlot(matrixStack, minecraft.getTextureManager());
        this.icon.drawIcon(icon, this.x+4, this.y+4);
        if (this.isSelected) {
            this.drawSelection(matrixStack, minecraft.getTextureManager());
        }

    }

    public boolean isHovered() {
        return super.isHovered() || this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        this.narrate();
    }

    private void drawSlot(MatrixStack matrixStack, TextureManager textureManager) {
        textureManager.bind(ChangeActScreen.UNIT_CHANGE_MENU);
        matrixStack.pushPose();
        matrixStack.translate(this.x, this.y, 0.0D);
        blit(matrixStack, 0, 0, 0.0F, 75.0F, 25, 25, 128, 128);
        matrixStack.popPose();
    }

    private void drawSelection(MatrixStack matrixStack, TextureManager textureManager) {
        textureManager.bind(ChangeActScreen.UNIT_CHANGE_MENU);
        matrixStack.pushPose();
        matrixStack.translate(this.x, this.y, 0.0D);
        blit(matrixStack, 0, 0, 25.0F, 75.0F, 25, 25, 128, 128);
        matrixStack.popPose();
    }
}
