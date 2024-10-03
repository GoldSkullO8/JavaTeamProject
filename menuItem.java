//IS 380 - 1001 Group Project

/*  Group 1:
 *  Herman Larke
 *  Christopher LaRusso
 *  Jonah Molnar
 *  Collin Reed
 *  Valeria Tang
 */

/*
    This class is for creating menu items to be added to order
    depending on user selected input! Inputs are predetermined to avoid
    error!
*/
public class menuItem
{
    //class variable declarations & details
    private String name;    //name of menu item
    private String sideItem; //name of preferred side dish
    private boolean isBreakfast = false;    //determines if entree is breakfast item
    private boolean isSingleItem = false;   //determines if entry is single item or not
    private String eggPref; //if breakfast item, represents how eggs are cooked
    private double price;   //price of menu item
    private String description; //description of item
    private int spiciness;  //level of spiciness ranging from 0-10 (0 = not spicy & 10 = most spicy)
    private int popularity; //level of popularity from 1-5 (1 = least pop & 5 = most pop)

    /**
        Consructs menuItem based on inputs provided (Breakfast Menu)
        @param entree Name of selected Entree
        @param side Name of selected side item
        @param egg Preference of way egg is cooked
    */
    public menuItem(String entree, String side, String egg)
    {
        //sets class variables
        name = entree;
        sideItem = side;
        eggPref = egg;
        isBreakfast = true; //since breakfast item, sets bool to true
        isSingleItem = false;   //since not single item, sets bool to false

        //sets all class variables depending on user selected item
        if(entree == "Pancakes")
        {
            price = 9.99;
            description = "Stack of two buttermilk pancakes topped with " +
                            "butter and a side of syrup!";
            spiciness = 0;
            popularity = 4;
        }
        else if(entree == "Waffles")
        {
            price = 8.99;
            description = "Stack of two crunchy Waffles topped with " +
                            "butter and side of syrup!";
            spiciness = 0;
            popularity = 2;
        }
        else if(entree == "French Toast")
        {
            price = 11.99;
            description = "Two pieces of French Toast topped with " +
                            "butter, powdered sugar, and a side of syrup!";
            spiciness = 0;
            popularity = 3;
        }
        else if(entree == "Breakfast Burrito")
        {
            price = 12.99;
            description = "Scrambled eggs, melted cheese, savory bacon or " +
                        "sausage, and zesty salsa, all wrapped in a warm flour tortilla!";
            spiciness = 2;
            popularity = 5;
        }
        else if(entree == "Meat Lovers Omlette")
        {
            price = 11.99;
            description = "Pepper Jack cheese, bacon, ham, sausage and green onion!";
            spiciness = 1;
            popularity = 4;
        }
    }

    /**
        Consructs menuItem based on inputs provided (Lunch/Dinner Menu)
        @param entree Name of selected Entree
        @param side Name of selected side item
    */
    public menuItem(String entree, String side)
    {
        //sets class variables
        name = entree;
        sideItem = side;
        isBreakfast = false; //since not breakfast item, sets bool to false
        isSingleItem = false; //since not single item, sets bool to false

        //sets all class variables depending on user selected item
        if(entree == "Tomahawk Steak")
        {
            price = 39.99;
            description = "20oz freshly sourced beef served with a side " +
                            "of house steak sauce!";
            spiciness = 0;
            popularity = 5;
        }
        else if(entree == "Chicken Piccata")
        {
            price = 14.99;
            description = "Crispy pan seared chicken breast served with " +
                            "our housemade lemon caper sauce!";
            spiciness = 1;
            popularity = 4;
        }
        else if(entree == "Salmon Fillet")
        {
            price = 16.99;
            description = "Seasoned with butter, fresh herbs, and lemon " +
                            "juice, then baked until it's nice and flaky!";
            spiciness = 0;
            popularity = 2;
        }
        else if(entree == "Cheeseburger")
        {
            price = 12.99;
            description = "Juicy angus beef patty topped with cheese, " +
                            "lettuce, tomato, and red onion!";
            spiciness = 0;
            popularity = 3;
        }
        else if(entree == "Buffalo Bone-in Wings")
        {
            price = 10.99;
            description = "Crispy chicken wings tossed in spicy Buffalo " +
                            "sauce, served with ranch or blue cheese dressing. " + 
                            "Perfect for those craving tender chicken with bold flavor!";
            spiciness = 7;
            popularity = 4;
        }
        else if(entree == "Crispy Chicken Tenders")
        {
            price = 12.99;
            description = "Juicy chicken tenders with a crunchy coating served with " +
                            "your choice of dipping sauce!";
            spiciness = 0;
            popularity = 5;
        }
    }

    /**
        Consructs menuItem based on inputs provided (Beverage and Dessert Menus)
        @param item Name of desired item
    */
    public menuItem(String item)
    {
        //sets class variables
        name = item;
        isBreakfast = false; //since not breakfast item, sets bool to false
        isSingleItem = true; //since is single item, sets bool to true

        //sets all class variables depending on user selected item
        if(item == "Classic New York Cheesecake")
        {
            price = 2.99;
            description = "A slice of delicious Classic New " +
                    "York Cheesecake topped with whipped cream and fresh strawberries!";
            spiciness = 0;
            popularity = 3;
        }
        else if(item == "Chocolate Brownie Sundae")
        {
            price = 3.99;
            description = "Creamy vanilla ice cream topped with " +
                            "our rich chocolate brownie and hot fudge sauce!";
            spiciness = 0;
            popularity = 1;
        }
        else if(item == "Tiramisú")
        {
            price = 3.99;
            description = "A delicious Italian dessert made of ladyfinger pastries" +
                            "dipped in coffee, layered with a whipped mixture of eggs, " +
                            "sugar and mascarpone and flavoured with cocoa!";
            spiciness = 0;
            popularity = 5;
        }
        else if(item == "Crispy Apple Pie")
        {
            price = 4.99;
            description = "A classic favorite with a golden, flaky crust, and cinnamon-kissed " +
                            "apple slices. A delightful blend of sweet and tart flavors!";
            spiciness = 0;
            popularity = 4;
        }
        else if(item == "Water")
        {
            price = 0.00;
            description = "A nice cup of ice cold water!";
            spiciness = 0;
            popularity = 2;
        }
        else if(item == "Soft Drink")
        {
            price = 1.99;
            description = "Your choice of any of delicious soda options we offer!";
            spiciness = 0;
            popularity = 3;
        }
        else if(item == "Iced Tea")
        {
            price = 1.99;
            description = "A refreshing glass of freshly house brewed ice tea!";
            spiciness = 0;
            popularity = 4;
        }
        else if(item == "Coffee")
        {
            price = 1.99;
            description = "A hot cup of joe served with a side of sugar and cream!";
            spiciness = 0;
            popularity = 4;
        }

    }

    //all functions that return class details below!
    public String getName()
    {
        return name;
    }

    public String getSideItem()
    {
        return sideItem;
    }

    public String getEggPref()
    {
        return eggPref;
    }

    public double getPrice()
    {
        return price;
    }

    public String getDescription()
    {
        return description;
    }

    public int getSpice()
    {
        return spiciness;
    }

    public int getPopularity()
    {
        return popularity;
    }

    public boolean isBreakfast()
    {
        return isBreakfast;
    }

    public boolean isSingleItem()
    {
        return isSingleItem;
    }
}
