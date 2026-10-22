package dev.nenicadi.cbs.mixins

import dev.nenicadi.cbs.utils.BlockManager.isCancelled
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.NoteBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(value = [NoteBlock::class])
abstract class NoteBlockInteractionMixin {

    @Inject(method = ["useWithoutItem"], cancellable = true, at = [At(value = "RETURN")])
    fun onUse(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult,
        cir: CallbackInfoReturnable<InteractionResult>
    ) { if (level.isClientSide && isCancelled(state))
        cir.returnValue = InteractionResult.SUCCESS_SERVER
    }

}