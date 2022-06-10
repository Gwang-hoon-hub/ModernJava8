package com.modern.java.chap2;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCounts = getMetCriteriaCount(s);
        if(metCounts <= 1) return PasswordStrength.WEAK;

//        // 8글자 이하인 경우
//        if(lengthEnough && !containsNum && !containsUpp)
//            return PasswordStrength.WEAK;
//        // 숫자만 포함된 경우
//        if(!lengthEnough && containsNum && !containsUpp)
//            return PasswordStrength.WEAK;
//        // 대문자만 포함된 경우
//        if(!lengthEnough && !containsNum && containsUpp)
//            return PasswordStrength.WEAK;


        if(metCounts == 2) return PasswordStrength.NORMAL;
//        if(!lengthEnough){
//            return PasswordStrength.NORMAL;
//        }
////        if(s.length()<8){
////            return PasswordStrength.NORMAL;
////        }
//        if(!containsNum) return PasswordStrength.NORMAL;
//        if(!containsUpp) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCount(String s){
        int metCounts = 0;
        if(s.length() >= 8) metCounts++;
        if(meetsContainingNumberCriteria(s)) metCounts++;
        if(meetsContainingUppercaseCriteria(s)) metCounts++;
        return metCounts;
    }

    // refactoring
    private boolean meetsContainingNumberCriteria(String s){
        for(char ch : s.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s){
        for(char ch : s.toCharArray()){
            if(Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }
}
