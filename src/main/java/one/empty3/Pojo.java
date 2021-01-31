package one.empty3;

import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class Pojo {
    public static boolean parseBoolean(String s) {
        Boolean b =  ((s != null) && s.equalsIgnoreCase("true"));
        if(!b && (s==null || !s.equalsIgnoreCase("false")))
            throw new NumberFormatException("Boolean illegal string");
        return b;
    }
    public static void setO(Object o, String propName, String value) {

        Double d;
        Integer i = 0;
        Boolean b = false;
        try {
            i = (int) Integer.parseInt(value);
            setProperty(o, propName, i, Integer.class);
        } catch (Exception ex) {
            try {
                d = (double) Double.parseDouble(value);
                setProperty(o, propName, d, Double.class);
            } catch (Exception ex1) {
                try {
                    b = (boolean) parseBoolean(value);
                    setProperty(o, propName, b, Boolean.class);
                } catch (Exception ex2) {
                    try {
                        if(value!=null && !"".equals(value))
                            setProperty(o, propName, value, String.class);
                    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e1) {
                        //e1.printStackTrace();
                    }
                    //ex2.printStackTrace();
                }
            }
        }
        
    }
    public static void setP(Object p, String propName, 
                            String vType, String value) {
        try {
        Object o = null;
        Class c = Class.forName(vType);
        switch(vType) {
                case "double":
                case "Double":
                o = Double.parseDouble(value);
                    break;
                case "int":
                case "Integer":
                o = Integer.parseInt(value);
                    break;
                case "boolean":
                case "Boolean":
                    o = parseBoolean(value);
                    break;
                default:
                    break;
         }
         if(o!=null) {
                    
              setProperty(p, propName, o,
                               c);
         }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
   }


    public static boolean setProperties(Object o, Properties p) {
        try {
            Iterator it = p.keySet().iterator();

            while (it.hasNext()) {
                String pr = it.next().toString();
                String value = p.getProperty(pr);
                setO(o, pr, value);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /***
     * @param o Pojo object
     * @param p Properties list String (name), String (scalar value b|d|i)
     * @return properties getter list value
     */
    public static Properties getProperties(Object o, Properties p) {

        return null;
    }

    public static Class getPropertyType(Object o, String propertyName) throws NoSuchMethodException {
        Method propertyGetter = null;
        propertyGetter = o.getClass().getMethod("get" + ("" + propertyName.charAt(0)).toUpperCase() + (propertyName.length() > 1 ? propertyName.substring(1) : ""));
        return propertyGetter.getReturnType();
    }

    public static void setProperty(Object o, String propertyName, Object value
       , Class cl) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method propertySetter = null;
        propertySetter = o.getClass().getMethod("set" + ("" + propertyName.charAt(0)).toUpperCase() + (propertyName.substring(1)), cl);
        propertySetter.invoke(o, value);
        System.out.println("RType : " + o.getClass().getName() + " Property: " + propertyName + " New Value set " + getProperty(o, propertyName));
    }

    public static Object getProperty(Object o, String propertyName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method propertySetter = null;
        propertySetter = o.getClass().getMethod("get" + ("" + propertyName.charAt(0)).toUpperCase() + propertyName.substring(1));
        return propertySetter.invoke(o);
    }

}
