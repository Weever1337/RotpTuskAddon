package com.doggys_tilt.rotp_t.client.render.wormhole;

import com.doggys_tilt.rotp_t.capability.TuskCapabilityProvider;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.doggys_tilt.rotp_t.entity.WormholeArmEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class WormholeWithArmModel extends EntityModel<WormholeArmEntity> {
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer wormhole;

    public WormholeWithArmModel() {
        texWidth = 128;
        texHeight = 128;

        wormhole = new ModelRenderer(this);
        wormhole.setPos(0, -1F, 0);
        wormhole.texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 0.0F, 32.0F, 0.0F, false);

        texWidth = 64;
        texHeight = 64;

        leftArm = new ModelRenderer(this);
        leftArm.setPos(0.0F, -1F, 0.0F);
        leftArm.texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        leftArm.texOffs(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(0.0F, -1F, 0.0F);
        rightArm.texOffs(40, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
        rightArm.texOffs(40, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
    }

    @Override
    public void setupAnim(WormholeArmEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getOwner() != null){
            entity.getCapability(TuskCapabilityProvider.CAPABILITY).ifPresent(nailCapability -> {
                switch (entity.getOwner().getMainArm()) {
                    case LEFT:
                        if (nailCapability.getNailCount() > 5) {
                            rightArm.visible = false;
                            leftArm.visible = true;
                        } else if (nailCapability.getNailCount() > 0) {
                            rightArm.visible = true;
                            leftArm.visible = false;
                        }
                        break;
                    case RIGHT:
                        if (nailCapability.getNailCount() > 5) {
                            rightArm.visible = false;
                            leftArm.visible = true;
                        } else if (nailCapability.getNailCount() > 0) {
                            leftArm.visible = false;
                            rightArm.visible = true;
                        }
                        break;
                }
            });
        }
        wormhole.yRot = ageInTicks / 4;
        leftArm.xRot = (headPitch - 90) * ((float)Math.PI / 180F) ;
        leftArm.yRot = netHeadYaw * ((float)Math.PI / 180F);
        rightArm.xRot = (headPitch - 90) * ((float)Math.PI / 180F) ;
        rightArm.yRot = netHeadYaw * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderWormholeToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        wormhole.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
