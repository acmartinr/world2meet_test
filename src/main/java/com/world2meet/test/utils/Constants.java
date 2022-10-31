package com.world2meet.test.utils;
public class Constants {
    //Error response Code
    public final static int OBJECT_FOUNDED_CODE = 112;
    public final static int NOT_SUPER_HEROES_FOUND_CODE = 100;
    public final static int INCORRECT_DATE_FORMAT_CODE = 101;
    public final static int ERROR_GETTING_PRICES_CODE = 102;
    public final static int MISSING_PARAM_FIELDS_CODE = 103;
    public final static int INCORRECT_FORMAT_FIELDS_CODE = 104;
    public final static int UNKNOWN_ERRROR_CODE = 111;

    //Error code Texts
    public static String NOT_SUPER_HEROES_FOUND_MSG = "Lo sentimos no encontramos nningun super heroe con dicho id";
    public static String INCORRECT_DATE_FORMAT = "Valor incorrecto para startDate ,el valor de la fecha debe tener un formato correcto como 'uuuu-MM-dd HH:mm:ss' y tener valores validos para una fecha";
    public static String ERROR_GETTING_PRICES = "Hubo un Error al Obtener la lista de precios";
    public static String MISSING_PARAM_FIELDS = "Todos los parametros de entrada no han sido introducidos";
    public static String INCORRECT_FORMAT_FIELDS = "Los parametros contienen formatos incorrectos";
    public static String UNKNOWN_ERRROR = "Error desconocido";
    public static String SUPER_HEROE_WAS_REMOVED_MSG = "El super heroe fue eliminado correctamente del sistema";
    private Constants() {}
}
