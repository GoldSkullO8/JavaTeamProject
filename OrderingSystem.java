//IS 380 - 1001 Group Project

/*  Group 1:
 *  Herman Larke
 *  Christopher LaRusso
 *  Jonah Molnar
 *  Collin Reed
 *  Valeria Tang
 */

import java.util.Scanner;   //import the scanner class
import java.util.ArrayList; // import the ArrayList class

/*
    This program was written to allow a waiter/waitress of even a customer to navigate
    through a variety of menu options and add items to their order. The user will be able
    to add and remove items as they wish and when they are fully complete, they will be
    able to proceed to payment for the completed order.
*/
public class OrderingSystem
{
    public static void main(String[] args)
    {
        //variable initialization
        ArrayList<menuItem> order = new ArrayList<menuItem>();
        menuItem check;
        String menuChoice;
        boolean orderComplete = false;
        int editOrder = -1;

        System.out.println("Welcome to the Ordering System!");

        //while loop to continue iterating until user is finishing adding items
        while(orderComplete == false)
        {
            //bool to determine when user is finished editing order
            boolean editingComplete = false;
            menuChoice = menuSelect();  //allows user to select menu
            if(menuChoice == "Breakfast")
            {
                //displays breakfast menu if selected
                check = breakfastMenu();
                //checks if nothing was added from breakfast menu before adding to order
                if(check != null)
                {
                    order.add(check);
                }
            }
            else if(menuChoice == "Lunch/Dinner")
            {
                //displays lunch/dinner menu if selected
                check = lunchDinnerMenu();
                //checks if nothing was added from lunch/dinner menu before adding to order
                if(check != null)
                {
                    order.add(check);
                }
            }
            else if(menuChoice == "Beverages")
            {
                //displays beverage menu if selected
                check = beverageMenu();
                //checks if nothing was added from beverages menu before adding to order
                if(check != null)
                {
                    order.add(check);
                }
            }
            else if(menuChoice == "Dessert")
            {
                //displays dessert menu if selected
                check = dessertMenu();
                //checks if nothing was added from dessert menu before adding to order
                if(check != null)
                {
                    order.add(check);
                }
            }
            //checks if user states they are finished with their order
            else if(menuChoice == "Complete Order")
            {
                //checks for empty list to avoid error
                if(order.size() == 0)
                {
                    System.out.println();
                    System.out.println("No items added to order yet!");
                    orderComplete = false;
                }
                else
                {
                    //while loop to continue iterating until user is finished editing
                    while(editingComplete == false)
                    {
                        displayOrder(order);
                        System.out.println();
                        System.out.println();
                        System.out.println("Please select from the following options:");
                        System.out.println("1: Remove Items From Order");
                        System.out.println("2: Add Additional Items To Order");
                        System.out.println("3: Proceed to Payment");
    
                        editOrder = validateSelection();
    
                        switch (editOrder) 
                        {
                            //remove item
                            case 1:
                                //user can remove items from list unless list is empty
                                order = removeOrderItem(order);
                                if(order.size() == 0)
                                {
                                    editingComplete = true;
                                    orderComplete = false;
                                }
                                break;
                            //add additional items
                            case 2:
                                editingComplete = true; //editing completed
                                orderComplete = false;  //need to return to menu selection so order incomplete
                                break;
                            //order complete
                            case 3:
                                editingComplete = true; //no more editing needed
                                orderComplete = true;   //no need to return to menu selection
                                break;
                        
                            //resets selection variable if invalid input
                            default:
                                System.out.println("UNRECOGNIZED INPUT - PLEASE REENTER!");
                                editOrder = -1;
                                break;
                        }
    
                    }
                }
            }
            //last option for if user would like to exit the program immediately
            else if(menuChoice == "Exit")
            {
                System.out.println();
                System.out.println("Thank you for using our ordering system! Have a great day!");
                System.exit(0);     //exits out of program without processing order
            }
        }
        /*
            Last Step!
            Calculates grand total cost of order and processes user's payment!
        */
        processPayment(calculateTotal(order));
    }

    //function to validate whether or not user input is numerical
    public static int validateSelection()
    {
        Scanner keyboard = new Scanner(System.in);
        int selection = -1;

        //while loop to continue prompting for user input until valid
        while(selection == -1)
        {
            //temp variable to store user input
            String temp = keyboard.nextLine();

            //for loop to iterate through all characters in user input to check if numerical
            for(int i = 0; i<temp.length();i++)
            {
                //if not numerical, immediately break and give error message
                if(Character.isDigit(temp.charAt(i)) == false)
                {
                    selection = -1;
                    System.out.println("INVALID ENTRY. NUMERIC ENTRY ONLY!");
                    break;
                }
                //if at end of string and last character is numeric
                if(i == (temp.length()-1) && Character.isDigit(temp.charAt(i)) == true)
                {
                    //sets selection variable to user input
                    selection = Integer.parseInt(temp);
                }
            }
        }

        //returns user input only if numerical variable
        return selection;
    }

    /*
        This function allows the user to select the menu they would like to
        order from!
    */
    public static String menuSelect()
    {
        //needed variable declaration
        int selection = -1;
        String menuChoice = " ";

        //do while loop to continue looping for user input until valid input is recieved
        do
        {
            //menu selection layout
            System.out.println();
            System.out.println("Please select from the following menu options: ");
            System.out.println("1: Breakfast");
            System.out.println("2: Lunch/Dinner");
            System.out.println("3: Beverages");
            System.out.println("4: Dessert");
            System.out.println("5: Complete Order");
            System.out.println("0: Exit Program");

            //validates that input is numeric
            selection = validateSelection();

            //uses user input to determine menu choice
            switch (selection)
            {
                case 1:
                    menuChoice = "Breakfast";
                    break;
                case 2:
                    menuChoice = "Lunch/Dinner";
                    break;
                case 3:
                    menuChoice = "Beverages";
                    break;
                case 4:
                    menuChoice = "Dessert";
                    break;
                case 5:
                    menuChoice = "Complete Order";
                    break;
                case 0:
                    menuChoice = "Exit";
                    break;
                
                //resets selection variable if invalid input
                default:
                    System.out.println("UNRECOGNIZED INPUT - PLEASE REENTER!");
                    selection = -1;
                    break;
            }    
        } while (selection == -1);
        
        //when validated, returns menu chosen by user
        return menuChoice;
    }

    /*
        This function allows the user to navigate through the breakfast menu and add their
        choice of a breakfast entree, breakfast side, and specify how they would like their
        eggs cooked. This selection is then added to a list to keep track of the full order
    */
    public static menuItem breakfastMenu()
    {
        //necessary variable declarations
        menuItem orderItem;
        boolean confirmOrderItem = false;

        //selection variables
        int selection = -1;
        String bEntree = "";
        String bSide = "";
        String bEgg = "";
        
        do
        {
            //do while loop that lets user select breakfast entree
            do
            {
                //menu layout
                System.out.println();
                System.out.println("All Breakfast Entrees come with your choice of bacon, sausage, or fruit");
                System.out.println("and two eggs cooked to your preference!");
                System.out.println();
                System.out.println("Please select from the following breakfast entrees:");
                System.out.println("1: Breakfast Burrito($12.99) - Scrambled eggs, melted cheese, savory " +
                                    "bacon or sausage, and zesty salsa, all wrapped in a warm flour tortilla!");
                System.out.println("2: French Toast($11.99) - Two pieces of French Toast topped with " +
                                    "butter, powdered sugar, and a side of syrup!");
                System.out.println("3: Meat Lovers Omelette($11.99) - Pepper Jack cheese, bacon, ham, sausage " +
                                    "and green onion");
                System.out.println("4: Pancakes($9.99) - Stack of two buttermilk Pancakes topped with " + 
                                    "butter and a side of syrup!");
                System.out.println("5: Waffles($8.99) - Stack of two crunchy Waffles topped with " +
                                    "butter and side of syrup!");
                System.out.println("0: Return to Previous Menu");

                selection = validateSelection();

                //switch statement that sets breakfast entree selection
                switch (selection)
                {
                    case 0:
                        return null;
                    case 1:
                        bEntree = "Breakfast Burrito";
                        break;
                    case 2:
                        bEntree = "French Toast";
                        break;
                    case 3:
                        bEntree = "Meat Lovers Omlette";
                        break;
                    case 4:
                        bEntree = "Pancakes";
                        break;
                    case 5:
                        bEntree = "Waffles";
                        break;
        
                    //resets selection variable if invalid
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            //do while loop that lets user select breakfast side
            do
            {
                System.out.println();
                System.out.println("Please select from the following breakfast sides:");
                System.out.println("1: Bacon");
                System.out.println("2: Breakfast Potatoes");
                System.out.println("3: Fruit");
                System.out.println("4: Hashbrowns");
                System.out.println("5: Sausage");

                //verifies selection input is numeric
                selection = validateSelection();

                //switch statement for choosing breakfast side
                switch (selection)
                {
                    case 1:
                        bSide = "Bacon";
                        break;
                    case 2:
                        bSide = "Breakfast Potatoes";
                        break;
                    case 3:
                        bSide = "Fruit";
                        break;
                    case 4:
                        bSide = "Hashbrowns";
                        break;
                    case 5:
                        bSide = "Sausage";
                        break;
                    
                    
        
                    //resets selection variable if invalid
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            //do while loop that lets user select way eggs are cooked
            do
            {
                System.out.println();
                System.out.println("Select the way you would like the eggs cooked:");
                System.out.println("1: Over-Easy");
                System.out.println("2: Over-Hard");
                System.out.println("3: Scrambled");
                System.out.println("4: Sunny Side Up");
        
                selection = validateSelection();
        
                //switch statement for setting egg choice
                switch (selection)
                {
                    case 1:
                        bEgg = "Over-Easy";
                        break;
                    case 2:
                        bEgg = "Over-Hard";
                        break;
                    case 3:
                        bEgg = "Scrambled";
                        break;
                    case 4:
                        bEgg = "Sunny Side Up";
                        break;
                
                    //resets selection variable if invalid
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            //adds selected items to menuItem class
            orderItem = new menuItem(bEntree, bSide, bEgg);

            //do while loop to confirm user wants to add item to order
            do
            {
                System.out.println();
                System.out.println("Would you like to add the " + orderItem.getName() + " with a side of " +
                                    orderItem.getSideItem() + " and " + orderItem.getEggPref() + " eggs to your order?");
                System.out.println("1: Yes");
                System.out.println("2: No");
        
                //validates that selection variable is numeric
                selection = validateSelection();
        
                switch (selection)
                {
                    case 1:
                        confirmOrderItem = true;
                        break;
                    case 2:
                        confirmOrderItem = false;
                        break;
                
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);
        }while(confirmOrderItem == false);

        return orderItem;
    }

    /*
        This function allows the user to navigate through the Lunch/Dinner menu and add their
        choice of a main entree and side dish to their order!
        This selection is then added to a list to keep track of the full order
    */
    public static menuItem lunchDinnerMenu()
    {
        menuItem orderItem;
        boolean confirmOrderItem = false;

        int selection = -1;
        String ldEntree = "";
        String ldSide = "";
        
        do
        {
            //do while loop that lets user select their lunch/dinner entree
            do
            {
                System.out.println();
                System.out.println("All Lunch and Dinner entrees come with a side of your choice!");
                System.out.println("Please select from the following entree options:");
                System.out.println();
                System.out.println("1: Buffalo Bone-in Wings($10.99) - Crispy chicken wings tossed in " + 
                                        "spicy Buffalo sauce, served with ranch or blue cheese dressing. " + 
                                        "Perfect for those craving tender chicken with bold flavor!");
                System.out.println("2: Cheeseburger($12.99) - Juicy angus beef patty topped with cheese, " +
                                        "lettuce, tomato, and red onion!");
                System.out.println("3: Chicken Piccata($14.99) - Crispy pan seared chicken breast served with " +
                                        "our housemade lemon caper sauce!");
                System.out.println("4: Crispy Chicken Tenders($12.99) - Juicy chicken tenders with a " +
                                        "crunchy coating served with your choice of dipping sauce!");
                System.out.println("5: Salmon Fillet($16.99) - Seasoned with butter, fresh herbs, and lemon " +
                                        "juice, then baked until it's nice and flaky!");
                System.out.println("6: Tomahawk Steak($39.99) - 20oz freshly sourced beef served with a side " +
                                        "of house steak sauce!");
                System.out.println("0: Return to Previous Menu");

                selection = validateSelection();

                switch (selection)
                {
                    case 0:
                        return null;
                    case 1:
                        ldEntree = "Buffalo Bone-in Wings";
                        break;
                    case 2:
                        ldEntree = "Cheeseburger";
                        break;
                    case 3:
                        ldEntree = "Chicken Piccata";
                        break;
                    case 4:
                        ldEntree = "Crispy Chicken Tenders";
                        break;
                    case 5:
                        ldEntree = "Salmon Fillet";
                        break;
                    case 6:
                        ldEntree = "Tomahawk Steak";
                        break;
        
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            //do while loop that lets user select lunch/dinner side
            do
            {
                System.out.println();
                System.out.println("Please select from the following Lunch/Dinner sides:");
                System.out.println("1: Caesar Salad");
                System.out.println("2: French Fries");
                System.out.println("3: Garden Salad");
                System.out.println("4: Mac & Cheese");
                System.out.println("5: Onion Rings");


                selection = validateSelection();

                switch (selection)
                {
                    case 1:
                        ldSide = "Caesar Salad";
                        break;
                    case 2:
                        ldSide = "French Fries";
                        break;
                    case 3:
                        ldSide = "Garden Salad";
                        break;
                    case 4:
                        ldSide = "Mac & Cheese";
                        break;
                    case 5:
                        ldSide = "Onion Rings";
                        break;
        
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            orderItem = new menuItem(ldEntree, ldSide);

            //do while loop to confirm user wants to add item to order
            do
            {
                System.out.println();
                System.out.println("Would you like to add the " + orderItem.getName() + " with a side of " +
                                    orderItem.getSideItem() + " to your order?");
                System.out.println("1: Yes");
                System.out.println("2: No");
        
                selection = validateSelection();
        
                switch (selection)
                {
                    case 1:
                        confirmOrderItem = true;
                        break;
                    case 2:
                        confirmOrderItem = false;
                        break;
                
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);
        }while(confirmOrderItem == false);

        //returns menuItem variable if user confirms they would like what they selected
        return orderItem;
    }

    /*
        This function allows the user to navigate through the beverage menu and add their
        choice of a drink to their order!
        This selection is then added to a list to keep track of the full order
    */
    public static menuItem beverageMenu()
    {
        //necessary variable declarations
        menuItem orderItem;
        boolean confirmOrderItem = false;

        //selection variables
        int selection = -1;
        String beverage = "";
        
        do
        {
            //do while loop that lets user select beverage
            do
            {
                //menu layout
                System.out.println();
                System.out.println("We have multiple beverage options for you to choose from with unlimited refills!");
                System.out.println();
                System.out.println("Please select from the following beverages:");
                System.out.println("1: Coffee($1.99) - A hot cup of joe served with a side of sugar and cream!");
                System.out.println("2: Iced Tea($1.99) - A refreshing glass of freshly house brewed ice tea!");
                System.out.println("3: Soft Drink($1.99) - Your choice of any of delicious soda options we offer!");
                System.out.println("4: Water($0.00) - A nice cup of ice cold water!");
                System.out.println("0: Return to Previous Menu");

                selection = validateSelection();

                //switch statement that sets breakfast entree selection
                switch (selection)
                {
                    case 0:
                        return null;
                    case 1:
                        beverage = "Coffee";
                        break;
                    case 2:
                        beverage = "Iced Tea";
                        break;
                    case 3:
                        beverage = "Soft Drink";
                        break;
                    case 4:
                        beverage = "Water";
                        break;
        
                    //resets selection variable if invalid
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            //adds selected items to menuItem class
            orderItem = new menuItem(beverage);

            //do while loop to confirm user wants to add item to order
            do
            {
                System.out.println();
                System.out.println("Would you like to add the " + orderItem.getName() + " to your order?");
                System.out.println("1: Yes");
                System.out.println("2: No");
        
                //validates that selection variable is numeric
                selection = validateSelection();
        
                switch (selection)
                {
                    case 1:
                        confirmOrderItem = true;
                        break;
                    case 2:
                        confirmOrderItem = false;
                        break;
                
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);
        }while(confirmOrderItem == false);

        return orderItem;
    }

    /*
        This function allows the user to navigate through the dessert menu and add their
        choice of a dessert to their order!
        This selection is then added to a list to keep track of the full order
    */
    public static menuItem dessertMenu()
    {
        //necessary variable declarations
        menuItem orderItem;
        boolean confirmOrderItem = false;

        //selection variables
        int selection = -1;
        String dessert = "";
        
        do
        {
            //do while loop that lets user select dessert
            do
            {
                //menu layout
                System.out.println();
                System.out.println("We offer a variety of dessert choices to appeal to your sweet tooth!");
                System.out.println();
                System.out.println("Please select from the following dessert options:");
                System.out.println("1: Chocolate Brownie Sundae($3.99) - Creamy vanilla ice cream topped with " +
                                        "our rich chocolate brownie and hot fudge sauce!");
                System.out.println("2: Classic New York Cheesecake($2.99) - A slice of delicious Classic New " +
                                        "York Cheesecake topped with whipped cream and fresh strawberries!");
                System.out.println("3: Crispy Apple Pie($4.99) - A classic favorite with a golden, flaky crust, " +
                                        "and cinnamon-kissed apple slices. A delightful blend of sweet and tart flavors!");
                System.out.println("4: Tiramisú($3.99) - A delicious Italian dessert made of ladyfinger pastries" +
                    "dipped in coffee, layered with a whipped mixture of eggs, sugar and mascarpone and flavoured with cocoa!");
                System.out.println("0: Return to Previous Menu");

                selection = validateSelection();

                //switch statement that sets breakfast entree selection
                switch (selection)
                {
                    case 0:
                        return null;
                    case 1:
                        dessert = "Chocolate Brownie Sundae";
                        break;
                    case 2:
                        dessert = "Classic New York Cheesecake";
                        break;
                    case 3:
                        dessert = "Crispy Apple Pie";
                        break;
                    case 4:
                        dessert = "Tiramisú";
                        break;
        
                    //resets selection variable if invalid
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);

            //adds selected items to menuItem class
            orderItem = new menuItem(dessert);

            //do while loop to confirm user wants to add item to order
            do
            {
                System.out.println();
                System.out.println("Would you like to add the " + orderItem.getName() + " to your order?");
                System.out.println("1: Yes");
                System.out.println("2: No");
        
                //validates that selection variable is numeric
                selection = validateSelection();
        
                switch (selection)
                {
                    case 1:
                        confirmOrderItem = true;
                        break;
                    case 2:
                        confirmOrderItem = false;
                        break;
                
                    default:
                        System.out.println("INVALID SELECTION. PLEASE REENTER!");
                        selection = -1;
                        break;
                }    
            } while (selection == -1);
        }while(confirmOrderItem == false);

        return orderItem;
    }

    /**
        This function displays all menu items currently in order list
        @param  order   List of order items previously determined by the user
    */
    public static void displayOrder(ArrayList<menuItem> order)
    {

        System.out.println();
        System.out.print("Order Details");
    
        for(int i = 0; i < order.size(); i++)
        {
            System.out.println();
            System.out.print((i+1) + ": " + (order.get(i)).getName());
            if((order.get(i)).isSingleItem() == false)
            {
                System.out.print(" with a side of " + (order.get(i)).getSideItem());
            }
            if((order.get(i)).isBreakfast() == true)
            {
                System.out.print(" and " + (order.get(i)).getEggPref() + " Eggs");
            }
            System.out.println();
            System.out.println("   Description: " + (order.get(i).getDescription()));
            System.out.printf("   Price: $%.2f\n",(order.get(i)).getPrice());
            System.out.println("   Spiciness: " + (order.get(i)).getSpice() + "/10");
            System.out.print("   Popularity: " + (order.get(i)).getPopularity() + "/5");
        }
    }

    /**
        This function allows the user to remove items from their order
        @param  order   List of order items previously determined by the user
    */
    public static ArrayList<menuItem> removeOrderItem(ArrayList<menuItem> order)
    {
        int selection = -1;
        boolean removeComplete = false;

        while(removeComplete == false)
        {
            displayOrder(order);

            System.out.println();
            System.out.print("Please Enter the number of the item you wish to remove or 0 when finished: ");
            selection = validateSelection();
            if(selection > order.size() || selection < 0)
            {
                System.out.println();
                System.out.println("Invalid Selecition, please try again");
            }
            else if(selection == 0)
            {
                removeComplete = true;
            }
            else
            {
                order.remove(selection-1);
            }
        }
        
        return order;
    }

    /**
        This adds the cost of all menu items in order list, calculates the subtotal,
        tax, total, and prompts user to add a tip to calculate grand total;
        payments type.
        @param  order   List of order items previously determined by the user
    */
    public static double calculateTotal(ArrayList<menuItem> order)
    {
        //variables to calculate subtotal, total, and tip
        double subtotal = 0.0;
        double tax = 0.0;
        double total = 0.0;
        double tip = -1.0;
        boolean noError = true;
        Scanner keyboard = new Scanner(System.in);

        //iterates though all items in list and adds their price to subtotal
        for(int i = 0; i < order.size(); i++)
        {
            subtotal += (order.get(i)).getPrice();
        }
        tax = subtotal*(0.08375);
        total = subtotal + tax;

        //displays totals before tip for user
        System.out.printf("\nSubtotal = $%.2f\n", subtotal);
        System.out.printf("Tax(8.375%%) = $%.2f\n", tax);
        System.out.printf("Total(before tip) = $%.2f\n", total);

        //while loop continues to iterate until valid input from user is received
        while (tip == -1.0)
        {
            noError = true;
            try 
            {
                System.out.println();
                System.out.print("Please enter your tip amount: ");
                tip = Double.parseDouble(keyboard.nextLine());
            } 
            catch (NumberFormatException e)
            {
                // TODO: handle exception
                tip = -1.0;
                noError = false;
                System.out.println("Error: Only Numeric Entries.");
            }
            if(tip < 0.0 && noError == true)
            {
                tip = -1.0;
                System.out.println("Tip must be greater than or equal to zero!");
            }    
        }
        total += tip;   //adds tip to total
        //displays finalized totals after tip
        System.out.printf("\nSubtotal = $%.2f\n", subtotal);
        System.out.printf("Tax(8.375%%) = $%.2f\n", tax);
        System.out.printf("Tip = $%.2f\n", tip);
        System.out.printf("Total(after tip) = $%.2f\n", total);

        //returns finalized total for further calculations
        return total;
    }

    /**
        This allows the user to select a payment type and pay for the grand total
        of their order!
        @param  total   Grand total cost including tip of food order
    */
    public static void processPayment(double total)
    {
        int selection = -1;
        int cardChoice = -1;
        double cashPayment = -1.0;
        boolean noError = true;
        Scanner keyboard = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("How will you be paying this evening?");
            System.out.println("1: Cash");
            System.out.println("2: Card");
        
            //validates that selection variable is numeric
            selection = validateSelection();
        
            switch (selection)
            {
                case 1:
                    //while loop continues to iterate until valid input from user is received
                    while (cashPayment == -1.0)
                    {
                        System.out.printf("\nTotal = $%.2f", total);
                        noError = true;
                        try 
                        {
                            System.out.println();
                            System.out.print("Please enter amount of cash: ");
                            cashPayment = Double.parseDouble(keyboard.nextLine());
                        } 
                        catch (NumberFormatException e)
                        {
                            // TODO: handle exception
                            cashPayment = -1.0;
                            noError = false;
                            System.out.println("Error: Only Numeric Entries.");
                        }
                        if(cashPayment < total && noError == true)
                        {
                            cashPayment = -1.0;
                        System.out.println("Must pay with exact change or greater!");
                        }    
                    }
                    if(cashPayment > total)
                    {

                        System.out.printf("\nChange Due = $%.2f\n", (cashPayment-total));
                    }
                    System.out.println("Cash Payment Processed!");
                    System.out.println("Thank you for using our ordering system! Have a great day!");
                    break;
                case 2:
                    do
                    {
                        System.out.printf("\nTotal = $%.2f\n", total);
                        System.out.println("What kind of card will you be using?");
                        System.out.println("1: Debit Card");
                        System.out.println("2: AMEX");
                        System.out.println("3: DISCOVER");
                        System.out.println("4: MASTERCARD");
                        System.out.println("5: VISA");

                        //validates that selection variable is numeric
                        cardChoice = validateSelection();
                        System.out.println();
                
                        switch (cardChoice)
                        {
                            case 1:
                                System.out.printf("Debit Card Payment of $%.2f Processed!\n", total);
                                break;
                            case 2:
                                System.out.printf("AMEX Payment of $%.2f Processed!\n", total);
                                break;
                            case 3:
                                System.out.printf("DISCOVER Payment of $%.2f Processed!\n", total);
                                break;
                            case 4:
                                System.out.printf("MASTERCARD Payment of $%.2f Processed!\n", total);
                                break;
                            case 5:
                                System.out.printf("VISA Payment of $%.2f Processed!\n", total);
                                break;
                        
                            default:
                                System.out.println("INVALID SELECTION. PLEASE REENTER!");
                                cardChoice = -1;
                                break;
                        }
                    }while(cardChoice == -1);
                    System.out.println("Thank you for using our ordering system! Have a great day!");

                    break;
                
                default:
                    System.out.println("INVALID SELECTION. PLEASE REENTER!");
                    selection = -1;
                    break;
            }
        }while(selection == -1);
    }
}
