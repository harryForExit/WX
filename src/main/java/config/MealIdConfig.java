package config;

public class MealIdConfig {
    private static String [] mealid = {"2007","2008","2009","2010","2011","2012"};
    private static int [] price = {4,8,16,24,40,80};

    public static int getPrice(String mealId){
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
