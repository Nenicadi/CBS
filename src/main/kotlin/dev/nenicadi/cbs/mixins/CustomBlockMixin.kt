package dev.nenicadi.cbs.mixins

import dev.nenicadi.cbs.utils.BlockManager.isCancelled
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.ChorusPlantBlock
import net.minecraft.world.level.block.NoteBlock
import net.minecraft.world.level.block.TripWireBlock
import net.minecraft.world.level.block.state.BlockState
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(value = [NoteBlock::class, TripWireBlock::class, ChorusPlantBlock::class])
abstract class CustomBlockMixin {

    @Inject(method = ["updateShape"], cancellable = true, at = [At(value = "RETURN")])
    fun onUpdateShape(
        state: BlockState,
        level: LevelReader,
        ticks: ScheduledTickAccess,
        pos: BlockPos,
        directionToNeighbour: Direction,
        neighbourPos: BlockPos,
        neighbourState: BlockState,
        random: RandomSource,
        cir: CallbackInfoReturnable<BlockState>
    ) { if (isCancelled(state)) cir.returnValue = state }

}