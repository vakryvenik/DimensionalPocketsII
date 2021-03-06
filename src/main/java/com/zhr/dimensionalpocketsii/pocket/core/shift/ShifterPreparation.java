package com.zhr.dimensionalpocketsii.pocket.core.shift;

import com.zhr.dimensionalpocketsii.pocket.core.Pocket;

import net.minecraft.entity.player.PlayerEntity;

public class ShifterPreparation {
	public enum Direction {
		INTO_POCKET, OUT_OF_POCKET
	}

	private int ticksBeforeShift;
	private final PlayerEntity player;
	private final Pocket pocket;
	private final Direction direction;

	public ShifterPreparation(PlayerEntity player, int ticksBeforeShift, Pocket pocket, Direction direction) {
		this.player = player;
		this.ticksBeforeShift = ticksBeforeShift;
		this.pocket = pocket;
		this.direction = direction;
	}

	public boolean doPrepareTick() {
		if (ticksBeforeShift < 0)
			return true;

		ticksBeforeShift--;

		if (ticksBeforeShift <= 0) {
			if (direction == Direction.INTO_POCKET) {
				pocket.shiftTo(player);
			} else {
				pocket.shiftFrom(player);
			}
			return true;
		}
		return false;
	}
}
