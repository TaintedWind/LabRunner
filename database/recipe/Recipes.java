package database.recipe;

import gui.CraftingMenu;
import item.Item;
import item.projectiles.Projectile;
import item.resources.*;
import item.utilities.Tool;
import item.weapons.Weapon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Recipes {
    
    public static ArrayList<Object> recipes;
    static ArrayList<Image> images;
    
    public static void loadRecipes() {
        
        System.out.println("Loading recipes");
        
        recipes = new ArrayList<Object>();

        recipes.add(new RecipeIngredient("class item.resources.Resource", "STICK"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "GLUE"));
        recipes.add(new RecipeIngredient("NULL", "NULL"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STICKY STICK"));
        
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STICK"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "RUBBER"));
        recipes.add(new RecipeIngredient("NULL", "NULL"));
        recipes.add(new RecipeIngredient("class item.utilities.Tool", "PLUNGER"));
        
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STICK"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STRING"));
        recipes.add(new RecipeIngredient("NULL", "NULL"));
        recipes.add(new RecipeIngredient("class item.weapons.Weapon", "BOW"));
        
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STICK"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "RUBBER"));
        recipes.add(new RecipeIngredient("NULL", "NULL"));
        recipes.add(new RecipeIngredient("class item.utilities.Tool", "PLUNGER"));
        
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STICK"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "LEATHER"));
        recipes.add(new RecipeIngredient("class item.resources.Resource", "STEEL"));
        recipes.add(new RecipeIngredient("class item.weapons.Weapon", "IRON SWORD"));
        
        //finish adding recipes for 0.0.8-dev
        
        /*recipes.add(new Resource("GLUE",0, 999,999));
        recipes.add(new Resource("PAPER",0, 999, 999));
        recipes.add(new Tool("null", 0, 0));
        recipes.add(new Resource("BANDAGE", 0, 999, 999));
        
        recipes.add(new Resource("STICK",0, 999,999));
        recipes.add(new Resource("BOTTLE",0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Resource("MAGNIFYING GLASS", 0, 999, 999));
        
        recipes.add(new Resource("STONE",0, 999,999));
        recipes.add(new Resource("STEEL",0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Weapon("KNIFE", 0, 999, 999));
        
        recipes.add(new Resource("COPPER PIPE",0, 999,999));
        recipes.add(new Resource("SEEDS", 0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Weapon("BLOWPIPE", 0, 999, 999));
        
        recipes.add(new Resource("STICK",0, 999,999));
        recipes.add(new Resource("PIXIE DUST", 0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Weapon("WAND", 0, 999, 999));
        
        recipes.add(new Resource("LEATHER",0, 999,999));
        recipes.add(new Resource("LEATHER", 0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Weapon("WAND", 0, 999, 999));*/
        
        loadRecipeImages();
        
        
        
    }
    
    static void loadRecipeImages() {
        
        images = new ArrayList<Image>();
        
        try {
            images.add(new Image("./resources/sticky_stick.png"));
            images.add(new Image("./resources/plunger_icon.png"));
            images.add(new Image("./resources/bow_icon.png"));
            images.add(new Image("./resources/sword_icon.png"));
            images.add(new Image("./resources/bandaid.png"));
            images.add(new Image("./resources/mag_glass.png"));
        } catch (SlickException ex) {
            Logger.getLogger(Recipes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static ArrayList getRecipeIngredients(int ID, boolean verbose) {
        
        if (verbose) {
            System.out.println("Getting recipe "+(ID)+": "+((Item)recipes.get(ID * 4 + 3)).name);
        }
        
        
        ArrayList<String>returnedRecipe = new ArrayList<String>();
        returnedRecipe.add(((RecipeIngredient)recipes.get(ID * 4)).name); //ingredient 1
        returnedRecipe.add(((RecipeIngredient)recipes.get(ID * 4 + 1)).name); //2
        returnedRecipe.add(((RecipeIngredient)recipes.get(ID * 4 + 2)).name); //3
        
        if (verbose) {
            System.out.println(returnedRecipe);
        }
        
        return returnedRecipe;
        
    }
    
    public static Object getRecipeResult(int ID, boolean verbose) {
        
        ArrayList<Object>returnedRecipe = new ArrayList<Object>();
        returnedRecipe.add(((RecipeIngredient)recipes.get(ID * 4 + 3))); //index 3 of the returned array is the crafting result
        
        if (verbose) {
            System.out.println("Crafted item is: "+returnedRecipe.get(0));
        }
        
        return returnedRecipe.get(0);
    }
    
    public static Image getRecipePreview() {
        
        ArrayList<String> user_recipe = new ArrayList<String>();
        ArrayList<String> recipe = new ArrayList<String>();
        boolean processRecipes = true;
        
        try {

            if (CraftingMenu.recipe[0] != null) {
                user_recipe.add(((Item)CraftingMenu.recipe[0]).name);
            } else {
                user_recipe.add("NULL");
            }
            if (CraftingMenu.recipe[1] != null) {
                user_recipe.add(((Item)CraftingMenu.recipe[1]).name);
            } else {
                user_recipe.add("NULL");
            }
            if (CraftingMenu.recipe[2] != null) {
                user_recipe.add(((Item)CraftingMenu.recipe[2]).name);
            } else {
                user_recipe.add("NULL");
            }

            //sort the user recipe alphabetically
            Collections.sort(user_recipe);
            
            
          for (int c = 0; c < database.recipe.Recipes.recipes.size() / 4 && processRecipes == true; c++) {
              
              //get recipe c from the database and sort
              recipe = database.recipe.Recipes.getRecipeIngredients(c, false);
              Collections.sort(recipe);
              
              //if it matches the user_recipe, return the image and stop processing
              if (recipe.equals(user_recipe)) {
                  processRecipes = false;
                  System.out.println("Getting image "+c+" from database");
                  return images.get(c);
                  
              }
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
        
}