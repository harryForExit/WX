package config;

public class MealIdConfig {
    private static String [] mealid = {"2007","2008","2009","2010","2011","2012","2052","2053","2054","2055","2056","2057","2058","2059","2060","2061","2062","2063"};
    private static double [] price = {1.2, 2.4, 4.8, 7.2, 12, 24, 0.6, 0.9, 1.2, 1.5, 1.8, 2.4, 3.6, 4.8, 7.2, 9.6, 12, 18};

    public static double getPrice(String mealId){
        Integer index = null;
        for (int i=0;i<mealid.length;i++){
            if (mealId.equals(mealid[i])){
                index = i;
            }
        }

        if (index == null){
            throw new RuntimeException("无此套餐");
        }

        return price[index];
    }
}
