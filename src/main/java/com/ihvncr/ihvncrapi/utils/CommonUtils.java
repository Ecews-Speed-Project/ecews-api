package com.ihvncr.ihvncrapi.utils;

import com.blazebit.persistence.CriteriaBuilder;
import com.ihvncr.ihvncrapi.model.analytics.Search;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Tuple;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ihvncr.ihvncrapi.utils.HelperFunctions.convertDate;

public class CommonUtils {
    public final static int STARTING_PAGE_NUMBER = 0;
    public final static String UPLOADED_PROCESSING = "PROCESSING";
    public final static String UPLOADED_PROCESSED = "PROCESSED";
    public final static String UPLOADED_QUEUED = "QUEUED";
    public final static String UPLOADED_FAILED = "FAILED";
    
    

    public final static int ADMIN_ROLE = 1;
    public final static int STATE_ADMIN = 2;
    public final static int LIMIT = 2;
    public final static int FACILITY_ADMIN = 4;
    public final static int MANDE = 3;
    public final static String FEMALE = "F";
    public final static int ZERO = 0;
    public static final double VIRAL_LOAD_COUNT = 1000.0;

    public final static String TREATMENT_CURRENT = "TX_CURR";
    public final static List<String> AGE_RANGES = Arrays
            .asList("<1", "1-4", "5-9", "10-14", "15-19", "20-24", "25-29", "30-34",
                    "35-39", "40-44", "45-49", "50-54", "55-59", "60-64", "65+");

    public final static List<String> AGE_RANGE_FOR_CHART = Arrays
            .asList("<1", "1-4", "5-9", "10-14", "15-19", "20-24", "25-29", "30-34",
                    "35-39", "40-44", "45-49", "50+" );


    public final static String SEARCH_TYPE_ADVANCE = "ADVANCE";
    public final static String SEARCH_TYPE_NORMAL = "NORMAL";
    public final static String DISPLAY_LEVEL_STATE = "STATE";
    public final static String DISPLAY_LEVEL_LGA = "LGA";
    public final static String DISPLAY_LEVEL_FACILITY = "FACILITY";
    public final static String DISPLAY_LEVEL_SEX = "SEX";
    public final static String DISPLAY_LEVEL_AGE_RANGE = "AGA_RANGE";

    public final static String INDICATOR_TX_CURR = "TX_CURR";
    public final static String INDICATOR_TX_NEW = "TX_NEW";
    public final static String INDICATOR_PVLS = "PVLS";

    public static String getMappedValue(String value) {
        Map<String, String> map = new HashMap<>();
        map.put("F", "Female");
        map.put("M", "Male");

        if (map.containsKey(value)) {
            return map.get(value);
        }
        return null;
    }

    public static double calculatePercentage(long group, long total){
        double percent = (double) group * 100/total;
        percent = Math.round(percent * 100);
        return percent/100;
    }

    public static LocalDate getQuarterCutOffDate(LocalDate cutOff) {
        int year = cutOff.getYear();
        int month = cutOff.getMonthValue();
        return getQuarterEndDate(month, year);
    }

    public static LocalDate getQuarterStartDateFromQuarterCode(String quarterCode) {
        LocalDate cutOff = LocalDate.now();
        String year = String.valueOf(cutOff.getYear());
        int month;
        int quarterYear = Integer.parseInt(year.substring(0,2) + quarterCode.substring(2,4));
        int quarter = Integer.parseInt(quarterCode.substring(5));
        switch (quarter) {
            case 1 :
                month = 10;
                quarterYear = quarterYear - 1;
                break;
            case 2:
                month = 1;
                break;
            case 3:
                month = 4;
                break;
            default:
                month = 7;
        }

        return getQuarterStartDate(month, quarterYear);
    }

    private static LocalDate getQuarterStartDate(int month, int quarterYear) {
        TreeMap<Integer, LocalDate> rangeTreeMap = new TreeMap<>();

        rangeTreeMap.put(0, LocalDate.of(quarterYear, month, 1));

        rangeTreeMap.put(3, LocalDate.of(quarterYear, month, 1));

        rangeTreeMap.put(6, LocalDate.of(quarterYear, month, 1));

        rangeTreeMap.put(9, LocalDate.of(quarterYear, month, 1));

        return rangeTreeMap.get(rangeTreeMap.lowerKey(month));
    }

    private static LocalDate getQuarterEndDate(int month, int quarterYear) {
        TreeMap<Integer, LocalDate> rangeTreeMap = new TreeMap<>();

        rangeTreeMap.put(0, LocalDate.of(quarterYear - 1, 12, 31));

        rangeTreeMap.put(3, LocalDate.of(quarterYear, 3, 31));

        rangeTreeMap.put(6, LocalDate.of(quarterYear, 6, 30));

        rangeTreeMap.put(9, LocalDate.of(quarterYear, 9, 30));

        return rangeTreeMap.get(rangeTreeMap.lowerKey(month));
    }

    public static String getPreviousQuarterByDate(LocalDate cutOff) {
        return getCurrentQuarterCode(cutOff.minusDays(1));
    }

    public  static  String checkTreatmentGroup(LocalDate start_date, LocalDate end_date){
        long years = findDifference( start_date,  end_date);
        if(years >= 15){
            return "Adult";
        }
        return "Pediatrics";
    }
    public static long findDifference(LocalDate start_date, LocalDate end_date)
    {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date d1 = Date.from(start_date.atStartOfDay(defaultZoneId).toInstant());
        Date d2 = Date.from(end_date.atStartOfDay(defaultZoneId).toInstant());
        long difference_In_Time
                = d2.getTime() - d1.getTime();

        long difference_In_Years
                = (difference_In_Time
                / (1000l * 60 * 60 * 24 * 365));


      return difference_In_Years;
    }

    public static LocalDate getFirstDayOfMonthByDate(LocalDate cutOff) {
        return cutOff.withDayOfMonth(1);
    }

    public static LocalDate getQuarterEndDateFromQuarterCode(String quarterCode) {
        LocalDate cutOff = LocalDate.now();
        String year = String.valueOf(cutOff.getYear());
        int month;
        int quarterYear = Integer.parseInt(year.substring(0,2) + quarterCode.substring(2,4));
        int quarter = Integer.parseInt(quarterCode.substring(5));
        switch (quarter) {
            case 1 :
                month = 1;
                break;
            case 2:
                month = 4;
                break;
            case 3:
                month = 7;
                break;
            default:
                month = 10;
        }

        return getQuarterEndDate(month, quarterYear);
    }

    public static String getQuarter(LocalDate cutOff) {
        int year = cutOff.getYear();
        int month = cutOff.getMonthValue();

        if (month <= 3)
            return "FY"+String.valueOf(year).substring(2)+"Q2";
        else if (month <= 6)
            return "FY"+String.valueOf(year).substring(2)+"Q3";
        else if (month <= 9)
            return "FY"+String.valueOf(year).substring(2)+"Q4";
        else
            return "FY"+String.valueOf(year + 1).substring(2)+"Q1";
    }

    public static String getCurrentQuarterCode(LocalDate quarterCutoff) {
        String quarter;
        int month = quarterCutoff.getMonthValue();
        int year = quarterCutoff.getYear();
        String fy = String.format("FY%sQ", String.valueOf(year).substring(2));
        switch (month <= 3 ? 3 : month <= 6 ? 6 : month <= 9 ? 9 : 12) {
            case 3:
                quarter = fy + 2;
                break;
            case 6:
                quarter = fy + 3;
                break;
            case 9:
                quarter = fy + 4;
                break;
            default:
                quarter = String.format("FY%sQ%d", String.valueOf(year + 1).substring(2), 1);
        }
        return quarter;
    }

    public static CriteriaBuilder<Tuple> getTupleCriteriaBuilderForStateLgaAndFacility(Search search, CriteriaBuilder<Tuple> cb) {
        if (!search.getStates().isEmpty()) {
            cb.where("state").in(search.getStates());
        }
        if (!search.getLgas().isEmpty()) {
            cb.where("lgaName").in(search.getLgas());
        }
        if (!search.getFacilities().isEmpty()) {
            cb.where("facilityName").in(search.getFacilities());
        }
        return cb;
    }

    public  static String formatString(String value1, String value2){
        return String.format("%s%s", value1, value2);
    }

    public  static long dateToLong(LocalDateTime localDateTime){
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();
        return date;
    }

    public static  LocalDateTime  stringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd;HH:mm:ss");
        LocalDateTime  localDate = LocalDateTime .parse(date+";00:00:00", formatter);
        return localDate;
    }

    public static String decrypt(String text, String initVectorString, String secretKeyString) {
        try {
            byte[] initVector = Base64.getDecoder().decode(initVectorString);
        byte[] secretKey = Base64.getDecoder().decode(secretKeyString);

        IvParameterSpec initVectorSpec = new IvParameterSpec(initVector);
        SecretKeySpec secret = new SecretKeySpec(secretKey, "AES");
        String decrypted;


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secret, initVectorSpec);
            byte[] decodedValue = Base64.getDecoder().decode(text);
            byte[] original = cipher.doFinal(decodedValue);
            decrypted = new String(original, StandardCharsets.UTF_8);
            return decrypted;
        } catch (GeneralSecurityException var8) {
            //log.error("Error while decrypting: " + var8);
            throw new RuntimeException("could not decrypt text", var8);
        }
    }
}
