

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Write a description of JavaFX class SmartPhone here.
 *
 *  Chapter 12
 *  Programming Challenge 4
 *  Menu driven GUI
 *  
 * @author Gerald Glass
 * @version (1.0 - 11/14/2018)
 */
public class SmartPhone extends Application
{
    //Class Wide Variables
    RadioButton rb_8Gig; final double data8GigFee = 45;
    RadioButton rb_16Gig; final double data16GigFee = 65;
    RadioButton rb_20Gig; final double data20GigFee = 99;
    RadioButton rb_Model100; final double Mod100Price = 299.95;
    RadioButton rb_Model110; final double Mod110Price = 399.95;
    RadioButton rb_Model200; final double Mod200Price= 499.95;
    CheckBox cb_Insurance; final double InsuranceFee = 5;
    CheckBox cb_HotSpot; final double HotSpotFee = 10;
    final double SalesTaxRate = .06;
      
    Label totalCostValue, phoneCostValue, dataCostValue, optionsCostValue, taxValue;
    Label phone, tax, plan, options, total;

    @Override
    public void start(Stage stage) throws Exception
    {
        //Create box for Page heading
        HBox hBox_Heading = createHeading();
        
        // Create Vertical Box to Contain Data PLan Block
        VBox dataPlans = createDataPlanBlock ();
        
        //Create TitledPane for Data Plans
        TitledPane tp_DataPlan = new TitledPane("Data Plans",dataPlans);
        tp_DataPlan.setCollapsible(false);
        
        // Create Vertical Box to Contain Phone Block
        VBox Phones = createPhoneBlock ();
        
         //Create TitledPane for Phones
        TitledPane tp_Phones = new TitledPane("Phones",Phones);
        tp_Phones.setCollapsible(false);
        
        //Create Horiztonal Box to hold Above Titled Panes
        HBox requiredChoices = new HBox(20,tp_Phones, tp_DataPlan);
        requiredChoices.setAlignment(Pos.CENTER);
        
        //Create Horizontal Box to contain Option Block
        HBox options = createOptionsBlock();
        options.setAlignment(Pos.CENTER);
        
        TitledPane tp_Options = new TitledPane("Plan Options", options);
        tp_Options.setCollapsible(false);
        
        //Create Vertical Box for Costs Block
        
        VBox costs = createCostsBlock();
        
        
        //Vertical Box for Scene
        VBox centerPane = new VBox(15,requiredChoices, tp_Options,costs);
        
        //Create Border Pane
        BorderPane pane = new BorderPane();
        pane.setTop(hBox_Heading);
        pane.setCenter(centerPane);
        pane.setRight(costs);
        pane.setLeft(new Label("    "));
        
        

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,300);
        stage.setTitle("Smart Phone Packages");
        stage.setScene(scene);
        calculateCosts();

        // Show the Stage (window)
        stage.show();
    }
    
    /***********************************
     * Event Handler for 
     * Radio Buttons and Check Boxes
     ***********************************/
    
    EventHandler myeh = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            calculateCosts();
        }
        
    };
    
    /***********************************
     * Method to Calculate Costs
     ***********************************/
    
    private void calculateCosts()
    {
        double cost = 0;
        double temp = 0;
        
        //Get Phone Cost
        temp = phoneSelected();
        cost += temp;
        phoneCostValue.setText(setValue(temp));
        
        temp = temp*SalesTaxRate;
        cost += temp;
        taxValue.setText(setValue(temp));
        
        temp = planSelected();
        cost += temp;
        dataCostValue.setText(setValue(temp));
        
        temp = optionsSelected();
        cost += temp;
        optionsCostValue.setText(setValue(temp));
               
        totalCostValue.setText(setValue(cost));
            
           
    }
    
    /***********************************
     * Method setValue
     ***********************************/
    
    private String setValue(double value)
    {
        String output;
        
        // Convert Double to string
        output = String.format("$%9.2f",value);
        
        return output;
    }
    
    /***********************************
     * Method to Determine Which Phone
     ***********************************/
    
    private double phoneSelected()
    {
        double cost = 0;
        
        //Check Radio Buttons for selection
        if(rb_Model100.isSelected())
            cost = Mod100Price;
        else if (rb_Model110.isSelected())
            cost = Mod110Price;
        else if (rb_Model200.isSelected())
            cost=Mod200Price;
        else
            cost=0;
            
        return cost;     
    }
    
    
    /***********************************
     * Method to Determine Which Plan
     ***********************************/
    
    private double planSelected()
    {
        double cost = 0;
        
        //Check Radio Buttons for selection
        if(rb_8Gig.isSelected())
            cost = data8GigFee;
        else if (rb_16Gig.isSelected())
            cost = data16GigFee;
        else if (rb_20Gig.isSelected())
            cost = data20GigFee;
        else
            cost = 0;
            
        return cost;     
    }
    
    /***********************************
     * Method to Determine Which Options
     ***********************************/
    
    private double optionsSelected()
    {
        double cost = 0;
        
        cost += cb_Insurance.isSelected() ? InsuranceFee:0;
        cost += cb_HotSpot.isSelected()?HotSpotFee:0;
            
        return cost;     
    }
    
    /***********************************
     * Method to Create Page Heading
     ***********************************/
     
    private HBox createHeading ()
    {
        Label lb_Heading = new Label("\nRichard's Mobile Phones\n  ");
        lb_Heading.setId("label-heading");
        HBox hbox = new HBox(lb_Heading);
        hbox.setAlignment(Pos.CENTER);
        
        return hbox;
        
    }

    /***********************************
     * Method to Create Data Plan Bock
     ***********************************/
    
    private VBox createDataPlanBlock ()
    {
        //Create Radio Buttons for Data Plans
        rb_8Gig = new RadioButton("8 Gigabytes/Month");
        rb_8Gig.setOnAction(myeh);
        rb_16Gig = new RadioButton("16 Gigabytes/Month");
        rb_16Gig.setOnAction(myeh);
        rb_20Gig = new RadioButton("20 Gigabytes/Month");
        rb_20Gig.setOnAction(myeh);
        
        //Create Toggle Group and Assign buttons to Group
        ToggleGroup tg_DataPlan = new ToggleGroup();
        rb_8Gig.setToggleGroup(tg_DataPlan);
        rb_16Gig.setToggleGroup(tg_DataPlan);
        rb_20Gig.setToggleGroup(tg_DataPlan);
        
        // Place Radio Buttons into a Vertical Box
        VBox box = new VBox(10,rb_8Gig,rb_16Gig,rb_20Gig);
        
        return box;
    }
    
    /***********************************
     * Method to Create Phone Bock
     ***********************************/
    
    private VBox createPhoneBlock ()
    {
        //Create Radio Buttons for Data Plans
        rb_Model100 = new RadioButton("Model 100: $299.95");
        rb_Model100.setOnAction(myeh);
        rb_Model110 = new RadioButton("Model 110: $399.95");
        rb_Model110.setOnAction(myeh);
        rb_Model200 = new RadioButton("Model 200: $499.95");
        rb_Model200.setOnAction(myeh);
        
        //Create Toggle Group and Assign buttons to Group
        ToggleGroup tg_Phone = new ToggleGroup();
        rb_Model100.setToggleGroup(tg_Phone);
        rb_Model110.setToggleGroup(tg_Phone);
        rb_Model200.setToggleGroup(tg_Phone);
        
        // Place Radio Buttons into a Vertical Box
        VBox box = new VBox(10,rb_Model100,rb_Model110,rb_Model200);
        
        return box;
    }
    
    /***********************************
     * Method to Create Options Bock
     ***********************************/
    
    private HBox createOptionsBlock ()
    {
        //Create Radio Buttons for Data Plans
        cb_Insurance = new CheckBox("Phone Replacement\nInsurance");
        cb_Insurance.setOnAction(myeh);
        cb_HotSpot = new CheckBox("WiFi Hotspot\nCapability");
        cb_HotSpot.setOnAction(myeh);       
        
        // Place Radio Buttons into a Vertical Box
        HBox box = new HBox(20,cb_Insurance, cb_HotSpot);
        
        return box;
    }
    
    /***********************************
     * Method to Create Costs Bock
     ***********************************/
    
    private VBox createCostsBlock ()
    {
        //Create Labels for Costs
        phone = new Label(  "Phone");
        plan = new Label(   "Plan");
        options = new Label("Options ");
        total = new Label(  "Total Cost ");
        tax = new Label("Sales Tax");
        
        phoneCostValue = new Label("$0.00");
        taxValue = new Label ("$0.00");
        dataCostValue = new Label("$0.00");
        optionsCostValue = new Label("$0.00");
        totalCostValue = new Label("$0.00");
        
        Label costHeading = new Label("Costs");
        
        GridPane gp_Costs = new GridPane();
        gp_Costs.add(phone,0,0);
        gp_Costs.add(tax,0,1);
        gp_Costs.add(plan,0,2);
        gp_Costs.add(options,0,3);
        gp_Costs.add(total,0,4); 
        gp_Costs.add(phoneCostValue,1,0);
        gp_Costs.add(taxValue,1,1);
        gp_Costs.add(dataCostValue,1,2);
        gp_Costs.add(optionsCostValue,1,3);
        gp_Costs.add(totalCostValue,1,4);
        
        
        // Place Radio Buttons into a Vertical Box
        VBox box = new VBox(15,costHeading,gp_Costs);
        box.setPadding(new Insets(10));
                
        return box;
    }
}
