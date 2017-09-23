package config;

import java.util.ArrayList;
import java.util.List;

public class MealIdConfig {

    private static List<AddMeal> addMeals = new ArrayList<AddMeal>();

    private static List<MonthMeal> monthMeals = new ArrayList<MonthMeal>();

    static {
        //加餐包
        addMeals.add(new AddMeal("2007",1.2,"30MB"));
        addMeals.add(new AddMeal("2008",2.4,"100MB"));
        addMeals.add(new AddMeal("2009",4.8,"300MB"));
        addMeals.add(new AddMeal("2010",7.2,"500MB"));
        addMeals.add(new AddMeal("2011",12,"1GB"));
        addMeals.add(new AddMeal("2007",24,"3GB"));

        //月固定
        monthMeals.add(new MonthMeal("2062",12,"1GB "));
        monthMeals.add(new MonthMeal("2064",24,"3GB "));
        monthMeals.add(new MonthMeal("2066",41,"6GB "));
        monthMeals.add(new MonthMeal("2067",54,"8GB "));
        monthMeals.add(new MonthMeal("2068",60,"10GB "));
        monthMeals.add(new MonthMeal("2069",81,"15GB "));
        monthMeals.add(new MonthMeal("2070",105,"20GB "));
        monthMeals.add(new MonthMeal("2071",150,"30GB "));
        monthMeals.add(new MonthMeal("2072",234,"50GB "));
        monthMeals.add(new MonthMeal("2073",420,"100GB "));
        monthMeals.add(new MonthMeal("2074",1140,"300GB "));
        monthMeals.add(new MonthMeal("2075",1650,"500GB "));

    }

    public static double getPrice(String mealId){
        for (AddMeal addMeal : addMeals){
            if (addMeal.getMealid().equals(mealId)){
                return addMeal.getPrice();
            }
        }

        for (MonthMeal monthMeal : monthMeals){
            if (monthMeal.getMealid().equals(mealId)){
                return monthMeal.getPrice();
            }
        }

        throw new RuntimeException("无此套餐");
    }

    public static String getDetail(String mealId){
        for (AddMeal addMeal : addMeals){
            if (addMeal.getMealid().equals(mealId)){
                return addMeal.getDesc();
            }
        }

        for (MonthMeal monthMeal : monthMeals){
            if (monthMeal.getMealid().equals(mealId)){
                return monthMeal.getDesc();
            }
        }

        throw new RuntimeException("无此套餐");
    }



    public static List<MonthMeal> getMonthMeals(){
        return monthMeals;
    }

    public static List<AddMeal> getAddMeals(){
        return addMeals;
    }
}
