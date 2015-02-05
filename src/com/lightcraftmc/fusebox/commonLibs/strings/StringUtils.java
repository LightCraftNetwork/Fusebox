package com.lightcraftmc.fusebox.commonLibs.strings;

public class StringUtils
{
  public StringUtils() {}
  
  public static String decapitalize(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return paramString;
    }
    if ((paramString.length() > 1) && (Character.isUpperCase(paramString.charAt(1))) && (Character.isUpperCase(paramString.charAt(0)))) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    arrayOfChar[0] = Character.toLowerCase(arrayOfChar[0]);
    return new String(arrayOfChar);
  }
  
  public static String capitalize(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    arrayOfChar[0] = Character.toUpperCase(arrayOfChar[0]);
    return new String(arrayOfChar);
  }
}

/* Location:           C:\Program Files\Java\jre1.8.0_25\lib\rt.jar
 * Qualified Name:     com.sun.xml.internal.ws.util.StringUtils
 * Java Class Version: 8 (52.0)
 * JD-Core Version:    0.7.0.1
 */