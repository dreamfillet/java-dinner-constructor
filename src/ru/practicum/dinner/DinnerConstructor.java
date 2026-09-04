package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();

    Random random = new Random();

    public void addNewDish(String dishType, String dishName) {
        ArrayList<String> dishesForType;
        if (dinnersByType.containsKey(dishType)) {
            dishesForType = dinnersByType.get(dishType);
        } else {
            dishesForType = new ArrayList<>();
            dinnersByType.put(dishType, dishesForType);
        }
        dishesForType.add(dishName);
    }


    public ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        for (int i = 1; i <= comboNumber; i++) {
            ArrayList<String> combo = generateCombo(dishTypes);
            combos.add(combo);
        }
        return combos;
    }


    private ArrayList<String> generateCombo(ArrayList<String> dishTypes) {
        ArrayList<String> selectedDishes = new ArrayList<>();
        for (String dishType : dishTypes) {
            ArrayList<String> availableDishes = dinnersByType.get(dishType);
            if (availableDishes == null || availableDishes.isEmpty()) {
                throw new IllegalArgumentException("Нет блюд для типа: " + dishType);
            }
            String selectedDish = getRandomDish(availableDishes);
            selectedDishes.add(selectedDish);
        }
        return selectedDishes;
    }

    public boolean checkType(String type) {
        //return dinnersByType.get(type) != null && dinnersByType.containsKey(type);
        return dinnersByType.containsKey(type);
    }

    private String getRandomDish(ArrayList<String> availableDishes) {
        int numberOfDishesForType = availableDishes.size();
        int dishIndex = random.nextInt(numberOfDishesForType);
        String selectedDish = availableDishes.get(dishIndex);
        return selectedDish;
    }

}
