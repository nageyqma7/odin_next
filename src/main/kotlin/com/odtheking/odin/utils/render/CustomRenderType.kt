package com.odtheking.odin.utils.render

import net.minecraft.client.renderer.rendertype.LayeringTransform
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType

object CustomRenderType {

    // 26.3: OutputTarget / RenderSetupBuilder.setOutputTarget() were removed together with the old
    // blaze3d render target plumbing (targets are now owned by the frame graph / LevelTargetBundle),
    // so the ITEM_ENTITY_TARGET output target that 26.2 set here has no 26.3 equivalent.

    // RenderTypes.LINES, RenderTypes.LINES_TRANSLUCENT || LINES_ESP, LINES_TRANSLUCENT_ESP

    val LINES_ESP: RenderType = RenderType.create(
        "lines-esp",
        RenderSetup.builder(CustomRenderPipelines.LINES_ESP)
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .createRenderSetup()
    )

    val LINES_TRANSLUCENT_DEPTH: RenderType = RenderType.create(
        "lines-translucent-depth",
        RenderSetup.builder(CustomRenderPipelines.LINES_TRANSLUCENT_DEPTH)
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .createRenderSetup()
    )

    val LINES_TRANSLUCENT_ESP: RenderType = RenderType.create(
        "lines-translucent-esp",
        RenderSetup.builder(CustomRenderPipelines.LINES_TRANSLUCENT_ESP)
            .createRenderSetup()
    )

    // RenderTypes.DEBUG_FILLED_BOX / RenderTypes.debugFilledBox() || QUADS_OPAQUE / QUADS_TRANSLUCENT / QUADS_ESP / QUADS_TRANSLUCENT_ESP

    val QUADS_OPAQUE: RenderType = RenderType.create(
        "quads-opaque",
        RenderSetup.builder(CustomRenderPipelines.QUADS_OPAQUE)
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .sortOnUpload()
            .createRenderSetup()
    )

    val QUADS_TRANSLUCENT: RenderType = RenderType.create(
        "quads-translucent",
        RenderSetup.builder(CustomRenderPipelines.QUADS_TRANSLUCENT)
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .sortOnUpload()
            .createRenderSetup()
    )

    val QUADS_ESP: RenderType = RenderType.create(
        "quads-esp",
        RenderSetup.builder(CustomRenderPipelines.QUADS_ESP)
            .sortOnUpload()
            .createRenderSetup()
    )

    val QUADS_TRANSLUCENT_ESP: RenderType = RenderType.create(
        "quads-translucent-esp",
        RenderSetup.builder(CustomRenderPipelines.QUADS_TRANSLUCENT_ESP)
            .sortOnUpload()
            .createRenderSetup()
    )
}