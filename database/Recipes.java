package database;

import item.Item;
import item.projectiles.Projectile;
import item.resources.*;
import item.utilities.Tool;
import item.weapons.Weapon;
import java.util.ArrayList;
import org.newdawn.slick.Image;

public class Recipes {
    
    public static ArrayList<Object> recipes;
    
    public static void loadRecipes() {
        
        System.out.println("Loading recipes");
        
        recipes = new ArrayList<Object>();

        recipes.add(new Resource("STICK",0, 999, 999));
        recipes.add(new Resource("GLUE",0, 999, 999));
        recipes.add(new Tool("null", 9999, 9999));
        recipes.add(new Resource("STICKY STICK",0, 9999, 9999));
        
        recipes.add(new Resource("STICK",0, 999,999));
        recipes.add(new Resource("RUBBER",0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Tool("PLUNGER", 999, 999));
        
        recipes.add(new Resource("STICK",0, 999,999));
        recipes.add(new Resource("STRING",0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
        recipes.add(new Weapon("BOW", 0, 999, 999));
        
        recipes.add(new Tool("STICK", 999, 999));
        recipes.add(new Tool("LEATHER", 999, 999));
        recipes.add(new Tool("STEEL", 999, 999));
        recipes.add(new Weapon("IRON SWORD", 0, 999, 999));
        
        recipes.add(new Resource("GLUE",0, 999,999));
        recipes.add(new Resource("PAPER",0, 999, 999));
        recipes.add(new Tool("null", 999, 999));
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
        recipes.add(new Weapon("WAND", 0, 999, 999));
        
        
        
    }
    
    public static ArrayList getRecipe(int ID, boolean verbose) {
        
        ArrayList<String>returnedRecipe = new ArrayList<String>();
        returnedRecipe.add(((Item)recipes.get(ID * 4)).name); //ingredient 1
        returnedRecipe.add(((Item)recipes.get(ID * 4 + 1)).name); //2
        returnedRecipe.add(((Item)recipes.get(ID * 4 + 2)).name); //3
        returnedRecipe.add(((Item)recipes.get(ID * 4 + 3)).name); //result
        
        if (verbose) {
            System.out.println("Getting recipe "+(ID)+": "+((Item)recipes.get(ID * 4 + 3)).name);
            System.out.println(returnedRecipe);
        }
        
        return returnedRecipe;
        
    }
    
    public static Object getRecipeResult(int ID, boolean verbose) {
        
        ArrayList<Object>returnedRecipe = new ArrayList<Object>();
        returnedRecipe.add(recipes.get(ID * 4)); //ingredient 1
        returnedRecipe.add(recipes.get(ID * 4 + 1)); //2
        returnedRecipe.add(recipes.get(ID * 4 + 2)); //3
        returnedRecipe.add(recipes.get(ID * 4 + 3)); //index 3 of the returned array is the crafting result
        
        if (verbose) {
            System.out.println("Crafted item is: "+((Item)returnedRecipe.get(3)).name);
        }
        
        return returnedRecipe.get(3);
    }
    
    public static Image getRecipePreview(int ID) {
        
        ArrayList<Object>returnedRecipe = new ArrayList<Object>();
        returnedRecipe.add(recipes.get(ID * 4)); //ingredient 1
        returnedRecipe.add(recipes.get(ID * 4 + 1)); //2
        returnedRecipe.add(recipes.get(ID * 4 + 2)); //3
        returnedRecipe.add(recipes.get(ID * 4 + 3)); //index 3 of the returned array is the crafting result

        if (((Item)returnedRecipe.get(3)).defaultTexture != null) {
            return ((Item)returnedRecipe.get(3)).defaultTexture;
        } else {
            return null;
        }
        
    }
        
}