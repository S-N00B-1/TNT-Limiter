package io.github.sn00b1.tntLimiter.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.sn00b1.tntLimiter.config.TntLimiterConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntBlock.class)
public class TntBlockMixin {
    /*
     * This mixin is taken from [Legacy4J](https://github.com/Wilyicaro/Legacy-Minecraft/blob/main/src/main/java/wily/legacy/mixin/base/TntBlockMixin.java)
     */

    @Unique
    private static final AABB tntDetectBounding = new AABB(-TntLimiterConfig.DetectionRadius,-TntLimiterConfig.DetectionRadius,-TntLimiterConfig.DetectionRadius,
                                                           TntLimiterConfig.DetectionRadius, TntLimiterConfig.DetectionRadius, TntLimiterConfig.DetectionRadius);

    @ModifyExpressionValue(method = "prime(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private static boolean explode(boolean original, Level level, BlockPos pos) {
        return original && level instanceof ServerLevel serverLevel && (TntLimiterConfig.TntLimit == 0 || level.getEntitiesOfClass(PrimedTnt.class,tntDetectBounding.move(pos)).size() < TntLimiterConfig.TntLimit);
    }
    @Inject(method = "wasExploded", at = @At("HEAD"), cancellable = true)
    private void wasExploded(ServerLevel level, BlockPos blockPos, Explosion explosion, CallbackInfo ci) {
        if (TntLimiterConfig.TntLimit > 0 && level.getEntitiesOfClass(PrimedTnt.class,tntDetectBounding.move(blockPos)).size() >= TntLimiterConfig.TntLimit) ci.cancel();
    }
}