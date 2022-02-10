package me.jesusmx.tacohub.customtimer;

import lombok.Data;
import me.jesusmx.tacohub.customtimer.command.CustomTimer;
import me.jesusmx.tacohub.customtimer.command.CustomTimerCache;

import java.util.concurrent.TimeUnit;

/**
 * @Author UKry
 * @Project TacoHub
 **/

@Data
public class CharProcessor {

    private final CustomTimer timer;
    private final int size;
    private final int intervalInMillis;

    private int pos = 0;
    private String finalText;
    private long lastMillis = System.currentTimeMillis();

    public String getFinalTextt() {
        update();
        return finalText;
    }

    public void update() {
        if (CustomTimerCache.getLastText().containsKey(timer)) {
            finalText = CustomTimerCache.getLastText().get(timer);
        }
        if ((lastMillis + intervalInMillis) >= System.currentTimeMillis()) {
            if (CustomTimerCache.getInited().contains(timer)) {
                return;
            } else {
                CustomTimerCache.getInited().add(timer);
            }
        }
        lastMillis = System.currentTimeMillis();
        String text = timer.getText();
        long t;
        if (timer.getExpireAt() != 0) {
            t = System.currentTimeMillis() - timer.getExpireAt();
        } else {
            t = timer.getReaming();
        }
        String tt;
        if (text.contains("%s%")) {
            text = remove(text, "%s%");
            tt = formatTime(t);
        } else {
            tt = ": " + formatTime(t);
        }

        if (tt.length() > 16) {
            finalText = "ERROR, TOO LONG";
            return;
        }
        if (text.length() > 16) {
            CustomTimerCache.getCharPos().putIfAbsent(timer, 0);
            pos = CustomTimerCache.getCharPos().get(timer);

            if (!timer.isPaused()) {
                if (timer.getReaming() != Long.MAX_VALUE) {
                    timer.updateReaming();
                }
            }
            int reaming = 16 - tt.length();
            if (CustomTimerCache.getReaming().containsKey(timer)) {
                int bReaming = CustomTimerCache.getReaming().get(timer);
                if (bReaming != reaming) {
                    CustomTimerCache.getReaming().remove(timer);
                }
            } else {
                CustomTimerCache.getReaming().put(timer, reaming);
            }
            CustomTimerCache.getCharPos().putIfAbsent(timer, 0);
            if (!timer.isPaused()) {
                if (timer.getReaming() != Long.MAX_VALUE) {
                    char[] charText = new char[reaming];
                    if (pos <= timer.getText().length() - 1) {
                        for (int i = 0; i < reaming; i++) {
                            charText[i] = text.charAt(pos + i);
                        }
                        pos = pos + 1;
                    } else {
                        for (int i = 0; i < reaming; i++) {
                            charText[i] = text.charAt(pos + i);
                        }
                        pos = 0;
                    }
                    finalText = new String(charText) + tt;
                    CustomTimerCache.getCharPos().replace(timer, pos);
                }
            } else {
                finalText = CustomTimerCache.getLastText().get(timer);
            }
        } else {
            finalText = text + tt;
        }
        if (CustomTimerCache.getLastText().containsKey(timer)) {
            CustomTimerCache.getLastText().replace(timer, finalText);
        } else {
            CustomTimerCache.getLastText().put(timer, finalText);
        }
        CustomTimerCache.getCharPos().replace(timer, pos);
        CustomTimerCache.getChars().replace(timer, this);
    }

    private String formatTime(Long time) {
        long h = TimeUnit.MILLISECONDS.toHours(time);
        long m = TimeUnit.MILLISECONDS.toMinutes(time);
        long s = TimeUnit.MILLISECONDS.toSeconds(time);
        if (h > 1) {
            return h + "h";
        }
        if (m > 1) {
            return m + "m";
        }
        if (s > 1) {
            return s + "s";
        }
        return "0s";
    }



    private String remove(String text, String sep) {
        if (text != null && text.contains(sep)) {
            int position = text.indexOf(sep);
            text = text.substring(0, position - 1);
        }
        return text;
    }
}