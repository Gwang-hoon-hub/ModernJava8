package com.modern.java.chap4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NoteHighlightTest {

    @Test
    void highlight(){
        //abc -> abc

        assertThat(highlight("abc")).isEqualTo("abc");
        assertThat(highlight("efg")).isEqualTo("efg");
        assertThat(highlight("note")).isEqualTo("{note}");
        assertThat(highlight("1 note")).isEqualTo("1 {note}");
        assertThat(highlight("1 note 2")).isEqualTo("1 {note} 2");
        assertThat(highlight("keynote")).isEqualTo("keynote");
        assertThat(highlight("ke1note")).isEqualTo("ke1note");
    }

    private String highlight(String str) {
        int idx = str.indexOf("note");
        if(idx == -1 ){
            return str;
        }
        // if(isPreChNotSpace(str, idx) return str          --> 리팩토링을 위한 코드
        boolean preChNotSpace = isPreChNotSpace(str, idx);
        if(preChNotSpace) return str;
        return str.replace("note", "{note}");
    }

    private boolean isPreChNotSpace(String str, int idx) {
        boolean preChNotSpace = false;
        int preChIdx = idx - 1;
        if(preChIdx >= 0) {
            char pre = str.charAt(preChIdx);
            if (IsNotSpace(pre)) {
                preChNotSpace = true;

            }
        }
        return preChNotSpace;
    }

    private boolean IsNotSpace(char pre) {
        return (pre >= 'a' && pre <= 'y') || (pre >= '0' && pre <= '9');
    }

}
