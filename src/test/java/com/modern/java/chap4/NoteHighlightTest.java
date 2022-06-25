package com.modern.java.chap4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NoteHighlightTest {

    @Test
    void highlight(){
//        assertThat(highlight("abc")).isEqualTo("abc");
//        assertThat(highlight("efg")).isEqualTo("efg");
//        assertThat(highlight("note")).isEqualTo("{note}");
//        assertThat(highlight("1 note")).isEqualTo("1 {note}");
//        assertThat(highlight("1 note 2")).isEqualTo("1 {note} 2");
//        assertThat(highlight("keynote")).isEqualTo("keynote");
//        assertThat(highlight("ke1note")).isEqualTo("ke1note");
//        assertThat(highlight("yes note1")).isEqualTo("yes note1");
//        assertThat(highlight("yes notea")).isEqualTo("yes notea");
//        assertThat(highlight("no a note")).isEqualTo("no a {note}");
        assertThat(highlight("no a note note")).isEqualTo("no a {note} {note}");
        assertThat(highlight("no a note anote")).isEqualTo("no a {note} anote");    // error loop 만들어서 처리하기
        assertThat(highlight("no a note anote aa note")).isEqualTo("no a {note} anote aa {note}");    // error loop 만들어서 처리하기
    }

    private String highlight(String str) {
        String result = "";
        while(true) {
            int idx = str.indexOf("note"); // 찾고자 하는 값이 indexOf 에 있으면 0을 return 한다. 없으면 -1 return
            if (idx == -1) {
                result += str;
                break;
            }
            // if(isPreChNotSpace(str, idx) return str          --> 리팩토링을 위한 코드
            if (isPrePostChNotSpace(str, idx)) {
                result += str.substring(0, idx + "note".length());
                str += str.substring(idx + "note".length());
            } else {
                String preStr = idx > 0 ? str.substring(0, idx) : "";
                result += preStr + "{note}";
                str = str.substring(idx + "note".length());
            }
        }
        return result;
    }

    private boolean isPrePostChNotSpace(String str, int idx){ // 앞뒤로 공간이 있는 경우 그냥 str을 return 한다.
        int postChIdx = idx + "note".length();
        int preChIdx = idx - 1;
        return ((postChIdx < str.length() && IsNotSpace(str.charAt(postChIdx))) || (preChIdx >= 0 && IsNotSpace(str.charAt(preChIdx))));
    }

    private boolean isPostChNotSpace(String str, int idx) {
        int postChIdx = idx + "note".length();
        return postChIdx < str.length() && IsNotSpace(str.charAt(postChIdx)); // idx 의 길이가 str의 길이보다 작아야 한다.
    }

    private boolean isPreChNotSpace(String str, int idx) {
        int preChIdx = idx - 1;
        return preChIdx >= 0 && IsNotSpace(str.charAt(preChIdx));
    }

    private boolean IsNotSpace(char pre) {
        return (pre >= 'a' && pre <= 'y') || (pre >= '0' && pre <= '9');
    }
}
