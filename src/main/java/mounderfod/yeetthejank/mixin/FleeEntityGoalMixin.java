package mounderfod.yeetthejank.mixin;

import com.mojang.datafixers.optics.Wander;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FleeEntityGoal.class)
abstract public class FleeEntityGoalMixin<T extends LivingEntity> extends Goal {
    @Shadow protected T targetEntity;
    @Shadow @Final protected Class<ZombieEntity> classToFleeFrom;
    @Shadow @Final protected PathAwareEntity mob;

    @Inject(method="start()V", at = @At(value = "HEAD"), cancellable = true)
    public void fixTrader(CallbackInfo ci) {
        if(this.mob instanceof WanderingTraderEntity && this.classToFleeFrom == ZombieEntity.class && this.targetEntity instanceof ZombifiedPiglinEntity) {
            ci.cancel();
        }
    }
}
