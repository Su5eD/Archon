package safro.archon.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SturdyStatusEffect extends StatusEffect {

    public SturdyStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xA06A41);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
