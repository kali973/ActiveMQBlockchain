//package com.solarix.camel.processus;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//public class HashBlockchain {
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_BLUE = "\u001B[34m";
//    public static final String ANSI_PURPLE = "\u001B[35m";
//    public static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_RESET = "\u001B[0m";
//    private static String var10;
//
//    public static void HashBlockchain(String data) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        Date var1 = new Date();
//        long var2 = var1.getTime();
//        new GregorianCalendar(2018, 10, 28);
//
//        if (data != null) {
//            var10 = data;
//        } else {
//            var10 = reader.readLine();
//        }
//
//        var10 = var10.toLowerCase();
//        String var11 = "FFFF";
//
//        boolean valuesPrintedDeux = false;
//        boolean valuesPrintedTrois = false;
//        boolean valuesPrintedQuatre = false;
//        boolean valuesPrintedCinq = false;
//
//        System.out.println();
//
//        for (int var12 = 0; var11.compareTo("00000") != 0; ++var12) {
//            var10 = var10.concat(Integer.toString(var12));
//            String var13 = ComputeSHA_256_as_Hex_String(var10);
//            var11 = var13.substring(0, 4);
//            String var22 = var13.substring(0, 5);
//            System.out.print(var13.toUpperCase() + " - iteration number:" + var12 + "\r");
//
//            if (var22.substring(0, 5).compareTo("00000") == 0 & !valuesPrintedCinq) {
//                valuesPrintedCinq = true;
//                Date var14 = new Date();
//                long var7 = var14.getTime() - var2;
//                // Supprimer les séquences ANSI de l'affichage
//                String affichageSansANSI = ANSI_RED + " ==========> " + var13.toUpperCase() + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_RED + " ==========> Mining time = " + var7 / 1000L + " sec" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_RED + " ==========> Number of iterations: " + var12 + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_RED + " ==========> Mining time = " + var7 / (1000L * 60) + " min" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                System.out.println();
//            } else if (var22.substring(0, 4).compareTo("0000") == 0 & !valuesPrintedQuatre) {
//                valuesPrintedQuatre = true;
//                Date var14 = new Date();
//                long var7 = var14.getTime() - var2;
//                // Supprimer les séquences ANSI de l'affichage
//                String affichageSansANSI = ANSI_BLUE + " ==========> " + var13.toUpperCase() + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_BLUE + " ==========> Mining time = " + var7 / 1000L + " sec" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_BLUE + " ==========> Number of iterations: " + var12 + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_BLUE + " ==========> Mining time = " + var7 / (1000L * 60) + " min" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//                System.out.println();
//            } else if (var22.substring(0, 3).compareTo("000") == 0 & !valuesPrintedTrois) {
//                valuesPrintedTrois = true;
//                Date var14 = new Date();
//                long var7 = var14.getTime() - var2;
//                String affichageSansANSI = ANSI_PURPLE + " ==========> " + var13.toUpperCase() + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_PURPLE + " ==========> Mining time = " + var7 / 1000L + " sec" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_PURPLE + " ==========> Number of iterations: " + var12 + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_PURPLE + " ==========> Mining time = " + var7 / (1000L * 60) + " min" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//                System.out.println();
//            } else if (var22.substring(0, 2).compareTo("00") == 0 & !valuesPrintedDeux) {
//                valuesPrintedDeux = true;
//                Date var14 = new Date();
//                long var7 = var14.getTime() - var2;
//                String affichageSansANSI = ANSI_GREEN + " ==========> " + var13.toUpperCase() + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_GREEN + " ==========> Mining time = " + var7 / 1000L + " sec" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_GREEN + " ==========> Number of iterations: " + var12 + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//
//                affichageSansANSI = ANSI_GREEN + " ==========> Mining time = " + var7 / (1000L * 60) + " min" + ANSI_RESET;
//                affichageSansANSI = affichageSansANSI.replaceAll("\u001B\\[[;\\d]*m", ""); // Supprimer les séquences ANSI
//                System.out.println(affichageSansANSI);
//                System.out.println();
//            }
//        }
//        Date var14 = new Date();
//        long var7 = var14.getTime() - var2;
//        System.out.println("Mining time = " + var7 / 1000L + " sec");
//
//    }
//
//    public static String ComputeSHA_256_as_Hex_String(String var0) {
//        try {
//            MessageDigest var1 = MessageDigest.getInstance("SHA-1");
//            var1.update(var0.getBytes(StandardCharsets.UTF_8), 0, var0.length());
//            byte[] var2 = var1.digest();
//            return convertToHex(var2);
//        } catch (NoSuchAlgorithmException var3) {
//            System.out.println("No such algorithm exception thrown " + var3);
//        }
//
//        return null;
//    }
//
//    private static String convertToHex(byte[] var0) {
//        StringBuffer var1 = new StringBuffer();
//
//        for (int var2 = 0; var2 < var0.length; ++var2) {
//            int var3 = var0[var2] >>> 4 & 15;
//            int var4 = 0;
//
//            do {
//                if (0 <= var3 && var3 <= 9) {
//                    var1.append((char) (48 + var3));
//                } else {
//                    var1.append((char) (97 + (var3 - 10)));
//                }
//                var3 = var0[var2] & 15;
//            } while (var4++ < 1);
//        }
//        return var1.toString();
//    }
//}