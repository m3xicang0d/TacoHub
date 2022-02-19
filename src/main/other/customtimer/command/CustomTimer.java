package me.jesusmx.tacohub.customtimer.command;

import lombok.Data;

/**
 * @Author UKry
 * @Project TacoHub
 **/

@Data
public class CustomTimer {
    private final String id;
    private boolean activated = false;
    private boolean paused = false;
    private String text = "";
    private long reaming = Long.MAX_VALUE;
    private long expireAt = 0L;

    public void updateReaming() {
        reaming = reaming - 1L;
    }
}
