//Glossary
//killfeed: all of the text and images at the top right of the UI.
//kill entry: 1 line of text within the killfeed, shows information on a player killing another player in the game
//pos: same thing as a kill entry (A kill entry can be stored in any pos. pos0, pos1, pos2.. etc.)
//line: same thing as kill entry

var Killfeed = pc.createScript('killfeed');


//This is how you should declare variables so that they are accessible everywhere in this script and accessible from other scripts (Script attributes). See code example below ("Killfeed.attributes.add").
//Click "parse" in editor to update the editor with newly added script attributes.
//How to access script attributes
//1. Access script attributes in this script with "this.kfsize" or "this.timers[i]".
//2. Access script attributes within "functions that stored as variables" (e.g. onKillfeedAdd) in this script using the following method:
//(Note: can also get entity as a script attribute at the top of code (declare) and choose which entity in editor. (better probably))
//var killfeedgroup = this.entity.findByName('killfeedgroup'); in initilize (get the entity as a variable like this): 
//killfeedgroup.script.killfeed.timers[i] = 0; access script member like this. Here I am saying get the i element in the timers array in the script called killfeed.js on the entity which i have stored as killfeedgroup (I think this is "an instance" of the script).
//3. Access script attributes in other scripts by doing the same thing as "2." . Probably (I haven't done that in this system.)

//Script attributes: https://developer.playcanvas.com/en/user-manual/scripting/script-attributes/
//Scripting fundamentals: https://developer.playcanvas.com/en/user-manual/scripting/creating-new/
//Scripting: https://developer.playcanvas.com/en/user-manual/scripting/anatomy/
//Events https://developer.playcanvas.com/en/user-manual/scripting/communication/
//I had to google my issues in coding many times but I never just found the answer, just learnt how to do all the things I needed and more. I made this whole system myself no examples of killfeeds out there.
//Note to self: You can literally code anything. Start with reading the user manual of the entire sections that relate to what you're doing all the way from the basics if unsure. e.g. Playcanvas scripting + user interface (Don't think about speed. don't rush, @set yourself time@ @do it in advance@. "Good things take time").

//The killfeedgroup entity in the editor is a "layout group", so are the "pos0, pos1..." entities. Layout groups help position entities on the screen.
//I am moving the kill entries on the screen by using reparent() to change the order of the "pos0, pos1..." entities in the hierarchy programmatically. Order on hierarchy = order they appear (load order, I think it's called).

//Layout groups https://developer.playcanvas.com/en/user-manual/user-interface/layout-groups/
//Read everything in the user interface section of that manual to create the layout group elements correctly. "Add a Layout Group by adding the LayoutGroup Component to an existing Element Entity." (in hierarchy: + -> User Interface -> Group Element then add component -> user interface -> layout group)

///My toubleshooting console.logs have been marked with /// slashes. Ctrl+f /// or just Ctrl+f console.log to enable them again if needed.

//Used to store entities: pos's, p1's, p2's
//pos's = the entity that represents 1 line of the kill feed
//p1's p2's the text entitity in the line that shows the player names for the player that scored a kill (p1) and the player that died (p2).
Killfeed.attributes.add('posArray', {
    type: 'entity',
    array: true,
    default: [""]
});

//amount of pos's
Killfeed.attributes.add('kfsize', {
    type: 'number',
    default: 7
});

//enbld array. Each element represents the pos's pos0, pos1, pos2... the value is a that boolean shows enabled/not enabled. 1 = enabled
Killfeed.attributes.add('enbld', {
    type: 'number',
    array: true,
    default: [0]
});

//array of numbers. Each element represents the pos's pos0, pos1, pos2... the value is a number that is a timer for pos0, pos1, pos2...
Killfeed.attributes.add('timers', {
    type: 'number',
    array: true,
    default: [5.0]
});

//timerActive array. Each element represents the pos's pos0, pos1, pos2... the value is a boolean that shows that the timer is active/timer is not active.
//1 = active. Active means it it being counted. (updated every frame or not)
Killfeed.attributes.add('timerActive', {
    type: 'number',
    array: true,
    default: [0]
});



// initialize code called once per entity
Killfeed.prototype.initialize = function()
{
    //this.killfeedgroup = this.entity.findByName('killfeedgroup');
    var killfeedgroup = this.entity.findByName('killfeedgroup');

   

    //How to access entities in the posArray
    //this.posArray[(posNum)*3+(0 for pos, 1 for p1, 2 for p2)]

    //Set default text for only first 3 lines (for debugging if you see the word "Text" come up on the kill feed)
    //Set Text
    //this.posArray[0*3+1].element.text = "n1";
    //Set Text
    //this.posArray[0*3+2].element.text = "n1";
        
    //Set Text
    //this.posArray[1*3+1].element.text = "n2";
    //Set Text
    //this.posArray[1*3+2].element.text = "n2";
        
    //Set Text
    //this.posArray[2*3+1].element.text = "n3";
    //Set Text
    //this.posArray[2*3+2].element.text = "n3";



    //All disabled to start with
    this.posArray[0*3+0].enabled = false;
    this.posArray[1*3+0].enabled = false;
    this.posArray[2*3+0].enabled = false;
    this.posArray[3*3+0].enabled = false;
    this.posArray[4*3+0].enabled = false;
    this.posArray[5*3+0].enabled = false;
    this.posArray[6*3+0].enabled = false;
    


    //Timer values initially
    this.timers[0] = 5;
    this.timers[1] = 5;
    this.timers[2] = 5;
    this.timers[3] = 5;
    this.timers[4] = 5;
    this.timers[5] = 5;
    this.timers[6] = 5;
    this.timerActive[0] = 0;
    this.timerActive[1] = 0;
    this.timerActive[2] = 0;
    this.timerActive[3] = 0;
    this.timerActive[4] = 0;
    this.timerActive[5] = 0;
    this.timerActive[6] = 0;

    //Method that adds new kill
    //Function used to add a new entry to the killfeed
    //This function will run when a kill entry is added by firing an application event called 'killfeed:add' in any other script in playcanvas.
    //Code to make this function run when event is fired is below... @this.app.on('killfeed:add', onKillfeedAdd);@
    var onKillfeedAdd = function(player1, player2)
    {
        //Variable to keep track of whether there is "space" for the kill entry. If there is no space gonna make space
        //space = a disabled kill entry to apply names to
        //make space = replace the oldest kill entry by checking timers
        var spaceForThisKillEntry = 0;

        //variables for checking timers for making space
        let entityWithLongestTimer = 99;
        let longestTimer = 0;
        
        ///console.log("In the event code. Running onKillfeedAdd function");
        

        //player1 = "fire hazard";
        //player2 = "roblox";

        //Adding a kill entry
        //If a pos is disabled then it is not being used so it can be used to display a kill entry
        //Find the lowest disabled pos
        for (var i = 0; i < killfeedgroup.script.killfeed.kfsize; i++)
        {
            ///console.log("In loop for kfsize. Element : "+i);
            ///console.log("enbld[i]: "+killfeedgroup.script.killfeed.enbld[i]);
            if(killfeedgroup.script.killfeed.enbld[i] == 0)
            {
                //Found a disabled pos for this new kill entry
                //lowest disabled entity = i

                spaceForThisKillEntry = 1;

                ///console.log("Lowest disabled pos found. There is space for this kill entry.");
                
                //Setting a new entry

                //Enable the element so it's visible
                //Make the line entity (e.g. pos0) visible
                killfeedgroup.script.killfeed.posArray[i*3+0].enabled = true;

                ///console.log("pos enabled");

                //Set text player 1
                killfeedgroup.script.killfeed.posArray[i*3+1].element.text = player1;
                ///console.log("player1 set");

                //Set text player 2
                killfeedgroup.script.killfeed.posArray[i*3+2].element.text = player2;
                ///console.log("player2 set");

                

                //Re-order pos.
                //Send the pos to the bottom of the layout group so that it is displayed at the bottom on the screen.
                //The latest kills are displayed at the bottom of the killfeed
                killfeedgroup.script.killfeed.posArray[i*3+0].reparent(killfeedgroup, killfeedgroup.script.killfeed.kfsize-1);
                ///console.log("reparented the pos");
                
                
                

                //Set timer to 0 for that line
                ///console.log(killfeedgroup.script.killfeed.timers[i]);
                killfeedgroup.script.killfeed.timers[i] = 0.0;
                ///console.log(killfeedgroup.script.killfeed.timers[i]);

                //Set timer to active for that line
                ///console.log("before: timerActive["+i+"]: "+killfeedgroup.script.killfeed.timerActive[i]);
                killfeedgroup.script.killfeed.timerActive[i] = 1;
                ///console.log("after: timerActive["+i+"]: "+killfeedgroup.script.killfeed.timerActive[i]);
                

                //Set enbld to 1 for that element
                //used to keep track of which pos is enabled
                killfeedgroup.script.killfeed.enbld[i] = 1;

                ///console.log("timers["+i+"] and timerActive["+i+"] has been set");

                //Job done.
                //Stop looking for the lowest disabled pos
                break;
            }
        }

        ///console.log("Done looking for lowest disabled pos");


        //Adding a kill entry if there were no disabled pos's. e.g. There are a lot of kills right now in the killfeed.
        //If it didn't find any disabled pos open then make a slot by checking all timers, longest timer, set new timer, set visible, set enbld

        //if we couldn't find space after looking through all of them
        //take the entity with the longest timer and use that to add this entry ()
        if(spaceForThisKillEntry == 0)
        {

            ///console.log("No space for this kill entry. Making space");

            //make space
            //Need to display a kill entry. But there have been 7 kills in the last 5 seconds. All pos's have been used.
            //Replace the oldest kill entry.
            //Check all timers. find the longest timer and line (entityWithLongestTimer)
            for(let k = 0; k < killfeedgroup.script.killfeed.kfsize; k++)
            {
                if(killfeedgroup.script.killfeed.timers[k] > longestTimer)
                {
                    entityWithLongestTimer = k;
                    longestTimer = killfeedgroup.script.killfeed.timers[k];
                }
            }

            //Update oldest kill entry as new one

            //Already enabled
            //killfeedgroup.script.killfeed.posArray[entityWithLongestTimer*3+0].enabled = true;

            //Set text player 1
            killfeedgroup.script.killfeed.posArray[entityWithLongestTimer*3+1].element.text = player1;

            //Set text player 2
            killfeedgroup.script.killfeed.posArray[entityWithLongestTimer*3+2].element.text = player2;

            //Re order when making new one, (put at the bottom)
            killfeedgroup.script.killfeed.posArray[entityWithLongestTimer*3+0].reparent(killfeedgroup, killfeedgroup.script.killfeed.kfsize-1);

            //Set timer
            killfeedgroup.script.killfeed.timers[entityWithLongestTimer] = 0.0;

            //Make sure that timer active for that line is set to true
            //killfeedgroup.script.killfeed.timerActive[i] = 1;

            //Make sure that enabled variable is set to 1 for that element (used to help keep track of who is enabled)
            //killfeedgroup.script.killfeed.enbld[i] = 1;
            
            spaceForThisKillEntry = 1;
        }
        
        ///console.log("Kill entry added");

        //Possible feature: highlight (A red box in kill entry to show that the player is involved in this kill entry)
        //Steps to adding highlight
        //Set "Group"'s to red in editor. (Group is the default name of the entity). This will just be red box (may use a different entity if it doesn't become a red box when setting colour).
        //Make posArray to 28 size instead of 21 (via the editor. Click on killfeedgroup. Scroll down on inspector to killfeed script). // Reference each Group by dragging and dropping. order is: pos0, p1, p2, Group, pos1, p1, p2, Group... 
        //Change all my posArray references in this code to [posNum*4+(0 for posEntity, 1 for p1, 2 for p2, 3 for Group)]
        //currently it is [posNum*3+(0 for posEntity, 1 for p1, 2 for p2)]\
        //that change put each Group in the posArray so that they can be accessed in code.
        //Check for highlight when setting player names. if([username] == player1 || [username] == player2) set the group in pos to visible (in "//make space" area and "//Adding a kill entry").
        //Set the Group in pos to invsible when it exipires (in prototype.update part of code) and when it is changed in make space
    };

    //Listening for killfeed:add event (Application event)
    this.app.on('killfeed:add', onKillfeedAdd);

    //Remove the listener when this script is destroyed (it is in documentation, just in case)
    this.on('destroy', function()
    {
        this.app.off('killfeed:add', onKillfeedAdd);
    });
};

// update code called every frame
Killfeed.prototype.update = function(dt)
{
    //each kill entry dissapears after 5 seconds
    //independently caculated because there is a timer for each pos

    //loop through timers
    for(let i = 0; i < this.kfsize; i++)
    {
        //for the active timers...
        if(this.timerActive[i] == 1)
        {
            //update the timer with the time that has passed since the last frame
            this.timers[i] += dt;

            ///console.log("this.timers["+i+"]: "+this.timers[i]);

            //If timer has expired, set active to 0 so it will stop counting
            //**
            //Runaway timers could exist if changes are made to this code. Runaway timers = Timers that don't stop counting
            //Put the folowing if statement outside of the "if(this.timerActive[i] == 1)" to prevent that possibility.
            //Will use more time steps. Only needed if timers are set to higher values elsewhere in code.
            //**
            if(this.timers[i] >= 5)
            {
                //Timer is expired
                //timer 5 or more 

                ///console.log("this.timerActive["+i+"]: "+this.timerActive[i]);
                ///console.log("this.timers["+i+"]: "+this.timers[i]);

                //Set the timer to not active so that time steps will not be used to count timer
                this.timerActive[i] = 0;
                ///console.log("this.timerActive["+i+"]: "+this.timerActive[i]);
                ///console.log("this.timers["+i+"]: "+this.timers[i]);

                //The timer has expired, don't show the kill anymore
                //Disable pos so it cannot be seen
                this.posArray[i*3+0].enabled = false;

                //enbld
                this.enbld[i] = 0;

                //set the timer to 0
                this.timers[i] = 0;
            }
        }
    }

};

// swap method called for script hot-reloading
// inherit your script state here
// Killfeed.prototype.swap = function(old) { };

// to learn more about script anatomy, please read:
// https://developer.playcanvas.com/en/user-manual/scripting/