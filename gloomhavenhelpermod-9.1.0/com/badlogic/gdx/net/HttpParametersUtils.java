package com.badlogic.gdx.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public final class HttpParametersUtils {
   public static String defaultEncoding = "UTF-8";
   public static String nameValueSeparator = "=";
   public static String parameterSeparator = "&";

   private HttpParametersUtils() {
   }

   public static String convertHttpParameters(Map parameters) {
      Set keySet = parameters.keySet();
      StringBuilder convertedParameters = new StringBuilder();

      for (String name : keySet) {
         convertedParameters.append(encode(name, defaultEncoding));
         convertedParameters.append(nameValueSeparator);
         convertedParameters.append(encode((String)parameters.get(name), defaultEncoding));
         convertedParameters.append(parameterSeparator);
      }

      if (convertedParameters.length() > 0) {
         convertedParameters.deleteCharAt(convertedParameters.length() - 1);
      }

      return convertedParameters.toString();
   }

   private static String encode(String content, String encoding) {
      try {
         return URLEncoder.encode(content, encoding);
      } catch (UnsupportedEncodingException var3) {
         throw new IllegalArgumentException(var3);
      }
   }
}
